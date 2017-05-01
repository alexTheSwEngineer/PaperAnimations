package com.example.imgManipulation.defaultImplementations;

import com.example.imgManipulation.interfaces.CollorEffects;
import com.example.imgManipulation.interfaces.Image;
import com.example.imgManipulation.interfaces.Pixel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by atrposki on 30-Apr-17.
 */
public class ImageWrapper implements Image {
    private BufferedImage image;
    private Graphics2D graphics2D;
    public ImageWrapper(BufferedImage img){
        for (int i = 0; i < img.getWidth(); i++) {
            for (int j = 0; j < img.getHeight(); j++) {
                if(img.getRGB(i,j)==0){
                    img.setRGB(i,j, Color.ORANGE.getRGB());
                }
            }
        }
        this.image=img;
        graphics2D = img.createGraphics();
    }
    public ImageWrapper(String file) throws IOException {
        image = ImageIO.read(new File(file));
    }

    @Override
    public BufferedImage getRaw() {
        return  image;
    }

    @Override
    public int getWidth() {
        return image.getWidth();
    }

    @Override
    public int getHeight() {
        return image.getHeight();
    }

    @Override
    public Pixel getPixel(int x, int y) {
        return new Pixel(image.getRGB(x,y));
    }

    @Override
    public void setPixel(int x, int y, Pixel pixel) {
        image.setRGB(x,y,pixel.getRGB());
    }
}
