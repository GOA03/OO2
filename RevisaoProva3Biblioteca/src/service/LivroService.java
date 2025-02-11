package service;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JOptionPane;

import dao.BancoDados;
import dao.LivroDAO;
import entities.Livro;
import userinterface.CadastroLivroFrame; // Supondo que você tenha uma tela para cadastro de livros
import userinterface.LivrosFrame;

public class LivroService {

    private CadastroLivroFrame cadastroLivroFrame;
    private LivrosFrame livrosFrame;

    public void cadastrarLivro(String titulo, String autor, int anoPublicacao, int paginas) {
        if (camposInvalidos(titulo, autor, anoPublicacao, paginas)) {
            return;
        }

        Connection conn = null;
        try {
            conn = BancoDados.conectar();
            LivroDAO livroDAO = new LivroDAO(conn);

            Livro livro = new Livro();
            livro.setTitulo(titulo);
            livro.setAutor(autor);
            livro.setAnoPublicacao(anoPublicacao);
            livro.setPaginas(paginas);

            livroDAO.cadastrar(livro);
            JOptionPane.showMessageDialog(null, "Livro cadastrado com sucesso!", "Sucesso",
                    JOptionPane.INFORMATION_MESSAGE);
            cadastroLivroFrame.limparCampos(); // Método para limpar os campos na tela de cadastro
            cadastroLivroFrame.dispose();
            livrosFrame.atualizarLivros();
        } catch (SQLException | IOException e) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar livro: " + e.getMessage(), "Erro de Cadastro",
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

    public List<Livro> buscarTodosLivros() {
        Connection conn = null;
        List<Livro> listaLivros = null;
        try {
            conn = BancoDados.conectar();
            LivroDAO livroDAO = new LivroDAO(conn);
            listaLivros = livroDAO.buscarTodos();
        } catch (SQLException | IOException e) {
            JOptionPane.showMessageDialog(null, "Erro ao buscar livros: " + e.getMessage(), "Erro de Busca",
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
        return listaLivros;
    }

    public void atualizarLivro(int id, String titulo, String autor, int anoPublicacao, int paginas) {
        if (camposInvalidos(titulo, autor, anoPublicacao, paginas)) {
            return;
        }

        Connection conn = null;
        try {
            conn = BancoDados.conectar();
            LivroDAO livroDAO = new LivroDAO(conn);

            Livro livro = new Livro();
            livro.setId(id);
            livro.setTitulo(titulo);
            livro.setAutor(autor);
            livro.setAnoPublicacao(anoPublicacao);
            livro.setPaginas(paginas);

            int resultado = livroDAO.atualizarLivro(livro);
            if (resultado > 0) {
                JOptionPane.showMessageDialog(null, "Livro atualizado com sucesso!", "Sucesso",
                        JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "ERRO: Livro NÃO foi atualizado.", "Erro",
                        JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException | IOException e) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar livro: " + e.getMessage(), "Erro de Atualização",
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

    private boolean camposInvalidos(String titulo, String autor, int anoPublicacao, int paginas) {
        // Verifica se o título ou autor estão vazios
        if (titulo == null || titulo.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "O título não pode estar vazio!", "Erro nos campos", JOptionPane.ERROR_MESSAGE);
            return true;
        }
        
        if (autor == null || autor.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "O autor não pode estar vazio!", "Erro nos campos", JOptionPane.ERROR_MESSAGE);
            return true;
        }
        
        // Verifica se o ano de publicação é menor ou igual a zero
        if (anoPublicacao <= 0) {
            JOptionPane.showMessageDialog(null, "O ano de publicação deve ser um número positivo!", "Erro nos campos", JOptionPane.ERROR_MESSAGE);
            return true;
        }
        
        // Verifica se o número de páginas é menor ou igual a zero
        if (paginas <= 0) {
            JOptionPane.showMessageDialog(null, "O número de páginas deve ser um número positivo!", "Erro nos campos", JOptionPane.ERROR_MESSAGE);
            return true;
        }
        
        // Se todos os campos são válidos
        return false;
    }

	public void removerLivro(int id) {
        Connection conn = null;
        try {
            conn = BancoDados.conectar();
            LivroDAO livroDAO = new LivroDAO(conn);
            
            livroDAO.removerLivro(id);
            JOptionPane.showMessageDialog(null, "Livro excluído com sucesso!", "Sucesso",
                    JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException | IOException e) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir livro: " + e.getMessage(), "Erro de Exclusão",
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


    public void setCadastroLivroFrame(CadastroLivroFrame cadastroLivroFrame) {
        this.cadastroLivroFrame = cadastroLivroFrame;
    }

	public void setLivrosFrame(LivrosFrame livrosFrame) {
		this.livrosFrame = livrosFrame;
	}
}