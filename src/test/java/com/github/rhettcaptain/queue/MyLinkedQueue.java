package com.github.rhettcaptain.queue;

import java.util.LinkedList;

public class MyLinkedQueue<E> {
	private Node<E> front;
	private Node<E> rear;
	
	public static void main(String[] args) {
		MyLinkedQueue<Integer> queue = new MyLinkedQueue<Integer>();
		queue.dequeue();
		queue.enqueue(0);
		queue.dequeue();
		queue.dequeue();
		for(int i=0;i<10;i++) {
			queue.enqueue(i);
		}
		queue.dequeue();
		for(int i=10;i<20; i++) {
			queue.enqueue(i);
		}
		Integer res;
		while((res = queue.dequeue()) != null) {
			System.out.println(res);
		}
		LinkedList<Integer> list = new LinkedList<>();
		list.add(1);
	}
	
	public void enqueue(E item) {
		if(rear == null) {
			Node<E> node = new Node<E>(item, null);
			front = node;
			rear = node;
		}else {
			rear.next = new Node<E>(item, null);
			rear = rear.next;
		}
	}
	
	public E dequeue() {
		if(front == null) {
			return null;
		}
		E res = front.item;
		front = front.next;
		if(front == null) {
			rear = null;
		}
		return res;
	}
	
	private static class Node<E> {
		E item;
		Node<E> next;
		public Node(E item, Node<E> next) {
			this.item = item;
			this.next = next;
		}
	}
}
