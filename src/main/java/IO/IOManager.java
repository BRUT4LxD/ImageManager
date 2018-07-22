package IO;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;

public final class IOManager {

    public static int countFilesInDirectory(String path){

        File directory=new File(path);

        return directory.list().length;

    }

    public static ArrayList<String> readFromFile(String file) {

        // The name of the file to open.
        String fileName = file;
        ArrayList<String> content = new ArrayList<>();

        // This will reference one line at a time
        String line = null;

        try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader =
                    new FileReader(fileName);

            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader =
                    new BufferedReader(fileReader);

            while((line = bufferedReader.readLine()) != null) {
                content.add(line);
            }

            // Always close files.
            bufferedReader.close();
            return  content;
        }
        catch(FileNotFoundException ex) {
            System.out.println(
                    "Unable to open file '" +
                            fileName + "'");
        }
        catch(IOException ex) {
            System.out.println(
                    "Error reading file '"
                            + fileName + "'");
            // Or we could just do this:
            // ex.printStackTrace();
        }

        return content;
    }
    public static void saveImage(Image image, String filePath){
        try {
            // retrieve image
            BufferedImage bi = toBufferedImage(image);
            File outputfile = new File(filePath);
            ImageIO.write(bi, "jpg", outputfile);
        } catch (IOException e) {
        }
    }

    public static BufferedImage toBufferedImage(Image im)
    {
        BufferedImage bi = new BufferedImage(im.getWidth(null),im.getHeight(null),BufferedImage.TYPE_INT_RGB);
        Graphics bg = bi.getGraphics();
        bg.drawImage(im, 0, 0, null);
        bg.dispose();
        return bi;
    }
    public static void imageRename(String oldPath, String newPath) throws IOException {
        // File (or directory) with old name
        File file1 = new File(oldPath);

        // File (or directory) with new name
        File file2 = new File(newPath);

        if (file2.exists())
        {
                throw new IOException("file exists");
        }

        // Rename file (or directory)
        boolean success = file1.renameTo(file2);

        if (!success) {
            System.out.println("File was not successfully renamed");
        }
    }

}
