package pgdp.ds;

import java.util.Arrays;

public class RingBuffer {

	private int[] mem;
	private int in;
	private int out;
	private int stored;

	RingBuffer(int capacity) {
		mem = new int[capacity];
		in = 0;
		out = 0;
		stored = 0;
	}

	// TODO implement missing methods
	// utiliser in-out pour derminer le nombre de valeurs stokees
	public boolean isEmpty() {
		boolean p = true;
		for (int i = 0; i < mem.length; i++) {
			if (mem[i] != 0) {
				p = false;
			}
		}
		return p;
	}

	public boolean isFull() {
		boolean p = true;
		for (int i = 0; i < mem.length; i++) {
			if (mem[i] == 0) {
				p = false;
			}
		}
		return p;
	}

	public boolean put(int value) {
		if (this.isFull()) {
			return false;
		}
		in = in % mem.length;
		mem[in] = value;
		in++;
		stored++;
		return true;

	}

	public int get() {
		if (this.isEmpty()) {
			return Integer.MIN_VALUE;
		}

		int a = mem[out];

		out++;

		out = out % mem.length;
		stored--;

		return a;

	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("RingBuffer := { capacity = ").append(mem.length).append(", out = ").append(out).append(", in = ")
				.append(in).append(", stored = ").append(stored).append(", mem = ").append(Arrays.toString(mem))
				.append(", buffer = [");
		if (!isEmpty()) {
			if (in >= 0 || in < mem.length) {
				int i = out;
				do {
					sb.append(mem[i]).append(", ");
					i = (i + 1) % mem.length;
				} while (i != in);
				sb.setLength(sb.length() - 2);
			} else {
				sb.append("Error: Field 'in' is <").append(in)
						.append(">, which is out of bounds for an array of length ").append(mem.length);
			}
		}
		sb.append("] }");
		return sb.toString();
	}
}
