package userinterfaces;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import entities.Usuario;
import service.LoginService;

public class LoginView extends JFrame {

	private static final long serialVersionUID = 1L;
	private static JTextField usuario;
	private static JPasswordField senha;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginView frame = new LoginView();
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
	public LoginView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("Menu");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmCadastrar = new JMenuItem("Cadastrar");
		mnNewMenu.add(mntmCadastrar);
		
		JMenu mnAjuda = new JMenu("Sobre");
		menuBar.add(mnAjuda);
		
		JMenuItem mntmAjuda = new JMenuItem("Ajuda");
		mnAjuda.add(mntmAjuda);
		getContentPane().setLayout(null);
		
		// ABRE A JANELA DE CADASTRO
		mntmCadastrar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				CadastroView cadastroView =  new CadastroView();
				cadastroView.setVisible(true);
			}
		});
		
		JPanel panel = new JPanel();
		panel.setBounds(63, 0, 317, 212);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		usuario = new JTextField();
		usuario.setBounds(135, 60, 110, 19);
		usuario.setColumns(10);
		panel.add(usuario);
		
		senha = new JPasswordField();
		senha.setBounds(135, 91, 110, 19);
		panel.add(senha);
		
		JLabel lblUsuario = new JLabel("Usuario");
		lblUsuario.setFont(new Font("Dialog", Font.BOLD, 11));
		lblUsuario.setBounds(72, 63, 54, 15);
		panel.add(lblUsuario);
		
		JLabel lblSenha = new JLabel("Senha");
		lblSenha.setFont(new Font("Dialog", Font.BOLD, 11));
		lblSenha.setBounds(72, 91, 43, 15);
		panel.add(lblSenha);
		
		JLabel lblNewLabelLogin = new JLabel("Login");
		lblNewLabelLogin.setFont(new Font("DejaVu Sans", Font.BOLD, 14));
		lblNewLabelLogin.setBounds(136, 22, 48, 15);
		panel.add(lblNewLabelLogin);
		
		JButton btnNewButtonLogin = new JButton("Login");
		btnNewButtonLogin.setBounds(72, 128, 173, 19);
		panel.add(btnNewButtonLogin);
		
		btnNewButtonLogin.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// ENVIANDO INFORMAÇÕES PARA O PACOTE SERVICE/LOGIN-SERVICE QUE TRATA AS INFORMAÇÕES
				
				LoginService loginService = new LoginService();
				
				try {
					loginService.fazerLogin(usuario.getText(), new String(senha.getPassword()));
				} catch (SQLException | IOException e1) {
					e1.printStackTrace();
				}
				dispose();
				
			}
		});	
	}
}
