
package javafxapplication1;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;


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
    
    private String estadoFormulario = "inicial";
    @FXML
    private Label label;
    
        
    @FXML
    public void initialize() {
        atualizarBotoes();
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
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
    }

    @FXML
    private void handleCancelar() {
        estadoFormulario = "inicial";
        atualizarBotoes();
        // Lógica para reverter alterações, se necessário
    }

    @FXML
    private void handleExcluir() {
        estadoFormulario = "excluindo";
        atualizarBotoes();
        // Lógica para excluir dados
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
