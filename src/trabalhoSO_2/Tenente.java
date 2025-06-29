package trabalhoSO_2;

import java.util.LinkedList;

public class Tenente extends Thread{
	/*● Estado de ocupação da(s) cadeira(s) (% por categoria e livre)
	● Comprimento médio das filas
	● Tempo médio de atendimento por categoria
	● Tempo médio de espera por categoria*/
	LinkedList<Cliente> cadeiras = new LinkedList<Cliente>();
	// Informação de Ocupação
	double percent_0; //free
	double percent_1; //rank 1	
	double percent_2; //rank 2
	double percent_3; //rank_3
	int length;
	//Informação de Espera
	double wait_1;
	double wait_2;
	double wait_3;
	//Informação de Atendimento
	double atend_1;
	double atend_2;
	double atend_3;
	double atend_1_uncounted;
	double atend_2_uncounted;
	double atend_3_uncounted;
	//Contadores
	int reportCounter;
	int atendCounter;
	int atendUncounted;
	
	public Tenente(LinkedList<Cliente> cadeiras) {
		this.cadeiras = cadeiras;
	}
	
	public void reportFinished() {
		length = length/reportCounter;
		percent_0 *= ((1/reportCounter)/20)*100;
		percent_1 *= ((1/reportCounter)/20)*100;
		percent_2 *= ((1/reportCounter)/20)*100;
		percent_3 *= ((1/reportCounter)/20)*100;
		wait_1 *= ((1/reportCounter)/percent_1)*100;
		wait_2 *= ((1/reportCounter)/percent_2)*100;
		wait_3 *= ((1/reportCounter)/percent_3)*100;
		atend_1 /= atendCounter;
		atend_2 /= atendCounter;
		atend_3 /= atendCounter;

		System.out.println(
				"Comprimento médio das filas: " + length
				+ "Porcentagem de ocupação das cadeiras por:"
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
		try {
			while (true) {
				wait(3000); //espera por 3 segundos antes de atualizar.
				for (Cliente cliente : cadeiras) {
					switch (cliente.tipo) {
						case 0: percent_0++;
						case 1: 
							percent_1++;
							wait_1 += (System.currentTimeMillis() - cliente.startTime)/1000;
						case 2: 
							percent_2++;
							wait_2 += (System.currentTimeMillis() - cliente.startTime)/1000;
						case 3: 
							percent_3++;
							wait_3 += (System.currentTimeMillis() - cliente.startTime)/1000;	
					}
					length++;
				}
				reportCounter++;
				atend_1 += atend_1_uncounted;
				atend_2 += atend_2_uncounted;
				atend_3 += atend_3_uncounted;
				atendCounter += atendUncounted;
				atend_1_uncounted = 0;
				atend_2_uncounted = 0;
				atend_3_uncounted = 0;
				atendUncounted = 0;
			}
			
		}
		catch(Exception e) {
			System.out.println(e + " at Tenente.run()");
		}
	}
	
	public void atendimentoCompleto(double time, int tipo) {
		switch (tipo) {
			case 1: atend_1_uncounted++;
			case 2:	atend_2_uncounted++;
			case 3: atend_3_uncounted++;
		}
		atendUncounted++;
	};
	
	
	
}
