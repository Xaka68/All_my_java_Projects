package pgdp.datastructures.lists;

import java.util.Arrays;

public class RecIntList {
	private RecIntListElement head;
	private int size;

	public RecIntList() {
		head = null;

	}

	public void append(int value) {
		if (head == null) {
			head = new RecIntListElement(value);
		} else {
			head.append(value);
		}
	}

	public void append1(RecIntListElement list) {
		if (head == null) {
			head = list;
		} else {
			head.append1(list);
		}
	}

	public int get(int idx) {
		if (head == null) {
			System.out.println("Invalid index: list is empty!");
			return Integer.MAX_VALUE;
		}
		return head.get(idx);
	}

	public int size() {
		/**
		 * can be rewritten as if(head==null) return 0; else return head.size();
		 */
		return head == null ? 0 : head.size();
	}

	public boolean insert(int value, int idx) {
		if (head == null) {
			if (idx == 0) {
				append(value);
				return true;
			} else {
				System.out.println("You may only insert at index 0 to a empty list!");
				return false;
			}
		}
		return head.insert(value, idx);
	}

	public boolean insert1(RecIntListElement list, int idx) {
		if (head == null) {
			if (idx == 0) {
				append1(list);
				return true;
			} else {
				System.out.println("You may only insert at index 0 to a empty list!");
				return false;
			}
		}
		return head.insert1(list, idx);
	}

	@Override
	public String toString() {
		if (head != null) {
			return "List: [" + head.toString() + "]";
		} else {
			return "Empty list";
		}
	}

	public String toConnectionString() {
		if (head != null) {
			return "List: [" + head.toConnectionString() + "]";
		} else {
			return "Empty list";
		}
	}

	public long[] countThresh(int threshold) {
		if (this.head == null) {
			return new long[] { 0, 0, 0 };
		}

		return this.head.countThresh(threshold, new long[] { 0, 0, 0 });

	}

	public void kinguinSort(boolean increasing) {
		if (increasing == true) {
			int a = Integer.MIN_VALUE;

			if (this.head != null) {
				a = this.head.getValue();
				if (this.head.getNext() != null) {
					this.head = this.head.getNext();
					this.head.setPrev(null);
				} else {
					this.head = null;

				}

			}

			if (this.head != null || (this.head == null && a != Integer.MIN_VALUE)) {
				if (this.head != null) {
					if (this.head.getValue() < a) {
						this.head = this.head.rem(a);
						if (this.head != null)
							this.head.setPrev(null);
					}
					if (this.head == null) {
						this.kinguinSort(increasing);
					}
					if (this.head != null && this.head.getNext() != null) {
						this.kinguinSort(increasing);
					}
				}
				this.insert(a, 0);
			}

		} else

		{
			int a = Integer.MIN_VALUE;

			if (this.head != null) {
				a = this.head.getValue();
				if (this.head.getNext() != null) {
					this.head = this.head.getNext();
					this.head.setPrev(null);
				} else {
					this.head = null;

				}

			}
			if (this.head != null || (this.head == null && a != Integer.MIN_VALUE)) {
				if (this.head != null) {
					if (this.head.getValue() > a) {
						this.head = this.head.rem2(a);
						if (this.head != null)
							this.head.setPrev(null);
					}
					if (this.head == null) {
						this.kinguinSort(increasing);
					}
					if (this.head != null && this.head.getNext() != null) {
						this.kinguinSort(increasing);
					}
				}
				this.insert(a, 0);
			}

		}

	}

	public void reverse() {
		if (this.head != null)
			this.head = this.head.reverse(head);

	}

	public static void zip(RecIntList l1, RecIntList l2) {
		if (l1.head != null) {
			l1.head.zip(l2.head);
		}
	}

	public static void main(String[] args) {
		// countThresh example
		RecIntList countThreshExample = new RecIntList();
		for (int i = 1; i <= 5; i++) {
			countThreshExample.append(i);
		}
		System.out.println(Arrays.toString(countThreshExample.countThresh(3)));
		RecIntListElement element = new RecIntListElement(9);
		countThreshExample.insert1(element, 0);
		System.out.println(countThreshExample);
		// kinguinSort example (1)
		RecIntList kinguinSortExample = new RecIntList();
		int[] kinguinSortvalues = new int[] { 3 };
		for (int i : kinguinSortvalues) {
			kinguinSortExample.append(i);
		}
		kinguinSortExample.kinguinSort(true); // false for example (2)
		System.out.println(kinguinSortExample);

		// reverse example
		RecIntList reverseExample = new RecIntList();
		for (int i = 1; i < 2; i++) {
			reverseExample.append(i);
		}
		reverseExample.reverse();
		System.out.println(reverseExample);

		// zip example
		RecIntList l1 = new RecIntList();
		RecIntList l2 = new RecIntList();
		for (int i = 1; i <= 5; i += 2) {
			l1.append(i);
			l2.append(i + 1);
		}
		l1.append(7);
		l1.append(8);

		RecIntList.zip(l1, l2);
		System.out.println(l1);
	}

	public RecIntListElement getHead() {
		return head;
	}

	public void setHead(RecIntListElement head) {
		this.head = head;
	}

}
