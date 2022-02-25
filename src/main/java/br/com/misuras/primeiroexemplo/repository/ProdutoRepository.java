package br.com.misuras.primeiroexemplo.repository;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Repository;
import br.com.misuras.primeiroexemplo.model.Produto;

@Repository
public class ProdutoRepository {
    
    private List<Produto> produtos = new ArrayList<Produto>();
    private Integer ultimoId = 0;

    /**
     * Método para retornar uma Lista de produtos
     * @return lista de produtos
     */
    public List<Produto> obterTodos(){
        return produtos;
    }

    /**
     * Método que retorna o produto encontrado pelo seu ID;
     * @param id do produto que será localizado
     * @return retorna um Produto caso seja encontrado
     */
    public Optional<Produto> obterPorId(Integer id){
        return produtos
            .stream()
            .filter(produto -> produto.getId() == id)
            .findFirst();
    }


    /**
     * Método para adicionar produto na lista
     * @param produto que será adicionado
     * @return retorna produto que foi adicionado na lista
     */
    public Produto adicionar(Produto produto){
        ultimoId++;
        produto.setId(ultimoId);
        produtos.add(produto);
        return produto;
    }

    /**
     * Método para deletar produto por id
     * @param id do produto a ser deletado
     */
    public void deletar(Integer id){
        produtos.removeIf(produto -> produto.getId() == id);
    }

    /**
     * Método para atualizar produto na lista
     * @param produto que será atualizado
     * @return produto atualizado
     */
    public Produto atualizar(Produto produto){
        Optional<Produto> produtoEncontrado = obterPorId(produto.getId());

        if(!produtoEncontrado.isPresent()){
            throw new InputMismatchException("Produto não encontrado");
        }

        deletar(produto.getId());
        produtos.add(produto);

        return produto;
    }
}
