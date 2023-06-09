package pgdp.ds;

public class DenseGraph implements Graph {
	private int nodes;
	private Knoten[] setKnoten;

	public DenseGraph(int nodes) {
		this.nodes = nodes;
		if (nodes >= 0)
			setKnoten = new Knoten[nodes];
		for (int i = 0; i < nodes; i++) {

			setKnoten[i] = new Knoten(i);

		}
	}

	@Override
	public int getNumberOfNodes() {
		if (nodes < 0) {
			return 0;
		}
		return nodes;
	}

	@Override
	public void addEdge(int from, int to) {
		boolean p = false, t = false;

		for (int i = 0; i < setKnoten.length; i++) {
			if (setKnoten[i].getId() == from) {
				p = true;
			}
			if (setKnoten[i].getId() == to) {
				t = true;
			}
		}
		if (p && t) {
			if (!setKnoten[from].getNachbarn().contains(to)) {
				setKnoten[from].getNachbarn().add(to);
			}
		}
	}

	@Override
	public boolean isAdj(int from, int to) {
		boolean p = false, t = false;

		for (int i = 0; i < setKnoten.length; i++) {
			if (setKnoten[i].getId() == from) {
				p = true;
			}
			if (setKnoten[i].getId() == to) {
				t = true;
			}
		}
		if (p && t) {
			return setKnoten[from].getNachbarn().contains(to);

		} else {
			return false;
		}

	}

	@Override
	public int[] getAdj(int id) {
		boolean p = false;
		for (int i = 0; i < setKnoten.length; i++) {
			if (setKnoten[i].getId() == id) {
				p = true;
			}

		}
		if (p == false) {
			return null;
		} else {
			return setKnoten[id].getNachbarn().toArray();
		}

	}

}
