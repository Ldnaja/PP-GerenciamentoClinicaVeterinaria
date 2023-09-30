package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import conexao.conexao;
import entity.Cliente;

public class ClienteDAO {

    public void adicionarCliente(Cliente cliente, String nomeAnimal, String animalCategoria) {
        String sql = "INSERT INTO CLIENTE (nome) VALUES (?)";

        try (PreparedStatement stmt = conexao.getConexao().prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, cliente.getNome());
            stmt.execute();

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    cliente.setId(generatedKeys.getInt(1));  // Define o ID do cliente
                } else {
                    throw new SQLException("Falha ao obter o ID do cliente após a inserção.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        Cliente clienteAchado = acharCliente(cliente);

        String sql2 = "INSERT INTO ANIMAL (nome, categoria, idcliente) VALUES (?, ?, ?)";

        try (PreparedStatement stmt = conexao.getConexao().prepareStatement(sql2)) {
            stmt.setString(1, nomeAnimal);
            stmt.setString(2, animalCategoria);
            stmt.setInt(3, clienteAchado.getId());
            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Cliente acharCliente(Cliente cliente) {
        String sql = "SELECT * FROM CLIENTE WHERE nome = ?";

        try (PreparedStatement stmt = conexao.getConexao().prepareStatement(sql)) {
            stmt.setString(1, cliente.getNome());

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    cliente.setId(rs.getInt("idCliente"));  // Define o ID do cliente
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cliente;
    }

    public boolean verificarCliente(Cliente cliente) {
        return false;
    }

    public static void main(String[] args){
        Cliente cliente = new Cliente(null);
        cliente.setNome("kristophr");

        ClienteDAO clienteDAO = new ClienteDAO();
        clienteDAO.adicionarCliente(cliente, "Thiago", "Gato");
    }
}