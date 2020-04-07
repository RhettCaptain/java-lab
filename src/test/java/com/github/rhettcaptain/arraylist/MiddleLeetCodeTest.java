package com.github.rhettcaptain.arraylist;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Test;

public class MiddleLeetCodeTest {
	public void containerWithMostWater0011() {
		
	}
	
	private int containerWithMostWater0011S1(int[] height) {
		int i=0;
		int j = height.length-1;
		int maxArea = 0;
		while(i<j) {
			boolean leftShort = height[i]<height[j];
			int area = (j-i)* (leftShort ? height[i] : height[j]);
			if(area>maxArea) {
				maxArea = area;
			}
			if(leftShort) {
				i++;
			}else {
				j--;
			}
		}
		return maxArea;
	}
	
	@Test
	public void sum0015() {
		System.out.println(sum0015S1(new int[3]));
	}
	
	private List<List<Integer>> sum0015S1(int[] nums){
		List<List<Integer>> resList = new ArrayList<>();

		return resList;
	}
	

}
