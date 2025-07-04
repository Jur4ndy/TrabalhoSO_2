package trabalhoSO_2;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.concurrent.Semaphore;

//todas as alterações às filas de clientes seram feitas pela barbearia
public class Barbearia{
    public static final Semaphore semaphore = new Semaphore(1);
	LinkedList<Cliente> proxClientes = new LinkedList<Cliente>();
	LinkedList<Cliente> cadeiras_1 = new LinkedList<Cliente>();
	LinkedList<Cliente> cadeiras_2 = new LinkedList<Cliente>();
	LinkedList<Cliente> cadeiras_3 = new LinkedList<Cliente>();
	double tempoSono;
	Barbeiro RecrutaZero;
	Barbeiro Dentinho;
	Barbeiro Otto;
	Sargento Tainha;
	Tenente Escovinha;
	
	// Flag to prevent simultaneous simulations
	private boolean simulationRunning = false;
	
	/**
	 * Tempo de Sono deve ser dado em segundos
	 * @param tempoSono
	 */
	public Barbearia(double tempoSono) {
		RecrutaZero = new Barbeiro(cadeiras_1, cadeiras_2, cadeiras_3, 0);
		Dentinho = new Barbeiro(cadeiras_1, cadeiras_2, cadeiras_3, 0);
		Otto = new Barbeiro(cadeiras_1, cadeiras_2, cadeiras_3, 0);
		Escovinha = new Tenente(cadeiras_1, cadeiras_2, cadeiras_3, proxClientes, RecrutaZero, Dentinho, Otto);
		this.tempoSono = tempoSono;
	}
	
	private void resetSimulation() {
		// Reset all thread states
		RecrutaZero.stop = true;
		Dentinho.stop = true;
		Otto.stop = true;
		Sargento.stop = false;
		Escovinha.stop = true;
		
		// Reset barbeiro statistics
		RecrutaZero.resetAtend();
		Dentinho.resetAtend();
		Otto.resetAtend();
		
		// Clear all queues
		cadeiras_1.clear();
		cadeiras_2.clear();
		cadeiras_3.clear();
		
		// Reset simulation flag
		simulationRunning = false;
		
		System.out.println("Simulação resetada - Pronta para nova execução");
	}
	
	private void waitForSimulationEnd() {
		try {
			// Wait for Sargento to finish
			while (!Sargento.stop) {
				Thread.sleep(100);
			}
			
			// Wait a bit more for barbeiros to finish current clients
			Thread.sleep(2000);
			
			// Wait for Tenente to finish its report
			while (!Escovinha.stop) {
				Thread.sleep(100);
			}
			
			// Wait for barbeiros to completely stop
			while (!RecrutaZero.stop || !Dentinho.stop || !Otto.stop) {
				Thread.sleep(100);
			}
			
			System.out.println("Simulação finalizada completamente!");
			
		} catch (InterruptedException e) {
			System.out.println("Erro ao aguardar fim da simulação: " + e);
		}
	}
	
	//1 Barbeiro;
	public void casoA() {
		if (simulationRunning) {
			System.out.println("Aguardando simulação anterior terminar...");
			waitForSimulationEnd();
			resetSimulation();
		}
		
		simulationRunning = true;
		System.out.println("=== INICIANDO CASO A - 1 BARBEIRO ===");
		
		// Create new instances for this simulation
		RecrutaZero = new Barbeiro(cadeiras_1, cadeiras_2, cadeiras_3, 0);
		Dentinho = new Barbeiro(cadeiras_1, cadeiras_2, cadeiras_3, 0); // Create but don't start
		Otto = new Barbeiro(cadeiras_1, cadeiras_2, cadeiras_3, 0); // Create but don't start
		Tainha = new Sargento(tempoSono, cadeiras_1, cadeiras_2, cadeiras_3, proxClientes);
		Escovinha = new Tenente(cadeiras_1, cadeiras_2, cadeiras_3, proxClientes, RecrutaZero, Dentinho, Otto);
		
		// Only RecrutaZero works in Case A
		RecrutaZero.modo = 0;
		
		// Start threads
		Tainha.start();
		RecrutaZero.start();
		// Dentinho and Otto are NOT started in Case A
		
		try {
			Thread.sleep(100); // Small delay to let barbeiros start
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		Escovinha.start();
		
		// Wait for simulation to complete
		waitForSimulationEnd();
		resetSimulation();
	}
	
	//2 Barbeiros;
	public void casoB() {
		if (simulationRunning) {
			System.out.println("Aguardando simulação anterior terminar...");
			waitForSimulationEnd();
			resetSimulation();
		}
		
		simulationRunning = true;
		System.out.println("=== INICIANDO CASO B - 2 BARBEIROS ===");
		
		// Create new instances for this simulation
		RecrutaZero = new Barbeiro(cadeiras_1, cadeiras_2, cadeiras_3, 0);
		Dentinho = new Barbeiro(cadeiras_1, cadeiras_2, cadeiras_3, 0);
		Otto = new Barbeiro(cadeiras_1, cadeiras_2, cadeiras_3, 0); // Create Otto but don't start it
		Tainha = new Sargento(tempoSono, cadeiras_1, cadeiras_2, cadeiras_3, proxClientes);
		Escovinha = new Tenente(cadeiras_1, cadeiras_2, cadeiras_3, proxClientes, RecrutaZero, Dentinho, Otto);
		
		// Both barbeiros work with mode 0 (serve any client type, priority: Oficial > Sargento > Cabo)
		RecrutaZero.modo = 0;
		Dentinho.modo = 0;
		
		// Start threads
		Tainha.start();
		RecrutaZero.start();
		Dentinho.start();
		// Otto is NOT started in Case B
		
		try {
			Thread.sleep(100); // Small delay to let barbeiros start
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		Escovinha.start();
		
		// Wait for simulation to complete
		waitForSimulationEnd();
		resetSimulation();
	}
	
	//3 barbeiros, 1 para cada tipo de cliente;
	public void casoC() {
		if (simulationRunning) {
			System.out.println("Aguardando simulação anterior terminar...");
			waitForSimulationEnd();
			resetSimulation();
		}
		
		simulationRunning = true;
		System.out.println("=== INICIANDO CASO C - 3 BARBEIROS ===");
		
		// Create new instances for this simulation
		RecrutaZero = new Barbeiro(cadeiras_1, cadeiras_2, cadeiras_3, 0);
		Dentinho = new Barbeiro(cadeiras_1, cadeiras_2, cadeiras_3, 1);
		Otto = new Barbeiro(cadeiras_1, cadeiras_2, cadeiras_3, 2);
		Tainha = new Sargento(tempoSono, cadeiras_1, cadeiras_2, cadeiras_3, proxClientes);
		Escovinha = new Tenente(cadeiras_1, cadeiras_2, cadeiras_3, proxClientes, RecrutaZero, Dentinho, Otto);
		
		// Start threads
		Tainha.start();
		RecrutaZero.start();
		Dentinho.start();
		Otto.start();
		
		try {
			Thread.sleep(100); // Small delay to let barbeiros start
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		Escovinha.start();
		
		// Wait for simulation to complete
		waitForSimulationEnd();
		resetSimulation();
	}
	
	public void getClientes(int num) {
		// Clear previous clients before generating new ones
		proxClientes.clear();
		
		String entrada = "";
		double d1 = 0;
		int tipo = 0;
		double d2 = 0;
		double tempo = 0;
		for (int i = 0; i < num; i++) {
			d1 = Math.random();
			d2 = Math.random();
			if (d1 < 0.25) tipo = 0;
			else if (d1 < 0.5) tipo = 1;
			else if (d1 < 0.75) tipo = 2;
			else tipo = 3;
			switch(tipo) {
			case 0: tempo = 0; break;
			// 0.9999999999999999 == 1
			case 3: tempo = 1 + 2*d2; break;
			case 2: tempo = 2 + 2*d2; break;
			case 1: tempo = 4 + 2*d2; 
			}
			entrada += "<" + tipo + "><" + tempo + ">";
		}
		getClients(entrada);
	}
	
	public void getClients(String line) {		
		// Clear previous clients before adding new ones
		proxClientes.clear();
		
		line = line.replace("<", "");
		String[] clientsData = line.split(">");
		int tipo = -1;
		double tempoServico = -1;
		int ind = 0;
		
		for (String data : clientsData) {   
			if (ind%2 == 0) {
				tipo = Integer.parseInt(data);
				if (tipo != 0) tipo = Math.abs(tipo - 3) + 1;
			}
			else {
				tempoServico = Double.parseDouble(data);
				proxClientes.add(new Cliente(tipo, tempoServico));				
			}
			ind++;
		}
		System.out.println("Clientes na fila: " + (ind+1)/2);
	}

	public void getClientes(String text) {
		// Clear previous clients before loading new ones
		proxClientes.clear();
		
		try {
			File file = new File(text);
			Scanner scan = new Scanner(file);
			String line = scan.nextLine();
			line = line.replace("<", "");
			String[] clientsData = line.split(">");
			int tipo = -1;
			double tempoServico = -1;
			int ind = 0;
			
			for (String data : clientsData) {   
				if (ind%2 == 0) {
					tipo = Integer.parseInt(data);
					if(tipo != 0) tipo = Math.abs(tipo - 3) + 1;
				}
				else {
					tempoServico = Double.parseDouble(data);
					proxClientes.add(new Cliente(tipo, tempoServico));				
				}
				ind++;
			}
			System.out.println("Clientes na fila: " + (ind+1)/2);
			scan.close();
		}
		catch(IOException erro) {
			System.out.println("ERROR! file not found: " + erro);
		}
	}
}

class SortByTipo implements Comparator<Cliente> {
	public int compare(Cliente c1, Cliente c2) {
		// TODO Auto-generated method stub
		return Integer.compare(c1.tipo, c2.tipo);
	}
}