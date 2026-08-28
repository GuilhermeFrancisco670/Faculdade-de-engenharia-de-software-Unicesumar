package br.com.reuseplus.repository;

import br.com.reuseplus.domain.Doacao;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DoacaoRepository {
    private final String url = System.getenv().getOrDefault("REUSE_DB_URL", "jdbc:mysql://localhost:3306/reuse_plus?useSSL=false&serverTimezone=UTC");
    private final String user = System.getenv().getOrDefault("REUSE_DB_USER", "root");
    private final String password = System.getenv().getOrDefault("REUSE_DB_PASSWORD", "");

    private Connection conectar() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

    public long criar(Doacao doacao) throws SQLException {
        String sql = "INSERT INTO doacao (doador_id, item_id, status, data_cadastro) VALUES (?, ?, ?, ?)";
        try (Connection c = conectar(); PreparedStatement ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setLong(1, doacao.getDoadorId()); ps.setLong(2, doacao.getItemId());
            ps.setString(3, doacao.getStatus()); ps.setDate(4, Date.valueOf(doacao.getDataCadastro()));
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) { if (rs.next()) return rs.getLong(1); }
        }
        throw new SQLException("Não foi possível obter o ID da doação.");
    }

    public List<Doacao> listar() throws SQLException {
        List<Doacao> resultado = new ArrayList<>();
        String sql = "SELECT id, doador_id, item_id, status, data_cadastro FROM doacao ORDER BY id";
        try (Connection c = conectar(); PreparedStatement ps = c.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) resultado.add(new Doacao(rs.getLong("id"), rs.getLong("doador_id"), rs.getLong("item_id"), rs.getString("status"), rs.getDate("data_cadastro").toLocalDate()));
        }
        return resultado;
    }

    public boolean atualizarStatus(long id, String status) throws SQLException {
        String sql = "UPDATE doacao SET status = ? WHERE id = ?";
        try (Connection c = conectar(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, status); ps.setLong(2, id); return ps.executeUpdate() > 0;
        }
    }

    public boolean excluirLogicamente(long id) throws SQLException {
        String sql = "UPDATE doacao SET status = 'CANCELADA' WHERE id = ? AND status <> 'ENTREGUE'";
        try (Connection c = conectar(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setLong(1, id); return ps.executeUpdate() > 0;
        }
    }
}
