package mancala;
import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Saver {

    public static void saveObject(Serializable toSave, String filename) throws IOException {
        File assetsFolder = new File("assets");
        if (!assetsFolder.exists()) {
            assetsFolder.mkdir();
        }

        Path currentDirectory = Paths.get(System.getProperty("user.dir"));
        Path assetsFolderPath = currentDirectory.resolve("assets");

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(assetsFolderPath.resolve(filename).toFile()))) {
            oos.writeObject(toSave);
        }
    }


    public static Serializable loadObject(String filename) throws IOException, ClassNotFoundException {
        Path currentDirectory = Paths.get(System.getProperty("user.dir"));
        Path assetsFolderPath = currentDirectory.resolve("assets");

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(assetsFolderPath.resolve(filename).toFile()))) {
            return (Serializable) ois.readObject();
        }
    }

}
