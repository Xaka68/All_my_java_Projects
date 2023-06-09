package pgdp.ds;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Stack {
	private Stack next;
	private Stack prev;
	private final int[] mem;
	private int top;

	public Stack(int capacity) {
		next = null;
		prev = null;
		mem = new int[capacity];
		top = -1;
	}

	// TODO implement missing methods
	public boolean isFull() {
		return top >= mem.length - 1;
	}

	public boolean isEmpty() {
		if (this == null) {
			return true;
		}
		return top < 0;
	}

	public void remove() {
		if (this.prev != null)
			this.prev.next = this.next;
	}

	public int pop() {

		if (!this.next.isFull()) {
			int a = this.next.mem[this.next.top];
			this.next.top--;
			if (this.next.top < 0) {

				this.next = null;
			}

			return a;
		} else {
			if ((this.next.next != null)) {

				return this.next.pop();

			}

			else {
				int t = this.next.mem[this.next.top];
				this.next.top--;
				return t;
			}

		}

	}

	public int top() {
		if (!this.isFull()) {
			return this.mem[this.top];
		} else {
			if (this.next != null) {
				return this.next.top();
			} else {
				return this.mem[this.top];
			}
		}

	}

	public void push(int value) {

		if (!this.isFull()) {
			top = top + 1;
			mem[top] = value;
		} else {

			if (this.getNext() != null) {
				this.getNext().push(value);
			} else {
				Stack stack = new Stack(2 * this.mem.length);
				this.next = stack;
				stack.prev = this;
				stack.next = null;
				stack.push(value);

			}
		}

	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("{\ncapacity = ").append(mem.length).append(",\n");
		sb.append("mem = [")
				.append(IntStream.range(0, top + 1).mapToObj(x -> "" + mem[x]).collect(Collectors.joining(", ")))
				.append("],\n");
		sb.append("next = ").append(next == null ? "null" : "\n" + next.toString()).append("\n}\n");
		return sb.toString();
	}

	public Stack getNext() {
		return next;
	}

	public void setNext(Stack next) {
		this.next = next;
	}

	public int getTop() {
		return top;
	}

	public void setTop(int top) {
		this.top = top;
	}

	public int[] getMem() {
		return mem;
	}

	public Stack getPrev() {
		return prev;
	}

	public void setPrev(Stack prev) {
		this.prev = prev;
	}

}
