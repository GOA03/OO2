package userinterface;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import entities.Usuario;
import service.UsuarioService;

public class TelaPrincipal extends JFrame {

    private static final long serialVersionUID = 1L;
	private Usuario usuario;

    public TelaPrincipal(Usuario usuario) {
    	this.usuario = usuario;
        setTitle("Tela Principal");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        carregarComponentes();
    }

    private void carregarComponentes() {
        JPanel panel = new JPanel();
        getContentPane().add(panel);
        panel.setLayout(null);

        // Botão para visualizar dados do usuário
        JButton btnVisualizarDados = new JButton("Visualizar Dados do Usuário");
        btnVisualizarDados.setBounds(40, 30, 220, 30);
        btnVisualizarDados.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UsuarioService usuarioService = new UsuarioService();
                usuarioService.abrirInfoFrame(usuario, TelaPrincipal.this);
            }
        });
        panel.add(btnVisualizarDados);

        // Botão para ver livros
        JButton btnVerLivros = new JButton("Ver Livros");
        btnVerLivros.setBounds(100, 80, 100, 30);
        btnVerLivros.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LivrosFrame livrosFrame = new LivrosFrame(TelaPrincipal.this);
                livrosFrame.setLocationRelativeTo(null);
                livrosFrame.setVisible(true);
            }
        });
        panel.add(btnVerLivros);
    }
}