package pgdp.ds;

public class SparseGraph implements Graph {
	private int knotens;
	private int kanten;
	private int[][] matrix;

	public SparseGraph(int nodes) {
		knotens = nodes;
		kanten = 0;
		if (nodes >= 0)
			matrix = new int[nodes][nodes];

	}

	@Override
	public int getNumberOfNodes() {
		if (knotens < 0) {
			return 0;
		}
		return knotens;
	}

	@Override
	public void addEdge(int from, int to) {
		if (from >= 0 && to >= 0) {
			if (from < knotens && to < knotens) {
				if (!isAdj(from, to)) {
					matrix[from][to] = 1;
				}
			}

		}

	}

	@Override
	public boolean isAdj(int from, int to) {
		boolean p = false;
		if (to >= knotens || to < 0 || from >= knotens || from < 0) {
			p = false;
		} else if (matrix[from][to] == 1) {
			p = true;

		}

		return p;
	}

	@Override
	public int[] getAdj(int id) {
		if (id >= knotens || id < 0) {
			return null;
		}

		SimpleSet nachbarn = new SimpleSet();

		for (int i = 0; i < knotens; i++) {
			if (matrix[id][i] == 1) {

				nachbarn.add(i);

			}

		}

		return nachbarn.toArray();
	}

}
