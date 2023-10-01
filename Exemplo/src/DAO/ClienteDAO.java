package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import conexao.conexao;
import entity.Cliente;

public class ClienteDAO {

    public void adicionarCliente(Cliente cliente, String cpf, String nomeAnimal, String animalCategoria) {
        String sql = "INSERT INTO CLIENTE (nome,cpf) VALUES (?,?)";

        try (PreparedStatement stmt = conexao.getConexao().prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cpf);
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
        String sql = "SELECT * FROM CLIENTE WHERE cpf = ?";

        try (PreparedStatement stmt = conexao.getConexao().prepareStatement(sql)) {
            stmt.setString(1, cliente.getCpf());

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

    public static boolean verificarCliente(String cpf) {
        String sql = "SELECT * FROM CLIENTE WHERE cpf = ?";
        try{
            PreparedStatement stmt = conexao.getConexao().prepareStatement(sql);
            stmt.setString(1, cpf);
            try(ResultSet rs = stmt.executeQuery()){
                if(rs.next()){
                    return true; //existe cliente
                }else{
                    return false; //nao existe cliente
                }
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    public static Cliente acharClientePeloCPF(String cpf) {
        String sql = "SELECT * FROM CLIENTE WHERE cpf = ?";

        try (PreparedStatement stmt = conexao.getConexao().prepareStatement(sql)) {
            stmt.setString(1, cpf);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Cliente cliente = new Cliente();
                    cliente.setId(rs.getInt("idCliente"));  // Define o ID do cliente
                    cliente.setNome(rs.getString("nome"));
                    cliente.setCpf(rs.getString("cpf"));
                    return cliente;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<Cliente> acharClientes(String nomeAnimal){
        String sql = "SELECT * FROM CLIENTE INNER JOIN ANIMAL ON CLIENTE.idCliente = ANIMAL.idCliente WHERE ANIMAL.nome = ?";

        List<Cliente> clientes = new ArrayList<Cliente>();
        try(PreparedStatement stmt = conexao.getConexao().prepareStatement(sql)){
            stmt.setString(1, nomeAnimal);

            try(ResultSet rs = stmt.executeQuery()){

                while(rs.next()){
                    Cliente cliente = new Cliente();
                    cliente.setId(rs.getInt("idCliente"));
                    cliente.setNome(rs.getString("nome"));
                    cliente.setCpf(rs.getString("cpf"));
                    clientes.add(cliente);
                }
            }
            return clientes;
        }catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    /*public static void main(String[] args){
        Cliente cliente = new Cliente();
        cliente.setNome("João");
        cliente.setCpf("11223344");

        ClienteDAO clienteDAO = new ClienteDAO();
        //clienteDAO.adicionarCliente(cliente, "Thiago", "Gato");

        Cliente cpfTeste = clienteDAO.acharClientePeloCPF("11223344");
        System.out.println(cpfTeste.getNome());
        System.out.println(verificarCliente(cliente));
    }*/
}