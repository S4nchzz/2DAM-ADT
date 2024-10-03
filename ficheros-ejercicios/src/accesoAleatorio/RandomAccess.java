package accesoAleatorio;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class RandomAccess {
    public static void main(String[] args) {
        File file = new File("./Projects/ficheros-ejercicios/src/accesoAleatorio/enteros.dat");
        Scanner sc = new Scanner(System.in);
        System.out.println("Numero a escribir: ");
        final int newNumber = sc.nextInt();
        
        System.out.println("Antes: " + mostrarFichero(file, "rw"));
        
        writeInFile(-1, file, newNumber);
        
        System.out.println("Despues: " + mostrarFichero(file, "rw"));

        sc.close();
    }

    private static void writeInFile(final int pos, final File file, final int number) {
        RandomAccessFile acc = null;

        try {
            acc = new RandomAccessFile(file, "rw");
            if (pos == -1) {
                acc.seek(acc.length());
            } else {
                acc.seek(pos);
            }
            
            acc.write(number);
        } catch (IOException ioe) {}
    }

    private static String mostrarFichero(final File file, String type) {
        StringBuilder sb = new StringBuilder();
        RandomAccessFile acc = null;
        
        int n;
        try {
            acc = new RandomAccessFile(file, type);

            while ((n = acc.read()) != -1) {
                sb.append(n);
            }

            acc.close();
        } catch (IOException e) {
        }

        return sb.toString();
    }
}