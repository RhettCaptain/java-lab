package com.github.rhettcaptain.heap;

import org.junit.Assert;
import org.junit.Test;

import java.util.PriorityQueue;

public class HeapTest {
    @Test
    public void minHeapTest(){
        minHeapTest(new BinMinHeap<>());
        PriorityQueue<Integer> q = new PriorityQueue<>();
        q.add(1);
        q.add(2);
        q.add(0);
        System.out.println(q.peek());

    }

    private void minHeapTest(MinHeap<Integer> heap){
        for(int i=0; i<10; i++){
            heap.insert(i);
        }
        Assert.assertEquals(new Integer(0), heap.deleteMin());
        heap.delete(1);
        heap.delete(2);
        heap.insert(1);
        Assert.assertEquals(new Integer(1), heap.deleteMin());
    }
}
