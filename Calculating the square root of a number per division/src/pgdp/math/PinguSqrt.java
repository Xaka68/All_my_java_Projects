package pgdp.math;

public class PinguSqrt {

	public static void sqrt(double n) {
		if (n < 0) {
			System.out.println("Keine negativen Wurzeln!");
		} else {
			System.out.println("Wurzel aus " + n);
			long num = (long) (10000 * n);
			String s = String.valueOf(num);
			int lang = s.length();
			if (s.length() % 2 == 1) {
				lang = s.length() + 1;
			}

			Long[] T = new Long[lang / 2 + 1];
			long p = 1;
			T[0] = (long) 0;
			for (int i = 1; i <= (lang / 2); i++) {
				if (p > 0) {
					T[i] = ((num % (100 * p)) - num % p) / p;
					p = 100 * p;
				}
			}

			int j;
			int k = 0;
			long t, sqrt = 0, l = 1, a = 1, m = 0;

			t = T[lang / 2];

			for (int i = lang / 2; i >= 1; i--) {
				j = 0;
				k = 0;
				System.out.println("\n" + t);
				System.out.println("--------");

				while (t - l >= 0) {

					j++;// compteur de soustraction

					a = t - l; // Conserve la derniere valeur de t-l
					System.out.println("-" + (m + (2 * k + 1)));

					k++;

					l = l + m + (2 * k + 1);
				}

				sqrt = 10 * sqrt + j;
				t = 100 * a + T[i - 1];
				l = 2 * sqrt * 10 + 1;
				m = l - 1;

				System.out.println("--------");
				System.out.println("Rest: " + a);
				if (t < l) {
					a = t;
				}
				System.out.println("neue Ergebnis Ziffer: " + j);

			}
			System.out.println("\nErgebnis: " + ((double) sqrt) / 100);
		}
	}

	public static void main(String[] args) {
		// test your implementation here
		sqrt(1049.76);
		sqrt(0);

	}

}
