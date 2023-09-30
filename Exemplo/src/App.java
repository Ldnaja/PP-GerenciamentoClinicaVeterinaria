import DAO.*;
import entity.*;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class App extends Application{
    public static void main(String[] args) throws Exception {
        launch(args);
       
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("telaGerenciamento.fxml"));//voltar para a tela de login
        Parent root = loader.load();
        Scene tela = new Scene(root);

        primaryStage.setTitle("Clinica Veterinaria");
        primaryStage.setScene(tela);
        primaryStage.show();
    }
}
