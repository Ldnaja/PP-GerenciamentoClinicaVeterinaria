/*package entity;

public class Veterinario {
    private int idveterinario;
    private String nome;
    private String especializacao;

    public Veterinario(String nome, String especializacao) {
        this.nome = nome;
        this.especializacao = especializacao;
    }

    // Getter para o campo 'idveterinario'
    public int getId() {
        return idveterinario;
    }

    // Setter para o campo 'idveterinario'
    public void setId(int idveterinario) {
        this.idveterinario = idveterinario;
    }

    // Getter para o campo 'nome'
    public String getNome() {
        return nome;
    }

    // Setter para o campo 'nome'
    public void setNome(String nome) {
        this.nome = nome;
    }

    // Getter para o campo 'especializacao'
    public String getEspecializacao() {
        return especializacao;
    }

    // Setter para o campo 'especializacao'
    public void setEspecializacao(String especializacao) {
        this.especializacao = especializacao;
    }
}*/

package entity;

import java.util.ArrayList;
import java.util.List;

public class Veterinario {
    private int idveterinario;
    private String nome;
    private List<Servico> servicos;  // Lista de serviços associados a este veterinário

    public Veterinario(String nome) {
        this.nome = nome;
        this.servicos = new ArrayList<>();
    }

    public int getId() {
        return idveterinario;
    }

    public void setId(int idveterinario) {
        this.idveterinario = idveterinario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Servico> getServicos() {
        return servicos;
    }

    public void adicionarServico(Servico servico) {
        this.servicos.add(servico);
    }
}



