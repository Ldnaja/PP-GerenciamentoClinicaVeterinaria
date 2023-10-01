import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
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
import DAO.ConsultaDAO;
import DAO.VeterinarioDAO;
import conexao.conexao;
import entity.Cliente;
import entity.Consulta;
import entity.Servico;
import entity.Veterinario;

public class MarcarConsultaController {

    @FXML
    private Button botaoMarcarConsulta;

    @FXML
    private Button botaoPesquisarVeterinario;

    @FXML
    private Button botaoRetornar;

    @FXML
    private TextField campoCPFdono;

    @FXML
    private TextField campoTipoServico;

    @FXML
    private TableColumn<Veterinario, String> colunaVeterinario;

    @FXML
    private TableView<Veterinario> tabelaVeterinarios;

    @FXML
    void clicado(MouseEvent event) {
        int i = tabelaVeterinarios.getSelectionModel().getSelectedIndex();

        Veterinario veterinario = tabelaVeterinarios.getItems().get(i);

        System.out.println(veterinario.getNome());
    }

    @FXML
    void marcarConsulta(ActionEvent event) {
        // Verificar se algum veterinário foi selecionado
        if (tabelaVeterinarios.getSelectionModel().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro!");
            alert.setHeaderText("Nenhum veterinário selecionado");
            alert.setContentText("Por favor, selecione um veterinário antes de marcar a consulta.");
            alert.showAndWait();
            return;
        }

        // Obter o índice selecionado na tabela
        int selectedIndex = tabelaVeterinarios.getSelectionModel().getSelectedIndex();

        // Obter o veterinário associado ao índice selecionado
        Veterinario veterinario = tabelaVeterinarios.getItems().get(selectedIndex);

        // Obter o cliente associado ao CPF informado
        String cpfDono = campoCPFdono.getText();
        Cliente cliente = ClienteDAO.acharClientePeloCPF(cpfDono);

        // Verificar se o cliente foi encontrado
        if (cliente == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro!");
            alert.setHeaderText("Cliente não encontrado");
            alert.setContentText("Por favor, verifique o CPF digitado.");
            alert.showAndWait();
            return;
        }

        // Criar uma nova consulta com o cliente e veterinário
        Consulta consulta = new Consulta(cliente, veterinario);

        // Chamar o ConsultaDAO para inserir a nova consulta no banco de dados
        ConsultaDAO.inserirConsulta(consulta);

        // Exibir uma mensagem de sucesso
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Sucesso!");
        alert.setHeaderText("Consulta marcada");
        alert.setContentText("A consulta foi marcada com sucesso.");
        alert.showAndWait();
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

                tabelaVeterinarios.getItems().clear(); // Limpa a tabela antes de adicionar novos dados
                tabelaVeterinarios.getItems().addAll(veterinarios); // Adiciona os dados à tabela

                colunaVeterinario.setCellValueFactory(cellData -> cellData.getValue().nomePropertyVet());

                /*Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Sucesso!");
                alert.setHeaderText("Veterinário encontrado");
                alert.setContentText("Por favor, selecione o veterinário desejado.");
                alert.showAndWait();*/

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
