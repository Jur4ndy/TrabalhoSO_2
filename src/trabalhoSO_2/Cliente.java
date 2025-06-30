package trabalhoSO_2;

public class Cliente {
	int tipo;
	long tempoServico;
	long startTime;
	
	public Cliente(int tipo, long tempoServico) {
		this.tipo = tipo;
		this.tempoServico = tempoServico;
		startTime = System.currentTimeMillis();
	}
	
	public void resetTime() {
		startTime = System.currentTimeMillis(); 
	}
	
}
