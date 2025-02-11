package userinterface;

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

public class CadastroFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField FieldEmail;
	private JPasswordField FieldSenha;
	private JTextField FieldNome;

	public CadastroFrame() {
		
		carregarComponentes();
	}

	private void carregarComponentes() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 374, 272);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JLabel lblLogin = new JLabel("Login");
		lblLogin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
				LoginFrame loginFrame = new LoginFrame();
				loginFrame.setLocationRelativeTo(null);
				loginFrame.setVisible(true);
			}
		});
		menuBar.add(lblLogin);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 338, 189);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel LabelCadastro = new JLabel("Cadastro");
		LabelCadastro.setHorizontalAlignment(SwingConstants.CENTER);
		LabelCadastro.setFont(new Font("Tahoma", Font.BOLD, 20));
		LabelCadastro.setBounds(119, 11, 99, 32);
		panel.add(LabelCadastro);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setHorizontalAlignment(SwingConstants.RIGHT);
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblEmail.setBounds(51, 80, 50, 24);
		panel.add(lblEmail);
		
		JLabel lblSenha = new JLabel("Senha");
		lblSenha.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSenha.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSenha.setBounds(44, 115, 57, 24);
		panel.add(lblSenha);
		
		FieldEmail = new JTextField();
		FieldEmail.setBounds(111, 82, 157, 24);
		panel.add(FieldEmail);
		FieldEmail.setColumns(10);
		
		FieldSenha = new JPasswordField();
		FieldSenha.setColumns(10);
		FieldSenha.setBounds(111, 117, 157, 24);
		panel.add(FieldSenha);
		
		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UsuarioService usuarioService = new UsuarioService();
				usuarioService.setCadastroFrame(CadastroFrame.this);
				usuarioService.validarCadastro(FieldNome.getText(), FieldEmail.getText(), new String(FieldSenha.getPassword()));
				
			}
		});
		btnCadastrar.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnCadastrar.setBounds(64, 150, 204, 24);
		panel.add(btnCadastrar);
		
		JLabel lblNome = new JLabel("Nome");
		lblNome.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNome.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNome.setBounds(51, 46, 50, 24);
		panel.add(lblNome);
		
		FieldNome = new JTextField();
		FieldNome.setColumns(10);
		FieldNome.setBounds(111, 48, 157, 24);
		panel.add(FieldNome);
		
	}

	public void limparCampos() {
		FieldNome.setText("");
		FieldEmail.setText("");
		FieldSenha.setText("");
	}
}
