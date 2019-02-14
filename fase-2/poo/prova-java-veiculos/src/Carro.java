public class Carro implements VeiculoInterface {
	
	private String cor, modelo;
	private int marcha, qtdMarcha;
	private float velocidadeMax, velocidadeAtual, velocidadePorMarcha;
	private boolean estaLigado = false, isManual = true;
	
	
	Carro(String cor, String modelo, int marcha, int qtdMarcha, float velocidadeMax){
		this.cor = cor;
		this.modelo = modelo;
		this.marcha = marcha;
		this.qtdMarcha = qtdMarcha;
		this.velocidadeMax = velocidadeMax;
		this.velocidadePorMarcha = this.velocidadeMax / this.qtdMarcha;
	}
	
	public String getCor() {
		return cor;
	}

	public String getModelo() {
		return modelo;
	}

	public int getQtdMarcha() {
		return qtdMarcha;
	}

	public boolean isEstaLigado() {
		return estaLigado;
	}

	public float getVelocidadeMax() {
		return velocidadeMax;
	}

	@Override
	public void liga() {
		this.marcha = 0;
		this.estaLigado = true;
	}

	@Override
	public void desliga() {
		this.velocidadeAtual = 0;
		this.marcha = 0;
		this.estaLigado = false;
	}

	@Override
	public void trocaMarcha(int marcha) {
		if(marcha <= this.qtdMarcha && marcha > 0){
			this.marcha = marcha;
			
			if(this.isManual){
				this.velocidadeAtual = this.velocidadePorMarcha * marcha;
			}
			this.isManual = true;
		}
	}

	@Override
	public int pegaMarcha() {
		return this.marcha;
	}

	@Override
	public String pegaVelocidade() {
		return "A velocidade atual é: "+ this.velocidadeAtual;
	}
	
	public void acelerar(int velocidade){
		if(velocidade <= this.velocidadeMax){
			this.velocidadeAtual = velocidade;
			this.isManual = false;
			this.trocaMarcha(this.getQualMarcha());
		}
	}
	
	public int getQualMarcha(){
		for(int i = 1; i <= this.qtdMarcha; i++ ){
			if( (this.velocidadePorMarcha*i) >= this.velocidadeAtual ){
				return i;
			}
		}
		return 0;
	}
	
	public void painel(){
		System.out.println("-------------------------PAINEL-------------------------");
		System.out.println("O carro está ligado? " + this.isEstaLigado());
		System.out.println("A marcha atual do carro é: " + this.pegaMarcha());
		System.out.println(this.pegaVelocidade()+"\n");
	}
}
