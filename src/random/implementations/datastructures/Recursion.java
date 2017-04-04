/*
 * The MIT License
 *
 * Copyright 2016 Russell.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package random.implementations.datastructures;

import java.util.Random;

public class Recursion {

    private int[] numberList;
    private int[][] twoNumberList;
    private Random rand;
    private int[] binaryList = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};

    public Recursion() {
        numberList = new int[10];
        rand = new Random();
        twoNumberList = new int[10][10];
    }

    public void fillArray() {
        for (int i = 0; i < numberList.length; i++) {
            numberList[i] = rand.nextInt(11);
        }
    }

    public void fillTwoNumberList() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                twoNumberList[i][j] = j;
            }
        }
    }

    public void bubbleSort() {
        int n = numberList.length;
        int temp = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 1; j < (n - i); j++) {
                if (numberList[j - 1] > numberList[j]) {
                    //swap the elements
                    temp = numberList[j - 1];
                    numberList[j - 1] = numberList[j];
                    numberList[j] = temp;
                }
            }
        }
    }

    public boolean binarySearch(int key) {
        int low = 0;
        int high = binaryList.length - 1;

        while (high >= low) {
            int middle = (low + high) / 2;
            if (binaryList[middle] == key) {
                return true;
            }
            if (binaryList[middle] < key) {
                low = middle + 1;
            }
            if (binaryList[middle] > key) {
                high = middle - 1;
            }
        }
        return false;
    }

    public boolean binarySearch2(int[] data, int target, int low, int high) {
        if (low > high) {
            return false;
        } else {
            int mid = (low + high) / 2;
            if (target == data[mid]) {
                return true;
            } else if (target < data[mid]) {
                return binarySearch2(data, target, low, mid - 1);

            } else {
                return binarySearch2(data, target, mid + 1, high);
            }
        }

    }

    public void reverseArray(int arr[], int start, int end) {
        int temp;
        if (start >= end) {
            return;
        }
        temp = arr[start];
        arr[start] = arr[end];
        arr[end] = temp;
        reverseArray(arr, start + 1, end - 1);
    }

    public static void main(String[] args) {
        Recursion test = new Recursion();
        test.fillArray();
        int[] bullshit = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        boolean thing = test.binarySearch2(bullshit, 3, 0, 9);
        System.out.println(thing);

        test.fib(10);

    }

    public int fib(int n) {
        if (n <= 1) {
            return n;
        } else {
            return fib(n - 2) + fib(n - 1);
        }
    }
}
