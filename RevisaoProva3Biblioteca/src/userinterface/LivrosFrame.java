package userinterface;

import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import entities.Livro;
import service.LivroService;

public class LivrosFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;

	public LivrosFrame(TelaPrincipal telaPrincipal) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 661, 514);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 625, 417);
		contentPane.add(scrollPane);

		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("Titulo");
		model.addColumn("Autor");
		model.addColumn("Ano Publicação");
		model.addColumn("Páginas");

		table = new JTable(model);
		scrollPane.setViewportView(table);

		// Botão para acessar o CadastroLivroFrame
		JButton btnCadastrarLivro = new JButton("Cadastrar Livro");
		btnCadastrarLivro.setBounds(485, 439, 150, 30);
		btnCadastrarLivro.addActionListener(e -> {
			CadastroLivroFrame cadastroLivroFrame = new CadastroLivroFrame(LivrosFrame.this);
			cadastroLivroFrame.setLocationRelativeTo(null); // Centraliza a janela
			cadastroLivroFrame.setVisible(true); // Torna a janela visível
		});
		contentPane.add(btnCadastrarLivro);
		
		atualizarLivros();
	}

	public static void main(String[] args) {
		// Inicializa a interface gráfica na Event Dispatch Thread
		SwingUtilities.invokeLater(() -> {
			// Criação de uma instância de TelaPrincipal (substitua pelo construtor correto)
			LivrosFrame livrosFrame = new LivrosFrame(null);
			livrosFrame.setVisible(true); // Torna a janela visível
		});
	}

	public void atualizarLivros() {
		LivroService livroService = new LivroService();
		List<Livro> livros = livroService.buscarTodosLivros();

		DefaultTableModel model = (DefaultTableModel) table.getModel();
		model.setRowCount(0);

		if (livros != null) {
			for (Livro livro : livros) {
				model.addRow(new Object[] { 
					livro.getId(),
					livro.getTitulo(), 
					livro.getAutor(),
					livro.getAnoPublicacao(),
					livro.getPaginas()
				});
			}
		}
	}
}