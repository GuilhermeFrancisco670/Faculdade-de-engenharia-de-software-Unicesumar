package br.edu.unicesumar;

/**
 * Representa um computador que pode ser conectado a um Monitor.
 */
public class Computador {

    // O computador guarda uma referência para o monitor conectado.
    private Monitor monitor;

    /**
     * Construtor sem parâmetros.
     * Como não há configuração inicial, o objeto começa sem monitor conectado.
     */
    public Computador(){
        // O construtor está vazio de propósito: monitor começa como null.
    }

    /**
     * Conecta um monitor ao computador.
     * O objeto Monitor é criado fora desta classe e recebido pelo método.
     */
    public void conectarMonitor(Monitor monitor){
        // this.monitor é o atributo; monitor é o parâmetro recebido.
        this.monitor=monitor;
    }
}
