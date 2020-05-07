package com.github.rhettcaptain.sort;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SorterTest {
    @Test
    public void insertSortTest(){
        sorterTest(new InsertSorter<>());
    }

    @Test
    public void shellSortTest(){
        sorterTest(new ShellSorter<>());
    }

    @Test
    public void HeapSortTest(){
        sorterTest(new HeapSorter<>());
    }

    @Test
    public void MergeSortTest(){
        sorterTest(new MergeSorter<>());
    }

    private void sorterTest(Sorter<Integer> sorter){
        List<Integer> intList = new ArrayList<>();
        Random random = new Random();
        for(int i=0;i<1001;i++){
            intList.add(random.nextInt());
        }
        sorter.sort(intList);
        for(int i=0; i<1000; i++){
            Assert.assertTrue(intList.get(i) <= intList.get(i+1));
        }
    }
}
