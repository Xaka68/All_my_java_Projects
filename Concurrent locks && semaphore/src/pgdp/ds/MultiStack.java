package pgdp.ds;

import java.util.concurrent.locks.*;

import java.util.concurrent.Semaphore;

public class MultiStack {
	public ReentrantReadWriteLock rw = new ReentrantReadWriteLock(true);
	private final Stack stacks;
	private Semaphore free;
	private Semaphore occupied;

	public MultiStack() {

		stacks = new Stack(1);

	}

	public void push(int val) {
		synchronized (this) {

			Lock lockWrite = rw.writeLock();
			try {
				lockWrite.lock();
				stacks.push(val);
			} finally {
				lockWrite.unlock();
			}
		}
	}

	public int pop() {
		synchronized (this) {

			if (stacks.isEmpty()) {
				Lock lockRead = rw.readLock();
				try {
					lockRead.lock();
				} finally {
					lockRead.unlock();
				}
				return Integer.MIN_VALUE;
			}
			Lock lockWrite = rw.writeLock();
			try {

				lockWrite.lock();

			} finally {
				lockWrite.unlock();
			}
			return stacks.pop();
		}
	}

	public int top() {
		synchronized (this) {

			Lock lockRead = rw.readLock();
			try {
				lockRead.lock();

				if (stacks.isEmpty()) {
					return Integer.MIN_VALUE;
				}
			} finally {
				lockRead.unlock();
			}
			return stacks.top();
		}
	}

	public int size() {
		synchronized (this) {
			{

			}
			Lock lockRead = rw.readLock();
			try {
				lockRead.lock();
				if (stacks.isEmpty()) {
					return 0;
				}
			} finally {
				lockRead.unlock();
			}
			return stacks.size();
		}
	}

	public int search(int element) {
		synchronized (this) {

			Lock lockRead = rw.readLock();
			try {
				lockRead.lock();
				if (stacks.isEmpty()) {
					return -1;
				}
			} finally {
				lockRead.unlock();
			}
			return stacks.search(element);
		}
	}

	@Override
	public String toString() {
		synchronized (this) {

			Lock lockRead = rw.readLock();
			try {
				lockRead.lock();
			} finally {
				lockRead.unlock();
			}
			return stacks.toString();
		}
	}
}
