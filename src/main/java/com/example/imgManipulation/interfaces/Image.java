package com.example.imgManipulation.interfaces;

import java.awt.image.BufferedImage;

/**
 * Created by atrposki on 30-Apr-17.
 */
public interface Image {
    BufferedImage getRaw();
    int getWidth();
    int getHeight();
    Pixel getPixel(int x,int y);
    void setPixel(int x, int y, Pixel pixel);
}
