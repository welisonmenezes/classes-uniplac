import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class BancoMenezes extends JFrame {

	private JPanel contentPane;
	
	private ArrayList<Funcionario> funcionarios;
	private ArrayList<Conta> contas;
	private JLabel lblBemVindoAo;
	private JButton btnAdicionarConta;
	private JButton btnAdicionarFuncionario;
	private JButton btnListarContas;
	private JButton btnListarFuncionrios;
	private JButton btnNewButton;
	private JButton btnEditarConta;
	private JButton btnGerarContas;
	private JButton btnNewButton_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BancoMenezes frame = new BancoMenezes();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public BancoMenezes() {
		funcionarios = new ArrayList<Funcionario>();
		contas = new ArrayList<Conta>();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		

		lblBemVindoAo = new JLabel("BEM VINDO AO BANCO MENEZES SA");
		lblBemVindoAo.setBounds(10, 11, 217, 14);
		contentPane.add(lblBemVindoAo);
		
		btnAdicionarConta = new JButton("Adicionar Conta");
		btnAdicionarConta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// abre janela add contas
				BancoContas auxT = new BancoContas(contas);
				auxT.setVisible(true);
			}
		});
		btnAdicionarConta.setBounds(10, 36, 217, 23);
		contentPane.add(btnAdicionarConta);
		
		btnAdicionarFuncionario = new JButton("Adicionar Funcionario");
		btnAdicionarFuncionario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// abre janela add funcionarios
				BancoFuncionarios auxF = new BancoFuncionarios(funcionarios);
				auxF.setVisible(true);
			}
		});
		btnAdicionarFuncionario.setBounds(10, 128, 217, 23);
		contentPane.add(btnAdicionarFuncionario);
		
		btnListarContas = new JButton("Listar Contas");
		btnListarContas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// abre janela listar contas
				BancoListarContas auxLC = new BancoListarContas(contas);
				auxLC.setVisible(true);
			}
		});
		btnListarContas.setBounds(10, 70, 217, 23);
		contentPane.add(btnListarContas);
		
		btnListarFuncionrios = new JButton("Listar Funcion\u00E1rios");
		btnListarFuncionrios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// abre janela listar funcionários
				BancoListarFuncionarios auxLF = new BancoListarFuncionarios(funcionarios);
				auxLF.setVisible(true);
			}
		});
		btnListarFuncionrios.setBounds(10, 162, 217, 23);
		contentPane.add(btnListarFuncionrios);
		
		btnNewButton = new JButton("Fazer Transa\u00E7\u00E3o");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// abre janela fazer transação
				BancoTransacao auxBD = new BancoTransacao(contas);
				auxBD.setVisible(true);
			}
		});
		btnNewButton.setBounds(237, 36, 187, 23);
		contentPane.add(btnNewButton);
		
		btnGerarContas = new JButton("Gerar Dados Automaticamente");
		btnGerarContas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				// adicionando contas
				for(int i = 1; i <= 10; i++){
					Conta auxConta = new Conta(i, 100, 100, "Nome_"+i, "Endereço_"+i, "111222-0"+i);
					
					// adicionando titulares
					for(int x = 1; x <= 2; x++){
						Pessoa auxPessoa = new Pessoa("Nome_"+i+"_"+x, "111222-0"+i+""+x, "Endereço_"+i+"_"+x);
						auxConta.getTitulares().add(auxPessoa);
					}
					
					contas.add(auxConta);
				}
				
				// adicionando funcionarios
				for(int y = 1; y <= 10; y++){
					Funcionario auxFuncionario = new Funcionario("Nome_"+y, "111222-0"+y, "Endereço_"+y, "Matricula_"+y, 2000);
					funcionarios.add(auxFuncionario);
				}
				
				btnGerarContas.setEnabled(false);
				
			}
		});
		btnGerarContas.setBounds(10, 227, 414, 23);
		contentPane.add(btnGerarContas);
		
		btnEditarConta = new JButton("Editar Conta");
		btnEditarConta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// abre janela editar conta
				BancoEditarConta auxBEC = new BancoEditarConta(contas);
				auxBEC.setVisible(true);
			}
		});
		btnEditarConta.setBounds(237, 70, 187, 23);
		contentPane.add(btnEditarConta);
		
		btnNewButton_1 = new JButton("Editar Funcion\u00E1rio");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// abre janela editar funcionários
				BancoEditarFuncionario auxBEF = new BancoEditarFuncionario(funcionarios);
				auxBEF.setVisible(true);
			}
		});
		btnNewButton_1.setBounds(237, 128, 187, 23);
		contentPane.add(btnNewButton_1);
	}
}
