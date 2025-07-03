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
		switch (tipo) {
		case 0: return "Vazio"; 
		case 1: return "Cliente tipo: Cabo tempo de serviço: " + tempoServico;
		case 2: return "Cliente tipo: Sargento tempo de serviço: " + tempoServico;
		case 3: return "Cliente tipo: Oficial tempo de serviço: " + tempoServico;
		}
		return "3RR0";
	}
	
}
