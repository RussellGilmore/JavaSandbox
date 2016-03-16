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
package random.implementations.ciphers;

/**
 *
 * @author Russell
 */
public class Caesar {

    public static void main(String[] args) {
        Caesar plain = new Caesar();

        plain.makeKey();

    }

    public void setUp(int shift) {
        String ciphertext = "wdlpgtndj";
        char[] answer = new char[ciphertext.length()];

        for (int pos = 0; pos < ciphertext.length(); pos++) {
            int i = ciphertext.charAt(pos) - 'a';
            int rotate = (i - shift + 26) % 26;
            answer[pos] = (char) (rotate + 'a');
        }

        String plaintext = new String(answer);
        System.out.println("The key is " + shift);
        System.out.println(plaintext);
    }

    public void vigenereDo() {
        String plaintext = "sendmoremoney";
        char[] answer = new char[plaintext.length()];
        char[] alphabet = "abcdefghijklmnopqrstuvwxyz".toCharArray();

        int[] key = {9, 0, 1, 7, 23, 15, 21, 14, 11, 11, 2, 8, 9};
        for (int pos = 0; pos < answer.length; pos++) {
            int value = findLetter(plaintext.charAt(pos));
            if (value + key[pos] >= 26) {
                int shift = (value + key[pos]) % 26;
                answer[pos] = alphabet[shift];
            } else {
                answer[pos] = alphabet[value + key[pos]];
            }

        }
        String ciphertext = new String(answer);
        System.out.println(ciphertext);

    }

    public int findLetter(char letter) {
        char[] alphabet = "abcdefghijklmnopqrstuvwxyz".toCharArray();
        int value = 0;
        for (int i = 0; i < alphabet.length; i++) {
            if (alphabet[i] == letter) {
                value = i;
                break;
            }
        }
        return value;

    }

    public void makeKey() {
        String ciphertext = "beokjdmsxzpmh";
        String plaintext = "cashnotneeded";
        int[] key = new int[plaintext.length()];
        for (int i = 0; i < key.length; i++) {
            int cipherValue = findLetter(ciphertext.charAt(i));
            int plainValue = findLetter(plaintext.charAt(i));

            if (cipherValue < plainValue) {
                key[i] = plainValue - cipherValue;

            } else if (cipherValue > plainValue) {
                key[i] = (-1 * (cipherValue - 26) + plainValue);

            } else {
                key[i] = plainValue;
            }
        }
        for (int i = 0; i < key.length; i++) {
            key[i] = mod((-1 * key[i]), 26);

        }
        for (int i = 0; i < key.length; i++) {

            System.out.print(key[i] + " ");
        }

    }

    private int mod(int x, int y) {
        int result = x % y;
        if (result < 0) {
            result += y;
        }
        return result;
    }

    /**
     * Shit Bad
     */
    public void setUp2(int shift) {
        String ciphertext = "jrrggdb";
        char[] answer = new char[ciphertext.length()];
        char[] alphabet = "abcdefghijklmnopqrstuvwxyz".toCharArray();
        int pos = 0;
        while (pos < ciphertext.length()) {
            for (int i = 0; i < alphabet.length; i++) {
                if (ciphertext.charAt(pos) == alphabet[i]) {
                    if (i + shift >= 26) {
                        int rotate = (i - shift + 26);
                        answer[pos] = alphabet[rotate];
                        break;
                    } else {
                        answer[pos] = alphabet[(i + shift)];
                        break;
                    }
                }

            }
            pos++;
        }
        String plaintext = new String(answer);
        System.out.println(plaintext);
        System.out.println("The key is " + shift);
    }

}
