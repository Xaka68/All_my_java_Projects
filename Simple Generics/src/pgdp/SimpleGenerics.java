package pgdp;

import java.lang.reflect.Array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public final class SimpleGenerics {

	private SimpleGenerics() {
		throw new UnsupportedOperationException();
	}

	/**
	 * Returns a String of the given Collection.
	 * 
	 * @param collection
	 * @return String representation of the collection
	 */
	public static String toString(Collection<?> collection) {
		// TODO

		Iterator<?> it = collection.iterator();
		String s = "{";
		if (it.hasNext())
			s = s + it.next();
		while (it.hasNext()) {
			s += ", " + it.next();
		}

		return s + "}";
	}

	/**
	 * Returns int array of collection.
	 * 
	 * @param collection
	 * @return int array
	 */
	public static int[] toIntArray(Collection<Integer> collection) {
		// TODO
		int[] a = new int[collection.size()];
		Iterator<Integer> it = collection.iterator();
		for (int i = 0; i < a.length; i++) {
			if (it.hasNext()) {
				a[i] = it.next();
			}
		}

		return a;
	}

	/**
	 * Generates an generic array of type T with the given length.
	 * 
	 * @param <T>
	 * @param clazz
	 * @param length
	 * @return reference to the generated generic array
	 */
	@SuppressWarnings("unchecked")
	public static <T> T[] generateGenericArray(Class<T> clazz, int length) {
		final T[] arr = (T[]) Array.newInstance(clazz, length);
		return arr;
	}

	/**
	 * Returns the given collection in a sorted array.
	 * 
	 * @param <T>
	 * @param clazz
	 * @param collection
	 * @param comparator dictates the order of the output
	 * @return array of type T
	 */
	public static <T> T[] specialSort(Class<T> clazz, Collection<T> collection, Comparator<T> comparator) {
		T[] arr = generateGenericArray(clazz, collection.size());

		Iterator<T> it = collection.iterator();
		for (int i = 0; i < arr.length; i++) {
			if (it.hasNext()) {
				arr[i] = it.next();
			}
		}
		// Sortierung
		for (int i = 0; i < arr.length; i++) {
			for (int j = i + 1; j < arr.length; j++) {
				if (comparator.compare(arr[i], arr[j]) > 0) {
					T a = arr[i];
					arr[i] = arr[j];
					arr[j] = a;
				}
			}
		}

		return arr;
	}

	/**
	 * Returns a collection of all elements that are contained by each Collection of
	 * collections. Collections of the input are not modified.
	 * 
	 * @param <T>
	 * @param collections not null, may not contain null values.
	 * @return intersection of all collections
	 */
	public static <T> Collection<T> intersection(Collection<T>[] collections) {
		// TODO
		if (collections.length == 0) {
			return new ArrayList();
		}
		if (collections.length == 1) {
			return collections[0];
		}

		boolean p = true;

		Collection<T> a = new ArrayList();

		for (T b : collections[0]) {
			p = false;
			for (int i = 1; i < collections.length; i++) {

				if (collections[i].contains(b)) {
					p = true;
				} else {
					p = false;
					break;
				}
			}
			if (p == true)
				a.add(b);

		}

		return a;
	}

	/**
	 * Returns the values stored in the map. Equivalent to map.values().
	 * 
	 * @param <K> key type
	 * @param <V> value type
	 * @param map
	 * @return set of values
	 */
	public static <K, V> Set<V> getValues(Map<K, V> map) {
		// TODO
		Set<V> set = new HashSet<>();
		for (K k : map.keySet()) {
			V v = map.get(k);
			if (!set.contains(v)) {
				set.add(v);
			}

		}

		return set;
	}

	public static void main(String... args) {
		List<Integer> l = Arrays.asList(1, 2, 3, 4);
		System.out.println(toString(l));

		System.out.print(Arrays.toString(toIntArray(l)));
		HashSet<Integer> s = new HashSet();
		for (int i = 0; i < 3; i++) {
			s.add(i);
		}
		HashSet<Integer> w = new HashSet();
		for (int i = 2; i < 5; i++) {
			w.add(i);
		}
		HashSet<Integer> o = new HashSet();
		for (int i = 3; i < 9; i++) {
			o.add(i);
		}

		HashSet[] a = new HashSet[] { s, w, o };
		System.out.println(intersection(a));
	}
}
