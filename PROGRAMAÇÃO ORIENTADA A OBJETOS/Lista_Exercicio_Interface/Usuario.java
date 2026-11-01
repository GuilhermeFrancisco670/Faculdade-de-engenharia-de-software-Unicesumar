package br.edu.unicesumar;

public class Usuario implements ExportavelCSV{
   private String nome = "Guilherme";
   private String email = "meu-mail.com";

    @Override
    public String toCSV() {
        System.out.println(nome);
        System.out.println(email);
        return "";
    }
}
