
import java.io.IOException;
import DAO.ClienteDAO;
import entity.Cliente;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class CadastroClienteAnimalController {

    @FXML
    private Button botaoCadastrar;

    @FXML
    private TextField campoCategoriaAnimal;

    @FXML
    private TextField campoNomeAnimal;

    @FXML
    private TextField campoNomeDono;

    @FXML
    void cadastrar(ActionEvent event) {
        String nomeDono = campoNomeDono.getText();
        String nomeAnimal = campoNomeAnimal.getText();
        String categoriaAnimal = campoCategoriaAnimal.getText();

        Cliente cliente = new Cliente(nomeDono);
        ClienteDAO clienteDAO = new ClienteDAO();
        clienteDAO.adicionarCliente(cliente, nomeAnimal, categoriaAnimal);
        if(clienteDAO.verificarCliente(cliente)){
            System.out.println("Cliente cadastrado com sucesso!");
        }else{
            System.out.println("Cliente já cadastrado!");
        }

    }

    @FXML
    void retornarTela(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("telaGerenciamento.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
