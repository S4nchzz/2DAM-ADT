package entry801;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

public class StudentService {
    private static final String entrada801Path = "./Projects/ficheros-ejercicios/src/hoja1/entry801/entrada801.dat";
    private static final File entryFile = new File(entrada801Path);

    private static final List<Double> list = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        System.out.println(nAlumnos());
    }

    private static String nAlumnos() {
        DataInputStream in = null;
        StringBuilder sb = new StringBuilder();

        try {
            in = new DataInputStream(new FileInputStream(entryFile));

            while (true) {
                list.add((double)in.readInt());
            }
            
        } catch (IOException e) {
            
        } finally {
            try {in.close();} catch (IOException e) {}
            printDatInfo();
        }

        return sb.toString();
    }

    private static void printDatInfo() {
        System.out.println("Alumnos: " + list.size() + "\n");

        double sum = 0;
        double youngestStudent = list.get(0);
        double oldestStudent = list.get(0);
        int nMinorStudents = 0;

        for (Double d : list) {
            sum += d;

            if (d <= youngestStudent) {
                youngestStudent = d;
            } else {
                oldestStudent = d;
            }

            if (d < 18) {
                nMinorStudents++;
            }
        }

        double mean = sum / list.size();
        double sumOfSquares = 0;

        for (Double d : list) {
            sumOfSquares += Math.pow(d - mean, 2);
        }

        double standardDeviation = Math.sqrt(sumOfSquares / list.size());

        System.out.println("Media Aritmetica: " + (sum / list.size()));
        System.out.println("----------------------------");
        System.out.println("Alumno mas joven: " + youngestStudent);
        System.out.println("Alumno mas joven: " + oldestStudent);
        System.out.println("----------------------------");
        System.out.println("Alumnos menores de edad: " + nMinorStudents);
        System.out.println("Desviacion: " + standardDeviation);
    }
}
