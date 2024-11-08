
package javafxapplication1.model.dao;

import javafxapplication1.model.Produto;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class ProdutoDAOImpl implements ProdutoDAO {
    private List<Produto> lista = new ArrayList<>();
    
    @Override
    public void salvar(Produto produto) {
        lista.add(produto);
    }
    
    @Override
    public void atualizar(Produto produto) {
        Optional<Produto> listaExistente;
        listaExistente = lista.stream()
                .filter(c -> c.getCodigo().equals(produto.getCodigo()))
                .findFirst();
        
        listaExistente.ifPresent(c -> {
            c.setCodigo(produto.getCodigo());
            c.setNome(produto.getNome());
            c.setPreco(produto.getPreco());
            c.setQtd(produto.getQtd());
            c.setFornecedor(produto.getFornecedor());
            c.setTamanho(produto.getTamanho());
            c.setCategoria(produto.getCategoria());
            c.setMarca(produto.getMarca());
            c.setData_cadastro(produto.getData_cadastro());
            c.setDesc(produto.getDesc());
        });
    }

    public void deletar(String id) {
        lista.removeIf(Produto -> Produto.getCodigo().equals(id));
    }
    
    @Override
    public Produto buscarPorId(String codigo) {
        return lista.stream()
                .filter(Produto -> Produto.getCodigo().equals(codigo))
                .findFirst()
                .orElse(null);
    }
    
    @Override
    public List<Produto> listarTodos() {
        return lista;
    }

   

    
}
