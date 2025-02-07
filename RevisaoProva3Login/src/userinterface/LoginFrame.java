package userinterface;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import service.UsuarioService;


public class LoginFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField FieldEmail;
	private JPasswordField FieldSenha;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginFrame frame = new LoginFrame();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public LoginFrame() {
		
		carregarComponentes();
		
	}

	private void carregarComponentes() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 374, 244);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JLabel lblCadastro = new JLabel("Cadastrar Usuário");
		lblCadastro.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
				CadastroFrame cadastroFrame = new CadastroFrame();
				cadastroFrame.setLocationRelativeTo(null);
				cadastroFrame.setVisible(true);
			}
		});
		menuBar.add(lblCadastro);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 338, 183);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblLogin = new JLabel("Login");
		lblLogin.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogin.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblLogin.setBounds(133, 11, 71, 32);
		panel.add(lblLogin);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setHorizontalAlignment(SwingConstants.RIGHT);
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblEmail.setBounds(51, 51, 50, 24);
		panel.add(lblEmail);
		
		JLabel lblSenha = new JLabel("Senha");
		lblSenha.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSenha.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSenha.setBounds(44, 86, 57, 24);
		panel.add(lblSenha);
		
		FieldEmail = new JTextField();
		FieldEmail.setBounds(111, 53, 157, 24);
		panel.add(FieldEmail);
		FieldEmail.setColumns(10);
		
		FieldSenha = new JPasswordField();
		FieldSenha.setColumns(10);
		FieldSenha.setBounds(111, 88, 157, 24);
		panel.add(FieldSenha);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				UsuarioService usuarioService = new UsuarioService();
				usuarioService.setLoginFrame(LoginFrame.this);
				usuarioService.validarLogin(FieldEmail.getText(), new String(FieldSenha.getPassword()));
			}
		});
		
		btnLogin.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnLogin.setBounds(64, 126, 204, 24);
		panel.add(btnLogin);
		
	}
}
