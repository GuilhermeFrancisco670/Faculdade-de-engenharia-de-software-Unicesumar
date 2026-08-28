package br.com.reuseplus.domain;

public abstract class Usuario {
    private Long id;
    private String nome;
    private String email;
    private String telefone;
    private boolean ativo = true;

    protected Usuario(Long id, String nome, String email, String telefone) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
    }

    public abstract String[] obterPermissoes();
    public abstract String descreverAtuacao();

    public Long getId() { return id; }
    public String getNome() { return nome; }
    public String getEmail() { return email; }
    public String getTelefone() { return telefone; }
    public boolean isAtivo() { return ativo; }
    public void setId(Long id) { this.id = id; }
    public void setNome(String nome) { this.nome = nome; }
    public void setEmail(String email) { this.email = email; }
    public void setTelefone(String telefone) { this.telefone = telefone; }
    public void setAtivo(boolean ativo) { this.ativo = ativo; }
}
