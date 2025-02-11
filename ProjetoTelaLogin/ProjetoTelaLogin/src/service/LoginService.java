package service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JOptionPane;

import dao.UsuarioDAO;
import entities.Usuario;
import userinterfaces.LoginView;
import userinterfaces.TarefaView;

public class LoginService {

	public LoginService() {
	}
	
	public void fazerLogin(String nomeUsuario, String senha) throws SQLException, IOException {
		
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		List <Usuario> listaUsuario = usuarioDAO.buscarUsuario();
		
		for(Usuario listaClone : listaUsuario) {
			if (nomeUsuario.equals(listaClone.getNome()) && senha.equals(listaClone.getSenha())) {
				
				TarefaView tarefaView = new TarefaView(listaClone);
				tarefaView.setVisible(true);
	            return;
	        }
		}
        	JOptionPane.showMessageDialog(null, "Usuário ou senha incorretos.");
			LoginView loginView = new LoginView();
			loginView.setVisible(true);
	}
	
}
