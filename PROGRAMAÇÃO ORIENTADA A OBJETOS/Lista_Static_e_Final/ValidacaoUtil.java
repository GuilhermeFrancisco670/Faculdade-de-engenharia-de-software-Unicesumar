package br.edu.unicesumar;

public class ValidacaoUtil {

    // ✅ construtor privado impede instanciação (classe utilitária)
    private ValidacaoUtil() {
    }

    public static boolean isEmailValido(String email) {
        if (email == null || email.isEmpty()) {
            return false;
        }
        return email.contains("@") && email.contains(".");
    }
}