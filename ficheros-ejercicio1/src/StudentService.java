import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class StudentService {
    private static final String entrada801Path = "./ficheros-ejercicio1/src/entry801/entrada801.dat";
    private static final File entryFile = new File(entrada801Path);
    public static void main(String[] args) throws Exception {
        System.out.println(nAlumnos());
    }

    private static String nAlumnos() {
        DataInputStream in = null;
        StringBuilder sb = new StringBuilder();

        try {
            in = new DataInputStream(new FileInputStream(entryFile));
            
            while (true) {
                sb.append((char)in.readInt());
            }

        } catch (IOException e) {

        } finally {
            try {in.close();} catch (IOException e) {}
        }

        return sb.toString();
    }
}
