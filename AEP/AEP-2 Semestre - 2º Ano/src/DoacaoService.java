package br.com.reuseplus.service;

import br.com.reuseplus.domain.Doacao;
import br.com.reuseplus.repository.DoacaoRepository;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public class DoacaoService {
    private final DoacaoRepository repository;
    private static final Set<String> STATUS_VALIDOS = Set.of("DISPONIVEL", "EM_TRIAGEM", "RESERVADO", "ENTREGUE", "CANCELADA");

    public DoacaoService(DoacaoRepository repository) { this.repository = repository; }

    public long cadastrar(long doadorId, long itemId) throws SQLException {
        return repository.criar(new Doacao(null, doadorId, itemId, "DISPONIVEL", LocalDate.now()));
    }

    public List<Doacao> listar() throws SQLException { return repository.listar(); }

    public void alterarStatus(long id, String novoStatus) throws SQLException {
        String status = novoStatus.toUpperCase();
        if (!STATUS_VALIDOS.contains(status)) throw new IllegalArgumentException("Status inválido: " + status);
        if (!repository.atualizarStatus(id, status)) throw new IllegalArgumentException("Doação não encontrada: " + id);
    }

    public void excluir(long id) throws SQLException {
        if (!repository.excluirLogicamente(id)) throw new IllegalArgumentException("Doação inexistente ou já entregue: " + id);
    }
}
