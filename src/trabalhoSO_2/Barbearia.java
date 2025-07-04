package trabalhoSO_2;

import java.io.File;
import java.io.IOException;
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
	
	// previne que multiplas simulacoes rodem ao mesmo trempo
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
	
	/**
	 * Reseta todas os atributos dessa classe ao seu estado padrao
	 */
	private void resetSimulation() {
		// Reset paradas
		RecrutaZero.stop = true;
		Dentinho.stop = true;
		Otto.stop = true;
		Sargento.stop = false;
		Escovinha.stop = true;
		
		// Reset estatisticas
		RecrutaZero.resetAtend();
		Dentinho.resetAtend();
		Otto.resetAtend();
		
		// Limpa as cadeiras
		cadeiras_1.clear();
		cadeiras_2.clear();
		cadeiras_3.clear();
		proxClientes.clear();
		
		// Nao ha mais nenhuma simulacao rodando
		simulationRunning = false;
		
		System.out.println("Simulação resetada - Pronta para nova execução");
	}
	
	private void waitForSimulationEnd() {
		try {
			
			while (!Sargento.stop) {
				Thread.sleep(100);
			}
			
		
			Thread.sleep(2000);
			
			
			while (!Escovinha.stop) {
				Thread.sleep(100);
			}
			

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
		
		// Criar novas instâncias pra cada simulacao
		RecrutaZero = new Barbeiro(cadeiras_1, cadeiras_2, cadeiras_3, 0);
		Dentinho = new Barbeiro(cadeiras_1, cadeiras_2, cadeiras_3, 0); // Criada mas nao utilizada
		Otto = new Barbeiro(cadeiras_1, cadeiras_2, cadeiras_3, 0); // Criada mas nao utilizada
		Tainha = new Sargento(tempoSono, cadeiras_1, cadeiras_2, cadeiras_3, proxClientes);
		Escovinha = new Tenente(cadeiras_1, cadeiras_2, cadeiras_3, proxClientes, RecrutaZero, Dentinho, Otto);
		
		
		RecrutaZero.modo = 0;
		
		// Comeca as threads
		Tainha.start();
		RecrutaZero.start();

		
		try {
			Thread.sleep(100); // Pequeno delay 
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
		

		RecrutaZero = new Barbeiro(cadeiras_1, cadeiras_2, cadeiras_3, 0);
		Dentinho = new Barbeiro(cadeiras_1, cadeiras_2, cadeiras_3, 0);
		Otto = new Barbeiro(cadeiras_1, cadeiras_2, cadeiras_3, 0); // Criado mas nao utilizado
		Tainha = new Sargento(tempoSono, cadeiras_1, cadeiras_2, cadeiras_3, proxClientes);
		Escovinha = new Tenente(cadeiras_1, cadeiras_2, cadeiras_3, proxClientes, RecrutaZero, Dentinho, Otto);
		
		// Ambos usam a mesma prioridade
		RecrutaZero.modo = 0;
		Dentinho.modo = 0;
		
		// Inicializa as threads
		Tainha.start();
		RecrutaZero.start();
		Dentinho.start();
		// Otto nao e inicializado para esse caso
		
		try {
			Thread.sleep(100);
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
		

		RecrutaZero = new Barbeiro(cadeiras_1, cadeiras_2, cadeiras_3, 0);
		Dentinho = new Barbeiro(cadeiras_1, cadeiras_2, cadeiras_3, 1);
		Otto = new Barbeiro(cadeiras_1, cadeiras_2, cadeiras_3, 2);
		Tainha = new Sargento(tempoSono, cadeiras_1, cadeiras_2, cadeiras_3, proxClientes);
		Escovinha = new Tenente(cadeiras_1, cadeiras_2, cadeiras_3, proxClientes, RecrutaZero, Dentinho, Otto);
		
		// Todas as threads sao utlizadas
		Tainha.start();
		RecrutaZero.start();
		Dentinho.start();
		Otto.start();
		
		try {
			Thread.sleep(100); 
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		Escovinha.start();
		
		// Wait for simulation to complete
		waitForSimulationEnd();
		resetSimulation();
	}
	 
	/**
	 * Gera uma fila aleatória.
	 * @param num
	 */
	public void getClientes(int num) {

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
		// Limpa a lista antes de criar uma nova
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
		// Limpa a lista antes de criar uma nova.
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