package userinterfaces;

import java.awt.EventQueue;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import entities.Tarefa;
import entities.Usuario;
import service.TarefaService;

public class Teste extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Teste frame = new Teste();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Teste() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(10, 11, 414, 239);
		contentPane.add(scrollPane);
		
		DefaultTableModel modelo = new DefaultTableModel();
		modelo.addColumn("Teste");
		modelo.addColumn("Nome");
		modelo.addColumn("Nome");
		
		table = new JTable(modelo);
		scrollPane.setViewportView(table);
		buscarTarefas();
	}

	private void buscarTarefas() {
        TarefaService tarefaService = new TarefaService();
        Usuario usuario = new Usuario();
        
        usuario.setId(1);
        // Busca as tarefas associadas ao usuário
        List<Tarefa> tarefas = tarefaService.buscarTarefasPorUsuarios(usuario);
        
        // Limpa o modelo da tabela antes de adicionar novas tarefas
        DefaultTableModel modelo = (DefaultTableModel) table.getModel();
        modelo.setRowCount(0); // Limpa as linhas existentes

        // Verifica se a lista de tarefas não está vazia
        if (tarefas != null && !tarefas.isEmpty()) {
            for (Tarefa tarefa : tarefas) {
                modelo.addRow(new Object[]{
                    tarefa.getId(),
                    tarefa.getTitulo(),
                    tarefa.getConteudo(),
                    tarefa.getPrazo()
                });
            }
        } else {
            System.out.println("Nenhuma tarefa encontrada.");
        }
    }
}
