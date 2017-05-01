package com.example.imgManipulation.interfaces;

import java.awt.image.BufferedImage;
import java.util.List;

/**
 * Created by atrposki on 30-Apr-17.
 */
@FunctionalInterface
public interface ImgWriter {
    void writeToBuffer(Image img,List<Stripe> stripe);
}
