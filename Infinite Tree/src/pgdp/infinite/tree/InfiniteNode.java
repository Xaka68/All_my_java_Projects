package pgdp.infinite.tree;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class InfiniteNode<T> {

	private final InfiniteTree<T> tree;
	private final InfiniteNode<T> parent;
	private final T value;

	private List<InfiniteNode<T>> list;

	private Iterator<T> it;

	public InfiniteNode(InfiniteTree<T> tree, T value, InfiniteNode<T> parent) {
		this.parent = parent;
		this.tree = tree;
		this.value = value;
		list = new ArrayList();
		it = tree.children.apply(value);

	}

	public T getValue() {
		return value;
	}

	public InfiniteNode<T> getParent() {
		return parent;
	}

	/**
	 * @return Gibt alle bisher berechneten Kinder des Knotens zurück.
	 */
	public List<InfiniteNode<T>> getChildren() {
		// TODO: Implementieren.

		return list;

	}

	/**
	 * Berechnet das nächste Kind des Knotens und gibt es zurück.
	 * 
	 * @return das neue Kind oder null, wenn es keine weiteren Kinder gibt.
	 */
	public InfiniteNode<T> calculateNextChild() {
		// TODO: Implementieren.

		if (it.hasNext()) {
			T a = it.next();
			InfiniteNode<T> b = new InfiniteNode(tree, a, this);
			list.add(b);
			return b;
		} else {
			return null;
		}
	}

	/**
	 * Berechnet alle Kinder des Knotens.
	 */
	public void calculateAllChildren() {
		// TODO: Implementieren.

		while (it.hasNext()) {
			T a = it.next();
			list.add(new InfiniteNode(tree, a, this));
		}
	}

	/**
	 * @return true, wenn alle Kinder berechnet wurden, false sonst.
	 */
	public boolean isFullyCalculated() {
		// TODO: Implementieren.
		if (it == null) {
			return false;
		}
		return !it.hasNext();
	}

	/**
	 * Setzt die gesamte Berechnung des Knotens zurück.
	 */
	public void resetChildren() {
		// TODO: Implementieren.
		list.clear();
		it = tree.children.apply(value);

	}

}
