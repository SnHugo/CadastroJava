/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package javafxapplication1.controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafxapplication1.model.Produto;
import javafxapplication1.model.dao.ProdutoDAOImpl;

public class VendaProdutoController implements Initializable {
    
    private ProdutoDAOImpl produtoDAO = new ProdutoDAOImpl();
    
    private ObservableList<Produto> listaObservable;
    
    private List<Produto> lista;
    
    @FXML
    private Button btn_adicionar;

    @FXML
    private Button btn_cancelar;

    @FXML
    private TextField textField_codigo;

    @FXML
    private TextField textField_nome;

    @FXML
    private TextField textField_preco;

    @FXML
    private TextField textField_qtd;

    @FXML
    private TextField textField_forn;

    @FXML
    private TextField textField_marca;

    @FXML
    private TextField textField_desc;

    @FXML
    private ComboBox comboBox_tam;

    @FXML
    private ComboBox comboBox_cat;

    @FXML
    private DatePicker datePicker_dataCad;

    @FXML
    private TableView<Produto> tabelaAdicionados;

    @FXML
    private TableColumn<Produto, String> colunaCodigo;

    @FXML
    private TableColumn<Produto, String> colunaNome;

    @FXML
    private TableColumn<Produto, Double> colunaPreco;

    @FXML
    private TableColumn<Produto, Integer> colunaQtd;

    private String estado = "inicial";

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        atualizarEstado();
        configurarColunas();
        lista = produtoDAO.listarTodos();
        listaObservable = produtoDAO.listaObservable();
    }

    private void configurarColunas() {
        colunaCodigo.setCellValueFactory(new PropertyValueFactory<>("Codigo"));
        colunaNome.setCellValueFactory(new PropertyValueFactory<>("Nome"));
        colunaPreco.setCellValueFactory(new PropertyValueFactory<>("Preco"));
        colunaQtd.setCellValueFactory(new PropertyValueFactory<>("Qtd"));
    }

    
    @FXML
    private void handleAdicionar() {
        
        String codigo = textField_codigo.getText();
        Produto produto = produtoDAO.buscarPorId(codigo);
        if (produto != null) {
            estado = "adicionando";
            atualizarEstado();
            preencherCampos(produto);
        }
        
    }
    
    public void preencherCampos(Produto produto) {
        textField_codigo.setText(produto.getCodigo());
        textField_nome.setText(produto.getNome());
        textField_preco.setText(String.valueOf(produto.getPreco()));
        textField_qtd.setText(String.valueOf(produto.getQtd()));
        textField_forn.setText(produto.getFornecedor());
        textField_marca.setText(produto.getMarca());
        textField_desc.setText(produto.getDesc());
        comboBox_tam.setValue(produto.getTamanho());
        comboBox_cat.setValue(produto.getCategoria());
    }
    
    
    protected void atualizarEstado() {
        switch (estado) {
            case "inicial":
                textField_nome.setDisable(true);
                textField_preco.setDisable(true);
                textField_qtd.setDisable(true);
                textField_forn.setDisable(true);
                comboBox_tam.setDisable(true);
                comboBox_cat.setDisable(true);
                textField_marca.setDisable(true);
                datePicker_dataCad.setDisable(true);
                break;
            case "vendendo":
                textField_nome.setDisable(false);
                textField_preco.setDisable(false);
                textField_qtd.setDisable(false);
                textField_forn.setDisable(false);
                comboBox_tam.setDisable(false);
                comboBox_cat.setDisable(false);
                textField_marca.setDisable(false);
                datePicker_dataCad.setDisable(false);
                break;
        }
    }

}
