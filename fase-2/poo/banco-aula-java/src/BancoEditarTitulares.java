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


public class BancoEditarTitulares extends JFrame {
	
	private Pessoa auxTitular = null;
	private JPanel contentPane;
	private JTextField textFieldNome;
	private JTextField textFieldEndereco;
	private JTextField textFieldCpf;
	private JLabel lblNome;
	private JLabel lblSelecioneOTitular;
	private JComboBox comboBox;
	private JLabel lblEndereo;
	private JLabel lblCpf;
	private JButton btnSalvarTitular;
	private JTextArea textArea;
	private JButton btnDeletarTitular;

	/**
	 * Create the frame.
	 */
	public BancoEditarTitulares(Conta conta) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblSelecioneOTitular = new JLabel("Selecione o titular:");
		lblSelecioneOTitular.setBounds(10, 11, 120, 14);
		contentPane.add(lblSelecioneOTitular);
		
		comboBox = new JComboBox();
		comboBox.setBounds(10, 36, 165, 20);
		contentPane.add(comboBox);
		
		textFieldNome = new JTextField();
		textFieldNome.setBounds(10, 94, 125, 20);
		contentPane.add(textFieldNome);
		textFieldNome.setColumns(10);
		
		textFieldEndereco = new JTextField();
		textFieldEndereco.setColumns(10);
		textFieldEndereco.setBounds(145, 94, 125, 20);
		contentPane.add(textFieldEndereco);
		
		textFieldCpf = new JTextField();
		textFieldCpf.setColumns(10);
		textFieldCpf.setBounds(280, 94, 125, 20);
		contentPane.add(textFieldCpf);
		
		lblNome = new JLabel("Nome:");
		lblNome.setBounds(10, 78, 120, 14);
		contentPane.add(lblNome);
		
		lblEndereo = new JLabel("Endere\u00E7o:");
		lblEndereo.setBounds(145, 78, 120, 14);
		contentPane.add(lblEndereo);
		
		lblCpf = new JLabel("Cpf:");
		lblCpf.setBounds(280, 78, 120, 14);
		contentPane.add(lblCpf);
		
		btnSalvarTitular = new JButton("Salvar Titular");
		btnSalvarTitular.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				// pega infos dos campos
				String auxNome = textFieldNome.getText();
				String auxCpf = textFieldCpf.getText();
				String auxEndereco = textFieldEndereco.getText();
				
				if(auxTitular != null){

					// adiciona infos dos campos no objeto
					auxTitular.setNome(auxNome);
					auxTitular.setCpf(auxCpf);
					auxTitular.setEndereco(auxEndereco);
					
				}else{
					Pessoa auxNewTitular = new Pessoa(auxNome, auxEndereco, auxCpf);
					conta.getTitulares().add(auxNewTitular);
					
					// remove itens do combobox pra repopular
					comboBox.removeAllItems();
					
					// popula combobox
					comboBox.addItem( (Object) null );
					for(Pessoa titular: conta.getTitulares()){
						comboBox.addItem( (Object) titular );
					}
				}
				
				// reseta campos e objetos
				comboBox.setSelectedItem(null);
				textFieldNome.setText("");
				textFieldCpf.setText("");
				textFieldEndereco.setText("");
				auxTitular = null;
				
				// exibe infos dos titulares na tela
				textArea.setText("Titulares: \n");
				for(Pessoa titular: conta.getTitulares()){
					textArea.append("Nome: " + titular.getNome() + " Endereço: " + titular.getEndereco() + " Cpf: " + titular.getCpf() + "\n");
				}
			}
		});
		btnSalvarTitular.setBounds(290, 125, 115, 23);
		contentPane.add(btnSalvarTitular);
		
		textArea = new JTextArea();
		textArea.setBounds(10, 161, 395, 89);
		contentPane.add(textArea);
		
		btnDeletarTitular = new JButton("Excluir Titular");
		btnDeletarTitular.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(auxTitular != null){
					
					// remove titular da conta
					conta.getTitulares().remove(auxTitular);
					
					// remove itens do combobox pra repopular
					comboBox.removeAllItems();
					
					// popula combobox
					comboBox.addItem( (Object) null );
					for(Pessoa titular: conta.getTitulares()){
						comboBox.addItem( (Object) titular );
					}
					
					// reseta campos e objetos
					comboBox.setSelectedItem(null);
					textFieldNome.setText("");
					textFieldCpf.setText("");
					textFieldEndereco.setText("");
					auxTitular = null;
				}
			}
		});
		btnDeletarTitular.setBackground(Color.LIGHT_GRAY);
		btnDeletarTitular.setForeground(Color.RED);
		btnDeletarTitular.setBounds(280, 35, 125, 23);
		contentPane.add(btnDeletarTitular);
		
		comboBox.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent event) {
		    	
		    	if(comboBox.getSelectedItem() != null){
		    		
		    		textArea.setText("");
		    		
		    		// pega objeto selecionado
		    		auxTitular = (Pessoa) comboBox.getSelectedItem();
		    		
		    		// popula campos com dados do objeto selecionado
					textFieldNome.setText(auxTitular.getNome());
					textFieldCpf.setText(auxTitular.getCpf());
					textFieldEndereco.setText(auxTitular.getEndereco());
		    	}else{
		    		// reseta objeto e campos
		    		auxTitular = null;
		    		textFieldNome.setText("");
					textFieldCpf.setText("");
					textFieldEndereco.setText("");
		    	}
		    	
		    	
		    }
		});
		
		// popula combobox
		if(conta != null){
			comboBox.addItem( (Object) null );
			for(Pessoa titular: conta.getTitulares()){
				comboBox.addItem( (Object) titular );
			}
		}
	}
}
