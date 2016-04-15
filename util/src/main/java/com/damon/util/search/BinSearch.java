package com.damon.util.search;

import com.damon.util.sort.QuickSort;
import com.google.common.collect.Sets;

import java.util.Arrays;
import java.util.Set;

/**
 * 二分查找
 * Created by dongjun.wei on 16/4/4.
 */
public class BinSearch {

    public static int binSearch(int[] array, int low, int high, int key) {

        int left = low;
        int right = high;

        while (left <= right) {
            int mid = (left + right) >>> 1;
            int midVal = array[mid];

            if (midVal == key) {
                return mid;
            }else if (midVal < key) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] array = {23, 6, 57, 88, 60, 42, -83, 72, 48, 72};
        QuickSort.quickSort(array, 0, array.length - 1);
        System.out.println(Arrays.toString(array));
        System.out.println(binSearch(array, 0, array.length - 1, 88));
        System.out.println(Arrays.binarySearch(array, 0, array.length, 88));

        System.out.println(returnObject());
        System.out.println(returnSet().size());
    }

    public static String returnObject() {
        String str = "abc";

        try {
            return str;
        }finally {
            System.out.println("finally");
            str = "bcd";
        }
    }

    public static Set returnSet() {
        Set<String> set = Sets.newHashSet();

        try {
            return set;
        }finally {
            System.out.println("finally");
            set.add("aaa");
        }
    }


}
