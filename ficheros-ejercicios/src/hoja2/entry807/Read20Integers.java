package hoja2.entry807;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Read20Integers {
    private static File file;
    public static void main(String[] args) {
        file = new File("./Projects/ficheros-ejercicios/src/hoja2/entry806/datos806.dat");
        readFirst20Integers();
        System.out.println(readObject());
    }

    private static void readFirst20Integers() {
        IntegerList list = IntegerList.getInstance();

        DataInputStream in = null;

        try {
            in = new DataInputStream(new FileInputStream(file));

            for (int i = 0; i < 20; i++) {
                list.insertInt(in.readInt());
            }
            
        } catch (IOException e) {
            
        } finally {
            File serializedList = new File("./Projects/ficheros-ejercicios/src/hoja2/entry807", "intObjList.dat");
            try {
                serializedList.createNewFile();

                FileOutputStream fout = new FileOutputStream(serializedList);
                ObjectOutputStream oout = new ObjectOutputStream(fout);
                oout.writeObject(list);
                oout.close();
                fout.close();
            } catch (IOException e) {}
        }
    }

    private static Object readObject() {
        try {
            FileInputStream fin = new FileInputStream("./Projects/ficheros-ejercicios/src/hoja2/entry807/intObjList.dat");
            ObjectInputStream oin = new ObjectInputStream(fin);

            Object obj = oin.readObject();

            if (obj instanceof IntegerList) {
                System.out.println("El objeto es de clase IntegerList");
            } else {
                System.out.println("El objeto no es de clase IntegerList");
            }

            oin.close();
            return obj;

        } catch (IOException | ClassNotFoundException e) {}

        return null;
    }
}