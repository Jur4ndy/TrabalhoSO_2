package trabalhoSO_2;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//me mata pfv
		Barbearia Dorminhoco = new Barbearia(0.3);
		//home/ju/eclipse-workspace/TrabalhoSO_2/teste.txt
		Dorminhoco.getClientes(20);
		Dorminhoco.casoA();
		Dorminhoco.getClientes(30);
		Dorminhoco.casoB();
		Dorminhoco.getClientes(40);
		Dorminhoco.casoC();
	}
}