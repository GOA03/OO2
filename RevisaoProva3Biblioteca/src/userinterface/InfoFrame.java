package userinterface;

import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import entities.Usuario;
import service.UsuarioService;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.JPasswordField;

public class InfoFrame extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private Usuario usuario;
    private TelaPrincipal telaPrincipal;
    private JTextField FieldNome;
    private JTextField FieldEmail;
    private JPasswordField FieldSenha;

    public InfoFrame(Usuario usuario, TelaPrincipal telaPrincipal) {
    	this.usuario = usuario;
        this.telaPrincipal = telaPrincipal; 
        carregarComponentes();
        atualizarInformacoes(usuario);
    }

    private void carregarComponentes() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        JPanel panel = new JPanel();
        panel.setBounds(10, 10, 414, 240);
        contentPane.add(panel);
        panel.setLayout(null);
        
        JButton btnLogout = new JButton("Sair");
        btnLogout.setFont(new Font("Tahoma", Font.BOLD, 11));
        btnLogout.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                if (telaPrincipal != null) {
                	telaPrincipal.setVisible(true);
                } else {
                    System.err.println("loginFrame é nulo. Não é possível exibir a tela de login.");
                }
            }
        });
        btnLogout.setBounds(318, 207, 89, 23);
        panel.add(btnLogout);
        
        JLabel lbNome = new JLabel("Nome");
        lbNome.setHorizontalAlignment(SwingConstants.RIGHT);
        lbNome.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lbNome.setBounds(91, 76, 40, 14);
        panel.add(lbNome);
        
        JLabel lblEmail = new JLabel("Email");
        lblEmail.setHorizontalAlignment(SwingConstants.RIGHT);
        lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblEmail.setBounds(91, 103, 40, 14);
        panel.add(lblEmail);
        
        JLabel lblSenha = new JLabel("Senha");
        lblSenha.setHorizontalAlignment(SwingConstants.RIGHT);
        lblSenha.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblSenha.setBounds(91, 129, 40, 14);
        panel.add(lblSenha);
        
        FieldNome = new JTextField();
        FieldNome.setBounds(137, 76, 190, 20);
        panel.add(FieldNome);
        FieldNome.setColumns(10);
        
        FieldEmail = new JTextField();
        FieldEmail.setColumns(10);
        FieldEmail.setBounds(137, 103, 190, 20);
        panel.add(FieldEmail);
        
        FieldSenha = new JPasswordField();
        FieldSenha.setColumns(10);
        FieldSenha.setBounds(137, 129, 190, 20);
        panel.add(FieldSenha);
        
        JLabel lblNewLabel = new JLabel("Informações Usuário");
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        lblNewLabel.setBounds(56, 11, 301, 50);
        panel.add(lblNewLabel);
        
        JButton btnAtualizar = new JButton("Atualizar");
        btnAtualizar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                UsuarioService usuarioService = new UsuarioService();
                usuarioService.setInfoFrame(InfoFrame.this);
                usuarioService.atualizarUsuario(usuario.getEmail(), FieldNome.getText(), FieldEmail.getText(), new String(FieldSenha.getPassword()));
            }
        });
        btnAtualizar.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnAtualizar.setBounds(91, 157, 236, 23);
        panel.add(btnAtualizar);
        
        JButton btnExcluir = new JButton("Excluir Usuário");
        btnExcluir.setForeground(new Color(255, 0, 0));
        btnExcluir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                UsuarioService usuarioService = new UsuarioService();
                usuarioService.setInfoFrame(InfoFrame.this);
                usuarioService.excluirUsuario(FieldEmail.getText());
            }
        });
        btnExcluir.setFont(new Font("Tahoma", Font.BOLD, 11));
        btnExcluir.setBounds(10, 207, 115, 23);
        panel.add(btnExcluir);
    }

    public void atualizarInformacoes(Usuario usuario) {
        FieldNome.setText(usuario.getNome());
        FieldEmail.setText(usuario.getEmail());
        FieldSenha.setText(usuario.getSenha());
    }
}