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

/**
 *
 * @author ragilmore0
 */
public class GenericArrayStack<E> implements GenericStack<E> {
    private final E elements[];
    private final int size;
    private int top;
    
    
    
    public GenericArrayStack(Class<E> element, int size){
        this.size = size;
        top = -1;
        elements = (E[]) Array.newInstance(element, size);
        
        
    }

    @Override
    public void push(E e) {
        if(top < size - 1){
            elements[++top] = e;
        }

    }

    @Override
    public E pop() {
        E popElement = null;
        if(top >= 0){
            popElement = elements[top--];
        }
        return popElement;
    }

    @Override
    public E peek() {
        return elements[top];
    }

}
