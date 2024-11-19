package javafxapplication1.controller;

import java.awt.event.MouseEvent;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafxapplication1.model.dao.ProdutoDAOImpl;
import javafxapplication1.model.Produto;

public class ListarController implements Initializable {

    private ProdutoDAOImpl produtoDAO = ProdutoDAOImpl.getInstance();

    private CadastroProdutoController cadastroController;

    private Stage cadastroStage;

    @FXML
    protected TableView<Produto> tabela;

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

    private ObservableList<Produto> produtos = FXCollections.observableArrayList();

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        configurarColunas();
        tabela.refresh();
        tabela.setItems(produtos);

        
       
    }

    private void configurarColunas() {
        colunaCodigo.setCellValueFactory(new PropertyValueFactory<>("Codigo"));
        colunaNome.setCellValueFactory(new PropertyValueFactory<>("Nome"));
        colunaPreco.setCellValueFactory(new PropertyValueFactory<>("Preco"));
        colunaQtd.setCellValueFactory(new PropertyValueFactory<>("Qtd"));
        colunaForn.setCellValueFactory(new PropertyValueFactory<>("Fornecedor"));
        colunaMarca.setCellValueFactory(new PropertyValueFactory<>("Marca"));
        colunaDesc.setCellValueFactory(new PropertyValueFactory<>("Desc"));
        colunaTam.setCellValueFactory(new PropertyValueFactory<>("Tamanho"));
        colunaCat.setCellValueFactory(new PropertyValueFactory<>("Categoria"));
        colunaData.setCellValueFactory(new PropertyValueFactory<>("Data_cadastro"));
    }

    public ObservableList<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(ObservableList<Produto> produtos) {
        this.produtos = produtos;
        tabela.refresh();
        tabela.setItems(this.produtos);
    }

    public void removeProduto(String id) {
        produtos.removeIf(Produto -> Produto.getCodigo().equals(id));
    }

    

}
