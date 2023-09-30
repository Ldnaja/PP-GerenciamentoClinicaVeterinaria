package entity;

public class Funcionario {
    private int idFuncionario;
    private String login;
    private String senha;

    public Funcionario(String login, String senha) {
        this.login = login;
        this.senha = senha;
    }

    // Getter para o campo 'idFuncionario'
    public int getId() {
        return idFuncionario;
    }

    // Setter para o campo 'idFuncionario'
    public void setId(int idFuncionario) {
        this.idFuncionario = idFuncionario;
    }

    // Getter para o campo 'login'
    public String getLogin() {
        return login;
    }

    // Setter para o campo 'login'
    public void setLogin(String login) {
        this.login = login;
    }

    // Getter para o campo 'senha'
    public String getSenha() {
        return senha;
    }

    // Setter para o campo 'senha'
    public void setSenha(String senha) {
        this.senha = senha;
    }
}
