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

import java.lang.reflect.Array;
import java.util.StringJoiner;

/**
 *
 * @author ragilmore0
 */
public class ArrayUtil<E> implements IArrayUtil<E> {

    private final E elements[];
    private final int size;
    private int total = 0;

    public ArrayUtil(Class<E> element, int size) {
        this.elements = (E[]) Array.newInstance(element, size);
        this.size = size;
    }

    /**
     * Adds element to end.
     *
     * @param e
     * @return
     */
    @Override
    public boolean add(E e) {
        if (this.total == this.size) {
            return false;
        }
        this.elements[this.total] = e;
        this.total++;
        return true;
    }

    /**
     * Removes last element.
     *
     */
    @Override
    public void remove() {
        if (this.total == 0) {
            System.out.println("Nothing to remove");
        } else {
            this.elements[this.total - 1] = null;
            this.total--;
        }
    }

    /**
     * True if element is present in the Array.
     *
     * @param e
     * @return
     */
    @Override
    public boolean contains(E e) {
        if (this.total == 0) {
            return false;
        }
        for (int i = 0; i < this.total; i++) {
            if (this.elements[i] == e) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns the size of the array.
     *
     * @return
     */
    @Override
    public int size() {
        return this.total;
    }

    /**
     * Replaces element at index.
     *
     * @param index
     * @param e
     */
    @Override
    public void set(int index, E e) {
        if (index >= size) {
            System.out.println("Out of bounds!");
        } else {
            this.elements[index] = e;
        }
    }
    /**
     * Selection Sort
     * @param <E> 
     */
    @Override
    public <E extends Comparable<E>> void sort() {
        for (int i = 0; i < this.elements.length - 1; i++) {
            int low = i;
            for (int j = i + 1; j < this.elements.length; j++) {
                E itemOne = (E) this.elements[j];
                E itemTwo = (E) this.elements[low]; 
                if (itemOne.compareTo(itemTwo) <= 0) {
                    low = j;
                }
            }
            swap(this.elements, i, low);
        }
    }
    /**
     * Generic Swap Elements Method. 
     * @param <E>
     * @param a
     * @param i
     * @param j 
     */
    private <E> void swap(E[] a, int i, int j) {
        if (i != j) {
            E temp = a[i];
            a[i] = a[j];
            a[j] = temp;
        }
    }
    /**
     * Print all elements in the array;
     * @return 
     */
    @Override
    public String printMyArray() {
        StringJoiner sb = new StringJoiner(", ", "[", "]");
        for (int i = 0; i < this.size; i++) {
            sb.add(this.elements[i].toString());
        }
        return sb.toString();
    }

}
