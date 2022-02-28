package br.com.misuras.primeiroexemplo.services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.misuras.primeiroexemplo.model.Produto;
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
        Optional<Produto> produto = produtoRepository.findById(id);
        if(!produto.isPresent()){

        }
    }

    /**
     * Método para adicionar produto na lista
     * @param produto que será adicionado
     * @return retorna produto que foi adicionado na lista
     */
    public ProdutoDTO adicionar(ProdutoDTO produtoDto){
        //Pode haver alguma regra de negócio para validar repositorio
        return produtoRepository.save(produto);
    }

    /**
     * Método para deletar produto por id
     * @param id do produto a ser deletado
     */
    public void deletar(Integer id){
        // aqui poderia ter alguma lógica de validação;
       produtoRepository.deleteById(id);
    }

    /**
     * Método para atualizar produto na lista
     * @param produto que será atualizado
     * @return produto atualizado
     */
    public ProdutoDTO atualizar(Integer id, ProdutoDTO produto){
        return produtoRepository.save(produto);
        
    }


}
