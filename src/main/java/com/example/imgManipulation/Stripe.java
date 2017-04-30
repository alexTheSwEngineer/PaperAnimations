package com.example.imgManipulation;

import java.awt.image.BufferedImage;
import java.util.List;

/**
 * Created by atrposki on 29-Apr-17.
 */

public interface Stripe {
    List<Pixel> getRow(int i);
    int height();
    int width();
    BufferedImage toImg();
}
