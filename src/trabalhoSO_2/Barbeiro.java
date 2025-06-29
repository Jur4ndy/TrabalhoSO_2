package trabalhoSO_2;

import java.util.LinkedList;

public class Barbeiro extends Thread {
	int cliente;
	long tempoAtendimento;
	LinkedList clientes;
	
	public Barbeiro() {
	}
	
	/**
	 * cortar cabelo
	 * @param index
	 * @param cadeiras
	 */
	// tipos de cliente 1-oficial 2-sargento 3-cabo 0-pausa
	
	public double cutHair(Cliente cliente) {
		double random = Math.random();
		
		switch (cliente.tipo) {
			case 1:
				return (random*(2000) + 4000);
			case 2:
				return (random*(2000) + 2000);
			case 3:
				return (random*(2000) + 1000);
			default:
				return 0;
		}
	}
	
	
	public void run(Cliente cliente){ // tell it to my heart tell me i'm the only one
		try {
			tempoAtendimento = (long) cutHair(cliente);
			wait(tempoAtendimento);
		}
		catch (Exception e) {
			System.out.println(e + " at Barbeiro.run()");
		}
	}
}
