package trabalhoSO_2;

import java.util.LinkedList;

	int modo;
	long tempoAtendimento;

	LinkedList<Cliente> cadeiras_1 = new LinkedList<Cliente>();
	LinkedList<Cliente> cadeiras_2 = new LinkedList<Cliente>();
	LinkedList<Cliente> cadeiras_3 = new LinkedList<Cliente>();
	double atendimento_1 = 0;
	double atendimento_2 = 0;
	double atendimento_3 = 0;
	double atendCount_1 = 0;
	double atendCount_2 = 0;
	double atendCount_3 = 0;
	public boolean stop = true;

	
	public Barbeiro(LinkedList<Cliente> cadeiras_1, LinkedList<Cliente> cadeiras_2, LinkedList<Cliente> cadeiras_3, int modo) {
		this.cadeiras_1 = cadeiras_1;
		this.cadeiras_2 = cadeiras_2;
		this.cadeiras_3 = cadeiras_3;
		this.modo = modo;
	}
	
	/**
	 * cortar cabelo
	 * @param index
	 * @param cadeiras
	 */
	// tipos de cliente 1-oficial 2-sargento 3-cabo 0-pausa
	

	public void resetAtend() {
		atendimento_1 = 0;
		atendimento_2 = 0;
		atendimento_3 = 0;
		atendCount_1 = 0;
		atendCount_2 = 0;
		atendCount_3 = 0;
	}
	
	
	public double cutHair(Cliente cliente) {
		double random = Math.random();
		if (cliente == null || cliente.tipo == 0) {
			return 0;
		}
		
		switch (cliente.tipo) {
			case 1:
				atendimento_1 += (cliente.tempoServico);
				atendCount_1++;
				break;	
			case 2:
				atendimento_2 += (cliente.tempoServico);
				atendCount_2++;
				break;
			case 3:
				atendimento_3 += (cliente.tempoServico);
				atendCount_3++;
				break;
		}
		return cliente.tempoServico;
	}
	
	public synchronized Cliente selectClient() {
		/*IMPORTANTE!!!!! Dois Barbeiros NUNCA devem escolher os seus clientes ao mesmo tempo, essa funcao so deve ser liberada
		 * APOS o cliente alvo ser removido da lista.
		*/
			try {
			Barbearia.semaphore.acquire();
			int highestRank = 0;
			Cliente target;
			int ind = 0;
			switch (modo) {
			case 0:
				if (!cadeiras_3.isEmpty()) {
					target = cadeiras_3.pop();
					Barbearia.semaphore.release();
					return target;
				}
				if (!cadeiras_2.isEmpty()) {
					target = cadeiras_2.pop();
					Barbearia.semaphore.release();
					return target;
				}
				if (!cadeiras_1.isEmpty()) {
					target = cadeiras_1.pop();
					Barbearia.semaphore.release();
					return target;
				}
			case 1:
				if (!cadeiras_1.isEmpty()) {
					target = cadeiras_1.pop();
					Barbearia.semaphore.release();
					return target;
				}
				if (!cadeiras_3.isEmpty()) {
					target = cadeiras_3.pop();
					Barbearia.semaphore.release();
					return target;
				}
				if (!cadeiras_2.isEmpty()) {
					target = cadeiras_2.pop();
					Barbearia.semaphore.release();
					return target;
				}
		   	case 2:
		   		if (!cadeiras_2.isEmpty()) {
					target = cadeiras_2.pop();
					Barbearia.semaphore.release();
					return target;
				}
		   		if (!cadeiras_3.isEmpty()) {
					target = cadeiras_3.pop();
					Barbearia.semaphore.release();
					return target;
				}
		   		if (!cadeiras_1.isEmpty()) {
					target = cadeiras_1.pop();
					Barbearia.semaphore.release();
					return target;
				}
		   	default:  Barbearia.semaphore.release(); return null;
			}
			}
			catch(Exception e) {
				System.out.println(e + " at Barbeiro.java");
	
			}
			Barbearia.semaphore.release();
			return null;
	}
	
	public void run(){ // tell it to my heart tell me i'm the only one
		stop = false;
		try {
			while (!Sargento.stop && !(cadeiras_1.isEmpty() && cadeiras_2.isEmpty() && cadeiras_3.isEmpty())) {
				Cliente cliente = selectClient();
				if (cliente != null) {
					System.out.println("Barbeiro cortando " + cliente.toString());
					tempoAtendimento = (long) (cutHair(cliente) * 1000);
					sleep(tempoAtendimento);
				} 
			}
			stop = true;

		}
		catch (Exception e) {
			System.out.println(e + " at Barbeiro.run()");
		}
		stop = true;
	}
}
