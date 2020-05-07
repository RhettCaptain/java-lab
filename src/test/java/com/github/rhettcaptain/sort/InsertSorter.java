package com.github.rhettcaptain.sort;

import java.util.List;

public class InsertSorter<T extends Comparable> implements Sorter<T> {
    @Override
    public void sort(List<T> list) {
        for(int i=1; i<list.size(); i++){
            T tmp = list.get(i);
            int j=i;
            for(; j>0 && list.get(j-1).compareTo(tmp) > 0; j--){
                list.set(j,list.get(j-1));
            }
            list.set(j, tmp);
        }
    }
}
