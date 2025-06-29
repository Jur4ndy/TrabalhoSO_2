package trabalhoSO_2;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;



//todas as alterações às filas de clientes seram feitas pela barbearia
public class Barbearia{
	LinkedList<Integer> proxClientes = new LinkedList<Integer>();
	LinkedList<Cliente> cadeiras = new LinkedList<Cliente>();	
	int ocupacao = 0;
	Barbeiro RecrutaZero;
	Barbeiro Dentinho;
	Barbeiro Otto;
	Sargento Tainha;
	Tenente Escovinha;
	
	
	
	
	public Barbearia(int tempoSono) {
		Escovinha = new Tenente(cadeiras);
		RecrutaZero = new Barbeiro();
		Dentinho = new Barbeiro();
		Otto = new Barbeiro();
		Tainha = new Sargento(tempoSono, cadeiras, proxClientes);
	}
	
	//Função de ler os clietes
	
	//1 Barbeiro;
	public void casoA() {
		while(Tainha.tentativas < 3) {
			
		}
		
	}
	
	//2 Barbeiros;
	public void casoB() {
		while(Tainha.tentativas < 3) {
			
		}	
		
	}
	
	//3 barbeiros, 1 para cada tipo de cliente;
	public void casoC() {
		while(Tainha.tentativas < 3) {
			
		}
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






