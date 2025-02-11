package userinterface;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import entities.Livro;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class InfoLivroFrame extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField fieldTitulo;
    private JTextField fieldAutor;
    private JTextField fieldAnoPublicacao;
    private JTextField fieldPaginas;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    InfoLivroFrame frame = new InfoLivroFrame(new Livro()); // Passar um livro vazio para teste
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public InfoLivroFrame(Livro livro) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        // Título
        JLabel lblTitulo = new JLabel("Título:");
        lblTitulo.setBounds(30, 30, 80, 25);
        contentPane.add(lblTitulo);

        fieldTitulo = new JTextField();
        fieldTitulo.setBounds(120, 30, 250, 25);
        fieldTitulo.setEditable(false); // Tornar o campo somente leitura
        contentPane.add(fieldTitulo);

        // Autor
        JLabel lblAutor = new JLabel("Autor:");
        lblAutor.setBounds(30, 70, 80, 25);
        contentPane.add(lblAutor);

        fieldAutor = new JTextField();
        fieldAutor.setBounds(120, 70, 250, 25);
        fieldAutor.setEditable(false); // Tornar o campo somente leitura
        contentPane.add(fieldAutor);

        // Ano de Publicação
        JLabel lblAnoPublicacao = new JLabel("Ano de Publicação:");
        lblAnoPublicacao.setBounds(30, 110, 120, 25);
        contentPane.add(lblAnoPublicacao);

        fieldAnoPublicacao = new JTextField();
        fieldAnoPublicacao.setBounds(150, 110, 220, 25);
        fieldAnoPublicacao.setEditable(false); // Tornar o campo somente leitura
        contentPane.add(fieldAnoPublicacao);

        // Páginas
        JLabel lblPaginas = new JLabel("Páginas:");
        lblPaginas.setBounds(30, 150, 80, 25);
        contentPane.add(lblPaginas);

        fieldPaginas = new JTextField();
        fieldPaginas.setBounds(120, 150, 250, 25);
        fieldPaginas.setEditable(false); // Tornar o campo somente leitura
        contentPane.add(fieldPaginas);

        // Botão para fechar
        JButton btnFechar = new JButton("Fechar");
        btnFechar.setBounds(150, 200, 100, 30);
        btnFechar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose(); // Fecha a janela
            }
        });
        contentPane.add(btnFechar);

        // Preencher os campos com os dados do livro
        preencherCampos(livro);
    }

    private void preencherCampos(Livro livro) {
        fieldTitulo.setText(livro.getTitulo());
        fieldAutor.setText(livro.getAutor());
        fieldAnoPublicacao.setText(String.valueOf(livro.getAnoPublicacao()));
        fieldPaginas.setText(String.valueOf(livro.getPaginas()));
    }
}