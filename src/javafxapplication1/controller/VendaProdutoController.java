
package javafxapplication1.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class VendaProdutoController implements Initializable {
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
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
}
