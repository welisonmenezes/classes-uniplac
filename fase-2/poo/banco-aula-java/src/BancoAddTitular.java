import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.FlowLayout;


public class BancoAddTitular extends JPanel {
	private JTextField textFieldNome;
	private JTextField textFieldCpf;
	private JTextField textFieldEndereco;
	private JLabel label_2;
	private JLabel label_1;
	private JLabel label;
	
	public JTextField getTextFieldNome(){
		return textFieldNome;
	}
	
	public JTextField getTextFieldCpf(){
		return textFieldCpf;
	}
	
	public JTextField getTextFieldEndereco(){
		return textFieldEndereco;
	}

	/**
	 * Create the panel.
	 */
	public BancoAddTitular() {
		setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		label_2 = new JLabel("Nome");
		add(label_2);
		
		textFieldNome = new JTextField();
		textFieldNome.setColumns(10);
		add(textFieldNome);
		
		label_1 = new JLabel("Cpf");
		add(label_1);
		
		textFieldCpf = new JTextField();
		textFieldCpf.setColumns(10);
		add(textFieldCpf);
		
		label = new JLabel("Endere\u00E7o");
		add(label);
		
		textFieldEndereco = new JTextField();
		textFieldEndereco.setColumns(10);
		add(textFieldEndereco);
		
		
		this.setSize(465, 51);

	}

}
