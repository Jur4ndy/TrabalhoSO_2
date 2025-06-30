package trabalhoSO_2;
import java.util.LinkedList;
import java.util.LinkedList.*;

public class Sargento extends Thread {
	double tempoSono; //em segundos
	LinkedList<Cliente> cadeiras = new LinkedList<Cliente>();
	LinkedList<Integer> proxClientes = new LinkedList<Integer>();
	int tentativas;
	Sargento(double tempoSono, LinkedList<Cliente> cadeiras, LinkedList<Integer> proxClientes) {
		this.tempoSono = tempoSono;
		this.cadeiras = cadeiras;
		this.proxClientes = proxClientes;
	}
	
	/**
	 * dormir + adicionar cliente nas cadeiras.
	 */
	public void run() {
		try {
			while (true) {
			wait((long)(tempoSono*(1000)));
			if (!proxClientes.isEmpty() && tentativas < 3) {
				if (cadeiras.size() < 20) cadeiras.add(new Cliente(proxClientes.getFirst()));
				if (proxClientes.getFirst() == 0) tentativas ++;
				proxClientes.remove(0);
			}
			}
		}
		catch(Exception e) {
			
		}
	}
}
