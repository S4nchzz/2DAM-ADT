package entry802;

import java.util.Scanner;
import java.io.*;

public class NotepadInOut {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Escriba la ruta del archivo: ");
        final String path = sc.nextLine();
        sc.close();


        FileReader in = null;
        StringBuilder sb = new StringBuilder();
        try {
            File file = new File(path);
            in = new FileReader(file);
            
            int i;
            while ((i = in.read()) != -1) {
                if ((char)i == ' ') {
                    sb.append("-");
                } else {
                    sb.append((char)i);
                }
            }

            String notepadContent = sb.toString();
            System.out.println(notepadContent.toUpperCase());
        } catch (Exception e) {
            System.out.println("No se ha encontrado la ruta especificada.");
        } finally {
            try {in.close();} catch (IOException e1) {}
        }
    }
}