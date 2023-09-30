import java.io.IOException;

import DAO.VeterinarioDAO;
import entity.Veterinario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class VeterinarioController {

    @FXML
    private Button botaoCadastrar;

    @FXML
    private Button botaoRetornar;

    @FXML
    private TextField campoEspecializacao;

    @FXML
    private TextField campoNovoVeterinario;

    @FXML
    private TextField campoTipoServico;

    @FXML
    void fazerCadastro(ActionEvent event) {
        String nomeVeterinario = campoNovoVeterinario.getText();
        String tipoServico = campoTipoServico.getText();
        String especializacao = campoEspecializacao.getText();

        Veterinario veterinario = new Veterinario(nomeVeterinario);
        VeterinarioDAO veterinarioDAO = new VeterinarioDAO();
        veterinarioDAO.adicionarVeterinario(veterinario, tipoServico, especializacao);
        if(veterinarioDAO.verificarVeterinario(veterinario)){
            System.out.println("Veterinario cadastrado com sucesso!");
        }else{
            System.out.println("Veterinario já cadastrado!");
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
