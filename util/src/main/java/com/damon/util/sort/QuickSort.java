package com.damon.util.sort;

import java.util.Arrays;

/**
 * 快速排序 O(n*log(n))
 * 主要是分治的思想
 * 通过一趟排序将待排记录分割成独立的两部分，其中一部分记录的关键字均比另一部分记录的关键字小
 * 则可以对这两部分记录继续进行排序，以达到整个序列有序
 *
 * 选取枢轴时，一般可以选择第一个元素
 * http://blog.csdn.net/morewindows/article/details/6684558
 *
 * Created by dongjun.wei on 16/4/4.
 */
public class QuickSort {

    /**
     * 递归方式
     * @param array 待排序数组
     * @param low 起始位置
     * @param high 终止位置
     */
    public static void quickSort(int[] array, int low, int high) {

        if (low < high) { //这个判断条件不能少，跳出递归
            int left = low;
            int right = high;
            int key = array[low]; //选取第一个值作为最初的比较标杆

            while (left < right) {

                //right从后向前，找到第一个比key小的位置
                while (left < right && array[right] >= key) { //这里是大于或者等于，等于条件的存在可以省去相等情况下的交换
                    --right;
                }
                //把小的换到前面
                if (left < right) { //这个比较条件，可以省去最后left=right时的那次交换
                    array[left] = array[right];
                }

                //left从前向后，找到第一个比key大的位置
                while (left < right && array[left] <= key) {
                    ++left;
                }
                //把大的换到后面
                if (left < right) {
                    array[right] = array[left];
                }
            }

            array[left] = key;
            quickSort(array, low, left - 1);
            quickSort(array, right + 1, high);
        }

    }

    public static void main(String[] args) {
        int[] array = {23, 6, 57, 88, 60, 42, -83, 72, 48, 72};
        //int[] array = {1, 2, 3, 4, 5};
        quickSort(array, 0, array.length - 1);
        System.out.println(Arrays.toString(array));
    }
}
