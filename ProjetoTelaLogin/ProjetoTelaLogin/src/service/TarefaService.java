package service;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import dao.BancoDados;
import dao.TarefaDAO;
import entities.Tarefa;
import entities.Usuario;
import userinterfaces.TarefaView;

public class TarefaService {

	private TarefaView tarefaView;

	public TarefaService() {
	}

	public void cadastrarTarefa(int idUsuario, Tarefa newTarefa) throws SQLException, IOException {
	    Connection conn = null;

	    try {
	        // Conecta ao banco de dados
	        conn = BancoDados.conectar();

	        // Cria uma instância do TarefaDAO com a conexão
	        TarefaDAO tarefaDao = new TarefaDAO(conn);

	        // Tenta cadastrar a nova tarefa
	        int sucesso = tarefaDao.cadastrarTarefa(idUsuario, newTarefa);

	        // Verifica se o cadastro foi bem-sucedido
	        if (sucesso != 0) {
	            JOptionPane.showMessageDialog(null, "Tarefa cadastrada com sucesso!");
	            tarefaView.limparCampos(); // Limpa os campos da interface
	        } else {
	            JOptionPane.showMessageDialog(null, "Erro ao cadastrar Tarefa!");
	        }
	    } catch (SQLException e) {
	        // Tratamento de exceção para erros de SQL
	        JOptionPane.showMessageDialog(null, "Erro ao cadastrar tarefa: " + e.getMessage(), 
	                "Erro de Cadastro", JOptionPane.ERROR_MESSAGE);
	    } catch (IOException e) {
	        // Tratamento de exceção para erros de IO
	        JOptionPane.showMessageDialog(null, "Erro de IO: " + e.getMessage(), 
	                "Erro de Cadastro", JOptionPane.ERROR_MESSAGE);
	    } finally {
	        // Garante que a conexão com o banco de dados seja fechada
	        BancoDados.desconectar();
	    }
	}

	public List<Tarefa> buscarTarefasPorUsuarios(Usuario usuario) {
	    // Cria uma conexão com o banco de dados
	    Connection conn = null;
	    try {
	        conn = BancoDados.conectar();
	        // Cria uma instância do TarefaDAO com a conexão
	        TarefaDAO tarefaDao = new TarefaDAO(conn);
	        
	        // Busca as tarefas associadas ao usuário pelo ID
	        return tarefaDao.buscarTarefaPorUsuario(usuario.getId());
	        
	    } catch (SQLException e) {
	        // Tratamento de exceção para erros de SQL
	        e.printStackTrace();
	    } catch (IOException e) {
	        // Tratamento de exceção para erros de IO
	        e.printStackTrace();
	    } finally {
	        // Certifique-se de desconectar do banco de dados após a operação
	        BancoDados.desconectar();
	    }
	    return new ArrayList<>(); // Retorna uma lista vazia em caso de erro
	}
	
	public void setTarefaView(TarefaView tarefaView) {
		this.tarefaView = tarefaView;
	}


}
