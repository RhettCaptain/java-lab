package com.github.rhettcaptain.stack;

import java.util.Arrays;

public class MyArrayStack<E> {
	private static final int DEFAULT_SIZE = 16;
	private Object[] items;
	private int modCount;
	
	public static void main(String[] args) {
		MyArrayStack<Integer> arrayStack = new MyArrayStack<>();
		arrayStack.pop();
		for(int i=0;i<10;i++) {
			arrayStack.push(i);
		}
		arrayStack.pop();
		for(int i=9;i<20;i++) {
			arrayStack.push(i);
		}
		Integer res;
		while((res = arrayStack.pop()) != null) {
			System.out.println(res);
		}
	}
	
	public MyArrayStack() {
		this.items = new Object[DEFAULT_SIZE];
		this.modCount = -1;
	}
	
	public E pop() {
		E res = null;
		if(modCount >= 0) {
			res = (E)items[modCount];
			modCount--;
		}
		return res;
	}
	
	public void push(E item) {
		modCount++;
		if(modCount >= items.length) {
			grow();
		}
		items[modCount]=item;
	}
	private void grow() {
        // overflow-conscious code
        int oldCapacity = items.length;
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        // minCapacity is usually close to size, so this is a win:
        items = Arrays.copyOf(items, newCapacity);
    }
	
}
