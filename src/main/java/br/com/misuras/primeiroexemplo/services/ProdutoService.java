package br.com.misuras.primeiroexemplo.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.misuras.primeiroexemplo.model.Produto;
import br.com.misuras.primeiroexemplo.model.exception.ResourceNotFoundException;
import br.com.misuras.primeiroexemplo.repository.ProdutoRepository;
import br.com.misuras.primeiroexemplo.shared.ProdutoDTO;


@Service
public class ProdutoService {
    
    @Autowired
    private ProdutoRepository produtoRepository;

    /**
     * Método para
     * @return
     */
    public List<ProdutoDTO> obterTodos(){
        //Colocar regras
        //retorna uma lista de produto model
        List<Produto> produtos = produtoRepository.findAll();
        return produtos.stream()
        .map(produto -> new ModelMapper().map(produto, ProdutoDTO.class))
        .collect(Collectors.toList());
    }

    /**
     * Método que retorna o produto encontrado pelo seu ID;
     * @param id do produto que será localizado
     * @return retorna um Produto caso seja encontrado
     */
    public Optional<ProdutoDTO> obterPorId(Integer id){
        //Obtendo optional de produto por id
        Optional<Produto> produto = produtoRepository.findById(id);
        //se não encontrar lança um erro
        if(produto.isEmpty()){
            throw new ResourceNotFoundException("Produto com ID " + id + " não encontrado");
        }

        //Convertendo meu optional de produto em produtodto
        ProdutoDTO dto = new ModelMapper().map(produto.get(), ProdutoDTO.class);

        //Criando e retornando um optional de produtoDTO
        return Optional.of(dto);
    }

    /**
     * Método para adicionar produto na lista
     * @param produto que será adicionado
     * @return retorna produto que foi adicionado na lista
     */
    public ProdutoDTO adicionar(ProdutoDTO produtoDTO){
        //remover ID para conseguir fazer cadastro
        produtoDTO.setId(null);

        //Criar o objeto de mapeamento
        ModelMapper mapper = new ModelMapper();
        
        //Converter o produtoDTO em um Produto
        Produto produto = mapper.map(produtoDTO, Produto.class);
        
        //Salvar o produto no banco
        produto = produtoRepository.save(produto);
        produtoDTO.setId(produto.getId());
        
        //Retornar o ProdutoDTO atualizado
        return produtoDTO;
    }

    /**
     * Método para deletar produto por id
     * @param id do produto a ser deletado
     */
    public void deletar(Integer id){
        //Verificar se o produto existe
        Optional<Produto> produto = produtoRepository.findById(id);
        
        // Senão existe lança uma exception
        if(produto.isEmpty()){
            throw new ResourceNotFoundException("Não foi possivel deletar o produto com o id: " + id + " = Produto não existe");
        }
        
        // deletar pelo id
        produtoRepository.deleteById(id);
    }

    /**
     * Método para atualizar produto na lista
     * @param produto que será atualizado
     * @return produto atualizado
     */
    public ProdutoDTO atualizar(Integer id, ProdutoDTO produtoDTO){
        // Passar o id para o produtoDTO
        produtoDTO.setId(id);
        //Criar um objeto de mapeamento
        ModelMapper mapper = new ModelMapper();

        //Converter o ProdutoDTO em um Produto
        Produto produto = mapper.map(produtoDTO, Produto.class);

        //Atualizar o produto no banco de dados
        produtoRepository.save(produto);

        //retornar o produto atualizado
        return produtoDTO;

    }


}
