package trabalhoSO_2;

public class Tenente extends Thread{
	/*● Estado de ocupação da(s) cadeira(s) (% por categoria e livre)
	● Comprimento médio das filas
	● Tempo médio de atendimento por categoria
	● Tempo médio de espera por categoria*/
	double percent_0 = 0; //free
	double percent_1 = 0; //rank 1	
	double percentage_2 = 0; //rank 2
	double percentage_3= 0; //rank_3
	//percentage lenght can be calculated by adding up percentages 1-3
	int size = 0;
	int time = 0;
	
	int reportCounter = 0;
}
