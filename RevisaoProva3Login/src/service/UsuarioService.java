package service;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JOptionPane;

import dao.BancoDados;
import dao.UsuarioDAO;
import entities.Usuario;
import userinterface.CadastroFrame;
import userinterface.InfoFrame;
import userinterface.LoginFrame;

public class UsuarioService {

    private CadastroFrame cadastroFrame;
    private LoginFrame loginFrame;
    private InfoFrame infoFrame;

    public void validarLogin(String email, String senha) {
        if (camposInvalidos(email, senha)) {
            return;
        }

        if (!validarEmail(email)) {
            return;
        }

        Connection conn = null;
        try {
            conn = BancoDados.conectar();
            UsuarioDAO usuarioDAO = new UsuarioDAO(conn);
            List<Usuario> usuarios = usuarioDAO.buscarTodos();

            for (Usuario usuario : usuarios) {
                if (usuario.getEmail().equals(email) && usuario.getSenha().equals(senha)) {
                    JOptionPane.showMessageDialog(null, "Login realizado com sucesso!", "Sucesso",
                            JOptionPane.INFORMATION_MESSAGE);
                    abrirInfoFrame(usuario);
                    return;
                }
            }

            // Se não encontrar o usuário
            JOptionPane.showMessageDialog(null, "Email ou senha incorretos!", "Erro de Login",
                    JOptionPane.ERROR_MESSAGE);
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        } finally {
            // Fechar a conexão se estiver aberta
            if (conn != null) {
                try {
                    BancoDados.desconectar();
                } catch (SQLException e) {
                    System.err.println("Erro ao fechar a conexão: " + e.getMessage());
                }
            }
        }
    }

    public void validarCadastro(String nome, String email, String senha) {
        if (camposInvalidos(nome, email, senha)) {
            return;
        }

        if (!validarEmail(email)) {
            if (!validarExistenciaEmail(email)) {
                return;
            }
            return;
        }

        Connection conn = null;
        try {
            conn = BancoDados.conectar();
            UsuarioDAO usuarioDAO = new UsuarioDAO(conn);

            Usuario usuario = new Usuario();
            usuario.setNome(nome);
            usuario.setEmail(email);
            usuario.setSenha(senha);

            usuarioDAO.cadastrar(usuario);
            JOptionPane.showMessageDialog(null, "Cadastro realizado com sucesso!", "Sucesso",
                    JOptionPane.INFORMATION_MESSAGE);
            cadastroFrame.limparCampos();
        } catch (SQLException | IOException e) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar usuário: " + e.getMessage(), "Erro de Cadastro",
                    JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        } finally {
            // Fechar a conexão se estiver aberta
            if (conn != null) {
                try {
                    BancoDados.desconectar();
                } catch (SQLException e) {
                    System.err.println("Erro ao fechar a conexão: " + e.getMessage());
                }
            }
        }
    }

    public void atualizarUsuario(String emailAntigo, String nome, String email, String senha) {
        if (camposInvalidos(nome, email, senha)) {
            return;
        }

        if (!validarEmail(email)) {
            
            return;
        }
        
        if (!emailAntigo.equals(email) && !validarExistenciaEmail(email)) {
        	System.out.println("Aaaaaaaaa");
            return;
        }

        Connection conn = null;
        try {
            conn = BancoDados.conectar();
            UsuarioDAO usuarioDAO = new UsuarioDAO(conn);

            // Localiza o usuário pelo email
            Usuario usuario = usuarioDAO.localizarUsuarioPorEmail(emailAntigo);
            if (usuario == null) {
                JOptionPane.showMessageDialog(null, "Usuário não encontrado com o email: " + email, "Erro",
                        JOptionPane.ERROR_MESSAGE);
                return; // Retorna se o usuário não for encontrado
            }

            // Atualiza os dados do usuário
            usuario.setNome(nome);
            usuario.setEmail(email);
            usuario.setSenha(senha);

            int resultado = usuarioDAO.atualizarUsuario(usuario);
            if (resultado > 0) {
                JOptionPane.showMessageDialog(null, "Usuário atualizado com sucesso!", "Sucesso",
                        JOptionPane.INFORMATION_MESSAGE);
                infoFrame.atualizarInformacoes(usuario);
            } else {
                JOptionPane.showMessageDialog(null, "ERRO: Usuário NÃO foi atualizado.", "Erro",
                        JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException | IOException e) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar usuário: " + e.getMessage(), "Erro de Atualização",
                    JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        } finally {
            // Fechar a conexão se estiver aberta
            if (conn != null) {
                try {
                    BancoDados.desconectar();
                } catch (SQLException e) {
                    System.err.println("Erro ao fechar a conexão: " + e.getMessage());
                }
            }
        }
    }

    public void excluirUsuario(String email) {
        Connection conn = null;
        try {
            conn = BancoDados.conectar();
            UsuarioDAO usuarioDAO = new UsuarioDAO(conn);

            usuarioDAO.removerUsuario(email);
            JOptionPane.showMessageDialog(null, "Usuário excluído com sucesso!", "Sucesso",
                    JOptionPane.INFORMATION_MESSAGE);
            infoFrame.dispose();
            loginFrame.setVisible(true);
        } catch (SQLException | IOException e) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir usuário: " + e.getMessage(), "Erro de Exclusão",
                    JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        } finally {
            // Fechar a conexão se estiver aberta
            if (conn != null) {
                try {
                    BancoDados.desconectar();
                } catch (SQLException e) {
                    System.err.println("Erro ao fechar a conexão: " + e.getMessage());
                }
            }
        }
    }

    private boolean camposInvalidos(String... campos) {
        for (String campo : campos) {
            if (campo == null || campo.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Preencha todos os campos!", "Erro nos campos",
                        JOptionPane.ERROR_MESSAGE);
                return true;
            }
        }
        return false;
    }

    private boolean validarEmail(String email) {
        if (!email.matches("^[\\w-\\.]+@[\\w-]+\\.[a-zA-Z]{2,}$")) {
            JOptionPane.showMessageDialog(null, "Email inválido! Por favor, insira um email válido.", "Erro Email",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    private boolean validarExistenciaEmail(String email) {
        Connection conn = null;
        UsuarioDAO usuarioDAO = null;
        try {
            conn = BancoDados.conectar(); // Establish the connection
            usuarioDAO = new UsuarioDAO(conn); // Initialize UsuarioDAO with the connection

            // Verificar se o email já está cadastrado
            if (usuarioDAO.emailJaCadastrado(email)) {
                JOptionPane.showMessageDialog(null, "Email já cadastrado! Por favor, use outro email.",
                        "Erro de Cadastro", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao verificar email: " + e.getMessage(), "Erro",
                    JOptionPane.ERROR_MESSAGE);
        } finally {
            // Ensure the connection is closed
            if (conn != null) {
                try {
                    BancoDados.desconectar();
                } catch (SQLException e) {
                    System.err.println("Erro ao fechar a conexão: " + e.getMessage());
                }
            }
        }
        return true;
    }

    private void abrirInfoFrame(Usuario usuario) {
        InfoFrame infoFrame = new InfoFrame(usuario, loginFrame);
        this.infoFrame = infoFrame;
        if (loginFrame != null) {
            loginFrame.dispose();
        }
        infoFrame.setLocationRelativeTo(null);
        infoFrame.setVisible(true);
    }

    public void setCadastroFrame(CadastroFrame cadastroFrame) {
        this.cadastroFrame = cadastroFrame;
    }

    public void setInfoFrame(InfoFrame infoFrame) {
        this.infoFrame = infoFrame;
    }

	public void setLoginFrame(LoginFrame loginFrame) {
		this.loginFrame = loginFrame;
	}
    
    
}