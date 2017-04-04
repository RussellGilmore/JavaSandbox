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
package random.implementations.numbertheory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author ragilmore0
 */
public class Numbers {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Numbers hw = new Numbers();
        //hw.linearCongruentialGenerator(5, 1, 2, 11);
        //hw.weirdLinearCongruentialGenerator(5, 6, 13);
        //hw.weirdLinearCongruentialGenerator(5, 7, 13);
        //hw.blumBlumShubRetarded(8, 437);
        //hw.fermatsTheorem(4, 412, 5);
        //System.out.println(hw.mod((int) Math.pow(3, 10), 11));
        //System.out.println(hw.mod(1, 11));
        hw.findAllPrimitiveRoots(25);
    }

    private int mod(int x, int y) {
        int result = x % y;
        if (result < 0) {
            result += y;
        }
        return result;
    }

    /**
     *
     * @param s The seed
     * @param a
     * @param c
     * @param m
     */
    public void linearCongruentialGenerator(int s, int a, int c, int m) {
        int size = 100;
        int[] storeRandoms = new int[size];
        for (int i = 0; i < size; i++) {
            int randomNumber = mod(((a * s) + c), m);
            s = randomNumber;
            storeRandoms[i] = randomNumber;
        }
        System.out.println(Arrays.toString(storeRandoms));

    }

    public void weirdLinearCongruentialGenerator(int s, int a, int m) {
        int size = 12;
        int[] storeRandoms = new int[size];
        for (int i = 0; i < size; i++) {
            int randomNumber = mod(((a * s)), m);
            System.out.println("mod((" + a + " * " + s + "), " + m + ")");
            storeRandoms[i] = randomNumber;
            s = randomNumber;
            //System.out.println(randomNumber);
        }
        System.out.println(Arrays.toString(storeRandoms));

    }

    public void blumBlumShub(int s, int m) {
        int size = 5;
        int[] storeRandoms = new int[size];

        for (int i = 0; i < size; i++) {
            int firstMod = mod(mod(((int) Math.pow(s, 2)), m), 2);
            int randomNumber = mod(firstMod, 2);
            System.out.println("mod(mod(" + "mod(" + s + "^2 ," + m + "),2),2)");

            storeRandoms[i] = randomNumber;
            s = (int) Math.pow(s, 2);
        }
        System.out.println(Arrays.toString(storeRandoms));

    }

    public void fermatsTheorem(int a, int power, int mod) {
        int value = mod((int) Math.pow(a, (power)), mod);
        System.out.println(value);
    }
    
    public void findAllPrimitiveRoots(int prime){
        ArrayList<Integer> primitives = new ArrayList<>();
        ArrayList<Integer> confirmedPrimitives = new ArrayList<>();
        Set<Integer> duplicates = new HashSet<>();
        //int range = prime - 1;
        for(int i = 2; i < prime; i++){
            for(int power = 1; power < prime; power++){
                primitives.add(mod((int) Math.pow(i, power),prime));
            }
            duplicates.addAll(primitives);
            if(duplicates.size() == primitives.size()){
                confirmedPrimitives.add(i);
            }
            System.out.println(primitives.size() + " " + duplicates.size());
            primitives.clear();
            duplicates.clear();
            
        }
        int[] returnPrim = new int[confirmedPrimitives.size()];
        for(int i = 0; i < confirmedPrimitives.size(); i++){
            returnPrim[i] = confirmedPrimitives.get(i);
        }
        System.out.println(Arrays.toString(returnPrim));
        
        
    }
}
