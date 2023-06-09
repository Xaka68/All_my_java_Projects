package pgdp.datastructures.lists;

public class RecIntListElement {
	private int value;
	private RecIntListElement next;
	private RecIntListElement prev;

	public RecIntListElement(int value) {
		this(value, null);
	}

	public RecIntListElement(int value, RecIntListElement prev) {
		this.value = value;
		next = null;
		this.prev = prev;
	}

	public RecIntListElement append(int value) {
		if (next != null) {
			return next.append(value);
		} else {
			next = new RecIntListElement(value, this);
			return next;
		}
	}

	public RecIntListElement append1(RecIntListElement list) {
		if (next != null) {
			return next.append1(list);
		} else {
			list.prev = this;
			next = list;
			return next;
		}
	}

	public int get(int idx) {
		if (idx == 0) {
			return value;
		}
		if (next == null) {
			System.out.println("Invalid index: list is to short!");
			return Integer.MIN_VALUE;
		}
		return next.get(idx - 1);
	}

	public int size() {
		if (next == null) {
			return 1;
		}
		return 1 + next.size();
	}

	public boolean insert(int value, int idx) {
		if (idx < 0) {
			System.out.println("Cannot insert at negative index!");
			return false;
		}
		if (idx <= 1) {
			RecIntListElement n = new RecIntListElement(value, this);
			n.next = next;
			if (next != null) {
				next.prev = n;
			}
			next = n;
			if (idx == 0) {
				next.value = this.value;
				this.value = value;
			}
			return true;
		}
		if (next == null) {
			System.out.println("List is to short to insert at given index!");
			return false;
		}
		return next.insert(value, idx - 1);
	}

	public boolean insert1(RecIntListElement list, int idx) {
		int a = list.value;
		if (idx < 0) {
			System.out.println("Cannot insert at negative index!");
			return false;
		}
		if (idx <= 1) {
			list.prev = this;
			list.next = next;
			if (next != null) {
				next.prev = list;
			}
			next = list;
			if (idx == 0) {
				next.value = this.value;
				this.value = a;
			}
			return true;
		}
		if (next == null) {
			System.out.println("List is to short to insert at given index!");
			return false;
		}
		return next.insert1(list, idx - 1);
	}

	public RecIntListElement rem(int a) {

		if (this.value < a && ((this.next != null && this.next.value >= a) || this.next == null)) {
			return this.next;
		}
		if (this.value >= a) {
			return this;
		}

		return this.next.rem(a);

	}

	public RecIntListElement rem2(int a) {

		if (this.value > a && ((this.next != null && this.next.value <= a) || this.next == null)) {
			return this.next;
		}
		if (this.value <= a) {
			return this;
		}

		return this.next.rem2(a);

	}

	public long[] countThresh(int threshold, long[] tab) {
		if (this.next == null) {

			if (this.value < threshold) {
				tab[0] += this.value;
			} else if (this.value == threshold) {
				tab[1] += this.value;
			} else {
				tab[2] += this.value;
			}

			long[] tab1 = tab;

			return tab;
		}

		else {
			if (this.value < threshold) {
				tab[0] += this.value;
			} else if (this.value == threshold) {
				tab[1] += this.value;
			} else {
				tab[2] += this.value;
			}
			return this.next.countThresh(threshold, tab);
		}

	}

	public RecIntListElement reverse(RecIntListElement el) {

		RecIntListElement el1 = this;
		RecIntListElement el2 = el1.next;
		if (el2 != null)
			el2.prev = el1;
		if (el2 == null) {
			el = el1;
			el.prev = null;
			return el;

		}
		el = el2.reverse(el);
		el2.next = el1;
		el1.prev = el2;
		el1.next = null;

		return el;

	}

	public void zip1(RecIntListElement l2) {

		if (this.next != null) {
			l2.next = this.next;
			this.next = l2;
			l2.prev = this;

		}
	}

	public void zip(RecIntListElement l2) {
		if (l2 != null) {
			this.zip1(l2);

			if (l2.next != null && this.next != null)
				this.next.zip(l2.next);
		}
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		RecIntListElement tmp = this;
		do {
			sb.append(tmp.value).append(", ");
			tmp = tmp.next;
		} while (tmp != null);
		sb.setLength(sb.length() - 2);
		return sb.toString();
	}

	public String toConnectionString() {
		StringBuilder sb = new StringBuilder();
		RecIntListElement tmp = this;
		do {
			if (tmp.prev != null) {
				sb.append("<-");
			}
			sb.append(tmp.value);
			if (tmp.next != null) {
				sb.append("->");
			}
			tmp = tmp.next;
		} while (tmp != null);
		return sb.toString();
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public RecIntListElement getNext() {
		return next;
	}

	public void setNext(RecIntListElement next) {
		this.next = next;
	}

	public RecIntListElement getPrev() {
		return prev;
	}

	public void setPrev(RecIntListElement prev) {
		this.prev = prev;
	}

}
