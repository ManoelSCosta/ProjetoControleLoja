package br.com.misuras.primeiroexemplo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.misuras.primeiroexemplo.model.Produto;
import br.com.misuras.primeiroexemplo.repository.ProdutoRepository;

@Service
public class ProdutoService {
    
    @Autowired
    private ProdutoRepository produtoRepository;

    /**
     * Método para
     * @return
     */
    public List<Produto> obterTodos(){
        //Colocar regras
        return produtoRepository.obterTodos();
    }

    /**
     * Método que retorna o produto encontrado pelo seu ID;
     * @param id do produto que será localizado
     * @return retorna um Produto caso seja encontrado
     */
    public Optional<Produto> obterPorId(Integer id){
        return produtoRepository.obterPorId(id);
    }

    /**
     * Método para adicionar produto na lista
     * @param produto que será adicionado
     * @return retorna produto que foi adicionado na lista
     */
    public Produto adicionar(Produto produto){
        //Pode haver alguma regra de negócio para validar repositorio
        return produtoRepository.adicionar(produto);
    }

    /**
     * Método para deletar produto por id
     * @param id do produto a ser deletado
     */
    public void deletar(Integer id){
        // aqui poderia ter alguma lógica de validação;
       produtoRepository.deletar(id);
    }

    /**
     * Método para atualizar produto na lista
     * @param produto que será atualizado
     * @return produto atualizado
     */
    public Produto atualizar(Integer id, Produto produto){
        // poderia ter alguma validação
        produto.setId(id);
        return produtoRepository.atualizar(produto);
    }


}
