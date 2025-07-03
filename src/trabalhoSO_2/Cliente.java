package trabalhoSO_2;

public class Cliente {
	int tipo;
	double tempoServico;
	long startTime;
	
	public Cliente(int tipo, double tempoServico) {
		this.tipo = tipo;
		this.tempoServico = tempoServico;
		startTime = System.currentTimeMillis();
	}
	
	public void resetTime() {
		startTime = System.currentTimeMillis(); 
	}
	
	public String toString() {
		return "Cliente tipo: " + tipo + " tempo de serviço: " + tempoServico;
	}
	
}
