package trabalhoSO_2;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;



//todas as alterações às filas de clientes seram feitas pela barbearia
public class Barbearia{
	LinkedList<Cliente> proxClientes = new LinkedList<Cliente>();
	LinkedList<Cliente> cadeiras = new LinkedList<Cliente>();	
	int ocupacao = 0;
	Barbeiro RecrutaZero;
	Barbeiro Dentinho;
	Barbeiro Otto;
	Sargento Tainha;
	
	
	
	
	
	public Barbearia(int tempoSono) {
		RecrutaZero = new Barbeiro();
		Dentinho = new Barbeiro();
		Otto = new Barbeiro();
		Tainha = new Sargento(tempoSono, cadeiras, proxClientes);
	}
	
	//Função de ler os clietes
	
	//1 Barbeiro;
	public void casoA() {
		int semClientes = 0;
		while(semClientes < 3) {
			//Se o Sargento estiver acordado
			if (!Tainha.isAlive()) {
				if (ocupacao < 20 && proxClientes.getFirst().tipo != 0) {
					//Se há cadeiras livres, adiciona apenas um cliente
					cadeiras.add(proxClientes.getFirst());
					cadeiras.get(ocupacao).run(); //contar o tempo de espera
					ocupacao++;
					proxClientes.remove();
				}
				else if (ocupacao < 20) {
					semClientes++;
				}
				Tainha.run(); //Tira um cochilo
			}
			
			//Se o Recruta estiver livre
			if(!RecrutaZero.isAlive()) {
				Collections.sort(cadeiras, new SortByTipo());
				if (ocupacao != 0 && cadeiras.getFirst().tipo != 0) {
					// tira o primeiro da fila para cortar o cabelo
					RecrutaZero.run(cadeiras.getFirst().tipo); 
					
					cadeiras.remove();
					
				}
			}
			
		}
		
	}
	
	//2 Barbeiros;
	public void casoB() {
			
	}
	
	//3 barbeiros, 1 para cada tipo de cliente;
	public void casoC() {
		
	}

	 public static void main(String[] args) {

	 }
}

class SortByTipo implements Comparator<Cliente> {


	public int compare(Cliente c1, Cliente c2) {
		// TODO Auto-generated method stub
		return Integer.compare(c1.tipo, c2.tipo);
	}
}






