
package javafxapplication1.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafxapplication1.model.dao.ProdutoDAOImpl;
import javafxapplication1.model.Produto;

public class ListarController implements Initializable {
    private ProdutoDAOImpl produtoDAO;
    
    @FXML
    private TableView<Produto> tabela;
    
    @FXML
    private TableColumn<Produto, String> colunaCodigo;
    
    @FXML
    private TableColumn<Produto, String> colunaNome;
    
    @FXML
    private TableColumn<Produto, Double> colunaPreco;
    
    @FXML
    private TableColumn<Produto, Integer> colunaQtd;
    
    @FXML
    private TableColumn<Produto, String> colunaForn;
    
    @FXML
    private TableColumn<Produto, String> colunaMarca;
    
    @FXML
    private TableColumn<Produto, String> colunaDesc;
    
    @FXML
    private TableColumn<Produto, String> colunaTam;
    
    @FXML
    private TableColumn<Produto, String> colunaCat;
    
    @FXML
    private TableColumn<Produto, String> colunaData;
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        produtoDAO = new ProdutoDAOImpl();
        configurarColunas();
        listar();
    }    
    
    
    private void configurarColunas() {
        colunaCodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        colunaNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colunaPreco.setCellValueFactory(new PropertyValueFactory<>("preco"));
        colunaQtd.setCellValueFactory(new PropertyValueFactory<>("qtd"));
        colunaForn.setCellValueFactory(new PropertyValueFactory<>("fornecedor"));
        colunaMarca.setCellValueFactory(new PropertyValueFactory<>("marca"));
        colunaDesc.setCellValueFactory(new PropertyValueFactory<>("desc"));
        colunaTam.setCellValueFactory(new PropertyValueFactory<>("tamanho"));
        colunaCat.setCellValueFactory(new PropertyValueFactory<>("categoria"));
        colunaData.setCellValueFactory(new PropertyValueFactory<>("data_cadastro"));
    }
    
    public void listar() {
        List<Produto> produtos = produtoDAO.listarTodos();
        
        ObservableList<Produto> produtosObservable = FXCollections.observableArrayList(produtos);
        tabela.setItems(produtosObservable);
    }
    
}
