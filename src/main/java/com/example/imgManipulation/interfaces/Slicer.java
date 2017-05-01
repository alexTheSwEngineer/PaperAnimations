package com.example.imgManipulation.interfaces;

import java.awt.image.BufferedImage;
import java.util.List;

/**
 * Created by atrposki on 29-Apr-17.
 */
@FunctionalInterface
public interface Slicer {
    List<Stripe> split(Image img, int slicePeriod, int slidSizes, int ofset);
}
