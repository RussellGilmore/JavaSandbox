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
import java.util.Scanner;
import java.lang.*;

/**
 *
 * @author ragilmore0
 */
public class TestCases {

    private final Random rand;
    private final Scanner scan;

    public TestCases() {
        rand = new Random();
        scan = new Scanner(System.in);
        
    }

    // hurr hurr python
    public void init() {
        ArrayUtil<Integer> potato = new ArrayUtil<>(Integer.class, 10);
        for (int i = 0; i < 10; i++) {
            potato.add(rand.nextInt(200));
        }
        String result = potato.printMyArray();
        System.out.println(result);
        potato.sort();
        result = potato.printMyArray();
        System.out.println(result);
        
    }

    public void creatIntCollection() {

    }

}
