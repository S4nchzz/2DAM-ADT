package entry803;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class DupeFile {
    public static void main(String[] args) {
        dupeFile();    
    }

    private static void dupeFile() {
        Scanner sc = new Scanner(System.in);
        
        System.out.println("Escribe la ruta del archivo que quiere copiar: ");
        final String fromFile = sc.next();

        System.out.println("Escribe la ruta del archivo donde quiere pegar el contenido: ");
        final String toFile= sc.next();

        try {
            File fileA = new File(fromFile);
            File fileB = new File(toFile);
            
            FileReader reader = new FileReader(fileA);
            FileWriter writer = new FileWriter(fileB);

            int value;
            int nValuesCopied = 0;
            while ((value = reader.read()) != -1) {
                writer.write(value);
                nValuesCopied++;
            }

            System.out.println("Se han copiado " + nValuesCopied + " caracteres");
            reader.close();
            writer.close();
            sc.close();
        } catch (IOException ioe) {

        }
    }
}