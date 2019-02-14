import java.util.ArrayList;

public class Conta implements InterfaceConta {
	private int numero;
	private float saldo;
	private float limite;
	static int totalConta = 0;
	
	private ArrayList<Pessoa> titulares;
	
	public Conta(){
		this.numero = 0;
		this.saldo = 0;
		this.limite = 0;
		
		this.titulares = new ArrayList<Pessoa>();
		
		this.totalConta++;
	}
	
	public Conta(int numero, float saldo, float limite){
		this.numero = numero;
		this.saldo = saldo;
		this.limite = limite;
		
		this.titulares = new ArrayList<Pessoa>();
		
		this.totalConta++;
	}
	
	public Conta(int numero, float saldo, float limite, String nome, String endereco, String cpf){
		this.numero = numero;
		this.saldo = saldo;
		this.limite = limite;
		
		this.titulares = new ArrayList<Pessoa>();
		this.adicionarTitular(nome, endereco, cpf);
		
		this.totalConta++;
	}
	
	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public float getSaldo() {
		return saldo;
	}
	
	public float getSaldo(float taxa){
		this.saldo = this.saldo - taxa;
		return this.saldo;
	}

	public void setSaldo(float saldo) {
		this.saldo = saldo;
	}

	public float getLimite() {
		return limite;
	}

	public void setLimite(float limite) {
		this.limite = limite;
	}
	
	public ArrayList<Pessoa> getTitulares() {
		return titulares;
	}

	public void setTitulares(ArrayList<Pessoa> titulares) {
		this.titulares.clear();
		for(Pessoa titular: titulares){
			this.titulares.add(titular);
		}
	}

	public boolean saca(float valor){
		if(this.pegaLimiteMaisSaldo() >= valor){
			this.saldo = this.saldo - valor;
			return true;
		}
		return false;
	}
	
	public void deposita(float valor){
		this.saldo = this.saldo + valor;
	}
	
	public boolean transferir(float valor, Conta destino){
		if(this.saca(valor)){
			destino.deposita(valor);
			return true;
		}
		return false;
	}
	
	public float pegaLimiteMaisSaldo(){
		return this.saldo + this.limite;
	}
	
	public void adicionarTitular(String nome, String endereco, String cpf){
		Pessoa titular = new Pessoa(nome, endereco, cpf);
		this.titulares.add(titular);
	}
	
	public String toString(){
		return "Conta: " + this.numero;
	}
}
