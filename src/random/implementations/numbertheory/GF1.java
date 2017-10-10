package edu.frostburg.cosc620.projects.GF1;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Russell
 */
public class GF1 {

    private final List<String> lines;
    private int p;
    private int x;
    private int y;

    public GF1() {
        lines = new ArrayList<>();

    }

    private int mod(int x, int y) {
        int result = x % y;
        if (result < 0) {
            result += y;
        }
        return result;
    }

    public void add() {
        int sum = (x + y);
        int finalSum = mod(sum, p);

        try {
            writeSolution(finalSum);
        } catch (IOException ex) {
            Logger.getLogger(GF1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void subtract() {
        int difference = (x - y);
        int finalDifference = mod(difference, p);

        try {
            writeSolution(finalDifference);
        } catch (IOException ex) {
            Logger.getLogger(GF1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void multiply() {
        int product = (x * y);
        int finalProduct = mod(product, p);

        try {
            writeSolution(finalProduct);
        } catch (IOException ex) {
            Logger.getLogger(GF1.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void divide() {
        int[] quotient = EEA(p, y);
        int thing1 = x * quotient[1];
        int answer = mod(thing1, p);

        try {
            writeSolution(answer);
        } catch (IOException ex) {
            Logger.getLogger(GF1.class.getName()).log(Level.SEVERE, null, ex);
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

    public void writeSolution(int answer) throws IOException {
        String stringAnswer = Integer.toString(answer);

        try (BufferedWriter bw = new BufferedWriter(new FileWriter("output.txt", true))) {
            bw.write(stringAnswer);
            bw.newLine();
            bw.flush();
            bw.close();

        }

    }

    public void takeInput() {
        BufferedReader br = null;
        File file = new File("input.txt");
        try {
            br = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(GF1.class.getName()).log(Level.SEVERE, null, ex);
        }
        String line;
        try {
            int i = 0;
            while ((line = br.readLine()) != null) {

                lines.add(i, line);
                i++;
            }

        } catch (IOException ex) {
            Logger.getLogger(GF1.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            br.close();
        } catch (IOException ex) {
            Logger.getLogger(GF1.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.p = Integer.parseInt(lines.get(0));
        this.x = Integer.parseInt(lines.get(1));
        this.y = Integer.parseInt(lines.get(2));

    }

    public static void main(String[] args) {
        GF1 math = new GF1();
        math.takeInput();
        // 2,6,6,5

        math.add();
        math.subtract();
        math.multiply();
        math.divide();

    }

}
