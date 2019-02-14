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


public class BancoEditarFuncionario extends JFrame {

	private JPanel contentPane;
	private JLabel lblSelecioneUmFuncionrio;
	private JComboBox comboBox;
	private Funcionario auxFuncionario;
	private JTextField textFieldNome;
	private JTextField textFieldEndereco;
	private JTextField textFieldCpf;
	private JTextField textFieldMatricula;
	private JTextField textFieldSalario;
	private JLabel lblNome;
	private JLabel lblEndereo;
	private JLabel lblCpf;
	private JLabel lblMatricula;
	private JLabel lblSalrio;
	private JButton btnSalvar;
	private JTextArea textArea;
	private JButton btnExcluirFuncionrio;

	/**
	 * Create the frame.
	 */
	public BancoEditarFuncionario(ArrayList<Funcionario> funcionarios) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 342);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblSelecioneUmFuncionrio = new JLabel("Selecione um funcion\u00E1rio:");
		lblSelecioneUmFuncionrio.setBounds(10, 11, 184, 14);
		contentPane.add(lblSelecioneUmFuncionrio);
		
		comboBox = new JComboBox();
		comboBox.setBounds(10, 36, 198, 20);
		contentPane.add(comboBox);
		
		textFieldNome = new JTextField();
		textFieldNome.setBounds(10, 88, 115, 20);
		contentPane.add(textFieldNome);
		textFieldNome.setColumns(10);
		
		textFieldEndereco = new JTextField();
		textFieldEndereco.setColumns(10);
		textFieldEndereco.setBounds(135, 88, 115, 20);
		contentPane.add(textFieldEndereco);
		
		textFieldCpf = new JTextField();
		textFieldCpf.setColumns(10);
		textFieldCpf.setBounds(260, 88, 115, 20);
		contentPane.add(textFieldCpf);
		
		textFieldMatricula = new JTextField();
		textFieldMatricula.setColumns(10);
		textFieldMatricula.setBounds(10, 135, 115, 20);
		contentPane.add(textFieldMatricula);
		
		textFieldSalario = new JTextField();
		textFieldSalario.setColumns(10);
		textFieldSalario.setBounds(135, 135, 115, 20);
		contentPane.add(textFieldSalario);
		
		lblNome = new JLabel("Nome:");
		lblNome.setBounds(10, 67, 115, 14);
		contentPane.add(lblNome);
		
		lblEndereo = new JLabel("Endere\u00E7o:");
		lblEndereo.setBounds(135, 67, 115, 14);
		contentPane.add(lblEndereo);
		
		lblCpf = new JLabel("Cpf:");
		lblCpf.setBounds(260, 67, 115, 14);
		contentPane.add(lblCpf);
		
		lblMatricula = new JLabel("Matricula:");
		lblMatricula.setBounds(10, 119, 115, 14);
		contentPane.add(lblMatricula);
		
		lblSalrio = new JLabel("Sal\u00E1rio:");
		lblSalrio.setBounds(135, 119, 115, 14);
		contentPane.add(lblSalrio);
		
		btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if(auxFuncionario != null){
					// pega dados dos campos
					String auxNome = textFieldNome.getText();
					String auxEndereco = textFieldEndereco.getText();
					String auxCpf = textFieldCpf.getText();
					String auxMatricula = textFieldMatricula.getText();
					Float auxSalario = Float.parseFloat(textFieldSalario.getText());
					
					// seta dados dos campos no objeto
					auxFuncionario.setNome(auxNome);
					auxFuncionario.setEndereco(auxEndereco);
					auxFuncionario.setCpf(auxCpf);
					auxFuncionario.setMatricula(auxMatricula);
					auxFuncionario.setSalario(auxSalario);
					
					// exibe infos na tela
		    		textArea.setText("Informações do funcionário: \n");
					textArea.append("\nNome: " + auxFuncionario.getNome() + "\nEndereço: " + auxFuncionario.getEndereco() + "\nCpf: " + auxFuncionario.getCpf() + "\nMatricula: " + auxFuncionario.getMatricula() + "\nSalario: " + auxFuncionario.getSalario() +"\n");
					
					// reseta campos e objeto
					comboBox.setSelectedItem(null);
					auxFuncionario = null;
		    		textFieldNome.setText("");
		    		textFieldEndereco.setText("");
		    		textFieldCpf.setText("");
		    		textFieldMatricula.setText("");
		    		textFieldSalario.setText("");
				}
				
			}
		});
		btnSalvar.setBounds(260, 134, 115, 23);
		contentPane.add(btnSalvar);
		
		textArea = new JTextArea();
		textArea.setBounds(10, 169, 414, 123);
		contentPane.add(textArea);
		
		btnExcluirFuncionrio = new JButton("Excluir Funcion\u00E1rio");
		btnExcluirFuncionrio.setForeground(Color.RED);
		btnExcluirFuncionrio.setBackground(Color.LIGHT_GRAY);
		btnExcluirFuncionrio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(auxFuncionario != null){
					// remove funcionario do arraylist funcionarios
					funcionarios.remove(auxFuncionario);
					
					// remove itens do combobox pra repopular
					comboBox.removeAllItems();
					
					// repopula combobox
					comboBox.addItem( (Object) null );
					for(Funcionario funcionario: funcionarios){
						comboBox.addItem( (Object) funcionario );
					}
					
					Funcionario.totalFuncionario--;
				}
				
			}
		});
		btnExcluirFuncionrio.setBounds(244, 35, 131, 23);
		contentPane.add(btnExcluirFuncionrio);
		
		comboBox.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent event) {
		    	
		    	if(comboBox.getSelectedItem() != null){
		    		
		    		textArea.setText("");
		    		
		    		// pega objeto selecionado
		    		auxFuncionario = (Funcionario) comboBox.getSelectedItem();
		    		
		    		// popula campos com infos do objeto selecionado
		    		textFieldNome.setText(auxFuncionario.getNome());
		    		textFieldEndereco.setText(auxFuncionario.getEndereco());
		    		textFieldCpf.setText(auxFuncionario.getCpf());
		    		textFieldMatricula.setText(auxFuncionario.getMatricula());
		    		textFieldSalario.setText(""+auxFuncionario.getSalario());
		    		
		    	}else{
		    		// reseta objeto e campos
		    		auxFuncionario = null;
		    		textFieldNome.setText("");
		    		textFieldEndereco.setText("");
		    		textFieldCpf.setText("");
		    		textFieldMatricula.setText("");
		    		textFieldSalario.setText("");
		    	}
		    	
		    	
		    }
		});
		
		// popula combobox
		if(funcionarios != null){
			comboBox.addItem( (Object) null );
			for(Funcionario funcionario: funcionarios){
				comboBox.addItem( (Object) funcionario );
			}
		}
	}

}
