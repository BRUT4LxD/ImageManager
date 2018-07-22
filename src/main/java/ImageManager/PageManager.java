package ImageManager;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class PageManager{


    private String url;
    private String pageContent = "";
    public PageManager(String url){
        this.url = url;
        takeContent();
    }
    public void takeContent(){
        try {
            URL url = new URL(this.url);
            // Get the input stream through URL Connection
            URLConnection con = url.openConnection();
            InputStream is =con.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String line = null;
            int counter = 1;
            // read each line and write to System.out
            while ((line = br.readLine()) != null) {
                this.pageContent+=line;
            }
        } catch (MalformedURLException e) {
            System.out.println("URL does not exist!!");
        } catch (IOException e) {
            System.out.println("Cannot read file!");
        }
    }

    public Image takeImage(String url){
        Image image = null;
        try {
            URL urlWithImage = new URL(url);
            image = ImageIO.read(urlWithImage);
            return image;
        } catch (IOException e) {
            System.out.println("Image could not be loaded");
        }
        return null;
    }

    public ArrayList<Image> takeAllImagesFromContent(){

        if(this.pageContent.length() < 5){
            System.out.println("No image found!!");
            return null;
        }

        ArrayList<Image> images = new ArrayList<Image>();

        String currentItemUrl = "";
        int counter = 0;
        for(int i = 0 ; i < this.pageContent.length(); i++){
            if( i + 4 < this.pageContent.length() && this.pageContent.substring(i,i+4).equals("<img")){
                counter++;
                for( int j = i + 4 ; j < this.pageContent.length(); j++){
                    if(this.pageContent.substring(j, j + 1).equals(">")){
                        i +=4;
                        j = this.pageContent.length();
                        continue;
                    }
                    else if(this.pageContent.substring(j,j+4).equals("src=")){
                        currentItemUrl = "";
                        for( int k = j + 5; k < this.pageContent.length(); k++){
                            String currentChar = this.pageContent.substring(k, k+ 1);
                            if(currentChar.equals("\"")){
                                System.out.println(currentItemUrl);
                                if(!currentItemUrl.contains("allegroimg")){
                                    j = k + 1;
                                    k = this.pageContent.length();
                                    continue;
                                }
                                Image img = takeImage(currentItemUrl);

                                if(img !=null)
                                images.add(img);

                                j = k + 1;
                                k = this.pageContent.length();
                                continue;
                            }
                            currentItemUrl+=currentChar;
                        }
                    }
                }
            }
        }
        System.out.println("Images Found : " + counter);
        return images;
    }

}