package br.com.reuseplus.domain;

public class Doador extends Usuario {
    public Doador(Long id, String nome, String email, String telefone) {
        super(id, nome, email, telefone);
    }

    @Override
    public String[] obterPermissoes() { return new String[]{"CADASTRAR_DOACAO", "CONSULTAR_HISTORICO"}; }

    @Override
    public String descreverAtuacao() { return "Pessoa que disponibiliza itens para doação."; }
}
