package com.github.rhettcaptain.sort;

import java.util.List;

public class MergeSorter<T extends Comparable> implements Sorter<T> {
    @Override
    public void sort(List<T> list) {
        if (list.size() <= 1) {
            return;
        }
        List<T> left = list.subList(0, list.size() / 2);
        List<T> right = list.subList(list.size() / 2 , list.size());
        sort(left);
        sort(right);
        int li = 0;
        int ri = 0;
        for (int i = 0; i < list.size(); i++) {
            if (li >= left.size()) {
                list.set(i, right.get(ri++));
            } else if (ri >= right.size()) {
                list.set(i, left.get(li++));
            } else if (left.get(li).compareTo(right.get(ri)) < 0) {
                list.set(i, left.get(li++));
            } else {
                list.set(i, right.get(ri++));
            }
        }
    }
}
