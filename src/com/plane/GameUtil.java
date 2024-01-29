package com.plane;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;

public class GameUtil {
    //It's better to make the constructor of util class private
    private GameUtil(){
    }

    public static Image getImage(String path){
        BufferedImage bi = null;
        try {
            URL u = GameUtil.class.getClassLoader().getResource(path);
            bi = ImageIO.read(u);
        } catch (IOException e){
            e.printStackTrace();
        }
        return bi;
    }

}
