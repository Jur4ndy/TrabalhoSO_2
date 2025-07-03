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
	int tempoSono;
	Barbeiro RecrutaZero;
	Barbeiro Dentinho;
	Barbeiro Otto;
	Sargento Tainha;
	Tenente Escovinha;
	
	/**
	 * Tempo de Sono deve ser dado em segundos
	 * @param tempoSono
	 */
	public Barbearia(int tempoSono) {
		RecrutaZero = new Barbeiro(cadeiras_1, cadeiras_2, cadeiras_3, 0);
		Dentinho = new Barbeiro(cadeiras_1, cadeiras_2, cadeiras_3, 0);
		Otto = new Barbeiro(cadeiras_1, cadeiras_2, cadeiras_3, 0);
		Escovinha = new Tenente(cadeiras_1, cadeiras_2, cadeiras_3, RecrutaZero, Dentinho, Otto);
		this.tempoSono = tempoSono;
	}
	
	//1 Barbeiro;
	public void casoA() {
	    Tainha = new Sargento(tempoSono, cadeiras_1, cadeiras_2, cadeiras_3, proxClientes);
	    
	    Tainha.start();
	    try {
	        Thread.sleep(100); // pausa curta para dar tempo do sistema iniciar
	    } catch (InterruptedException e) {
	        e.printStackTrace();
	    }
	    
	    RecrutaZero.start();

	    try {
	        Thread.sleep(100); // pausa curta para dar tempo do sistema iniciar
	    } catch (InterruptedException e) {
	        e.printStackTrace();
	    }

	    Escovinha.start();

	    try {
	        // Wait for all threads to complete
	        Escovinha.join(); // espera o Tenente terminar
	        Tainha.join(); // espera o Sargento terminar
	        RecrutaZero.join(); // espera o Barbeiro terminar
	    } catch (InterruptedException e) {
	        e.printStackTrace();
	    }
	    
	    System.out.println("Simulação Caso A finalizada.");
	}

	//2 Barbeiros;
	public void casoB() {
		Tainha = new Sargento(tempoSono, cadeiras_1, cadeiras_2, cadeiras_3, proxClientes);
		Dentinho.modo = 0;
		Tainha.start();
		try {
	        Thread.sleep(100); // pausa curta para dar tempo do sistema iniciar
	    } catch (InterruptedException e) {
	        e.printStackTrace();
	    }
		
		RecrutaZero.start();
		Dentinho.start();
		
		 try {
		        Thread.sleep(100); // pausa curta para dar tempo do sistema iniciar
		    } catch (InterruptedException e) {
		        e.printStackTrace();
		    }
		 
		Escovinha.start();
		
		try {
			// Wait for all threads to complete
			Escovinha.join(); // espera o Tenente terminar
			Tainha.join(); // espera o Sargento terminar
			RecrutaZero.join(); // espera o primeiro Barbeiro terminar
			Dentinho.join(); // espera o segundo Barbeiro terminar
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("Simulação Caso B finalizada.");
	}
	
	//3 barbeiros, 1 para cada tipo de cliente;
	public void casoC() {
		Tainha = new Sargento(tempoSono, cadeiras_1, cadeiras_2, cadeiras_3, proxClientes);
		Dentinho.modo = 1;
		Otto.modo = 2;
		Tainha.start();
		
		try {
	        Thread.sleep(100); // pausa curta para dar tempo do sistema iniciar
	    } catch (InterruptedException e) {
	        e.printStackTrace();
	    }
		
		RecrutaZero.start();
		Dentinho.start();
		Otto.start();
		
		 try {
		        Thread.sleep(100); // pausa curta para dar tempo do sistema iniciar
		    } catch (InterruptedException e) {
		        e.printStackTrace();
		    }
		 
		Escovinha.start();
		
		try {
			// Wait for all threads to complete
			Escovinha.join(); // espera o Tenente terminar
			Tainha.join(); // espera o Sargento terminar
			RecrutaZero.join(); // espera o primeiro Barbeiro terminar
			Dentinho.join(); // espera o segundo Barbeiro terminar
			Otto.join(); // espera o terceiro Barbeiro terminar
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("Simulação Caso C finalizada.");
	}
	
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
				case 3: tempo = 1 + 2*d2; break;
				case 2: tempo = 2 + 2*d2; break;
				case 1: tempo = 4 + 2*d2; break;
			}
			entrada += "<" + tipo + "><" + tempo + ">";
		}
		getClients(entrada);
	}
	
	public void getClients(String line) {		
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