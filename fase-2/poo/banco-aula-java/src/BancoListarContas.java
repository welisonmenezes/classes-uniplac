import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextField;


public class BancoListarContas extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JScrollPane scrollPane;
	private JTextField textFieldTotal;
	private JLabel lblTotalDeContas;

	/**
	 * Create the frame.
	 */
	public BancoListarContas(ArrayList<Conta> contas) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblListaDeFuncionrios = new JLabel("LISTA DE CONTAS");
		lblListaDeFuncionrios.setBounds(10, 11, 196, 14);
		contentPane.add(lblListaDeFuncionrios);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 44, 414, 167);
		contentPane.add(scrollPane);
		
		// cria tabela
		table = new JTable();
		scrollPane.setViewportView(table);
		DefaultTableModel tableModel = new DefaultTableModel(new Object[] {
				"Número", "Títular Principal", "Saldo", "Limite"
		}, 0);
		// popula tabela
		for(Conta c: contas){
			String nome = (c.getTitulares().size() > 0) ? c.getTitulares().get(0).getNome() : "Sem titular";
			Object[] auxContas = {c.getNumero(), nome, c.getSaldo(), c.getLimite()};
			tableModel.addRow(auxContas);
		}
		table.setModel(tableModel);
		
		lblTotalDeContas = new JLabel("Total de contas:");
		lblTotalDeContas.setBounds(10, 236, 96, 14);
		contentPane.add(lblTotalDeContas);
		
		textFieldTotal = new JTextField();
		textFieldTotal.setBounds(120, 233, 86, 20);
		contentPane.add(textFieldTotal);
		textFieldTotal.setColumns(10);
		
		textFieldTotal.setText("" + Conta.totalConta );
	}
}
