package br.com.reuseplus.domain;

import java.time.LocalDate;

public class Doacao {
    private Long id;
    private Long doadorId;
    private Long itemId;
    private String status;
    private LocalDate dataCadastro;

    public Doacao(Long id, Long doadorId, Long itemId, String status, LocalDate dataCadastro) {
        this.id = id;
        this.doadorId = doadorId;
        this.itemId = itemId;
        this.status = status;
        this.dataCadastro = dataCadastro;
    }

    public void alterarStatus(String novoStatus) {
        if (novoStatus == null || novoStatus.isBlank()) throw new IllegalArgumentException("Status obrigatório");
        this.status = novoStatus.toUpperCase();
    }

    public boolean estaDisponivel() { return "DISPONIVEL".equals(status); }
    public Long getId() { return id; }
    public Long getDoadorId() { return doadorId; }
    public Long getItemId() { return itemId; }
    public String getStatus() { return status; }
    public LocalDate getDataCadastro() { return dataCadastro; }
}
