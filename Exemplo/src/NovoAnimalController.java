import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class NovoAnimalController {

    @FXML
    private Button botaoCadastrarNovoAnimal;

    @FXML
    private Button botaoRetornar;

    @FXML
    private TextField campoNomeCliente;

    @FXML
    private TextField campoNovaCategoria;

    @FXML
    private TextField campoNovoAnimal;

    @FXML
    void cadastrarNovoAnimal(ActionEvent event) {

    }

    @FXML
    void retornarTela(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("telaGerenciamento.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void verificaClienteExistente(ActionEvent event) {

    }

}
