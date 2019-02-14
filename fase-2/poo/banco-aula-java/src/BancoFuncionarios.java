import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JLabel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;


public class BancoFuncionarios extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldNome;
	private JTextField textFieldCpf;
	private JTextField textFieldEndereco;
	private JTextField textFieldMatricula;
	private JTextField textFieldSalario;
	
	private JLabel lblSalario;
	private JLabel lblMatricula;
	private JLabel lblEndereco;
	private JLabel lblCpf;
	private JLabel lblNome;
	private JButton btnListarFuncionarios;
	private JTextArea textArea;
	private JButton btnNewButton;
	
	private ArrayList<Funcionario> funcionarios;
	
	public BancoFuncionarios(ArrayList<Funcionario> funcionarios) {
		initComponents(funcionarios);
	}
	
	public void initComponents(ArrayList<Funcionario> funcionarios){
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 387);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textFieldNome = new JTextField();
		textFieldNome.setBounds(10, 35, 86, 20);
		contentPane.add(textFieldNome);
		textFieldNome.setColumns(10);
		
		textFieldCpf = new JTextField();
		textFieldCpf.setBounds(113, 35, 86, 20);
		contentPane.add(textFieldCpf);
		textFieldCpf.setColumns(10);
		
		textFieldEndereco = new JTextField();
		textFieldEndereco.setBounds(221, 35, 86, 20);
		contentPane.add(textFieldEndereco);
		textFieldEndereco.setColumns(10);
		
		textFieldMatricula = new JTextField();
		textFieldMatricula.setBounds(10, 95, 86, 20);
		contentPane.add(textFieldMatricula);
		textFieldMatricula.setColumns(10);
		
		textFieldSalario = new JTextField();
		textFieldSalario.setBounds(113, 95, 86, 20);
		contentPane.add(textFieldSalario);
		textFieldSalario.setColumns(10);
		
		btnNewButton = new JButton("Salvar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// pega os valores do campo
				String auxNome = textFieldNome.getText();
				String auxCpf = textFieldCpf.getText();
				String auxEndereco = textFieldEndereco.getText();
				String auxMatricula = textFieldMatricula.getText();
				float auxSalario = Float.parseFloat(textFieldSalario.getText());
				
				// limpar os campos
				textFieldNome.setText("");
				textFieldCpf.setText("");
				textFieldEndereco.setText("");
				textFieldMatricula.setText("");
				textFieldSalario.setText("");
				
				// cria funicionario e o adiciona no arraylist
				Funcionario auxFuncionario = new Funcionario(auxNome, auxCpf, auxEndereco, auxMatricula, auxSalario);
				funcionarios.add(auxFuncionario);
				
				btnListarFuncionarios.doClick();
			}
		});
		btnNewButton.setBounds(221, 94, 89, 23);
		contentPane.add(btnNewButton);
		
		textArea = new JTextArea();
		textArea.setBounds(10, 153, 300, 152);
		contentPane.add(textArea);
		
		btnListarFuncionarios = new JButton("Listar Funcionarios");
		btnListarFuncionarios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// exibe infos dos funcionarios na tela
				textArea.setText("Funcionários: \n");
				for(Funcionario f: funcionarios){
					textArea.append("Funcionario: " + f.getNome() + ", Matricula: " + f.getMatricula()+ "\n");
				}
			}
		});
		btnListarFuncionarios.setBounds(175, 316, 132, 23);
		contentPane.add(btnListarFuncionarios);
		
		lblNome = new JLabel("Nome");
		lblNome.setBounds(10, 11, 46, 14);
		contentPane.add(lblNome);
		
		lblCpf = new JLabel("Cpf");
		lblCpf.setBounds(113, 10, 46, 14);
		contentPane.add(lblCpf);
		
		lblEndereco = new JLabel("Endereco");
		lblEndereco.setBounds(221, 10, 46, 14);
		contentPane.add(lblEndereco);
		
		lblMatricula = new JLabel("Matricula");
		lblMatricula.setBounds(10, 70, 46, 14);
		contentPane.add(lblMatricula);
		
		lblSalario = new JLabel("Salario");
		lblSalario.setBounds(113, 70, 46, 14);
		contentPane.add(lblSalario);
	}
}
