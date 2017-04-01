/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mergesort;

import java.util.Arrays;

/**
 *
 * @author ragilmore0
 */
public class MergeSort {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int[] a = {5, 6, 8, 1, 3, 7, 2};
        System.out.println(Arrays.toString(a));
        a = mergeSort(a);
        System.out.println(Arrays.toString(a));

    }

    public static int[] mergeSort(int[] a) {
        if (a.length <= 1) {
            return a;
        }
        int mid = a.length / 2;
        int[] left = new int[mid];
        int[] right;
        if (a.length % 2 == 0) {
            right = new int[mid];
        } else {
            right = new int[mid + 1];
        }
        int[] result = new int[a.length];
        for (int i = 0; i < mid; i++) {
            left[i] = a[i];
        }
        int rightPos = 0;
        for (int i = mid; i < a.length; i++) {
            right[rightPos] = a[i];
            rightPos++;
        }
        left = mergeSort(left);
        right = mergeSort(right);
        result = merge(left, right);
        return result;
    }

    public static int[] merge(int[] left, int[] right) {
        int resultLength = left.length + right.length;
        int[] result = new int[resultLength];
        int posL = 0;
        int posR = 0;
        int posRes = 0;
        while (posL < left.length || posR < right.length) {
            if (posL < left.length && posR < right.length) {
                if (left[posL] <= right[posR]) {
                    //swap
                    result[posRes] = left[posL];
                    posL++;
                    posRes++;
                } else {
                    result[posRes] = right[posR];
                    posR++;
                    posRes++;
                }
            } else if (posL < left.length) {
                result[posRes] = left[posL];
                posL++;
                posRes++;
            } else if (posR < right.length) {
                result[posRes] = right[posR];
                posR++;
                posRes++;
            }
        }
        return result;
    }

}
