import java.util.ArrayList;
import java.util.Iterator;

public class Banco {

	public static void main(String[] args) {
		
		// cria lista pra receber funcionarios
		ArrayList<Funcionario> funcionarios = new ArrayList<Funcionario>();

		// cria funcionarios
		Funcionario f1 = new Funcionario("José", "355344", "rua qualquer josé", "33222", 3200f);
		Funcionario f2 = new Funcionario("Ana", "43422422", "rua qualquer ana", "321122", 4200f);
		Funcionario f3 = new Funcionario("Luiza", "111233", "rua qualquer luiza", "998722", 3500f);
		
		// adiciona funcionarios na lista
		funcionarios.add(f1);
		funcionarios.add(f2);
		funcionarios.add(f3);
		
		// lista os funcionarios
		for(Funcionario f: funcionarios){
			System.out.println("Funcionario: " + f.getNome() + ", Matricula: " + f.getMatricula());
			System.out.println("--------------------------------");
		}
		
		
		// cria conta 1 e add titulares
		Conta c1 = new Conta();
		c1.adicionarTitular("Welison", "Rua Welison", "11111");
		
		// cria conta 2 e add titulares
		Conta c2 = new Conta(
				2, 
				0f, 
				10000f, 
				"Fulano de Tal", 
				"Endereco de tal", 
				"11221122"
		);
		c2.adicionarTitular("Juliana", "Rua Juliana", "44444");
		
		// cria conta 3 e add titulares
		Conta c3 = new Conta(3, 0f, 2000f);
		c3.adicionarTitular("José", "Rua José", "11431");
		c3.adicionarTitular("Taís", "Rua Taís", "5411");
		
		// cria lista e adiciona contas na lista
		ArrayList<Conta> contas = new ArrayList<Conta>();
		contas.add(c1);
		contas.add(c2);
		contas.add(c3);
		
		// imprime contas e respectvios titulares
		for(Conta c : contas){
			System.out.println("Conta: " + c.getNumero());
			System.out.println("Titulares: ");
			for(Pessoa p : c.getTitulares()){
				System.out.println("--" + p.getNome());
			}
			System.out.println("--------------------------------");
		}
		

	}

}