package trabalhoSO_2;

import java.util.LinkedList;

public class Barbeiro extends Thread {
	int modo;
	long tempoAtendimento;
	LinkedList<Cliente> cadeiras;
	double atendimento_1;
	double atendimento_2;
	double atendimento_3;
	double atendCount;
	
	public Barbeiro(LinkedList<Cliente> cadeiras, int modo) {
		this.cadeiras = cadeiras;
		this.modo = modo;
	}
	
	/**
	 * cortar cabelo
	 * @param index
	 * @param cadeiras
	 */
	// tipos de cliente 1-oficial 2-sargento 3-cabo 0-pausa
	
	public void resetAtend() {
		atendimento_1 = 0;
		atendimento_2 = 0;
		atendimento_3 = 0;
		atendCount = 0;
	}
	
	
	public double cutHair(Cliente cliente) {
		double random = Math.random();
		
		switch (cliente.tipo) {
			case 1:
				atendimento_1 += (cliente.tempoServico);
	
			case 2:
				atendimento_2 += (cliente.tempoServico);

			case 3:
				atendimento_3 += (cliente.tempoServico);

		}
		return cliente.tempoServico;
	}
	
	public synchronized Cliente selectClient() {
		/*IMPORTANTE!!!!! Dois Barbeiros NUNCA devem escolher os seus clientes ao mesmo tempo, essa funcao so deve ser liberada
		 * APOS o cliente alvo ser removido da lista.
		*/
		int highestRank = 0;
		int target = 0;
		int ind = 0;
		switch (modo) {
		case 0:
			for (Cliente cliente : cadeiras) {
				if (cliente.tipo > highestRank) {
					target = ind;
				    highestRank = cliente.tipo;
				}
				ind++;
			}
			cadeiras.remove(target);
			return cadeiras.get(target);
		case 1:
			for (Cliente cliente : cadeiras) {
				if (cliente.tipo == 1) {
					cadeiras.remove(ind);
					return cliente;
				}
				if (cliente.tipo > highestRank) {
					target = ind;
				    highestRank = cliente.tipo;
				}
				ind++;
			}
			cadeiras.remove(target);
			return cadeiras.get(target);
	   	case 2:
	   		for (Cliente cliente : cadeiras) {
				if (cliente.tipo == 2) {
					cadeiras.remove(ind);
					return cliente;
				}
				if (cliente.tipo > highestRank) {
					target = ind;
				    highestRank = cliente.tipo;
				}
				ind++;
			}
	   		cadeiras.remove(target);
			return cadeiras.get(target);
	   	default: return null;
		}
	}
	
	public void run(){ // tell it to my heart tell me i'm the only one
		try {
			while (true) {
				Cliente cliente = selectClient();
				tempoAtendimento = (long) cutHair(cliente)*1000;
				wait(tempoAtendimento);
			}
		}
		catch (Exception e) {
			System.out.println(e + " at Barbeiro.run()");
		}
	}
}
