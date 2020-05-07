package com.github.rhettcaptain.heap;

public interface MinHeap<E> {
    void insert(E item);
    E deleteMin();
    E delete(int idx);
}
