package trabalhoSO_2;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Scanner;




//todas as alterações às filas de clientes seram feitas pela barbearia
public class Barbearia{
	LinkedList<Cliente> proxClientes = new LinkedList<Cliente>();
	LinkedList<Cliente> cadeiras = new LinkedList<Cliente>();	
	int ocupacao = 0;
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
		RecrutaZero = new Barbeiro(cadeiras, 0);
		Dentinho = new Barbeiro(cadeiras, 0);
		Otto = new Barbeiro(cadeiras, 0);
		Escovinha = new Tenente(cadeiras, RecrutaZero, Dentinho, Otto);
		this.tempoSono = tempoSono;
	}
	
	//Função de ler os clietes
	
	//1 Barbeiro;
	public void casoA() {
		Tainha = new Sargento(tempoSono, cadeiras, proxClientes);
		LinkedList<Cliente> cadeiras = new LinkedList<Cliente>();	
		while(Tainha.tentativas < 3) {
			Tainha.run();
			RecrutaZero.run();
			Escovinha.run();
		}
		Escovinha.report();
		
	}
	
	//2 Barbeiros;
	public void casoB() {
		Tainha = new Sargento(tempoSono, cadeiras, proxClientes);
		LinkedList<Cliente> cadeiras = new LinkedList<Cliente>();	
		Dentinho.modo = 0;
		while(Tainha.tentativas < 3) {
			Tainha.run();
			RecrutaZero.run();
			Dentinho.run();
			Escovinha.run();
		}
		Escovinha.report();
	}
	
	//3 barbeiros, 1 para cada tipo de cliente;
	public void casoC() {
		Tainha = new Sargento(tempoSono, cadeiras, proxClientes);
		LinkedList<Cliente> cadeiras = new LinkedList<Cliente>();	
		Dentinho.modo = 1;
		Otto.modo = 2;
		while(Tainha.tentativas < 3) {
			Tainha.run();
			RecrutaZero.run();
			Dentinho.run();
			Otto.run();
			Escovinha.run();
		}
		Escovinha.report();
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
			case 0: tempo = 0;
			// 0.9999999999999999 == 1
			case 3: tempo = 1 + 2*d2;
			case 2: tempo = 2 + 2*d2;
			case 1: tempo = 4 + 2*d2;
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
					tipo = Math.abs(tipo - 3) + 1;
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
					tipo = Math.abs(tipo - 3) + 1;
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






