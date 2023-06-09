package pgdp.megamerge;

import java.util.Arrays;

public class MegaMergeSort {

	/**
	 * Sorts the array using mega merge sort with div splits
	 * 
	 * @param array the array to be sorted
	 * @param div   the split factor
	 * @return the sorted array
	 */
	protected int[] megaMergeSort(int[] array, int div) {
		return megaMergeSort(array, div, 0, array.length);
	}

	/**
	 * Sorts the array using mega merge sort with div splits in the defined range
	 * 
	 * @param array the array to be sorted
	 * @param div   the split factor
	 * @param from  the lower bound (inclusive)
	 * @param to    the upper bound (exclusive)
	 * @return the sorted array
	 */
	protected int[] megaMergeSort(int[] array, int div, int from, int to) {
		int length = to - from;
		if (length == 0) {
			return new int[] {};
		}

		int[] lang = new int[div];

		for (int i = 0; i < div; i++) {
			if (length % div != 0) {
				if (i < length % div) {
					lang[i] = length / div + 1;
				} else {
					lang[i] = length / div;
				}
			}

			else {
				lang[i] = length / div;
			}
		}

		int[][] tab = new int[div][];
		for (int i = 0; i < div; i++) {
			tab[i] = new int[lang[i]];
		}

		int k = 0;
		for (int i = 0; i < div; i++) {
			for (int j = 0; j < lang[i]; j++) {
				tab[i][j] = array[k++];
			}
		}
		if (array.length <= 1) {
			return array;
		}
		int[] nik = new int[div];
		nik[0] = lang[0];
		for (int i = 1; i < div; i++) {
			nik[i] = nik[i - 1] + lang[i];
		}
		int l = 1;
		int[][] s = new int[div][];

		s[0] = megaMergeSort(tab[0], div, from, from + lang[0]);
		for (int i = 1; i < div; i++) {

			s[i] = megaMergeSort(tab[i], div, from + nik[i - 1], from + nik[i]);

		}

		return merge(s, from, to);

	}

	/**
	 * Merges all arrays in the given range
	 * 
	 * @param arrays to be merged
	 * @param from   lower bound (inclusive)
	 * @param to     upper bound (exclusive)
	 * @return the merged array
	 */
	protected int[] merge(int[][] arrays, int from, int to) {
		if (to - from <= 0) {
			return new int[] {};
		}

		return merge(arrays[from], merge(arrays, from + 1, to));
	}

	/**
	 * Merges the given arrays into one
	 * 
	 * @param arr1 the first array
	 * @param arr2 the second array
	 * @return the resulting array
	 */
	protected int[] merge(int[] arr1, int[] arr2) {
		int[] array = new int[arr1.length + arr2.length];
		int i = 0, j = 0, k = 0;
		while (i < arr1.length && j < arr2.length) {
			if (arr1[i] > arr2[j]) {
				array[k++] = arr2[j++];
			} else {
				array[k++] = arr1[i++];
			}

		}
		while (i < arr1.length) {
			array[k++] = arr1[i++];
		}
		while (j < arr2.length) {
			array[k++] = arr2[j++];
		}

		return array;
	}

	public static void main(String[] args) {
		MegaMergeSort mms = new MegaMergeSort();
		int[] arr = new int[] { 1, 2, 6, 7, 4, 3, 8, 9, 0, 5 };
		int[] res = mms.megaMergeSort(arr, 4);
		System.out.println(Arrays.toString(res));
	}
}
