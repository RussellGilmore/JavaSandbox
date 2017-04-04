/*
 * The MIT License
 *
 * Copyright 2016 russell.
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
package random.implementations.fun;

import java.util.Scanner;

/**
 *
 * @author Russell Converts a single word into pig Latin.
 */
public class PigLatin {

    private final Scanner scan;
    private final char[] vowels;
    private final String conEnd = "ay";
    private final String vowStart = "yay";
    private String input;

    public PigLatin() {
        scan = new Scanner(System.in);
        //Never y. 
        vowels = new char[]{'a', 'e', 'i', 'o', 'u'};

    }

    /**
     * Traverses through the word and checks for the first vowel. Takes in a
     * user inputed word.
     */
    public void makePig() {
        System.out.println("Please enter a word!");
        input = scan.nextLine();
        input = input.trim();
        input = input.toLowerCase();

        for (int i = 0; i <= input.length(); i++) {
            for (int j = 0; j < vowels.length; j++) {
                if (input.charAt(i) == vowels[j]) {
                    String sub = input.substring(0, i);
                    String remainder = input.substring(i, input.length());

                    if (i == 0) {
                        System.out.println(input + vowStart);
                        i = input.length();
                        break;

                    } else {
                        System.out.println(remainder + sub + conEnd);
                        i = input.length();
                        break;
                    }

                }
            }
        }
    }
}
