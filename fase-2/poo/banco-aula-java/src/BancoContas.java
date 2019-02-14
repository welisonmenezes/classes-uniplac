import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextArea;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;


public class BancoContas extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldNumero;
	private JTextField textFieldSaldo;
	private JTextField textFieldLimite;
	private JTextField textFieldNome;
	private JTextField textFieldCpf;
	private JTextField textFieldEndereco;
	private JTextField textFieldQuantidade;
	private JLabel lblNmero;
	private JLabel lblSaldo;
	private JLabel lblLimite;
	private JLabel lblNome;
	private JLabel lblCpf;
	private JLabel lblEndereo;
	private JLabel lblTitulares;
	private JLabel lblQuantidade;
	private JTextArea textAreaListar;
	private JButton btnOk;
	private JButton btnListar;
	private ArrayList<Conta> contas;
	
	public BancoContas(ArrayList<Conta> contas) {
		initComponents(contas);
	}
	
	public void initComponents(ArrayList<Conta> contas){
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 422);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textFieldNumero = new JTextField();
		textFieldNumero.setBounds(10, 24, 86, 20);
		contentPane.add(textFieldNumero);
		textFieldNumero.setColumns(10);
		
		textFieldSaldo = new JTextField();
		textFieldSaldo.setBounds(106, 24, 86, 20);
		contentPane.add(textFieldSaldo);
		textFieldSaldo.setColumns(10);
		
		textFieldLimite = new JTextField();
		textFieldLimite.setBounds(202, 24, 86, 20);
		contentPane.add(textFieldLimite);
		textFieldLimite.setColumns(10);
		
		lblNmero = new JLabel("N\u00FAmero");
		lblNmero.setBounds(10, 11, 46, 14);
		contentPane.add(lblNmero);
		
		lblSaldo = new JLabel("Saldo");
		lblSaldo.setBounds(106, 11, 46, 14);
		contentPane.add(lblSaldo);
		
		lblLimite = new JLabel("Limite");
		lblLimite.setBounds(202, 11, 46, 14);
		contentPane.add(lblLimite);
		
		textFieldNome = new JTextField();
		textFieldNome.setBounds(10, 76, 86, 20);
		contentPane.add(textFieldNome);
		textFieldNome.setColumns(10);
		
		textFieldCpf = new JTextField();
		textFieldCpf.setBounds(106, 76, 86, 20);
		contentPane.add(textFieldCpf);
		textFieldCpf.setColumns(10);
		
		textFieldEndereco = new JTextField();
		textFieldEndereco.setBounds(202, 76, 86, 20);
		contentPane.add(textFieldEndereco);
		textFieldEndereco.setColumns(10);
		
		textFieldQuantidade = new JTextField();
		textFieldQuantidade.setBounds(298, 76, 86, 20);
		contentPane.add(textFieldQuantidade);
		textFieldQuantidade.setColumns(10);
		
		btnOk = new JButton("Ok");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				// pega valores dos campos
				int auxNumero = Integer.parseInt(textFieldNumero.getText());
				float auxSaldo = Float.parseFloat(textFieldSaldo.getText());
				float auxLimite = Float.parseFloat(textFieldLimite.getText());
				String auxNome = textFieldNome.getText();
				String auxEndereco = textFieldEndereco.getText();
				String auxCpf = textFieldCpf.getText();
				int auxQuantidade = Integer.parseInt(textFieldQuantidade.getText());
				
				// cria um conta
				Conta auxConta = new Conta(
						auxNumero, 
						auxSaldo, 
						auxLimite,
						auxNome,
						auxEndereco,
						auxCpf
				);
				
				// reseta os campos
				textFieldNumero.setText("");
				textFieldSaldo.setText("");
				textFieldLimite.setText("");
				textFieldNome.setText("");
				textFieldEndereco.setText("");
				textFieldCpf.setText("");
				textFieldQuantidade.setText("");
				
				
				// abre popup para add titular
				for(int i = 0; i < auxQuantidade; i++){
					
					BancoAddTitular telaAdd = new BancoAddTitular();
					JOptionPane.showConfirmDialog(null, telaAdd);
					
					// add títular
					auxConta.getTitulares().add(
							new Pessoa(
									telaAdd.getTextFieldNome().getText(), 
									telaAdd.getTextFieldCpf().getText(), 
									telaAdd.getTextFieldEndereco().getText()
							)
					);
				}
				
				// add conta
				contas.add(auxConta);
				
				btnListar.doClick();
			}
		});
		btnOk.setBounds(298, 116, 89, 23);
		contentPane.add(btnOk);
		
		lblNome = new JLabel("Nome");
		lblNome.setBounds(10, 64, 46, 14);
		contentPane.add(lblNome);
		
		lblCpf = new JLabel("Cpf");
		lblCpf.setBounds(106, 64, 46, 14);
		contentPane.add(lblCpf);
		
		lblEndereo = new JLabel("Endere\u00E7o");
		lblEndereo.setBounds(202, 64, 86, 14);
		contentPane.add(lblEndereo);
		
		lblTitulares = new JLabel("Titulares");
		lblTitulares.setBounds(298, 64, 86, 14);
		contentPane.add(lblTitulares);
		
		textAreaListar = new JTextArea();
		textAreaListar.setBounds(21, 149, 363, 198);
		contentPane.add(textAreaListar);
		
		btnListar = new JButton("Listar");
		btnListar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textAreaListar.setText("Contas: \n");
				
				for(Conta conta: contas){
					textAreaListar.append("-Número: " + conta.getNumero() + " ");
					textAreaListar.append("-Titulares: ");
					for(Pessoa titular: conta.getTitulares()){
						textAreaListar.append("--" + titular.getNome() + " ");
					}
					textAreaListar.append("\n-------------------------------\n");
				}
			}
		});
		btnListar.setBounds(298, 358, 89, 23);
		contentPane.add(btnListar);
	}
}
