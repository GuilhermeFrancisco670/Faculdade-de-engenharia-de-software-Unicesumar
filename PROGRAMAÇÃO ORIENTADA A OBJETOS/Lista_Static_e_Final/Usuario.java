package br.edu.unicesumar;

public class Usuario {
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (ValidacaoUtil.isEmailValido(email)) {
            this.email = email;
        } else {
            System.out.println("⚠️ Email inválido, não foi cadastrado: " + email);
        }
    }
}