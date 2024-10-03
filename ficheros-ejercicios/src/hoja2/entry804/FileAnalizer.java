package hoja2.entry804;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class FileAnalizer {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        File file = new File(sc.next());
        System.out.println("Numero caracteres = " + getNChars(file));
        System.out.println("Numero lineas = " + getLines(file));
        System.out.println("Numero palabras = " + getNWords(file));
        sc.close();
    }

    private static int getNChars(final File file) {
        try {
            FileReader reader = new FileReader(file);
            BufferedReader breader = new BufferedReader(reader);
            int nChars = 0;

            String line;
            while ((line = breader.readLine()) != null) {
                nChars += line.length();
            }

            reader.close();
            return nChars;
        } catch (IOException e) {

        }

        return -1;
    }

    private static int getLines(final File file) {
        try {
            FileReader reader = new FileReader(file);
            BufferedReader breader = new BufferedReader(reader);
            int nLines = 0;

            while (breader.readLine() != null) {
                nLines++;
            }

            reader.close();
            return nLines;
        } catch (IOException e) {

        }

        return -1;
    }

    private static int getNWords(final File file) {
        try {
            FileReader reader = new FileReader(file);
            BufferedReader breader = new BufferedReader(reader);
            String line;
            int nWordCount = 0;

            while ((line = breader.readLine()) != null) {
                line = line.trim();

                if (!line.isEmpty() && !line.contains(" ")) {
                    nWordCount++;
                } else if (!line.isEmpty()) {
                    int spaceNumber = 0;
                    for (int k = 0; k < line.length(); k++) {
                        if (line.charAt(k) == ' ') {
                            spaceNumber++;
                        }
                    }
                    nWordCount += spaceNumber + 1;

                }
            }

            reader.close();
            return nWordCount;
        } catch (IOException e) {

        }
        return -1;
    }
}