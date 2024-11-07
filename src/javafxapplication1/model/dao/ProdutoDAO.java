
package javafxapplication1.model.dao;

import javafxapplication1.model.Produto;


public interface ProdutoDAO {
    
    public void salvar(Produto produto);
    
    public void atualizar(Produto produto);
    
    public Produto buscarPorId(String id);
    
    public List<Produto> listarTodos();
}
