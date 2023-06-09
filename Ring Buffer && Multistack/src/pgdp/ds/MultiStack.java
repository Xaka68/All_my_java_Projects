package pgdp.ds;

public class MultiStack {

	private final Stack stacks;

	public MultiStack() {
		stacks = new Stack(1);
	}

	public void push(int value) {
		stacks.push(value);
	}

	public int top() {
		if (stacks.isEmpty()) {
			return Integer.MIN_VALUE;
		}
		return stacks.top();

	}

	public void remove1(Stack stack) {
		Stack current = this.stacks;
		while (current != null) {
			if (current == stack) {
				current = current.getNext();
			}
			if (current != null)
				current = current.getNext();
		}
	}

	public int pop() {

		if (stacks.isEmpty()) {
			return Integer.MIN_VALUE;
		}
		if (stacks.getNext() != null) {
			return stacks.pop();
		}
		int a = stacks.getMem()[0];
		this.stacks.setTop(-1);
		return a;

	}

	@Override
	public String toString() {
		return stacks.toString();
	}

}
