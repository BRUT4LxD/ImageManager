package ImageManager;

import IO.IOManager;
import org.json.JSONObject;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class ImageDownloader {
    private int numOfPictures;
    private String phraseToSearch;
    private String urlPhrase;
    private PageManager pageManager;
    private final String url1 = "https://www.istockphoto.com/pl/obrazy/znak-stop?excludenudity=true&mediatype=photography&page=";
    private final String url2 = "&sort=mostpopular";


    public ImageDownloader(int numOfPictures, String phraseToSearch) {
        this.numOfPictures = numOfPictures;
        this.phraseToSearch = phraseToSearch;
        createQuery();
        createDirectory();
        System.out.println(urlPhrase);
    }

    private void createQuery(){

        String urlQuery = "&phrase=";
        for( int i = 0 ;i < phraseToSearch.length(); i++){
            if(phraseToSearch.substring(i,i+1).equals(" ")){
                urlQuery +="%20";
                continue;
            }
            urlQuery += phraseToSearch.substring(i,i+1);
        }
        this.urlPhrase = urlQuery;
    }
    public void downloadPictures(){
        int counter = 1 ;
        for( int k = 1; k < numOfPictures/60 + 2; k++){
            System.out.println("searching: " + url1 + k + urlPhrase + url2);
            PageManager pageManager = new PageManager(url1 + k + urlPhrase + url2);
            ArrayList<Image> images = pageManager.takeAllImagesFromContent();
            for(Image i:images){
                if(counter == numOfPictures + 1) return;
                String filePath = phraseToSearch + "\\" + phraseToSearch + "." + counter++ + ".jpg";
                IOManager.saveImage(i,filePath);
            }
        }
    }
    private void createDirectory(){
        File theDir = new File(this.phraseToSearch);

        if (!theDir.exists()) {
            System.out.println("Creating directory: " + theDir.getName());
            boolean result = false;

            try{
                theDir.mkdir();
                result = true;
            }
            catch(SecurityException se){
                //handle it
            }
            if(result) {
                System.out.println("DIR created");
            }
        }
    }

}
