package dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entities.Tarefa;

public class TarefaDAO {

    private Connection conn;

    public TarefaDAO(Connection conn) {
        this.conn = conn;
    }

    public int cadastrarTarefa(int idUsuario, Tarefa tarefa) throws SQLException, IOException {
        String sql = "INSERT INTO tarefas (titulo, prazo, conteudo, usuario_id) VALUES (?, ?, ?, ?)";
        try (PreparedStatement st = conn.prepareStatement(sql)) {
            st.setString(1, tarefa.getTitulo());
            st.setString(2, tarefa.getPrazo());
            st.setString(3, tarefa.getConteudo());
            st.setInt(4, idUsuario);

            return st.executeUpdate();
        } catch (SQLException e) {
            // Lançar uma exceção personalizada ou re-lançar a exceção
            throw new SQLException("Erro ao cadastrar tarefa: " + e.getMessage(), e);
        }
    }

    public List<Tarefa> buscarTarefaPorUsuario(int idUsuario) throws SQLException, IOException {
        List<Tarefa> tarefas = new ArrayList<>();
        String sql = "SELECT * FROM tarefas WHERE usuario_id = ?";
        
        try (PreparedStatement st = conn.prepareStatement(sql)) {
            st.setInt(1, idUsuario);
            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    Tarefa tarefa = new Tarefa();
                    tarefa.setId(rs.getInt("id"));
                    tarefa.setTitulo(rs.getString("titulo"));
                    tarefa.setPrazo(rs.getString("prazo"));
                    tarefa.setConteudo(rs.getString("conteudo"));
                    tarefas.add(tarefa);
                }
            }
        } catch (SQLException e) {
            throw new SQLException("Erro ao buscar tarefas: " + e.getMessage(), e);
        }
        
        return tarefas;
    }
}