package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import conexao.conexao;
import entity.Veterinario;

public class VeterinarioDAO {

    public void adicionarVeterinario(Veterinario veterinario, String tipoServico, String especializacaoVet){
        String sql = "INSERT INTO VETERINARIO (nome) VALUES (?)";

        try (PreparedStatement stmt = conexao.getConexao().prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, veterinario.getNome());
            stmt.execute();

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    veterinario.setId(generatedKeys.getInt(1));  // Define o ID do veterinario
                } else {
                    throw new SQLException("Falha ao obter o ID do veterinario após a inserção.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        Veterinario veterionarioAchado = acharVeterinario(veterinario);

        String sql2 = "INSERT INTO SERVICO (tiposervico, especializacaovet, idveterinario) VALUES (?, ?, ?)";

        try (PreparedStatement stmt = conexao.getConexao().prepareStatement(sql2)) {
            stmt.setString(1, tipoServico);
            stmt.setString(2, especializacaoVet);
            stmt.setInt(3, veterionarioAchado.getId());
            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Veterinario acharVeterinario(Veterinario veterinario) {
        String sql = "SELECT * FROM VETERINARIO WHERE nome = ?";

        try (PreparedStatement stmt = conexao.getConexao().prepareStatement(sql)) {
            stmt.setString(1, veterinario.getNome());

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    veterinario.setId(rs.getInt("idveterinario"));  // Define o ID do veterinario
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return veterinario;
    }

    public boolean verificarVeterinario(Veterinario veterinario) {
        String sql = "SELECT * FROM VETERINARIO WHERE nome = ?";

        try (PreparedStatement stmt = conexao.getConexao().prepareStatement(sql)) {
            stmt.setString(1, veterinario.getNome());

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void main(String[] args){
        Veterinario veterinario = new Veterinario(null);
        veterinario.setNome("kristophr");

        VeterinarioDAO veterinarioDAO = new VeterinarioDAO();
        veterinarioDAO.adicionarVeterinario(veterinario, "consulta normal", "Gato");
    }
}