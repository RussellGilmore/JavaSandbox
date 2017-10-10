package edu.frostburg.cosc620.projects.poly;

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
public class Poly {

    private final List<String> lines;
    private int[] FTop;
    private int[] FBottom;
    private int[] GTop;
    private int[] GBottom;

    public Poly() {
        lines = new ArrayList<>();

    }

    public static void main(String[] args) {
        try {
            Poly poly = new Poly();
            poly.loadRows();
            poly.makeNumbers();
            poly.add();
            poly.substract();
            poly.multiply();
        } catch (IOException ex) {
            Logger.getLogger(Poly.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void add() throws IOException {
        int[] sum = null;
        if (FTop[0] > GTop[0]) {
            int fPower = FTop[0];
            int gPower = GTop[0];
            int powerDif = fPower - gPower;
            int[] newGBottom = new int[GBottom.length + powerDif];
            for (int i = 0; i < newGBottom.length; i++) {
                if (i < powerDif) {
                    newGBottom[i] = 0;
                } else {
                    break;
                }
            }
            System.arraycopy(GBottom, 0, newGBottom, powerDif, GBottom.length);
            sum = new int[newGBottom.length];
            for (int i = 0; i < newGBottom.length; i++) {
                int f = FBottom[i];
                int g = newGBottom[i];
                sum[i] = f + g;
            }

        } else if (FTop[0] < GTop[0]) {
            int fPower = FTop[0];
            int gPower = GTop[0];
            int powerDif = gPower - fPower;
            int[] newFBottom = new int[FBottom.length + powerDif];
            for (int i = 0; i < newFBottom.length; i++) {
                if (i < powerDif) {
                    newFBottom[i] = 0;
                } else {
                    break;
                }
            }
            System.arraycopy(FBottom, 0, newFBottom, powerDif, FBottom.length);
            sum = new int[newFBottom.length];
            for (int i = 0; i < newFBottom.length; i++) {
                int g = GBottom[i];
                int f = newFBottom[i];
                sum[i] = f + g;
            }
        } else {
            sum = new int[FBottom.length];
            for (int i = 0; i < FBottom.length; i++) {
                int g = GBottom[i];
                int f = FBottom[i];
                sum[i] = f + g;
            }

        }
        checkAnswer(sum);

    }

    public void substract() throws IOException {
        int[] difference = null;
        if (FTop[0] > GTop[0]) {
            int fPower = FTop[0];
            int gPower = GTop[0];
            int powerDif = fPower - gPower;
            int[] newGBottom = new int[GBottom.length + powerDif];
            for (int i = 0; i < newGBottom.length; i++) {
                if (i < powerDif) {
                    newGBottom[i] = 0;
                } else {
                    break;
                }
            }
            System.arraycopy(GBottom, 0, newGBottom, powerDif, GBottom.length);
            difference = new int[newGBottom.length];
            for (int i = 0; i < newGBottom.length; i++) {
                int f = FBottom[i];
                int g = newGBottom[i];
                difference[i] = f - g;
            }

        } else if (FTop[0] < GTop[0]) {
            int fPower = FTop[0];
            int gPower = GTop[0];
            int powerDif = gPower - fPower;
            int[] newFBottom = new int[FBottom.length + powerDif];
            for (int i = 0; i < newFBottom.length; i++) {
                if (i < powerDif) {
                    newFBottom[i] = 0;
                } else {
                    break;
                }
            }
            System.arraycopy(FBottom, 0, newFBottom, powerDif, FBottom.length);
            difference = new int[newFBottom.length];
            for (int i = 0; i < newFBottom.length; i++) {
                int g = GBottom[i];
                int f = newFBottom[i];
                difference[i] = f - g;
            }
        } else {
            difference = new int[FBottom.length];
            for (int i = 0; i < FBottom.length; i++) {
                int g = GBottom[i];
                int f = FBottom[i];
                difference[i] = f - g;
            }

        }
        checkAnswer(difference);
    }

    public void multiply() throws IOException {

        int[] newProd = new int[(FTop[0] * GTop[0]) + 1];

        for (int s = 0, e = FBottom.length - 1; s < e; ++s, --e) {
            int temp = FBottom[s];
            FBottom[s] = FBottom[e];
            FBottom[e] = temp;
        }

        for (int s = 0, e = GBottom.length - 1; s < e; ++s, --e) {
            int temp = GBottom[s];
            GBottom[s] = GBottom[e];
            GBottom[e] = temp;
        }

        for (int i = 0; i < FBottom.length; i++) {
            for (int j = 0; j < GBottom.length; j++) {
                newProd[i + j] += FBottom[i] * GBottom[j];
            }
        }
        for (int s = 0, e = newProd.length - 1; s < e; ++s, --e) {
            int temp = newProd[s];
            newProd[s] = newProd[e];
            newProd[e] = temp;
        }
        checkAnswer(newProd);
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

    public void makeNumbers() {

        String temp;
        temp = lines.get(0);
        String[] functionFTop = temp.split("\\s+");

        temp = lines.get(1);
        String[] functionFBottom = temp.split("\\s+");

        temp = lines.get(2);
        String[] functionGTop = temp.split("\\s+");

        temp = lines.get(3);
        String[] functionGBottom = temp.split("\\s+");

        FTop = new int[functionFTop.length];
        FBottom = new int[functionFBottom.length];

        GTop = new int[functionGTop.length];
        GBottom = new int[functionGBottom.length];

        for (int i = 0; i < functionFTop.length; i++) {
            temp = functionFTop[i];
            FTop[i] = Integer.parseInt(temp);
        }

        int j = 0;
        for (String shit : functionFBottom) {
            temp = shit;
            FBottom[j] = Integer.parseInt(temp);
            j++;
        }

        for (int i = 0; i < functionFTop.length; i++) {
            GTop[i] = Integer.parseInt(functionGTop[i]);
        }

        j = 0;
        for (String shit : functionGBottom) {
            temp = shit;
            GBottom[j] = Integer.parseInt(temp);
            j++;
        }

    }

    public void loadRows() {

        BufferedReader br = null;
        File file = new File("input.txt");
        try {
            br = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Poly.class.getName()).log(Level.SEVERE, null, ex);
        }
        String line;
        try {
            int i = 0;
            while ((line = br.readLine()) != null) {

                lines.add(i, line);
                i++;
            }

        } catch (IOException ex) {
            Logger.getLogger(Poly.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            br.close();
        } catch (IOException ex) {
            Logger.getLogger(Poly.class.getName()).log(Level.SEVERE, null, ex);
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
