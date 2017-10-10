/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.frostburg.cosc620.projects.GF2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Russell
 */
public class GF2 {

    private int primeNumber; //Line 0
    private static int[] constants; //Line 2
    private static int[] functionF; //Line 4
    private static int[] functionG; //Line 6
    private final List<String> lines;

    public GF2() {
        lines = new ArrayList<>();
    }

    public static void main(String[] args) {
        GF2 math = new GF2();
        math.readFile();

        try {
            math.checkAnswer(math.add(functionF, functionG));
        } catch (IOException ex) {
            Logger.getLogger(GF2.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            math.checkAnswer(math.subtract(functionF, functionG));
        } catch (IOException ex) {
            Logger.getLogger(GF2.class.getName()).log(Level.SEVERE, null, ex);
        }

        int[][] product = math.multiply(functionF, functionG, constants);
        int[] rightProd = product[1];
        try {
            math.checkAnswer(rightProd);
        } catch (IOException ex) {
            Logger.getLogger(GF2.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            math.checkAnswer(math.GF2Divide(functionF, functionG, constants));
        } catch (IOException ex) {
            Logger.getLogger(GF2.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public int[] add(int[] funcF, int[] funcG) {
        int[] sum;
        // Init the size of the difference.
        if (funcF.length > funcG.length) {
            sum = new int[funcF.length];
            sum = Arrays.copyOf(funcF, funcF.length);

            for (int i = funcG.length - 1; i >= 0; i--) {
                sum[i + (sum.length - funcG.length)] += funcG[i];
            }

            for (int i = 0; i < sum.length; i++) {
                sum[i] = mod(sum[i], primeNumber);
            }

        } else if (funcF.length < funcG.length) {
            sum = new int[funcG.length];
            sum = Arrays.copyOf(funcG, funcG.length);
            for (int i = funcF.length - 1; i >= 0; i--) {
                sum[i + (sum.length - funcF.length)] += funcF[i];
            }

            for (int i = 0; i < sum.length; i++) {
                sum[i] = mod(sum[i], primeNumber);
            }

        } else {
            sum = new int[funcG.length];
            sum = Arrays.copyOf(funcG, funcG.length);
            for (int i = funcF.length - 1; i >= 0; i--) {
                sum[i] = sum[i] + funcF[i];
            }
            for (int i = 0; i < sum.length; i++) {
                sum[i] = mod(sum[i], primeNumber);
            }

        }
        return sum;

    }

    public int[] subtract(int[] funcF, int[] funcG) {
        int[] difference;
        // Init the size of the difference.
        if (funcF.length > funcG.length) {
            difference = new int[funcF.length];
            difference = Arrays.copyOf(funcF, funcF.length);
            for (int i = funcG.length - 1; i >= 0; i--) {
                difference[i + (difference.length - funcG.length)] -= funcG[i];
            }

            for (int i = 0; i < difference.length; i++) {
                difference[i] = mod(difference[i], primeNumber);
            }
            // Problem is here
        } else if (funcF.length < funcG.length) {

            difference = new int[funcG.length];
            int diff = funcG.length - funcF.length;
            int[] newF = new int[funcF.length + diff];
            for (int i = 0; i < diff; i++) {
                newF[i] = 0;
            }
            for (int i = 0; i < funcF.length; i++) {
                newF[diff] = funcF[i];
                diff++;
            }
            for (int i = 0; i < newF.length; i++) {
                difference[i] = newF[i] - funcG[i];
            }

            for (int i = 0; i < difference.length; i++) {
                difference[i] = mod(difference[i], primeNumber);
            }

        } else {
            difference = new int[funcF.length];
            difference = Arrays.copyOf(funcF, funcF.length);
            for (int i = funcG.length - 1; i >= 0; i--) {
                difference[i] = difference[i] - funcG[i];
            }

            for (int i = 0; i < difference.length; i++) {
                difference[i] = mod(difference[i], primeNumber);
            }

        }

        return difference;

    }

    public int[] modArray(int[] n) {
        for (int i = 0; i < n.length; i++) {
            n[i] = mod(n[i], primeNumber);
        }
        n = reduceAnswer(n);
        return n;
    }

    public int[][] PLDA(int[] n, int[] d) {

        n = modArray(n);
        d = modArray(d);

        int[] q = {0};
        int[] r = Arrays.copyOf(n, n.length);
        while (isZero(r) == false && r.length >= d.length) {
            int t = divide(lead(r), lead(d)); //Coefficient
            int tPower = (r.length - 1) - (d.length - 1);

            int[] tPoly = makePoly(tPower, t);

            q = reduceAnswer(add(q, tPoly));
            r = reduceAnswer(subtract(r, (multiplyPoly(tPoly, d))));

            q = modArray(q);
            r = modArray(r);

        }

        return new int[][]{q, r};

    }

    public boolean isZero(int[] poly) {
        if (poly.length == 1) {
            if (poly[0] == 0) {
                return true;

            }
        }
        return false;
    }

    public int[][] multiply(int[] fx, int[] gx, int[] cons) {
        int[] product = multiplyPoly(fx, gx);

        int[][] pldaProduct = PLDA(product, cons);
        return pldaProduct;

    }

    public int[] multiplyPoly(int[] f, int[] g) {
        int[] newProd = new int[((f.length) + (g.length) - 1)];

        for (int i = 0; i < f.length; i++) {
            for (int j = 0; j < g.length; j++) {

                newProd[i + j] += f[i] * g[j];
            }
        }

        newProd = modArray(newProd);

        return newProd;

    }

    public int[] makePoly(int power, int coe) {
        int[] poly = new int[power + 1];
        poly[0] = coe;

        return poly;
    }

    public int divide(int x, int y) {
        int[] quotient = EEA(primeNumber, y);
        int thing1 = x * quotient[1];
        int answer = mod(thing1, primeNumber);
        return answer;
    }

    public int[] GF2Divide(int[] f, int[] g, int[] conts) {
        int[][] quotient = EEAP(conts, g);
        int[] makeQuotient = quotient[1];
        int[][] makeAgain = multiply(f, makeQuotient, conts);
        int[] answer = makeAgain[1];
        answer = modArray(answer);
        return answer;
    }

    public int[][] EEAP(int[] a, int[] b) {
        a = modArray(a);
        b = modArray(b);

        if (b[0] == 0) {
            int inverse = divide(1, lead(a));
            int[] inversePoly = makePoly(a.length - 1, inverse);
            int[] zero = {0};
            return new int[][]{inversePoly, zero};

        } else {
            int[][] Q = PLDA(a, b);
            int[] q = Q[0];
            int[] r = Q[1];
            int[][] R = EEAP(b, r);
            int[] u = R[1];
            u = modArray(u);
            //Maybe Here
            int[][] multi = multiply(q, R[1], constants);
            int[] v = subtract(R[0], multi[1]);
            v = modArray(v);
            return new int[][]{u, v};
        }

    }

    public int[] EEA(int a, int b) {
        if (b == 0) {
            return new int[]{1, 0};
        } else {
            int q = a / b;
            int r = a % b;
            int[] R = EEA(b, r);
            return new int[]{R[1], R[0] - q * R[1]};
        }
    }

    public int lead(int[] lead) {
        int leadNum;
        if (lead.length == 0) {
            leadNum = 0;
        } else {
            leadNum = lead[0];
        }
        return leadNum;
    }

    private int mod(int x, int y) {
        int result = x % y;
        if (result < 0) {
            result += y;
        }
        return result;
    }

    private void assignValues() {

        String constants;
        String functionF;
        String functionG;
        this.primeNumber = Integer.parseInt(lines.get(0));
        constants = lines.get(2);
        functionF = lines.get(4);
        functionG = lines.get(6);
        constants = constants.replaceAll("\\s", "");
        functionF = functionF.replaceAll("\\s", "");
        functionG = functionG.replaceAll("\\s", "");

        this.constants = new int[constants.length()];
        this.functionF = new int[functionF.length()];
        this.functionG = new int[functionG.length()];
        for (int i = 0; i < constants.length(); i++) {
            this.constants[i] = Character.getNumericValue(constants.charAt(i));
        }
        for (int i = 0; i < functionF.length(); i++) {
            this.functionF[i] = Character.getNumericValue(functionF.charAt(i));
        }
        for (int i = 0; i < functionG.length(); i++) {
            this.functionG[i] = Character.getNumericValue(functionG.charAt(i));
        }

    }

    public void readFile() {
        BufferedReader br = null;
        File file = new File("input.txt");
        try {
            br = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(GF2.class.getName()).log(Level.SEVERE, null, ex);
        }
        String line;
        try {
            int i = 0;
            while ((line = br.readLine()) != null) {

                lines.add(i, line);
                i++;
            }

        } catch (IOException ex) {
            Logger.getLogger(GF2.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            br.close();
        } catch (IOException ex) {
            Logger.getLogger(GF2.class.getName()).log(Level.SEVERE, null, ex);
        }
        assignValues();
    }

    public int[] reduceAnswer(int[] answer) {
        if (answer[0] == 0) {
            int totalSum = 0;
            for (int i : answer) {
                totalSum = i + totalSum;
            }
            if (totalSum == 0) {
                int[] zero = {0};
                return zero;
            } else {
                int shiftLeft = 0;
                for (int i = 0; i < answer.length; i++) {
                    if (answer[i] == 0) {
                        shiftLeft++;
                    } else {
                        break;
                    }
                }
                int[] newAnswer = new int[answer.length - shiftLeft];
                System.arraycopy(answer, shiftLeft, newAnswer, 0, answer.length - shiftLeft);

                return newAnswer;
            }
            // No problems
        } else {
            return answer;
        }
    }

    public void checkAnswer(int[] answer) throws IOException {
        if (answer[0] == 0) {
            int totalSum = 0;
            for (int i : answer) {
                totalSum = i + totalSum;
            }
            if (totalSum == 0) {
                int[] zero = {0};
                writeOut(zero);
            } else {
                int shiftLeft = 0;
                for (int i = 0; i < answer.length; i++) {
                    if (answer[i] == 0) {
                        shiftLeft++;
                    } else {
                        break;
                    }
                }
                int[] newAnswer = new int[answer.length - shiftLeft];
                System.arraycopy(answer, shiftLeft, newAnswer, 0, answer.length - shiftLeft);

                writeOut(newAnswer);
            }
            // No problems
        } else {
            writeOut(answer);
        }
    }

    public void writeOut(int[] result) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("output.txt", true))) {
            for (int i = 0; i < result.length; i++) {
                bw.write(result[i] + " ");
            }
            bw.newLine();
            bw.flush();
            bw.close();
        }
    }
}
