package trabalhoSO_2;
import java.util.LinkedList;
import java.util.LinkedList.*;

public class Sargento extends Thread {
	double tempoSono; //em segundos
	LinkedList<Cliente> cadeiras;
	LinkedList<Cliente> proxClientes;
	int tentativas;
	Sargento(double tempoSono, LinkedList<Cliente> cadeiras, LinkedList<Cliente> proxClientes) {
		this.tempoSono = tempoSono;
		this.cadeiras = cadeiras;
		this.proxClientes = proxClientes;
	}
	
	/**
	 * dormir + adicionar cliente nas cadeiras.
	 */
	public void run() {
		try {
			wait((long)(tempoSono*(1000)));
			if (cadeiras.size() < 20 && proxClientes.isEmpty()) {
				cadeiras.add(proxClientes.getFirst());
				proxClientes.remove(0);
			}
		}
		catch(Exception e) {
			
		}
	}
}
