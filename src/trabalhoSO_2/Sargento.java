package trabalhoSO_2;
import java.util.LinkedList;
import java.util.LinkedList.*;

public class Sargento extends Thread {
	double tempoSono; //em segundos
	LinkedList<Cliente> cadeiras_1 = new LinkedList<Cliente>();
	LinkedList<Cliente> cadeiras_2 = new LinkedList<Cliente>();
	LinkedList<Cliente> cadeiras_3 = new LinkedList<Cliente>();
	LinkedList<Cliente> proxClientes = new LinkedList<Cliente>();
	int tentativas;
	Sargento(double tempoSono, LinkedList<Cliente> cadeiras_1, LinkedList<Cliente> cadeiras_2, LinkedList<Cliente> cadeiras_3, LinkedList<Cliente> proxClientes) {
		this.tempoSono = tempoSono;
		this.cadeiras_1 = cadeiras_1;
		this.cadeiras_2 = cadeiras_2;
		this.cadeiras_3 = cadeiras_3;
		this.proxClientes = proxClientes;
	}
	
	/**
	 * dormir + adicionar cliente nas cadeiras.
	 */
	public void run() {
		try {LinkedList<Cliente> cadeiras = new LinkedList<Cliente>();	

			while (true) {
			sleep((long)(tempoSono*(1000)));
			Barbearia.semaphore.acquire();
			if (!proxClientes.isEmpty() && tentativas < 3) {
				if ((cadeiras_1.size() + cadeiras_2.size() + cadeiras_3.size()) < 20) {
					proxClientes.getFirst().resetTime();
					switch(proxClientes.getFirst().tipo) {
						case 0: tentativas ++;
						case 1:	cadeiras_1.add(proxClientes.getFirst()); tentativas = 0;
						case 2: cadeiras_2.add(proxClientes.getFirst()); tentativas = 0;
						case 3: cadeiras_3.add(proxClientes.getFirst()); tentativas = 0;
					}
					System.out.println("Tenente adicionou: " + proxClientes.getFirst().toString());
				}
				proxClientes.remove(0);	
			}
			Barbearia.semaphore.release();
			}
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}
}
