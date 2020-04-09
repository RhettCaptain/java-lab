package com.github.rhettcaptain.queue;

public class MyArrayQueue<E> {
	private int size;
	private Object[] items;
	private int front = -1;
	private int rear = -1;

	public static void main(String[] args) {
		MyArrayQueue<Integer> queue = new MyArrayQueue<>(3);
		for(int i=0;i<5; i++) {
			queue.enqueue(i);
		}
		for(int i=0;i<5;i++) {
			System.out.println(queue.dequeue());
		}
		for(int i=0;i<5; i++) {
			queue.enqueue(i);
		}
		for(int i=0;i<5;i++) {
			System.out.println(queue.dequeue());
		}
	}
	
	public MyArrayQueue(int size) {
		this.size = size;
		items = new Object[size];
	}
	
	public E dequeue() {
		if(front == -1) {
			System.out.println("downflow");
			return null;
		}
		E res = (E)items[front];
		if(front == rear) {
			front = -1;
			rear = -1;
		}else {
			front++;
		}
		return res;
	}
	
	public boolean enqueue(E item) {
		if(front == -1) {
			front = 0;
			rear = 0;
			items[front] = item;
			return true;
		}
		int nextRear = (rear+1)%size;
		if(nextRear == front) {
			System.out.println("overflow");
			return false;
		}else {
			rear = nextRear;
			items[rear] = item;
			return true;
		}
	}
}
