package hoja2.entry806;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DoubleReader {
    private static File file;
    public static void main(String[] args) {
        file = new File("./Projects/ficheros-ejercicios/src/hoja2/entry806/datos806.dat");
        System.out.println(getFileInfo());
    }
    
    private static String getFileInfo() {
        DataInputStream in = null;
        StringBuilder sb = new StringBuilder();
        List<Double> dList = null;

        try {
            in = new DataInputStream(new FileInputStream(file));
            
            dList = new ArrayList<>();
            while(true) {
                dList.add(in.readDouble());
            }            
    
        } catch (IOException e) {
            double sum = 0;
            final double media;
            double biggest = dList.get(0);
            double lowest = dList.get(0);

            for (Double d : dList) {
                sum += d;

                if (d > biggest) {
                    biggest = d;
                }

                if (d < lowest) {
                    lowest = d;
                }
            }

            media = sum / dList.size();

            sb.append("\nSumatorio: " + sum);
            sb.append("\nMedia: " + media);
            sb.append("\nMayor: " + biggest);
            sb.append("\nMenor: " + lowest);

        } finally {
            try {
                in.close();
                return sb.toString();
            } catch (IOException e) {}   
        }

        return "";
    }
}
