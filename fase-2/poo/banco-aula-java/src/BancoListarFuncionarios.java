import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.JTableHeader;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;


public class BancoListarFuncionarios extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JScrollPane scrollPane;
	private JTextField textFieldTotal;
	private JLabel lblTotal;

	/**
	 * Create the frame.
	 */
	public BancoListarFuncionarios(ArrayList<Funcionario> funcionarios) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblListaDeFuncionrios = new JLabel("LISTA DE FUNCION\u00C1RIOS");
		lblListaDeFuncionrios.setBounds(10, 11, 196, 14);
		contentPane.add(lblListaDeFuncionrios);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 46, 379, 173);
		contentPane.add(scrollPane);
		
		// cria tabela
		table = new JTable();
		scrollPane.setViewportView(table);
		DefaultTableModel tableModel = new DefaultTableModel(new Object[] {
				"Matricula", "Nome", "Salário", "Cpf"
		}, 0);
		// popula tabela
		for(Funcionario f: funcionarios){
			Object[] funcs = {
					f.getMatricula(),
					f.getNome(),
					f.getSalario(),
					f.getCpf()
				};
			tableModel.addRow(funcs);
		}
		table.setModel(tableModel);
		
		lblTotal = new JLabel("Total:");
		lblTotal.setBounds(10, 236, 63, 14);
		contentPane.add(lblTotal);
		
		textFieldTotal = new JTextField();
		textFieldTotal.setBounds(81, 233, 86, 20);
		contentPane.add(textFieldTotal);
		textFieldTotal.setColumns(10);
		
		textFieldTotal.setText("" + Funcionario.totalFuncionario);
		
	}
}
