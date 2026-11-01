package br.edu.unicesumar;

public class Produto implements ExportavelCSV{
    private String nome_do_produto = "flunixin";
    private String preco_do_produto = "10,00";


    @Override
    public String toCSV() {
        System.out.println(nome_do_produto);
        System.out.println(preco_do_produto);
        return "";
    }
}
