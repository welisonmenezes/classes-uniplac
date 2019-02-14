
public class Funcionario extends Pessoa{
	private String matricula;
	private double salario;
	static int totalFuncionario = 0;
	
	public Funcionario(String nome, String cpf, String endereco, String matricula, double salario){
		super(nome, endereco, cpf);
		this.matricula = matricula;
		this.salario = salario;
		
		this.totalFuncionario++;
	}
	
	public String getMatricula() {
		return matricula;
	}
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
	public double getSalario() {
		return salario;
	}
	public void setSalario(double salario) {
		this.salario = salario;
	}
	
	
}
