package br.com.reuseplus.domain;

public class Instituicao extends Usuario {
    private String nomeFantasia;
    private String endereco;

    public Instituicao(Long id, String nome, String email, String telefone, String nomeFantasia, String endereco) {
        super(id, nome, email, telefone);
        this.nomeFantasia = nomeFantasia;
        this.endereco = endereco;
    }

    @Override
    public String[] obterPermissoes() { return new String[]{"CONSULTAR_DOACOES", "REGISTRAR_SOLICITACAO"}; }

    @Override
    public String descreverAtuacao() { return "Instituição que intermedeia necessidades e solicitações."; }

    public String getNomeFantasia() { return nomeFantasia; }
    public String getEndereco() { return endereco; }
}
