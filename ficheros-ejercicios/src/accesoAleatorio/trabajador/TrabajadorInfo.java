package accesoAleatorio.trabajador;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class TrabajadorInfo {
    private static File file;
    
    public static void main(String[] args) {
        file = new File("./Projects/ficheros-ejercicios/src/accesoAleatorio/trabajador/trabajador.dat");

        writeWorker("Sanchez", 1, 1342.44, 1);
        writeWorker("Gomez", 1, 1342.44, 2);

        System.out.println(readAll());
        System.out.println("-----------");
        System.out.println(readById(2));
    }

    private static void writeWorker(String surname, int departament, double salary, int id) {
        try {
            RandomAccessFile ran = new RandomAccessFile(file, "rw");

            StringBuffer buffer = new StringBuffer(surname);
            buffer.setLength(10);

            ran.seek(ran.length());

            ran.writeInt(id);
            ran.writeChars(buffer.toString());
            ran.writeInt(departament);
            ran.writeDouble(salary);
            
            ran.close();
        } catch (IOException e) {
            
        }
    }

    private static String readAll() {
        StringBuilder sb = new StringBuilder();
        RandomAccessFile acc = null;
        
        try {
            acc = new RandomAccessFile(file, "r");

            while (true) {
                sb.append("ID: " + acc.readInt() + "\n");

                sb.append("Apellido: ");
                for (int k = 0; k < 10; k++) {
                    sb.append(acc.readChar());
                }

                sb.append("\nDepartamento: " + acc.readInt());
                sb.append("\nSalario: " + acc.readDouble() + "\n\n");
            }

        } catch (IOException e) {
        } finally {
            try {
                acc.close();
                return sb.toString();
            } catch (IOException e) {}
        }

        return "Err.";
    }

    private static String readById(int id) {
        RandomAccessFile acc = null;
        StringBuilder sb = new StringBuilder();


        int pos = (id - 1) * 36;
        try {
            acc = new RandomAccessFile(file, "r");

            acc.seek(pos);
            int readId = acc.readInt();
                sb.append("ID: " + readId + "\n");
                sb.append("Apellido: ");
                for (int k = 0; k < 10; k++) {
                    sb.append(acc.readChar());
                }
                
                sb.append("\nDepartamento: " + acc.readInt());
                sb.append("\nSalario: " + acc.readDouble() + "\n\n");

                acc.close();
                return sb.toString();
            
        } catch (IOException e) {
            try {
                acc.close();
            } catch (IOException e1) {}
        }

        return "";

    }
}
