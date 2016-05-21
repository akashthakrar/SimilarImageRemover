package similarimageremover;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import javax.imageio.ImageIO;

/**
 *
 * @author Akash Thakrar
 */
public class SimilarImageRemover {

    
    public static void main(String[] args) throws IOException {                      
        Scanner in = new Scanner(System.in);
        System.out.println("Enter path to directory : ");
        String path  = in.nextLine();
        File folder = new File(path);
        List<File> listOfFiles = new LinkedList<File>(Arrays.asList(folder.listFiles()));       
        
        
        int i,j=0;        
        for (i = 0; i < listOfFiles.size(); i++) {
          if (((File)listOfFiles.get(i)).isFile() && ((((File)listOfFiles.get(i)).getName()).endsWith(".jpg") || (((File)listOfFiles.get(i)).getName()).endsWith(".png"))) {
          }
          else{
              listOfFiles.remove(i);
          }
        }
        System.out.println("List of files removed as below\n\n");
        for(i=0;i<listOfFiles.size()-1;i++){
            for(j=i+1;j<listOfFiles.size();j++){
                //Check if file size of images are same.
                if(((File)listOfFiles.get(i)).length() == ((File)listOfFiles.get(j)).length()){
                    BufferedImage img1 = ImageIO.read(((File)listOfFiles.get(i)));
                    BufferedImage img2 = ImageIO.read(((File)listOfFiles.get(j)));
                    //Check if resolution of images are same.
                    if((img1.getWidth()==img2.getWidth()) && (img1.getHeight()==img2.getHeight())){
                        //Get red among rgb of some pixels of both images.
                        Color c11 = new Color(img1.getRGB(0, 0));
                        Color c12 = new Color(img1.getRGB(1, 1));
                        Color c13 = new Color(img1.getRGB(2, 2));
                        Color c14 = new Color(img1.getRGB(img1.getHeight()/2, img1.getWidth()/2));
                        Color c15 = new Color(img1.getRGB(img1.getHeight()/2+1, img1.getWidth()/2+1));
                        Color c16 = new Color(img1.getRGB(img1.getHeight()/2-1, img1.getWidth()/2-1));                        
                        Color c17 = new Color(img1.getRGB(img1.getHeight()/3, img1.getWidth()/3));
                        Color c18 = new Color(img1.getRGB(img1.getHeight()/3+1, img1.getWidth()/3+1));                        
                        Color c19 = new Color(img1.getRGB(img1.getHeight()/3+2, img1.getWidth()/3+2));
                        
                        Color c21 = new Color(img2.getRGB(0, 0));
                        Color c22 = new Color(img2.getRGB(1, 1));
                        Color c23 = new Color(img2.getRGB(2, 2));
                        Color c24 = new Color(img2.getRGB(img2.getHeight()/2, img2.getWidth()/2));
                        Color c25 = new Color(img2.getRGB(img2.getHeight()/2+1, img2.getWidth()/2+1));
                        Color c26 = new Color(img2.getRGB(img2.getHeight()/2-1, img2.getWidth()/2-1));
                        Color c27 = new Color(img2.getRGB(img2.getHeight()/3, img2.getWidth()/3));
                        Color c28 = new Color(img2.getRGB(img2.getHeight()/3+1, img2.getWidth()/3+1));
                        Color c29 = new Color(img2.getRGB(img2.getHeight()/3+2, img2.getWidth()/3+2));
                       
                        //Check of that is same then image has highest probability to be same.
                        if( ( c11.getRed()!=c21.getRed() ) || ( c12.getRed()!=c22.getRed() ) || ( c13.getRed()!=c23.getRed() ) || ( c14.getRed()!=c24.getRed() ) || ( c15.getRed()!=c25.getRed() ) || ( c16.getRed()!=c26.getRed() ) || ( c17.getRed()!=c27.getRed() ) || ( c18.getRed()!=c28.getRed() ) || ( c19.getRed()!=c29.getRed() ) ){
                            continue;
                        }
                        else{     
                            //Removing the similar images keeping one image.
                            System.out.println(((File)listOfFiles.get(j)).getName());
                            ((File)listOfFiles.get(j)).delete();
                            listOfFiles.remove(j);
                            j--;                            
                        }                                
                    }
                }
            }
        }
    }    
}