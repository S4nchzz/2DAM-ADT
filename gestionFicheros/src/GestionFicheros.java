import java.io.*;
import java.util.Random;

public class GestionFicheros {
    private static String rootPath = "./gestionFicheros/src/";

    public static void main(String[] args) {
        final String myFolderName = "MyFolder";
        createFolder(rootPath, myFolderName);

        // Create rename and write with no append and append on that file
        verDir();
        createFile(rootPath + myFolderName, "/myFile.txt");

        final String renamedFile = "/notMyFile.txt";
        renameFile(new File(rootPath + myFolderName + "/myFile.txt"), renamedFile, rootPath + myFolderName);
        writeFile(rootPath + myFolderName + renamedFile, "W R I T E R");
        writeWithFileWriter(rootPath + myFolderName + renamedFile, "   <- Writer", true);
        verDir();

        // Read renamed file
        System.out.println("Content in " + renamedFile + " = " + readFile(new File(rootPath + myFolderName + renamedFile)));
        
        //Numbers to 100 on binary
        createFile(rootPath + myFolderName, "numbersTo100.dat");
        writeDatFileNTo100(rootPath + myFolderName + "/numbersTo100.dat");
        System.out.println("Content in numbersTo100.dat file = " + readDatFileNTo100(rootPath + "MyFolder/numbersTo100.dat") + "\n");


        // File with persons
        final String personsFolder = "/Persons";
        createFolder(rootPath + myFolderName, personsFolder);
        File personsFile = createFile(rootPath + myFolderName + personsFolder, "persons.dat");
        writePersonsWithAge(personsFile);
        System.out.println(readPersonsWithAge(personsFile));

        // Serialized Obj
        final String serializedFolder = "/SerializedObjDat";
        createFolder(rootPath + myFolderName, serializedFolder);
        File serializedFile = createFile(rootPath + myFolderName + serializedFolder, "objSerialized.dat");
        serializeObj(new Persona("Iyan", 20), serializedFile);

        System.out.println(readSerializableObj(serializedFile).toString());
    }

    private static void writeDatFileNTo100(final String path) {
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(new File(path));

            for (int i = 1; i <= 100; i++) {
                out.write(i);
            }

        } catch (IOException e) {

        } finally {
            try {out.close();} catch (IOException e) {}
        }
    }

    private static String readDatFileNTo100(final String path) {
        FileInputStream in = null;
        StringBuilder sb = new StringBuilder();

        try {
            in = new FileInputStream(new File(path));
            sb = new StringBuilder();
            int i = 0;
            while ((i = in.read()) != -1) {
                sb.append(i + ", ");
            }
        } catch (IOException ioe) {
            
        } finally {
            try {in.close();} catch (IOException e) {}
        }

        return sb.toString();
    }

    private static void writeFile(final String filePath, final String content) {
        File file = new File(filePath);
        DataOutputStream data = null;
        try {
            data = new DataOutputStream(new FileOutputStream(file));
            data.write(content.getBytes());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                data.close();
            } catch (IOException e) {System.out.println(e.getMessage());}
        }
    }

    private static void writeWithFileWriter(final String filePath, final String content, final boolean append) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(new File(filePath), append);
            writer.write(content);
        } catch (IOException e) {
            
        } finally {
            try {
                writer.close();
            } catch (IOException e) {System.out.println(e.getMessage());}
        }
    }

    private static String readFile(final File file) {
        DataInputStream input = null;
        String content = null;
        try {
            input = new DataInputStream(new FileInputStream(file));
            content = new String(input.readAllBytes());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {System.out.println(e.getMessage());}
            }
        }

        if (content == null) {
            return "";
        }
        
        return content;
    }

    private static void verDir() {
        File f = new File(rootPath);

        File listFiles [] = f.listFiles();

        System.out.println("Number of Files/Folders = " + listFiles.length);

        for (File file : listFiles) {
            String response = file.isDirectory() ? "-> D |" : "-> F |";
            System.out.println(response + file.getName());
        }
        System.out.println("\n");
    }

    private static void createFolder (final String path, final String myFolderName) {
        File folder = new File(path + myFolderName);
        folder.mkdir();
    }

    private static File createFile(final String path, final String fileName) {
        File file = new File(path, fileName);
        try {
            file.createNewFile();
            return file;
        } catch (IOException e) {}

        return null;
    }

    private static void renameFile(final File f, final String newName, final String path) {
        f.renameTo(new File(path + "/" + newName));
    }

    private static void writePersonsWithAge(final File file) {
        DataOutputStream out = null;
        try {
            out = new DataOutputStream(new FileOutputStream(file));
            String[] persons = new String[] {
                "John",
                "Jane",
                "Michael",
                "Emily",
                "David",
                "Olivia",
                "Daniel",
                "Sophia",
                "Matthew",
                "Isabella",
                "Andrew",
                "Mia",
                "James",
                "Ava",
                "William",
                "Charlotte",
                "Joseph",
                "Amelia",
                "Benjamin",
                "Ella"
            };

            int[] ages = new int[20];
            Random ran = new Random();

            for (int i = 0; i < ages.length; i++) {
                ages[i] = ran.nextInt(79) + 1;
            }

            try {
                for (int i = 0; i < persons.length; i++) {
                    out.writeUTF(persons[i]);
                    out.writeInt(ages[i]);
                }   
            } catch (IOException ioe) {
                
            } finally {
                try {out.close();} catch (IOException e) {}
            }

        } catch (FileNotFoundException e) {}
    }

    private static String readPersonsWithAge(final File file) {
        DataInputStream in = null;
        StringBuilder sb = new StringBuilder();

        try {
            in = new DataInputStream(new FileInputStream(file));

            try {
                while (true) {
                    sb.append(in.readUTF() + " ");
                    sb.append(in.readInt());
                    sb.append("\n");
                }
            } catch (IOException ioe) {

            } finally {
                try {in.close();} catch (IOException e) {}
            }
        } catch (FileNotFoundException e) {
            
        }

        return sb.toString();
    }

    private static void serializeObj(final Object obj, final File file) {
        ObjectOutputStream out = null;

        try {
            out = new ObjectOutputStream(new FileOutputStream(file));
            out.writeObject(obj);
        } catch (IOException e) {}
    }

    private static Object readSerializableObj(final File file) {
        ObjectInputStream in = null;
        Object serializedObj = null;

        try {
            in = new ObjectInputStream(new FileInputStream(file));
            serializedObj = in.readObject();
        } catch (IOException | ClassCastException | ClassNotFoundException c) {
        
        } finally {
            try {in.close();} catch (IOException e) {}
        }

        return serializedObj;
    }
}