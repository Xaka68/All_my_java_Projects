package pgdp.pengusurvivors;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import pgdp.pengusurvivors.FlowGraph.Edge;
import pgdp.pengusurvivors.FlowGraph.Vertex;

public class FlowGraph {

	private Set<Vertex> vertices;
	private Vertex s;
	private Vertex t;

	public FlowGraph() {
		vertices = new HashSet<>();
	}

	/**
	 * Adds a new Vertex to the FlowGraph and returns the corresponding Object.
	 * 
	 * @return new Vertex
	 */
	public Vertex addVertex() {
		return addVertex("");
	}

	/**
	 * Adds a new Vertex to the FlowGraph and returns the corresponding Object.
	 * 
	 * @param name label of the Vertex
	 * @return new Vertex
	 */
	public Vertex addVertex(String name) {
		Vertex v = new Vertex(name);
		vertices.add(v);
		return v;
	}

	/**
	 * Returns set of all vertices of the graph.
	 * 
	 * @return set of vertices
	 */
	public Set<Vertex> getVertices() {
		return vertices;
	}

	public Vertex getSource() {
		return s;
	}

	public void setSource(Vertex source) {
		s = source;
	}

	public Vertex getSink() {
		return t;
	}

	public void setSink(Vertex target) {
		t = target;
	}

	/**
	 * Computes a correct maximum flow assignment.
	 */
	public void computeMaxFlow() {
		generateResidualGraph();
		List<Vertex> augPath;
		while ((augPath = findPathInResidual()) != null) {
			int augFlow = calcAugmentingFlow(augPath);
			updateNetwork(augPath, augFlow);
		}
	}

	/**
	 * Computes the value of a maximum flow.
	 * 
	 * @return max flow value
	 */
	public int computeMaxFlowValue() {
		// TODO
		computeMaxFlow();
		List<Integer> list = new ArrayList();
		for (Vertex v : vertices) {
			List<Integer> list1 = new ArrayList();
			List<Integer> list2 = new ArrayList();
			v.neighbours.forEach((n, p) -> {
				list1.add(p.f);
			});
			v.precedent.forEach((n, p) -> {
				list2.add(p.f);
			});
			int a = list1.stream().mapToInt(n -> n).sum();
			int b = list2.stream().mapToInt(n -> n).sum();

			list.add(a);
			list.add(b);
		}
		return list.stream().mapToInt(n -> n).max().getAsInt();
	}

	/**
	 * Removes all edges of the residual graph.
	 */
	public void clearResidualGraph() {
		for (Vertex v : vertices) {
			v.residual.clear();
		}
	}

	public void fullPrecedent(Vertex f) {
		for (Vertex v : vertices) {
			if (v.neighbours.containsKey(f)) {
				f.precedent.put(f, v.neighbours.get(f));
			}
		}
	}

	/**
	 * Generates Edges of the corresponding residual graph.
	 */
	public void generateResidualGraph() {
		clearResidualGraph();
		// TODO

		for (Vertex v : vertices) {
			v.neighbours.forEach((k, s) -> {

				Edge p = new Edge(s.c - s.f);
				Edge q = new Edge(s.f);
				v.residual.put(k, p);
				k.residual.put(v, q);

			});
		}

	}

	/**
	 * Returns a path from source to sink (in the residual graph) with positive
	 * capacities. Null if no such path exists.
	 * 
	 * @return s-t path in the residual graph with positive edge capacities.
	 */
	public List<Vertex> findPathInResidual() {
		// TODO
		List<Vertex> list = new ArrayList();
		list.add(s);
		s.visited = true;
		while (!list.isEmpty()) {

		}

	}

	/**
	 * Returns the max. value of an augmenting flow along the given path.
	 * 
	 * @param path s-t-path in the residual network
	 * @return max. value of an augmenting flow along the given path
	 */
	public int calcAugmentingFlow(List<Vertex> path) {
		// TODO
		List<Integer> list = new ArrayList();

		for (int i = 0; i < path.size() - 1; i++) {
			Vertex s = path.get(i);
			Vertex l = path.get(i + 1);
			s.residual.forEach((k, v) -> {
				if (k == l) {
					list.add(v.c);

				}
			});
		}

		return list.stream().mapToInt(n -> n).min().getAsInt();
	}

	/**
	 * Updates the FlowGraph along the specified path by the given flow value.
	 * 
	 * @param path s-t-path in the residual network
	 * @param f    value of the augmenting flow along the given path
	 */
	public void updateNetwork(List<Vertex> path, int f) {
		// TODO
		if (vertices.containsAll(path)) {

			for (int i = 0; i < path.size() - 1; i++) {
				Vertex v = path.get(i);
				Vertex a = path.get(i + 1);
				if (v.neighbours.containsKey(a)) {
					v.neighbours.get(a).f += f;
				} else {

					a.neighbours.get(v).f -= f;
					if (a.neighbours.get(v).f < 0) {
						a.neighbours.get(v).f = 0;
					}

				}

			}

			generateResidualGraph();

		}

	}

	public static class Vertex {

		private static int id = 0;

		private final String label;
		private HashMap<Vertex, Edge> neighbours;
		private HashMap<Vertex, Edge> residual;
		private HashMap<Vertex, Edge> precedent;
		private boolean visited = false;

		public Vertex(String name) {
			label = "" + id++ + " - " + name;
			neighbours = new HashMap<>();
			residual = new HashMap<>();
			precedent = new HashMap<>();
		}

		public void addSingle(Vertex to) {
			addEdge(to, 1);
		}

		public Edge addEdge(Vertex to, int capacity) {
			neighbours.put(to, new Edge(capacity));
			return getEdge(to);
		}

		public Edge addResEdge(Vertex to, int capacity) {
			residual.put(to, new Edge(capacity));
			return getResEdge(to);
		}

		public boolean hasSuccessor(Vertex v) {
			return neighbours.keySet().contains(v);
		}

		public Set<Vertex> getSuccessors() {
			return neighbours.keySet();
		}

		public Set<Vertex> getResSuccessors() {
			return residual.keySet();
		}

		public Edge getEdge(Vertex to) {

			return neighbours.getOrDefault(to, null);

		}

		public Edge getResEdge(Vertex to) {

			return residual.getOrDefault(to, null);

		}

		@Override
		public String toString() {
			return "{ " + label + " : " + neighbours.entrySet().stream().map(entry -> {
				return entry.getKey().label + " - " + entry.getValue().toString();
			}).collect(Collectors.joining(", ")) + " }";
		}
	}

	public static class Edge {

		private int c; // capacity
		private int f; // flow

		/**
		 * Initialize active edge with capacity c=0 and no flow.
		 */
		public Edge() {
			this(0);
		}

		/**
		 * Initialize active edge with capacity c and no flow.
		 * 
		 * @param c capacity of the edge
		 */
		public Edge(int c) {
			this.c = c;
			f = 0;
		}

		public int getFlow() {
			return f;
		}

		public int getCapacity() {
			return c;
		}

		@Override
		public String toString() {
			return "c = " + c + " f = " + f;
		}
	}

	public static void main(String[] args) {
		Vertex a = new Vertex("a");
		Vertex b = new Vertex("b");
		Vertex c = new Vertex("c");
		Vertex d = new Vertex("d");
		Vertex e = new Vertex("e");
		FlowGraph graph = new FlowGraph();

		a.neighbours.put(b, new Edge(3));
		a.neighbours.get(b).f = 1;
		a.neighbours.put(c, new Edge(2));
		a.neighbours.get(c).f = 5;
		b.neighbours.put(c, new Edge(4));
		b.neighbours.get(c).f = 2;
		c.neighbours.put(d, new Edge(5));
		c.neighbours.get(d).f = 1;
		graph.s = a;
		graph.t = d;
		graph.vertices.add(a);
		graph.vertices.add(b);
		graph.vertices.add(c);
		graph.vertices.add(d);
		List<Vertex> path = new ArrayList();
		path.add(d);
		path.add(c);
		path.add(a);

		System.out.println(graph.vertices);
		graph.updateNetwork(path, 4);
		System.out.println(graph.vertices);
	}
}
