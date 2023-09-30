package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import conexao.conexao;
import entity.Funcionario;

public class FuncionarioDAO {

    public void cadastrarFuncionario(Funcionario funcionario) {
        String sql = "INSERT INTO FUNCIONARIO (login, senha) VALUES (?, ?)";

        try (PreparedStatement stmt = conexao.getConexao().prepareStatement(sql)) {
            stmt.setString(1, funcionario.getLogin());
            stmt.setString(2, funcionario.getSenha());
            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean verificarFuncionario(Funcionario funcionario) {
        String sql = "SELECT * FROM FUNCIONARIO WHERE login = ? AND senha = ?";
        try (PreparedStatement stmt = conexao.getConexao().prepareStatement(sql)) {
            stmt.setString(1, funcionario.getLogin());
            stmt.setString(2, funcionario.getSenha());

            try (ResultSet resultSet = stmt.executeQuery()) {
                return resultSet.next(); // Retorna true se o funcionário existe no banco de dados
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Retorna false em caso de exceção ou se o funcionário não existe
        }
    }

    public boolean mostrarFuncionario(Funcionario funcionario) {
        String sql = "SELECT * FROM FUNCIONARIO WHERE login = ?";
        try (PreparedStatement stmt = conexao.getConexao().prepareStatement(sql)) {
            stmt.setString(1, funcionario.getLogin());

            try (ResultSet resultSet = stmt.executeQuery()) {
                return resultSet.next(); // Retorna true se o funcionário existe no banco de dados
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Retorna false em caso de exceção ou se o funcionário não existe
        }
    }

    public void excluirFuncionario(int idFuncionario) {

        String sqlExcluir = "DELETE FROM FUNCIONARIO WHERE idFuncionario = ?";
        try (PreparedStatement stmtExcluir = conexao.getConexao().prepareStatement(sqlExcluir)) {
            stmtExcluir.setInt(1, idFuncionario);
            stmtExcluir.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        String sqlAtualizarIDs = "UPDATE FUNCIONARIO SET idFuncionario = idFuncionario - 1 WHERE idFuncionario > ?";
        try (PreparedStatement stmtAtualizarIDs = conexao.getConexao().prepareStatement(sqlAtualizarIDs)) {
            stmtAtualizarIDs.setInt(1, idFuncionario);
            stmtAtualizarIDs.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /*public static void main(String[] args) {
        Funcionario funcionario = new Funcionario(null, null);
        funcionario.setLogin("higgs");
        funcionario.setSenha("admin");

        FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
        funcionarioDAO.cadastrarFuncionario(funcionario);

        // Exemplo de verificarFuncionario
        boolean funcionarioExiste = funcionarioDAO.verificarFuncionario(funcionario);
        System.out.println("O funcionário existe no banco de dados? " + funcionarioExiste);

        // Exemplo de como usar o método excluirFuncionario
        funcionarioDAO.excluirFuncionario(3); // Substitua pelo ID do funcionário que você deseja excluir
    }*/
}
