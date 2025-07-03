package trabalhoSO_2;

import java.util.LinkedList;

public class Tenente extends Thread{
	/*● Estado de ocupação da(s) cadeira(s) (% por categoria e livre)
	● Comprimento médio das filas
	● Tempo médio de atendimento por categoria
	● Tempo médio de espera por categoria*/
	LinkedList<Cliente> cadeiras_1 = new LinkedList<Cliente>();
	LinkedList<Cliente> cadeiras_2 = new LinkedList<Cliente>();
	LinkedList<Cliente> cadeiras_3 = new LinkedList<Cliente>();
	// Informação de Ocupação
	double percent_0; //free
	double percent_1; //rank 1	
	double percent_2; //rank 2
	double percent_3; //rank_3
	double length, length_1, length_2, length_3, length_0;
	//Informação de Espera
	double wait_1;
	double wait_2;
	double wait_3;
	//Informação de Atendimento
	double atend_1;
	double atend_2;
	double atend_3;
	//Contadores
	int reportCounter;
	int atendCounter_1, atendCounter_2, atendCounter_3;
	public boolean stop = true;
	Barbeiro RecrutaZero;
	Barbeiro Dentinho;
	Barbeiro Otto;
	
	public Tenente(LinkedList<Cliente> cadeiras_1, LinkedList<Cliente> cadeiras_2, LinkedList<Cliente> cadeiras_3, Barbeiro RecrutaZero, Barbeiro Dentinho, Barbeiro Otto) {
		this.cadeiras_1 = cadeiras_1;
		this.cadeiras_2 = cadeiras_2;
		this.cadeiras_3 = cadeiras_3;
		this.RecrutaZero = RecrutaZero;
		this.Dentinho = Dentinho;
		this.Otto = Otto;
	}
	
	public void report() {
		length /=reportCounter;
		length_1 /= reportCounter;
		length_2 /= reportCounter;
		length_3 /= reportCounter;
		length_0 /= reportCounter;
		percent_0 *= ((1/reportCounter)/20)*100;
		percent_1 *= ((1/reportCounter)/20)*100;
		percent_2 *= ((1/reportCounter)/20)*100;
		percent_3 *= ((1/reportCounter)/20)*100;
		wait_1 *= ((1/reportCounter)/percent_1)*100;
		wait_2 *= ((1/reportCounter)/percent_2)*100;
		wait_3 *= ((1/reportCounter)/percent_3)*100;
		atend_1 /= atendCounter_1;
		atend_2 /= atendCounter_2;
		atend_3 /= atendCounter_3;
		
		if (percent_0 == 0) {
			percent_0 = -1;
		}			
		if (percent_1 == 0) {
			percent_1 = -1;
			wait_1 = -1;
		}
		if (percent_2 == 0) {
			percent_2 = -1;
			wait_2 = -1;
		}
		if (percent_3 == 0) {
			percent_3 = -1;
			wait_3 = -1;
		}
		

		System.out.println(
				"Comprimento médio das filas: " + length + " sendo\n\t"
				+ length_1 + " em média ocupada por Cabos\n\t" 
				+ length_2 + " em média ocupada por Sargentos\n\t"
				+ length_3 + " em média ocupada por Oficiais\n\t"
				+ length_0 + " em média ocupada por Ninguém (Vazio)"
				+ "\nPorcentagem de ocupação das cadeiras por:"
				+ "\n\tVazio: " + percent_0 + "%"
				+ "\n\tCabos: " + percent_1 + "%"
				+ "\n\tSargentos: " + percent_2 + "%"
				+ "\n\tOficiais: " + percent_3 + "%"
				+ "\nTempo médio de espera dos:"
				+ "\n\tCabos: " + wait_1 + "s"
				+ "\n\tSargentos " + wait_2 + "s"
				+ "\n\tOficiais: " + wait_3 + "s"
				+ "\nTempo médio de atendimento dos:"
				+ "\n\tCabos: " + atend_1 + "s"
				+ "\n\tSargentos: " + atend_2 + "s"
				+ "\n\tOficiais: " + atend_3 + "s"
				);
	}
	
	public void run() {
	   stop = false;
		try {
			while (!(RecrutaZero.stop && Dentinho.stop && Otto.stop)) {
				sleep(3000); //espera por 3 segundos antes de atualizar.
				percent_0 += 20;
				length_0 += 20;
				for (Cliente cliente : cadeiras_1) {
					percent_1++;
					length_1++;
					wait_1 += (System.currentTimeMillis() - cliente.startTime)/1000;
					percent_0 --;
					length_0 --;
					length++;
				}
				for (Cliente cliente : cadeiras_2) {
					percent_2++;
					length_2++;
					wait_2 += (System.currentTimeMillis() - cliente.startTime)/1000;
					percent_0 --;
					length_0 --;
					length++;
				}
				for (Cliente cliente : cadeiras_3) {
					percent_3++;
					length_3++;
					wait_3 += (System.currentTimeMillis() - cliente.startTime)/1000;
					percent_0 --;
					length_0 --;
					length++;
				}
				reportCounter++;
				atualizaAtendimento();
			}
			
		}
		catch(Exception e) {
			System.out.println(e + " at Tenente.run()");
		}
		stop = true;
		report();
	}
	
	public void atualizaAtendimento() {
		atend_1 += RecrutaZero.atendimento_1 + Dentinho.atendimento_1 + Otto.atendimento_1;
		atend_2 += RecrutaZero.atendimento_2 + Dentinho.atendimento_2 + Otto.atendimento_2;
		atend_3 += RecrutaZero.atendimento_3 + Dentinho.atendimento_3 + Otto.atendimento_3;
		atendCounter_1 += RecrutaZero.atendCount_1 + Dentinho.atendCount_1 + Otto.atendCount_1;
		atendCounter_2 += RecrutaZero.atendCount_2 + Dentinho.atendCount_2 + Otto.atendCount_2;
		atendCounter_3 += RecrutaZero.atendCount_3 + Dentinho.atendCount_3 + Otto.atendCount_3;
		RecrutaZero.resetAtend();
		Dentinho.resetAtend();
		Otto.resetAtend();
	};
	
	
	
}
