// UsuarioDAO.java
package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import entities.Usuario;

public class UsuarioDAO {

    private Connection conn;

    public UsuarioDAO(Connection conn) {
        this.conn = conn;
    }

    public int cadastrar(Usuario usuario) throws SQLException {
        String sql = "INSERT INTO usuario (nome, email, senha) VALUES (?, ?, ?)";
        try (PreparedStatement st = conn.prepareStatement(sql)) {
            st.setString(1, usuario.getNome());
            st.setString(2, usuario.getEmail());
            st.setString(3, usuario.getSenha());
            return st.executeUpdate();
        }
    }

    public List<Usuario> buscarTodos() throws SQLException {
        String sql = "SELECT * FROM usuario ORDER BY nome";
        List<Usuario> listaUsuarios = new ArrayList<>();
        try (PreparedStatement st = conn.prepareStatement(sql);
             ResultSet rs = st.executeQuery()) {
            while (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setId(rs.getInt("id"));
                usuario.setNome(rs.getString("nome"));
                usuario.setEmail(rs.getString("email"));
                usuario.setSenha(rs.getString("senha"));
                listaUsuarios.add(usuario);
            }
        }
        return listaUsuarios;
    }

    public int atualizarUsuario(Usuario usuario) throws SQLException {
        String sql = "UPDATE usuario SET nome = ?, email = ?, senha = ? WHERE id = ?";
        try (PreparedStatement st = conn.prepareStatement(sql)) {
            st.setString(1, usuario.getNome());
            st.setString(2, usuario.getEmail());
            st.setString(3, usuario.getSenha());
            st.setInt(4, usuario.getId());
            return st.executeUpdate();
        }
    }

    public int removerUsuario(String email) throws SQLException {
        String sql = "DELETE FROM usuario WHERE email = ?";
        try (PreparedStatement st = conn.prepareStatement(sql)) {
            st.setString(1, email);
            return st.executeUpdate();
        }
    }

    public boolean emailJaCadastrado(String email) {
        String sql = "SELECT COUNT(*) FROM usuario WHERE email = ?";
        try (PreparedStatement st = conn.prepareStatement(sql)) {
            st.setString(1, email);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Usuario localizarUsuarioPorEmail(String email) throws SQLException {
        String sql = "SELECT * FROM usuario WHERE email = ?";
        Usuario usuario = null;
        try (PreparedStatement st = conn.prepareStatement(sql)) {
            st.setString(1, email);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                usuario = new Usuario();
                usuario.setId(rs.getInt("id"));
                usuario.setNome(rs.getString("nome"));
                usuario.setEmail(rs.getString("email"));
                usuario.setSenha(rs.getString("senha"));
            }
        }
        return usuario;
    }
}