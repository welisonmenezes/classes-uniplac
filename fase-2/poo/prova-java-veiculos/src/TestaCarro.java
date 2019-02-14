
public class TestaCarro {
	public static void main(String[] args) {
		
		Carro carro1 = new Carro("Vermelho", "Sedan", 1, 5, 120);
		
		System.out.println("A cor do carro é: " + carro1.getCor());
		System.out.println("O modelo do carro é: " + carro1.getModelo());
		System.out.println("A quantidade de marchas do carro é: " + carro1.getQtdMarcha());
		System.out.println("A velocidade máxima é: " + carro1.getVelocidadeMax()+"\n");

		carro1.liga();
		carro1.acelerar(30);
		carro1.painel();
		carro1.acelerar(90);
		carro1.painel();
		carro1.trocaMarcha(1);
		carro1.painel();
		carro1.desliga();
		carro1.painel();
		
	}

}
