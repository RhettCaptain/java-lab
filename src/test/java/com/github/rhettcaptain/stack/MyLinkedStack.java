package com.github.rhettcaptain.stack;

public class MyLinkedStack<E> {
	public static void main(String[] args) {
		MyLinkedStack<Integer> stack = new MyLinkedStack<>();
		stack.push(1);
		stack.push(3);
		stack.pop();
		stack.push(2);
		stack.push(3);
		Integer pop;
		while((pop = stack.pop())!=null) {
			System.out.println(pop);
		}
	}
	
	private Node<E> top;

	public E pop() {
		if(top == null) {
			return null;
		}
		E res = top.item;
		top = top.prev;
		return res;
		
	}
	
	public void push(E item) {
		top = new Node<E>(item,top);
		
	}
	
	private static class Node<E> {
		E item;
		Node<E> prev;
		public Node(E item, Node<E> prev) {
			this.item = item;
			this.prev = prev;
		}
	}
}
