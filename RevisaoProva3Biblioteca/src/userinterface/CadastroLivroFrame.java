package userinterface;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import service.LivroService;

public class CadastroLivroFrame extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField fieldTitulo;
    private JTextField fieldAutor;
    private JTextField fieldAnoPublicacao;
    private JTextField fieldPaginas;
    private LivrosFrame livroFrame;

    public CadastroLivroFrame(LivrosFrame livroFrame) {
    	this.livroFrame = livroFrame;
        setTitle("Cadastro de Livro");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 400, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        // Título
        JLabel lblTitulo = new JLabel("Título:");
        lblTitulo.setBounds(30, 30, 80, 25);
        contentPane.add(lblTitulo);

        fieldTitulo = new JTextField();
        fieldTitulo.setBounds(120, 30, 200, 25);
        contentPane.add(fieldTitulo);
        fieldTitulo.setColumns(10);

        // Autor
        JLabel lblAutor = new JLabel("Autor:");
        lblAutor.setBounds(30, 70, 80, 25);
        contentPane.add(lblAutor);

        fieldAutor = new JTextField();
        fieldAutor.setBounds(120, 70, 200, 25);
        contentPane.add(fieldAutor);
        fieldAutor.setColumns(10);

        // Ano de Publicação
        JLabel lblAnoPublicacao = new JLabel("Ano de Publicação:");
        lblAnoPublicacao.setBounds(30, 110, 120, 25);
        contentPane.add(lblAnoPublicacao);

        fieldAnoPublicacao = new JTextField();
        fieldAnoPublicacao.setBounds(150, 110, 170, 25);
        contentPane.add(fieldAnoPublicacao);
        fieldAnoPublicacao.setColumns(10);

        // Páginas
        JLabel lblPaginas = new JLabel("Páginas:");
        lblPaginas.setBounds(30, 150, 80, 25);
        contentPane.add(lblPaginas);

        fieldPaginas = new JTextField();
        fieldPaginas.setBounds(120, 150, 200, 25);
        contentPane.add(fieldPaginas);
        fieldPaginas.setColumns(10);

        // Botão Cadastrar
        JButton btnCadastrar = new JButton("Cadastrar");
        btnCadastrar.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnCadastrar.setBounds(120, 200, 150, 30);
        contentPane.add(btnCadastrar);

        // Ação do botão
        btnCadastrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cadastrarLivro();
            }
        });
    }

    private void cadastrarLivro() {
        String titulo = fieldTitulo.getText();
        String autor = fieldAutor.getText();
        int anoPublicacao;
        int paginas;

        try {
            anoPublicacao = Integer.parseInt(fieldAnoPublicacao.getText());
            paginas = Integer.parseInt(fieldPaginas.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Ano de publicação e páginas devem ser números.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        LivroService livroService = new LivroService();
        livroService.setLivrosFrame(livroFrame);
        livroService.setCadastroLivroFrame(this);
        livroService.cadastrarLivro(titulo, autor, anoPublicacao, paginas);
    }

    public void limparCampos() {
        fieldTitulo.setText("");
        fieldAutor.setText("");
        fieldAnoPublicacao.setText("");
        fieldPaginas.setText("");
    }
}