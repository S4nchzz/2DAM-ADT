import java.io.*;

public class GestionFicheros {
    private static String path = "./gestionFicheros/src/";

    public static void main(String[] args) {
        final String folderName = "MyFolder";
        createFolder(path, folderName);
        createFolder(path + folderName, "/OtherFolder");

        changePath(path + folderName);
        
        final String fileName = "hola.txt";
        final String fileRenameName = "adios.txt";
        createFile(path, fileName);
        renameFile(new File(path + fileName), fileRenameName);

        createFile(path + "OtherFolder", "fileInside.txt");
        
        verDir();

        writeFile(path + fileRenameName, ".m.m.m.m.");
        System.out.println("Content inside " + fileRenameName + " is = " + readFile(path + fileRenameName));

        writeWithFileWriter(path + fileRenameName, "hola", true);
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

    private static String readFile(final String filePath) {
        DataInputStream input = null;
        String content = null;
        try {
            input = new DataInputStream(new FileInputStream(new File(filePath)));
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
        File f = new File(path);

        File listFiles [] = f.listFiles();

        System.out.println("Number of Files/Folders = " + listFiles.length);

        for (File file : listFiles) {
            String response = file.isDirectory() ? "-> D |" : "-> F |";
            System.out.println(response + file.getName());
        }
        System.out.println("\n");
    }

    private static void createFolder (final String path, final String folderName) {
        File folder = new File(path + folderName);
        folder.mkdir();
    }

    private static void createFile(final String path, final String fileName) {
        File file = new File(path, fileName);
        try {
            file.createNewFile();
        } catch (IOException e) {}
    }

    private static void renameFile(final File f, final String newName) {
        f.renameTo(new File(path + newName));
    }

    private static void changePath (final String newPath) {
        path = newPath + "/";
    }
}