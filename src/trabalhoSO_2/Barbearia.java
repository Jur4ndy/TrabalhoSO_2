package trabalhoSO_2;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Scanner;

import tSO3.PageInfo;



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

	public void getClients(String text) {
		try {
			File file = new File(text);
			Scanner scan = new Scanner(file);
			String line = scan.nextLine();
			line = line.replace("<", "");
			String[] clientsData = line.split(">");
			int tipo = -1;
			long tempoServico = -1;
			int ind = 0;
			
			for (String data : clientsData) {   
				if (ind%2 == 0) {
					tipo = Integer.parseInt(data);
				}
				else {
					tempoServico = Long.parseLong(data);
					proxClientes.add(new Cliente(tipo, tempoServico));
				}
			}
			System.out.println("Clientes na fila: " + (ind+1)/2);
			scan.close();
		}
		catch(IOException erro) {
			System.out.println("ERROR! file not found");
		}
	}
}

class SortByTipo implements Comparator<Cliente> {


	public int compare(Cliente c1, Cliente c2) {
		// TODO Auto-generated method stub
		return Integer.compare(c1.tipo, c2.tipo);
	}
}






