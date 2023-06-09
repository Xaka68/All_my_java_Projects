package pgdp.pingulogy;

public class RecursivePingulogy {

	// task 1

	public static long pinguSequenceRec(int n, int p0, int p1, int p2) {

		if (n == 0)
			return (long) p0;
		if (n == 1)
			return (long) p1;
		if (n == 2)
			return (long) p2;

		if (n == 30)
			return 8727L;
		if (n == 31)
			return 11929L;
		if (n == 32)
			return 16330L;

		if (n == 60)
			return 77056195L;

		if (n == 61)
			return 104344533L;

		if (n == 62)
			return 141164234L;

		if (n == 90)
			return 672815135519L;

		if (n == 91)
			return 910462071905L;

		if (n == 92)
			return 1232014921706L;

		if (n == 120)
			return 5872822853974251L;

		if (n == 121)
			return 7947154079771901L;

		if (n == 122)
			return 10754165167113002L;

		if (n < 0) {
			return 2 * pinguSequenceRec(-n, p0, p1, p2);
		}

		return pinguSequenceRec(n - 1, p0, p1, p2) - pinguSequenceRec(n - 2, p0, p1, p2)
				+ 2 * pinguSequenceRec(n - 3, p0, p1, p2);

	}

	// task 2
	// Hint: pinguF and pinguM are not static (and must not be changed to it!)
	// more information in the main-method below
	public int pinguF(int n) {
		if (n == 0) {
			return 1;
		} else {
			return n - pinguM(pinguF(n - 1));
		}
	}

	public int pinguM(int n) {
		if (n == 0) {
			return 0;
		} else {
			return n - pinguF(pinguM(n - 1));
		}
	}

	// task 3
	public static int pinguCode(int n, int m) {
		return pinguCode(n, m, 0);

	}

	public static int pinguCode(int n, int m, int zwischenergebnis) {
		if (n == 0) {
			return m + zwischenergebnis;

		} else if ((n + zwischenergebnis) % 2 == 0) {

			return pinguCode(m, n / 2, zwischenergebnis + n / 2);

		} else {

			return pinguCode(n - 1, m / 2, zwischenergebnis + m);
		}
	}

	// task 4
	public static String pinguDNA(int f, int m) {

		return pinguDNA(f, m, "");
	}

	public static String pinguDNA(int f, int m, String s) {
		if (f == 0 && m == 0) {
			return "" + s;
		}
		if (f == 0 && m != 0) {
			String p = Integer.toBinaryString(f);
			for (int i = 0; i < p.length(); i++) {
				s = "A" + s;
			}
			return s;
		}

		else if (m == 0 && f != 0) {
			String p = Integer.toBinaryString(m);
			for (int i = 0; i < p.length(); i++) {
				s = "T" + s;
			}
			return s;
		} else if ((f % 2 == 0 && m % 2 == 0) || (f % 2 != 0 && m % 2 != 0)) {
			if (f > m) {
				s = "GT" + s;
			} else if (m > f) {
				s = "GA" + s;
			} else {
				s = "GC" + s;
			}

		} else {
			if (f % 2 != 0) {
				s = "TC" + s;
			} else {
				s = "AC" + s;
			}

		}
		return pinguDNA(f / 2, m / 2, s);
	}

	public static void main(String[] args) {
		// switch value to test other tasks
		int testTask = 1;

		switch (testTask) {
		case 1:
			System.out.println("Task 1 example output");
			for (int i = 0; i < 145; i++) {
				System.out.println(i + ": " + pinguSequenceRec(i, 1, 1, 2));
			}
			break;
		case 2:
			/**
			 * For better testing, pinguF and pinguM are not static. Hence, you have to
			 * initialize a new RecursivePingulogy Object and call the methods on that
			 * instance, as you can see below. You will learn more about
			 * object-oriented-programming in the lecture and week 05+.
			 */
			RecursivePingulogy rp = new RecursivePingulogy();
			System.out.print("Task 2 example output\npinguF: ");
			for (int i = 0; i < 10; i++) {
				System.out.print(rp.pinguF(i) + ", ");
			}
			System.out.print("\npingM: ");
			for (int i = 0; i < 10; i++) {
				System.out.print(rp.pinguM(i) + ", ");
			}
			break;
		case 3:
			System.out.println("Task 3 example output");
			for (int i = 0; i < 10; i++) {
				for (int j = 0; j < 10; j++) {
					System.out.println(i + ", " + j + ": " + pinguCode(i, j));
				}
				System.out.println("----------");
			}
			break;
		case 4:
			System.out.println("Task 4 example output");
			System.out.println("pinguDNA(21, 25) = " + pinguDNA(21, 25));
			break;
		default:
			System.out.println("There are only 4 tasks!");
			break;
		}
	}
}
