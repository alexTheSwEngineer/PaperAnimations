package com.example.imgManipulation;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.List;

/**
 * Created by atrposki on 29-Apr-17.
 */
@FunctionalInterface
public interface Slicer {
    List<Stripe> split(BufferedImage img, int slicePeriod, int slidSizes);
}
