import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.Color;


public class BancoEditarConta extends JFrame {

	private Conta auxConta = null;
	private JPanel contentPane;
	private JTextField textFieldNumero;
	private JTextField textFieldSaldo;
	private JTextField textFieldLimite;
	private JLabel lblNmero;
	private JLabel lblSaldo;
	private JLabel lblLimite;
	private JButton btnEditar;
	private JLabel lblSelecioneAConta;
	private JComboBox comboBox;
	private JButton btnEditarTitulares;
	private JTextArea textArea;
	private JButton btnExcluirConta;

	/**
	 * Create the frame.
	 */
	public BancoEditarConta(ArrayList<Conta> contas) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblSelecioneAConta = new JLabel("Selecione a conta");
		lblSelecioneAConta.setBounds(10, 11, 135, 14);
		contentPane.add(lblSelecioneAConta);
		
		comboBox = new JComboBox();
		comboBox.setBounds(10, 36, 145, 20);
		contentPane.add(comboBox);
		
		textFieldNumero = new JTextField();
		textFieldNumero.setBounds(10, 92, 120, 20);
		contentPane.add(textFieldNumero);
		textFieldNumero.setColumns(10);
		
		textFieldSaldo = new JTextField();
		textFieldSaldo.setColumns(10);
		textFieldSaldo.setBounds(140, 92, 120, 20);
		contentPane.add(textFieldSaldo);
		
		textFieldLimite = new JTextField();
		textFieldLimite.setColumns(10);
		textFieldLimite.setBounds(270, 92, 120, 20);
		contentPane.add(textFieldLimite);
		
		lblNmero = new JLabel("N\u00FAmero:");
		lblNmero.setBounds(10, 77, 120, 14);
		contentPane.add(lblNmero);
		
		lblSaldo = new JLabel("Saldo:");
		lblSaldo.setBounds(140, 77, 120, 14);
		contentPane.add(lblSaldo);
		
		lblLimite = new JLabel("Limite:");
		lblLimite.setBounds(270, 77, 120, 14);
		contentPane.add(lblLimite);
		
		btnEditar = new JButton("Editar");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(auxConta != null){
					// pega valores dos campos
					int auxNumero = Integer.parseInt(textFieldNumero.getText());
			    	float auxSaldo = Float.parseFloat(textFieldSaldo.getText());
			    	float auxLimite = Float.parseFloat(textFieldLimite.getText());
			    	
			    	// seta na valores no objeto
			    	auxConta.setNumero(auxNumero);
			    	auxConta.setSaldo(auxSaldo);
			    	auxConta.setLimite(auxLimite);
			    	
			    	// exibe infos na tela
			    	textArea.setText("Informações da conta: \n");
					textArea.append("\nNúmero: " + auxConta.getNumero() + "\nSaldo: " + auxConta.getSaldo() + "\nLimite: " + auxConta.getLimite() + "\n");
			    	
					// reseta campos
			    	comboBox.setSelectedItem(null);
			    	textFieldNumero.setText("");
			    	textFieldSaldo.setText("");
			    	textFieldLimite.setText("");
			    	auxConta = null;
			    	
				}
			}
		});
		btnEditar.setBounds(301, 123, 89, 23);
		contentPane.add(btnEditar);
		
		// popula combobox
		comboBox.addItem( (Object) null );
		for(Conta conta: contas){
			comboBox.addItem( (Object) conta );
		}
		
		btnEditarTitulares = new JButton("Editar Titulares");
		btnEditarTitulares.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// abre janela pra editar titulares
				BancoEditarTitulares auxBEC = new BancoEditarTitulares(auxConta);
				auxBEC.setVisible(true);
			}
		});
		btnEditarTitulares.setBounds(10, 123, 120, 23);
		contentPane.add(btnEditarTitulares);
		
		textArea = new JTextArea();
		textArea.setBounds(10, 157, 414, 93);
		contentPane.add(textArea);
		
		btnExcluirConta = new JButton("Excluir Conta");
		btnExcluirConta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(auxConta != null){
					
					// remove conta do arrayList contas
					contas.remove(auxConta);
					
					// remove itens do combobox pra repopular
					comboBox.removeAllItems();
					
					// popula combobox
					comboBox.addItem( (Object) null );
					for(Conta conta: contas){
						comboBox.addItem( (Object) conta );
					}
					
					// reseta campos
			    	comboBox.setSelectedItem(null);
			    	textFieldNumero.setText("");
			    	textFieldSaldo.setText("");
			    	textFieldLimite.setText("");
			    	auxConta = null;
			    	
			    	Conta.totalConta--;
				}
			}
		});
		btnExcluirConta.setBackground(Color.LIGHT_GRAY);
		btnExcluirConta.setForeground(Color.RED);
		btnExcluirConta.setBounds(270, 33, 120, 23);
		contentPane.add(btnExcluirConta);
		
		comboBox.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent event) {
		    	
		    	if(comboBox.getSelectedItem() != null){
		    		
		    		textArea.setText("");
		    		
		    		// pega objeto selecionado
		    		auxConta = (Conta) comboBox.getSelectedItem();
			    	
		    		// popula os campos com infos do objeto selecionado
			    	textFieldNumero.setText(""+auxConta.getNumero());
			    	textFieldSaldo.setText(""+auxConta.getSaldo());
			    	textFieldLimite.setText(""+auxConta.getLimite());
		    	}else{
		    		
		    		// reseta campos e objeto
		    		auxConta = null;
		    		textFieldNumero.setText("");
			    	textFieldSaldo.setText("");
			    	textFieldLimite.setText("");
		    	}
		    	
		    	
		    }
		});
	}
}
