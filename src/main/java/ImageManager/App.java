package ImageManager;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import IO.*;
public class App 
{
    public static void main( String[] args ) throws IOException {


        /*ArrayList<String> vegetables = IOManager.readFromFile("vg.txt");

        for( String s: vegetables){

            ImageDownloader imageDownloader = new ImageDownloader(10,s);

            imageDownloader.downloadPictures();
        }*/

        /*File file1 = new File("Allegro2");
        file1.mkdir();
        final int NUM_OF_PAGES = 18;
        int counter = 1;
        for( int j = 1 ; j <=NUM_OF_PAGES; j++){
            PageManager pageManager = new PageManager("https://allegro.pl/listing?string=samochody%20osobowe&order=m&bmatch=ss-base-relevance-floki-5-nga-hcp-wp-aut-1-1-0328&p=" + j);
            ArrayList<Image> images = pageManager.takeAllImagesFromContent();
            for(Image i:images){
                String filePath = "Allegro2" + "\\" + counter++ + ".jpg";
                IOManager.saveImage(i,filePath);
            }
        }*/

        String mainPath = "C:\\train\\znaki-inz-filmy\\Test\\walidacja";
        File folder = new File(mainPath);
        File[] listOfFiles = folder.listFiles();
        String category = "";
        int counter = 1;
        for (int i = 0; i < listOfFiles.length; i++) {

            /*category = "";
            for( int j = 0 ; j < listOfFiles[i].getName().length(); j++){

                if(listOfFiles[i].getName().charAt(j) == '.')
                    break;

                category += listOfFiles[i].getName().charAt(j);

            }*/

            IOManager.imageRename(mainPath + "\\" + listOfFiles[i].getName(),mainPath + "\\" + counter++ + ".jpg");
            if (listOfFiles[i].isFile()) {
                System.out.println("File " + listOfFiles[i].getName());
            } else if (listOfFiles[i].isDirectory()) {
                System.out.println("Directory " + listOfFiles[i].getName());
            }
        }
    }
    public static boolean checkInput(String[] args){
        if(args.length != 2){
            System.out.println("invalid number of parameters!");
            return false;
        };
        return true;
    }
    public static void parkingRename() throws IOException {
        String mainPath = "C:\\train\\zdjecia_rename";

        int counter = 1;
        for( int j = 0; j <= 14; j++){
            File folder = new File(mainPath + "\\" + j);
            File[] listOfFiles = folder.listFiles();
            for (int i = 0; i < listOfFiles.length - 1; i++) {

                String s = listOfFiles[i].getName().substring(0,1);
                if((int)s.charAt(0) < 48 || (int)s.charAt(0) >57 ) continue;
                IOManager.imageRename(mainPath + "\\" + j + "\\" + listOfFiles[i].getName(),mainPath + "\\" + j + "\\" + j + "." + counter++ + ".jpg");
                if (listOfFiles[i].isFile()) {
                    System.out.println("File " + listOfFiles[i].getName());
                } else if (listOfFiles[i].isDirectory()) {
                    System.out.println("Directory " + listOfFiles[i].getName());
                }
            }
        }
        }
}
