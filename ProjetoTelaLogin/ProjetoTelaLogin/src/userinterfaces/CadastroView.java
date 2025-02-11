package userinterfaces;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import entities.Usuario;
import service.CadastroUsuarioService;
import dao.UsuarioDAO;

public class CadastroView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtEmail;
	private JTextField textSenha;
	private JTextField textNome;

	/**
	 * Create the frame.
	 */
	public CadastroView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 453, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblCadastro = new JLabel("Cadastro");
		lblCadastro.setFont(new Font("Dialog", Font.BOLD, 18));
		lblCadastro.setBounds(175, 12, 91, 15);
		contentPane.add(lblCadastro);
		
		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnCadastrar.setBounds(228, 209, 100, 25);
		contentPane.add(btnCadastrar);
		
		txtEmail = new JTextField();
		txtEmail.setBounds(122, 103, 207, 19);
		contentPane.add(txtEmail);
		txtEmail.setColumns(10);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setBounds(122, 86, 144, 15);
		contentPane.add(lblEmail);
		
		JLabel lblSenha = new JLabel("Senha");
		lblSenha.setBounds(122, 134, 144, 15);
		contentPane.add(lblSenha);
		
		textSenha = new JTextField();
		textSenha.setColumns(10);
		textSenha.setBounds(122, 151, 207, 19);
		contentPane.add(textSenha);
		
		JLabel lblNome = new JLabel("Nome");
		lblNome.setBounds(122, 39, 40, 15);
		contentPane.add(lblNome);
		
		textNome = new JTextField();
		textNome.setColumns(10);
		textNome.setBounds(122, 55, 207, 19);
		contentPane.add(textNome);
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnVoltar.setBounds(122, 209, 100, 25);
		contentPane.add(btnVoltar);
		
		// AÇAO DO BOTÃO VOLTAR
		btnVoltar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose(); // método que fecha somente ajanela atual
			}
		});
		
//		AÇAO DO BOTÃO CADASTRAR
		btnCadastrar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Usuario usuario = new Usuario(textNome.getText(), txtEmail.getText(), 
						textSenha.getText());
				
				CadastroUsuarioService cadastroService = new CadastroUsuarioService();
				try {
					cadastroService.cadastrarUsuario(usuario);
					JOptionPane.showMessageDialog(null, "Usuário cadastrado!");
				} catch (IOException | SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
	}

}
