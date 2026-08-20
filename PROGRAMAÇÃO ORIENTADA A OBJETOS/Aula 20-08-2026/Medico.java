package br.edu.unicesumar;

/**
 * Representa um médico.
 */
public class Medico {

    // O CRM identifica o médico e fica encapsulado nesta classe.
    private String crm;

    /**
     * Construtor que recebe o CRM e o armazena no novo objeto.
     */
    public Medico(String crm){
        this.crm=crm;
    }

    /**
     * Atende um paciente recebido como parâmetro.
     *
     * Este método ilustra um relacionamento de dependência/associação:
     * Medico usa um objeto Paciente para realizar a operação.
     */
    public void atender(Paciente paciente){
        // O código original acessa exames diretamente porque o campo é public.
        // Em um projeto mais encapsulado, Paciente deveria oferecer um método
        // como adicionarExame(String exame), mantendo sua regra internamente.
        paciente.exames += " Incluindo exames";
    }
}
