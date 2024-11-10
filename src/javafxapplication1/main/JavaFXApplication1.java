package javafxapplication1.main;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafxapplication1.controller.CadastroProdutoController;
import javafxapplication1.controller.ListarController;

public class JavaFXApplication1 extends Application {
    
    @Override public void start(Stage primaryStage) throws Exception {
        FXMLLoader cadastroLoader = new FXMLLoader(getClass().getResource("/javafxapplication1/view/CadastroProduto.fxml"));
        Scene cadastroScene = new Scene(cadastroLoader.load()); CadastroProdutoController cadastroController = cadastroLoader.getController();
        primaryStage.setScene(cadastroScene);
        primaryStage.setTitle("Cadastro de Produtos");
        
        primaryStage.show();
        FXMLLoader listarLoader = new FXMLLoader(getClass().getResource("/javafxapplication1/view/Listar.fxml"));
        Scene listarScene = new Scene(listarLoader.load()); ListarController listarController = listarLoader.getController();
        cadastroController.setListarController(listarController);
    }

    
    public static void main(String[] args) {
        launch(args);
    }
    
}
