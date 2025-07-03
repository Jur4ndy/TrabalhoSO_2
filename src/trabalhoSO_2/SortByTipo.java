package trabalhoSO_2;

import java.util.Comparator;

public class SortByTipo implements Comparator<Cliente> {
	public int compare(Cliente c1, Cliente c2) {
		return Integer.compare(c1.tipo, c2.tipo);
	}
}