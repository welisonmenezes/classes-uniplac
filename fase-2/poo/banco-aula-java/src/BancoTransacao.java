import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;

import javax.swing.JTextArea;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JComboBox;


public class BancoTransacao extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldConta;
	private JTextField textFieldValor;
	private JTextArea textArea;
	private JLabel lblFaaSeuDepsito;
	private JLabel lblNConta;
	private JLabel lblValor;
	private JButton btnDepositar;
	private JTextField textFieldDest;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JRadioButton rdbtnSacar;
	private JRadioButton rdbtnDepositar;
	private JRadioButton rdbtnTransferir;
	private JLabel lblDestino;
	private JComboBox comboBoxSacar;
	private JComboBox comboBoxDestino;

	/**
	 * Create the frame.
	 */
	public BancoTransacao(ArrayList<Conta> contas) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textFieldValor = new JTextField();
		textFieldValor.setBounds(226, 93, 86, 20);
		contentPane.add(textFieldValor);
		textFieldValor.setColumns(10);
		
		lblNConta = new JLabel("Origem");
		lblNConta.setBounds(10, 77, 86, 14);
		contentPane.add(lblNConta);
		
		lblValor = new JLabel("Valor");
		lblValor.setBounds(226, 77, 46, 14);
		contentPane.add(lblValor);
		
		btnDepositar = new JButton("Salvar");
		btnDepositar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// pega dados dos campos
				boolean auxSacar = rdbtnSacar.isSelected();
				boolean auxDepositar = rdbtnDepositar.isSelected();
				boolean auxTransferir = rdbtnTransferir.isSelected();
				float auxValor = Float.parseFloat(textFieldValor.getText());
				Conta auxOrigem = (Conta)comboBoxSacar.getSelectedItem();
			
				if(auxTransferir){ // se transferir estiver selecionado
					
					Conta auxDestino = (Conta)comboBoxDestino.getSelectedItem();
					
					if(auxOrigem.transferir(auxValor, auxDestino)){
						textArea.setText("Transferência realizada com sucesso: \n");
						textArea.append("O saldo atual da conta origem " + auxOrigem.getNumero() + " é de: " + auxOrigem.getSaldo());
						textArea.append("\nO saldo atual da conta destino " + auxDestino.getNumero() + " é de: " + auxDestino.getSaldo());
					}else{
						textArea.setText("Saldo indisponível pra fazer a transferênica: \n");
					}
				}else if(auxDepositar){ // se depositar estiver selecionado
					
					auxOrigem.deposita(auxValor);
					textArea.setText("Depósito realizado com sucesso: \n");
					textArea.append("O saldo atual da conta" + auxOrigem.getNumero() + " é de: " + auxOrigem.getSaldo());
				
				}else if(auxSacar){ // se sacar estiver selecionados
					
					if(auxOrigem.saca(auxValor)){
						textArea.setText("Saque realizado com sucesso: \n");
						textArea.append("O saldo atual da conta" + auxOrigem.getNumero() + " é de: " + auxOrigem.getSaldo());
					}else{
						textArea.setText("A conta não possui limite suficiente. \n");
					}
					
				}
				
				textFieldValor.setText("");
				
			}
		});
		btnDepositar.setBounds(335, 92, 89, 23);
		contentPane.add(btnDepositar);
		
		lblFaaSeuDepsito = new JLabel("FA\u00C7A SUA TRANSA\u00C7\u00C3O");
		lblFaaSeuDepsito.setBounds(10, 27, 207, 14);
		contentPane.add(lblFaaSeuDepsito);
		
		textArea = new JTextArea();
		textArea.setBounds(10, 156, 375, 70);
		contentPane.add(textArea);
		
		rdbtnSacar = new JRadioButton("Sacar");
		buttonGroup.add(rdbtnSacar);
		rdbtnSacar.setBounds(10, 47, 109, 23);
		contentPane.add(rdbtnSacar);
		rdbtnSacar.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent event) {
		    	comboBoxDestino.setEnabled(false);
		    }
		});
		
		rdbtnDepositar = new JRadioButton("Depositar");
		buttonGroup.add(rdbtnDepositar);
		rdbtnDepositar.setBounds(136, 48, 109, 23);
		contentPane.add(rdbtnDepositar);
		rdbtnDepositar.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent event) {
		    	comboBoxDestino.setEnabled(false);
		    }
		});
		
		rdbtnTransferir = new JRadioButton("Transferir");
		buttonGroup.add(rdbtnTransferir);
		rdbtnTransferir.setBounds(278, 47, 109, 23);
		contentPane.add(rdbtnTransferir);
		rdbtnTransferir.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent event) {
		    	comboBoxDestino.setEnabled(true);
		    }
		});
		
		lblDestino = new JLabel("Destino");
		lblDestino.setBounds(116, 77, 86, 14);
		contentPane.add(lblDestino);
		
		comboBoxSacar = new JComboBox();
		comboBoxSacar.setBounds(10, 93, 86, 20);
		contentPane.add(comboBoxSacar);
		
		comboBoxDestino = new JComboBox();
		comboBoxDestino.setBounds(116, 93, 86, 20);
		contentPane.add(comboBoxDestino);
		comboBoxDestino.setEnabled(false);
		
		// popula comboboxes
		for(Conta conta: contas){
			comboBoxSacar.addItem( (Object) conta );
			comboBoxDestino.addItem( (Object) conta );
		}
	}
}
