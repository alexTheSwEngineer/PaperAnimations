package com.example.imgManipulation;

import java.awt.image.BufferedImage;
import java.util.List;

/**
 * Created by atrposki on 30-Apr-17.
 */
@FunctionalInterface
public interface ImgWriter {
    void writeToBuffer(BufferedImage img,List<Stripe> stripe);
}
