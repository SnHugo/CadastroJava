
package javafxapplication1.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafxapplication1.model.Produto;
import javafxapplication1.model.dao.ProdutoDAOImpl;


public class CadastroProdutoController implements Initializable {
    
    private ProdutoDAOImpl produtoDAO;
    
    @FXML
    private Button btn_cancelar;
    
    @FXML
    private Button btn_salvar;
    
    @FXML
    private Button btn_excluir;
    
    @FXML
    private Button btn_adicionar;
    
    @FXML
    private Button btn_editar;
    
    @FXML
    private Button btn_listar;
    
    @FXML
    private Label label;
    
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
    private String estadoFormulario = "inicial";
    
    private ListarController listarController;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        produtoDAO = new ProdutoDAOImpl();
        comboBox_tam.getItems().addAll("P", "M", "G", "GG");
        comboBox_cat.getItems().addAll("Camiseta", "Calça", "Vestido");
        atualizarBotoes();
        
    }    
    
    @FXML
    private void handleAdicionar() {
        estadoFormulario = "editando";
        atualizarBotoes();
        // Lógica para preparar o formulário para adição
    }

    @FXML
    private void handleSalvar() {
        // Lógica para salvar os dados
        if (estadoFormulario == "excluindo") {
            String codigo = textField_codigo.getText();
            produtoDAO.deletar(codigo);
            System.out.println(produtoDAO.listarTodos());
            estadoFormulario = "salvo";
            
            atualizarBotoes();
            limparValores();
            return;
        }
        String codigo = textField_codigo.getText();
        String nome = textField_nome.getText();
        double preco = Double.parseDouble(textField_preco.getText());
        int quantidade = Integer.parseInt(textField_qtd.getText());
        String fornecedor = textField_forn.getText();
        String marca = textField_marca.getText();
        String descricao = textField_desc.getText();
        String tamanho = comboBox_tam.getValue().toString();
        String categoria = comboBox_cat.getValue().toString();
        String dataCadastro = datePicker_dataCad.getValue().toString();
        
        Produto produto = new Produto(codigo, nome, preco, quantidade, fornecedor, tamanho, categoria, marca, dataCadastro, descricao);
        produtoDAO.salvar(produto);
        listarController.getProdutos().add(produto);
        
        estadoFormulario = "salvo";
        atualizarBotoes();
        limparValores();
        System.out.println(produtoDAO.listarTodos());
    }


    @FXML
    private void handleCancelar() {
        estadoFormulario = "inicial";
        atualizarBotoes();
        limparValores();
        // Lógica para reverter alterações, se necessário
    }

    @FXML
    private void handleExcluir() {
        String codigo = textField_codigo.getText();
        estadoFormulario = "excluindo";
        atualizarBotoes();
        // Lógica para excluir dados
        
    }
    @FXML    
    private void limparValores(){
        textField_nome.clear();
        textField_codigo.clear();
        textField_desc.clear();
        textField_forn.clear();
        textField_marca.clear();
        textField_qtd.clear();
        textField_preco.clear();
    }
    
    @FXML
    private void mudarParaListarProdutos() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/javafxapplication1/view/Listar.fxml"));
        Parent novaTela = loader.load();
        
        ListarController listarController = loader.getController();
        listarController.setProdutos(this.listarController.getProdutos());
        Scene novaCena = new Scene(novaTela);
        Stage novoPalco = new Stage();
        novoPalco.setScene(novaCena);
        novoPalco.show();   
    }
    
    public void setListarController(ListarController listarController) {
        this.listarController = listarController;
    }
    
    private void atualizarBotoes() {
        switch (estadoFormulario) {
            case "inicial":
                btn_adicionar.setDisable(false);
                btn_salvar.setDisable(true);
                btn_cancelar.setDisable(true);
                btn_excluir.setDisable(false);
                break;
            case "editando":
                btn_adicionar.setDisable(true);
                btn_salvar.setDisable(false);
                btn_cancelar.setDisable(false);
                btn_excluir.setDisable(true);
                break;
            case "salvo":
                btn_adicionar.setDisable(false);
                btn_salvar.setDisable(true);
                btn_cancelar.setDisable(true);
                btn_excluir.setDisable(false);
                break;
            case "excluindo":
                btn_adicionar.setDisable(true);
                btn_salvar.setDisable(false);
                btn_cancelar.setDisable(false);
                btn_excluir.setDisable(true);
                break;
        }
    }
}
