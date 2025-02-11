package userinterfaces;

import java.util.List;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import entities.Tarefa;
import entities.Usuario;
import service.TarefaService;

public class TarefasView extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable tableTarefas;
    private Usuario usuario;

    public TarefasView(Usuario usuario) {
        this.usuario = usuario;
        
        carregarComponentes();
        buscarTarefas();
    }

    private void carregarComponentes() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 602, 627);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        // Criar o modelo da tabela
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("ID");
        modelo.addColumn("Título");
        modelo.addColumn("Conteúdo");
        modelo.addColumn("Prazo");

        // Criar a tabela e definir o modelo
        tableTarefas = new JTable(modelo);
        JScrollPane scrollPane = new JScrollPane(tableTarefas);
        scrollPane.setBounds(10, 11, 566, 566); // Ajuste o tamanho do JScrollPane
        contentPane.add(scrollPane);
    }

    private void buscarTarefas() {
        TarefaService tarefaService = new TarefaService();
        
        // Busca as tarefas associadas ao usuário
        List<Tarefa> tarefas = tarefaService.buscarTarefasPorUsuarios(usuario);
        
        // Limpa o modelo da tabela antes de adicionar novas tarefas
        DefaultTableModel modelo = (DefaultTableModel) tableTarefas.getModel();
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