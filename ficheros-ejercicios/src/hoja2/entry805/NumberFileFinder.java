package hoja2.entry805;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class NumberFileFinder {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        final String path = sc.nextLine();
        sc.close();

        File file = new File(path);
        System.out.println(showValues(file));
        System.out.println("Media " + getAverage(file));
    }

    private static String showValues(final File file) {
        FileReader reader = null;
        StringBuilder sb = new StringBuilder();
        try {
            reader = new FileReader(file);
            
            int value;
            while ((value = reader.read()) != -1) {
                sb.append((char)value);
            }

        } catch (IOException ioe) {
            System.out.println("File not found or bad read");
        }

        return sb.toString();
    }

    private static double getAverage(final File file) {
        BufferedReader reader = null;
        List<Integer> n = new ArrayList<>(); 
        try {
            reader = new BufferedReader(new FileReader(file));

            String value;
            while ((value = reader.readLine()) != null) {
                n.add(Integer.valueOf(value));
            }

        } catch (IOException ioe) {
            System.out.println("File not found or bad read");
        }

        double sum = 0;
        for (Integer i : n) {
            sum += i;
        }

        return sum / n.size();
    }
}
