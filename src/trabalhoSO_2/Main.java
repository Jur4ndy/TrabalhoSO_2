package trabalhoSO_2;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
    //me mata pfv
	Barbearia Dorminhoco = new Barbearia(3);
	Scanner scan = new Scanner(System.in);
	System.out.println("Enter .txt file link. \nTest File: home/ju/eclipse-workspace/TrabalhoSO_2/teste.txt/");
	String link = scan.nextLine();
	//home/ju/eclipse-workspace/TrabalhoSO_2/teste.txt
	Dorminhoco.getClientes(link);
	String wait = scan.nextLine();
	Dorminhoco.casoA();
	Dorminhoco.getClientes(link);
	Dorminhoco.casoB();
	Dorminhoco.getClientes(link);
	Dorminhoco.casoC();
	}

}
