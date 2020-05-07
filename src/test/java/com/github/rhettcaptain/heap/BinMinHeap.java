package com.github.rhettcaptain.heap;

import java.util.ArrayList;
import java.util.List;

public class BinMinHeap<E extends Comparable> implements MinHeap<E> {
    private List<E> nodeList;

    public BinMinHeap(){
        this.nodeList = new ArrayList<>();
        this.nodeList.add(null);
    }

    public BinMinHeap(E... es){
        this();
        for(E e : es){
            insert(e);
        }
    }

    @Override
    public void insert(E item) {
        int hole = nodeList.size();
        nodeList.add(null);
        for(; hole>1 && item.compareTo(nodeList.get(hole/2))<0; hole/=2){
            nodeList.set(hole, nodeList.get(hole/2));
        }
        nodeList.set(hole, item);
    }

    @Override
    public E deleteMin() {
        if(nodeList.size()<2){
            return null;
        }
        final E min = nodeList.get(1);
        E last = nodeList.remove(nodeList.size()-1);
        if(nodeList.size() == 1){
            return min;
        }
        nodeList.set(1,last);
        int hole = 1;
        while(hole * 2 < nodeList.size()){
            int leftChildIdx = hole*2;
            int rightChildIdx = leftChildIdx+1;
            int smallIdx = leftChildIdx;
            if(rightChildIdx < nodeList.size() && nodeList.get(leftChildIdx).compareTo(nodeList.get(rightChildIdx)) > 0){
                smallIdx = rightChildIdx;
            }
            E smallObj = nodeList.get(smallIdx);
            if(smallObj.compareTo(last) < 0){
                nodeList.set(hole, smallObj);
                nodeList.set(smallIdx, last);
                hole = smallIdx;
            }else{
                break;
            }
        }
        return min;
    }

    @Override
    public E delete(int idx) {
        E res = nodeList.get(idx);
        adjustKey(idx, (E)(Comparable<E>)o -> -1);
        deleteMin();
        return res;
    }

    private void adjustKey(int idx, E newKey){
        int hole = idx;
        int comp = newKey.compareTo(nodeList.get(hole));
        if(comp < 0){
            //percolate up
            for(; hole>1; hole/=2){
                if(newKey.compareTo(nodeList.get(hole)) < 0){
                    nodeList.set(hole, nodeList.get(hole/2));
                }else{
                    break;
                }
            }
            nodeList.set(hole, newKey);
        } else{
            //percolate down
            while(hole*2 < nodeList.size()){
                int leftChildIdx = hole * 2;
                int rightChildIdx = leftChildIdx + 1;
                int smallIdx = leftChildIdx;
                if(rightChildIdx < nodeList.size() && nodeList.get(rightChildIdx).compareTo(nodeList.get(leftChildIdx))<0){
                    smallIdx = rightChildIdx;
                }
                E smallObj = nodeList.get(smallIdx);
                if(smallObj.compareTo(newKey)<0){
                    nodeList.set(hole, smallObj);
                    hole *= 2;
                }else{
                    break;
                }
            }
            nodeList.set(hole, newKey);
        }
    }

}
