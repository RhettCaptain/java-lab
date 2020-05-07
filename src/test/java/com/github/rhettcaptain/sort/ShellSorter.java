package com.github.rhettcaptain.sort;

import java.util.List;

public class ShellSorter<T extends Comparable> implements Sorter<T> {
    @Override
    public void sort(List<T> list) {
        hibbardSort(list);
    }

    private void shellSort(List<T> list) {
        for (int gap = list.size() / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < list.size(); i += gap) {
                T tmp = list.get(i);
                int j = i;
                for (; j >= gap && list.get(j - gap).compareTo(tmp) > 0; j -= gap) {
                    list.set(j, list.get(j - gap));
                }
                list.set(j, tmp);
            }
        }
    }

    private void hibbardSort(List<T> list) {
        for (int gap = 1; gap < list.size(); gap = (gap + 1) * 2 - 1) {
            for (int i = gap; i < list.size(); i += gap) {
                T tmp = list.get(i);
                int j = i;
                for (; j >= gap && list.get(j - gap).compareTo(tmp) > 0; j -= gap) {
                    list.set(j, list.get(j - gap));
                }
                list.set(j, tmp);
            }
        }
    }
}
