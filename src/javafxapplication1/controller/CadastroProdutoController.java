
package javafxapplication1.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafxapplication1.model.Produto;


public class CadastroProdutoController implements Initializable {
    
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
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
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
        estadoFormulario = "adicionado";
        atualizarBotoes();
        limparValores();
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
            case "adicionado":
                btn_adicionar.setDisable(false);
                btn_salvar.setDisable(true);
                btn_cancelar.setDisable(true);
                btn_excluir.setDisable(false);
                break;
            case "excluindo":
                btn_adicionar.setDisable(true);
                btn_salvar.setDisable(true);
                btn_cancelar.setDisable(false);
                btn_excluir.setDisable(true);
                break;
        }
    }
}
