package service;

import java.io.IOException;
import java.sql.SQLException;

import dao.UsuarioDAO;
import entities.Usuario;

public class CadastroUsuarioService {
	
	public CadastroUsuarioService() {
	}
	
	public void cadastrarUsuario(Usuario usuario) throws IOException, SQLException {
		 UsuarioDAO usuarioDAO = new UsuarioDAO();
	     usuarioDAO.cadastrarUsuario(usuario); // Método que insere o usuário no banco
	}

}
