package trabalhoSO_2;

import java.util.LinkedList;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Tenente extends Thread{
	/*● Estado de ocupação da(s) cadeira(s) (% por categoria e livre)
	● Comprimento médio das filas
	● Tempo médio de atendimento por categoria
	● Tempo médio de espera por categoria*/
	LinkedList<Cliente> cadeiras_1 = new LinkedList<Cliente>();
	LinkedList<Cliente> cadeiras_2 = new LinkedList<Cliente>();
	LinkedList<Cliente> cadeiras_3 = new LinkedList<Cliente>();
	LinkedList<Cliente> proxClientes = new LinkedList<Cliente>();
	
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
	int totalServiced_1, totalServiced_2, totalServiced_3;
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
	
	// Constructor that also receives proxClientes
	public Tenente(LinkedList<Cliente> cadeiras_1, LinkedList<Cliente> cadeiras_2, LinkedList<Cliente> cadeiras_3, 
			LinkedList<Cliente> proxClientes, Barbeiro RecrutaZero, Barbeiro Dentinho, Barbeiro Otto) {
		this.cadeiras_1 = cadeiras_1;
		this.cadeiras_2 = cadeiras_2;
		this.cadeiras_3 = cadeiras_3;
		this.proxClientes = proxClientes;
		this.RecrutaZero = RecrutaZero;
		this.Dentinho = Dentinho;
		this.Otto = Otto;
	}
	
	public void report() {
		
		// Calculate averages
		double avgLength = reportCounter > 0 ? length / reportCounter : 0;
		double avgLength_1 = reportCounter > 0 ? length_1 / reportCounter : 0;
		double avgLength_2 = reportCounter > 0 ? length_2 / reportCounter : 0;
		double avgLength_3 = reportCounter > 0 ? length_3 / reportCounter : 0;
		double avgLength_0 = reportCounter > 0 ? length_0 / reportCounter : 0;
		
		double avgPercent_0 = reportCounter > 0 ? (percent_0 / reportCounter) * 100 / 20 : 0;
		double avgPercent_1 = reportCounter > 0 ? (percent_1 / reportCounter) * 100 / 20 : 0;
		double avgPercent_2 = reportCounter > 0 ? (percent_2 / reportCounter) * 100 / 20 : 0;
		double avgPercent_3 = reportCounter > 0 ? (percent_3 / reportCounter) * 100 / 20 : 0;
		
		double avgWait_1 = atendCounter_1 > 0 ? wait_1 / atendCounter_1 : 0;
		double avgWait_2 = atendCounter_2 > 0 ? wait_2 / atendCounter_2 : 0;
		double avgWait_3 = atendCounter_3 > 0 ? wait_3 / atendCounter_3 : 0;
		
		double avgAtend_1 = totalServiced_1 > 0 ? atend_1 / totalServiced_1 : 0;
		double avgAtend_2 = totalServiced_2 > 0 ? atend_2 / totalServiced_2 : 0;
		double avgAtend_3 = totalServiced_3 > 0 ? atend_3 / totalServiced_3 : 0;
		
		// Count people in proxClientes
		int proxCount_0 = 0, proxCount_1 = 0, proxCount_2 = 0, proxCount_3 = 0;
		for (Cliente cliente : proxClientes) {
			switch (cliente.tipo) {
				case 0: proxCount_0++; break;
				case 1: proxCount_1++; break;
				case 2: proxCount_2++; break;
				case 3: proxCount_3++; break;
			}
		}
		
		String report = "\n========== RELATÓRIO FINAL ==========\n";

		report += "-COMPRIMENTO MÉDIO DAS FILAS:\n";
		report += "\tTotal: " + String.format("%.2f", avgLength) + "\n";
		report += "\tCabos: " + String.format("%.2f", avgLength_1) + "\n";
		report += "\tSargentos: " + String.format("%.2f", avgLength_2) + "\n";
		report += "\tOficiais: " + String.format("%.2f", avgLength_3) + "\n";
		report += "\tVazio: " + String.format("%.2f", avgLength_0) + "\n";

		report += "\n-PORCENTAGEM MÉDIA DE OCUPAÇÃO DAS CADEIRAS:\n";
		report += "\tVazio: " + String.format("%.2f", avgPercent_0) + "%\n";
		report += "\tCabos: " + String.format("%.2f", avgPercent_1) + "%\n";
		report += "\tSargentos: " + String.format("%.2f", avgPercent_2) + "%\n";
		report += "\tOficiais: " + String.format("%.2f", avgPercent_3) + "%\n";

		report += "\n-TEMPO MÉDIO DE ESPERA:\n";
		report += "\tCabos: " + String.format("%.2f", avgWait_1) + "s\n";
		report += "\tSargentos: " + String.format("%.2f", avgWait_2) + "s\n";
		report += "\tOficiais: " + String.format("%.2f", avgWait_3) + "s\n";

		report += "\n-TEMPO MÉDIO DE ATENDIMENTO:\n";
		report += "\tCabos: " + String.format("%.2f", avgAtend_1) + "s\n";
		report += "\tSargentos: " + String.format("%.2f", avgAtend_2) + "s\n";
		report += "\tOficiais: " + String.format("%.2f", avgAtend_3) + "s\n";

		report += "\n-NÚMERO DE PESSOAS ATENDIDAS:\n";
		report += "\tCabos: " + totalServiced_1 + "\n";
		report += "\tSargentos: " + totalServiced_2 + "\n";
		report += "\tOficiais: " + totalServiced_3 + "\n";
		report += "\tTotal: " + (totalServiced_1 + totalServiced_2 + totalServiced_3) + "\n";

		report += "\n-PESSOAS RESTANTES EM PROXCLIENTES:\n";
		report += "\tVazio: " + proxCount_0 + "\n";
		report += "\tCabos: " + proxCount_1 + "\n";
		report += "\tSargentos: " + proxCount_2 + "\n";
		report += "\tOficiais: " + proxCount_3 + "\n";
		report += "\tTotal: " + proxClientes.size() + "\n";

		report += "\n-NÚMERO DE RELATÓRIOS GERADOS: " + reportCounter + "\n";
		report += "=====================================\n";
		
		 try {
		      FileWriter myWriter = new FileWriter("TenenteReport.txt");
		      myWriter.write(report);
		      myWriter.close();
		      System.out.println("Arquivo Criado :)");
		    } catch (IOException e) {
		      System.out.println("Erro na criacao do arquivo :(");
		      e.printStackTrace();
		    }
		
		System.out.println(report);

	}
	
	public void run() {
		stop = false;
		System.out.println("Tenente iniciado - Monitoramento em andamento...");
		
		try {
			// Espera um tempo pra que as outras threads comecem direito
			sleep(1000);
			
			while (!Sargento.stop || !(cadeiras_1.isEmpty() && cadeiras_2.isEmpty() && cadeiras_3.isEmpty())) { 
				sleep(3000); // Atualiza de 3 em 3 segundos
				
				// Reset contadores 
				int currentEmpty = 20;
				int current_1 = 0, current_2 = 0, current_3 = 0;
				
				// Percorre as cadeiras pra pegar informacao
				for (Cliente cliente : cadeiras_1) {
					current_1++;
					currentEmpty--;
					wait_1 += (System.currentTimeMillis() - cliente.startTime) / 1000.0;
					atendCounter_1++;
				}
				for (Cliente cliente : cadeiras_2) {
					current_2++;
					currentEmpty--;
					wait_2 += (System.currentTimeMillis() - cliente.startTime) / 1000.0;
					atendCounter_2++;
				}
				for (Cliente cliente : cadeiras_3) {
					current_3++;
					currentEmpty--;
					wait_3 += (System.currentTimeMillis() - cliente.startTime) / 1000.0;
					atendCounter_3++;
				}
				
				// Update statistics
				length_0 += currentEmpty;
				length_1 += current_1;
				length_2 += current_2;
				length_3 += current_3;
				length += (current_1 + current_2 + current_3);
				
				percent_0 += currentEmpty;
				percent_1 += current_1;
				percent_2 += current_2;
				percent_3 += current_3;
				
				reportCounter++;
				atualizaAtendimento();
				
				// Printa os status das cadeiras a cada 10 reports (30 segundos)
				if (reportCounter % 10 == 0) {
					System.out.println("Status - Cadeiras ocupadas: " + 
						(current_1 + current_2 + current_3) + "/20 | " +
						"Fila: " + proxClientes.size() + " clientes");
				}
			}
			
		}
		catch(Exception e) {
			System.out.println("Erro no Tenente: " + e);
		}
		
		stop = true;
		System.out.println("Tenente finalizando - Gerando relatório final...");
		report();
	}
	
	public void atualizaAtendimento() {
		atend_1 += RecrutaZero.atendimento_1 + Dentinho.atendimento_1 + Otto.atendimento_1;
		atend_2 += RecrutaZero.atendimento_2 + Dentinho.atendimento_2 + Otto.atendimento_2;
		atend_3 += RecrutaZero.atendimento_3 + Dentinho.atendimento_3 + Otto.atendimento_3;
		
		totalServiced_1 += RecrutaZero.atendCount_1 + Dentinho.atendCount_1 + Otto.atendCount_1;
		totalServiced_2 += RecrutaZero.atendCount_2 + Dentinho.atendCount_2 + Otto.atendCount_2;
		totalServiced_3 += RecrutaZero.atendCount_3 + Dentinho.atendCount_3 + Otto.atendCount_3;
		
		RecrutaZero.resetAtend();
		Dentinho.resetAtend();
		Otto.resetAtend();
	}
}