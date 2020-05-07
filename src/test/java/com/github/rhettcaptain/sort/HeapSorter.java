package com.github.rhettcaptain.sort;

import com.github.rhettcaptain.heap.BinMinHeap;

import java.util.List;

public class HeapSorter<T extends Comparable> implements Sorter<T> {
    @Override
    public void sort(List<T> list) {
        BinMinHeap<T> minHeap = new BinMinHeap<>();
        list.stream().forEach(item -> minHeap.insert(item));
        for(int i=0; i<list.size(); i++){
            list.set(i, minHeap.deleteMin());
        }
    }
}
