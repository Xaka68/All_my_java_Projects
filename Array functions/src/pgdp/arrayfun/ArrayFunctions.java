package pgdp.arrayfun;

import java.util.Arrays;

public class ArrayFunctions {

	protected ArrayFunctions() {
		throw new IllegalStateException("Don't create objects of type 'ArrayFunctions'!");
	}

	public static void main(String[] args) {
		// example call

		int[] a = new int[] { 1, 2, 3, 4, 5 };
		rotate(a, 45);
		System.out.println(Arrays.toString(a));
	}

	/**
	 * Berechnet für das übergebene Array die Summe der Quadrate der Einträge. Gibt
	 * dabei einen Fehler aus und -1 zurück, wenn ein Overflow entsteht.
	 *
	 * @param array Ein beliebiges Integer-Array.
	 * @return Die Summe der Quadrate, wenn diese in einen 'long' passt, -1 sonst.
	 */
	public static long sumOfSquares(int[] array) {
		long s = 0;

		if (array.length == 0) {
			return 0;
		}

		else {
			for (int i = 0; i < array.length; i++) {
				if (s + (long) (array[i]) * (long) (array[i]) > 0
						&& s + (long) (array[i]) * (long) (array[i]) <= Long.MAX_VALUE) {
					s += (long) (array[i]) * (long) array[i];

				} else {

					System.out.println("Overflow!");
					return -1;

				}

			}

			return s;
		}

	}

	/**
	 * Methode, die zwei Arrays zu einem verbindet, indem sie abwechselnd Einträge
	 * des ersten und des zweiten Input- Arrays verwendet.
	 *
	 * @param a Ein beliebiges Integer-Array.
	 * @param b Ein beliebiges Integer-Array.
	 * @return 'a' und 'b' zusammengezipped.
	 */
	public static int[] zip(int[] a, int[] b) {
		if (a.length == 0 && b.length == 0) {
			return new int[] {};
		}
		int[] s = new int[a.length + b.length];
		if (a.length == b.length) {

			for (int i = 0; i < a.length; i++) {
				s[2 * i] = a[i];
				s[2 * i + 1] = b[i];

			}

		} else if (a.length < b.length) {
			for (int i = 0; i < a.length; i++) {
				s[2 * i] = a[i];
				s[2 * i + 1] = b[i];
			}
			for (int i = 2 * a.length; i < s.length; i++) {
				s[i] = b[a.length + (i - 2 * a.length)];
			}

		} else {
			for (int i = 0; i < b.length; i++) {
				s[2 * i] = a[i];
				s[2 * i + 1] = b[i];
			}
			for (int i = 2 * b.length; i < s.length; i++) {
				s[i] = a[b.length + (i - 2 * b.length)];
			}
		}
		return s;
	}

	/**
	 * Methode, die eine beliebige Zahl an Arrays (dargestellt als Array von Arrays)
	 * zu einem einzigen Array verbindet, indem sie abwechselnd von jedem Array
	 * einen Eintrag nimmt, bis alle aufgebraucht sind.
	 *
	 * @param arrays Array von Integer-Arrays
	 * @return Die Arrays in 'arrays' zusammengezipped
	 */
	public static int[] zipMany(int[][] arrays) {

		if (arrays.length == 0) {
			return new int[] {};
		}
		int lang = 0;
		for (int i = 0; i < arrays.length; i++) {
			lang = lang + arrays[i].length;
		}

		int[] s = new int[lang];
		int max = arrays[0].length;
		for (int i = 0; i < arrays.length; i++) {
			if (max < arrays[i].length) {
				max = arrays[i].length;
			}
		}

		int[][] a = new int[arrays.length][max];
		for (int i = 0; i < arrays.length; i++) {
			for (int j = 0; j < max; j++) {

				a[i][j] = Integer.MAX_VALUE - 1;
			}
		}
		for (int i = 0; i < arrays.length; i++) {
			for (int j = 0; j < arrays[i].length; j++) {
				a[i][j] = arrays[i][j];
			}
		}
		int lang1 = 0;
		for (int i = 0; i < a.length; i++) {
			lang1 = lang1 + a[i].length;
		}

		int[] tab = new int[lang1];

		for (int i = 0; i < a.length; i++) {

			for (int j = 0; j < max; j++) {

				tab[i + (j * arrays.length)] = a[i][j];

			}

		}
		for (int l = 0; l < lang1; l++) {
			for (int i = 0; i < lang1; i++) {
				if (tab[i] == Integer.MAX_VALUE - 1) {
					for (int k = i; k < lang1 - 1; k++) {
						tab[k] = tab[k + 1];
					}
					lang1--;
				}
			}
		}

		for (int i = 0; i < lang1; i++) {
			s[i] = tab[i];
		}
		return s;

	}

	/**
	 * Behält aus dem übergebenen Array nur die Einträge, die innerhalb der
	 * übergebenen Grenzen liegen. Gibt das Ergebnis als neues Array zurück.
	 *
	 * @param array Ein beliebiges Integer-Array
	 * @param min   Ein beliebiger Integer
	 * @param max   Ein beliebiger Integer
	 * @return Das gefilterte Array
	 */
	public static int[] filter(int[] array, int min, int max) {
		if (min > max) {
			return new int[] {};
		}
		int k = 0;
		int[] s = new int[array.length];
		for (int i = 0; i < array.length; i++) {
			if (array[i] <= max && array[i] >= min) {
				s[k] = array[i];
				k++;

			}

		}
		int[] m = new int[k];
		for (int j = 0; j < k; j++) {
			m[j] = s[j];
		}
		return m;
	}

	/**
	 * Rotiert das übergebene Array um die übergebene Anzahl an Schritten nach
	 * rechts. Das Array wird In-Place rotiert. Es gibt keine Rückgabe.
	 *
	 * @param array  Ein beliebiges Integer-Array
	 * @param amount Ein beliebiger Integer
	 */
	public static void rotate(int[] array, int amount) {
		if (amount == 0) {
			return;
		}
		if (amount < 0) {
			while (amount < 0) {
				amount = array.length + amount;

			}
		} else {
			while (amount > array.length) {
				amount = amount - array.length;
			}
		}

		int[] tem = new int[amount];
		int[] tem2 = new int[array.length - amount];
		for (int j = 0; j < amount; j++) {
			tem[j] = array[array.length - 1 - j];
		}
		for (int j = 0; j < array.length - amount; j++) {
			tem2[j] = array[j];
		}
		for (int i = 0; i < array.length; i++) {
			if (i + amount <= array.length - 1) {
				array[i + amount] = tem2[i];

			}
		}
		for (int i = 0; i < amount; i++) {
			array[amount - 1 - i] = tem[i];
		}
	}

	/**
	 * Zählt die Anzahl an Vorkommen jeder Zahl im übergebenen Array, die in diesem
	 * mindestens einmal vorkommt. Die Rückgabe erfolgt über ein 2D-Array, bei dem
	 * jedes innere Array aus zwei Einträgen besteht: Einer Zahl, die im übergebenen
	 * Array vorkommt sowie der Anzahl an Vorkommen dieser. Für jede im übergebenen
	 * Array vorkommenden Zahl gibt es ein solches inneres Array. Diese tauchen im
	 * Rückgabewert in der gleichen Reihenfolge auf, in der die jeweils ersten
	 * Vorkommen der Zahlen im übergebenen Array auftauchen.
	 *
	 * @param array Ein beliebiges Integer-Array
	 * @return Das Array mit den Vielfachheiten der einzelnen Zahlen, wiederum als
	 *         Integer-Arrays mit zwei Einträgen dargestellt.
	 */
	public static int[][] quantities(int[] array) {

		int lang = array.length;
		int[] s = new int[lang];
		for (int i = 0; i < lang; i++) {
			s[i] = array[i];
		}

		for (int i = 0; i < lang; i++) {

			for (int j = i + 1; j < lang;) {
				if (s[i] == s[j]) {
					for (int k = j; k < lang - 1; k++) {
						s[k] = s[k + 1];
					}
					lang--;
				} else {
					j++;
				}
			}
		}
		int[][] p = new int[lang][2];
		for (int j = 0; j < lang; j++) {
			p[j][0] = s[j];
		}
		for (int j = 0; j < lang; j++) {
			p[j][1] = 0;
		}

		for (int i = 0; i < lang; i++) {
			for (int j = 0; j < array.length; j++) {
				if (p[i][0] == array[j]) {
					p[i][1] = p[i][1] + 1;
				}
			}
		}

		return p;
	}
}
