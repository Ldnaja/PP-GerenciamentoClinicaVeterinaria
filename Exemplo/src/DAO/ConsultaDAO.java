package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import conexao.conexao;
import entity.Cliente;
import entity.Consulta;
import entity.Veterinario;

public class ConsultaDAO {

    // Inserir uma nova consulta no banco de dados
    public static void inserirConsulta(Consulta consulta) {
        String sql = "INSERT INTO Consulta (cpf_cliente, crmv_veterinario) VALUES (?, ?)";
        
        try (PreparedStatement stmt = conexao.getConexao().prepareStatement(sql)) {
            stmt.setString(1, consulta.getDono().getCpf());
            stmt.setString(2, consulta.getMedico().getCRMV());
            
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<Consulta> acharConsultas() {
        List<Consulta> consultas = new ArrayList<>();
    
        String sql = "SELECT * FROM Consulta";
    
        try (PreparedStatement stmt = conexao.getConexao().prepareStatement(sql)) {
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    // Obter dados do resultado e criar instância de Consulta
                    // Você precisa adaptar isso com base na nova estrutura da tabela
                    int idconsulta = rs.getInt("idconsulta");
                    String cpf_cliente = rs.getString("cpf_cliente");
                    String crmv_veterinario = rs.getString("crmv_veterinario");

                    // Buscar Cliente e Veterinario associados
                    Cliente cliente = ClienteDAO.acharClientePeloCPF(cpf_cliente);
                    Veterinario veterinario = VeterinarioDAO.acharVeterinarioPeloCRMV(crmv_veterinario);
                        
                    // Criar instância de Consulta e adicioná-la à lista
                    Consulta consulta = new Consulta(cliente, veterinario);
                    consulta.setId(idconsulta);
                    consultas.add(consulta);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    
        return consultas;
    }
}