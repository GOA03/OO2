// LivroDAO.java
package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import entities.Livro;

public class LivroDAO {

    private Connection conn;

    public LivroDAO(Connection conn) {
        this.conn = conn;
    }

    public int cadastrar(Livro livro) throws SQLException {
        String sql = "INSERT INTO livro (titulo, autor, ano_publicacao, paginas) VALUES (?, ?, ?, ?)";
        try (PreparedStatement st = conn.prepareStatement(sql)) {
            st.setString(1, livro.getTitulo());
            st.setString(2, livro.getAutor());
            st.setInt(3, livro.getAnoPublicacao());
            st.setInt(4, livro.getPaginas());
            return st.executeUpdate();
        }
    }

    public List<Livro> buscarTodos() throws SQLException {
        String sql = "SELECT * FROM livro ORDER BY titulo";
        List<Livro> listaLivros = new ArrayList<>();
        try (PreparedStatement st = conn.prepareStatement(sql);
             ResultSet rs = st.executeQuery()) {
            while (rs.next()) {
                Livro livro = new Livro();
                livro.setId(rs.getInt("id"));
                livro.setTitulo(rs.getString("titulo"));
                livro.setAutor(rs.getString("autor"));
                livro.setAnoPublicacao(rs.getInt("ano_publicacao"));
                livro.setPaginas(rs.getInt("paginas"));
                listaLivros.add(livro);
            }
        }
        return listaLivros;
    }

    public int atualizarLivro(Livro livro) throws SQLException {
        String sql = "UPDATE livro SET titulo = ?, autor = ?, ano_publicacao = ?, paginas = ? WHERE id = ?";
        try (PreparedStatement st = conn.prepareStatement(sql)) {
            st.setString(1, livro.getTitulo());
            st.setString(2, livro.getAutor());
            st.setInt(3, livro.getAnoPublicacao());
            st.setInt(4, livro.getPaginas());
            st.setInt(5, livro.getId());
            return st.executeUpdate();
        }
    }

    public int removerLivro(int id) throws SQLException {
        String sql = "DELETE FROM livro WHERE id = ?";
        try (PreparedStatement st = conn.prepareStatement(sql)) {
            st.setInt(1, id);
            return st.executeUpdate();
        }
    }
}