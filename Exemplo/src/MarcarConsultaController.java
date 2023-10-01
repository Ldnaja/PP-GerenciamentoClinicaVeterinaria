import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import DAO.ClienteDAO;
import DAO.VeterinarioDAO;
import conexao.conexao;
import entity.Cliente;
import entity.Servico;
import entity.Veterinario;

public class MarcarConsultaController {

    @FXML
    private Button botaoMarcarConsulta;

    @FXML
    private Button botaoPesquisarVeterinario;

    @FXML
    private TextField campoCPFdono;

    @FXML
    private Button botaoRetornar;

    @FXML
    private TextField campoTipoServico;

    @FXML
    private TableView<?> tabelaVeterinarios;

    @FXML
    void clicado(MouseEvent event) {

    }

    @FXML
    void marcarConsulta(ActionEvent event) {
        
    }

    @FXML
    void pesquisarVeterinario(ActionEvent event) {
        String cpfDono = campoCPFdono.getText();
        String tipoServico = campoTipoServico.getText();

        ClienteDAO clienteDAO = new ClienteDAO();

        if(ClienteDAO.verificarCliente(cpfDono) == false){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro!");
            alert.setHeaderText("Cliente não encontrado");
            alert.setContentText("Por favor, verifique o CPF digitado.");
            alert.showAndWait();
        }else{
            Cliente cliente = clienteDAO.acharClientePeloCPF(cpfDono);
            List<Veterinario> veterinarios = VeterinarioDAO.acharVeterinarios(tipoServico);

            if(veterinarios.isEmpty()){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erro!");
                alert.setHeaderText("Veterinário não encontrado");
                alert.setContentText("Por favor, verifique o tipo de serviço digitado.");
                alert.showAndWait();
            }else{
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Sucesso!");
                alert.setHeaderText("Veterinário encontrado");
                alert.setContentText("Por favor, selecione o veterinário desejado.");
                alert.showAndWait();
            }
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
