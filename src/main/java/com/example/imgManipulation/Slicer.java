package com.example.imgManipulation;

import java.awt.Image;
import java.util.List;

/**
 * Created by atrposki on 29-Apr-17.
 */
public interface Slicer {
    List<Stripe> split(Image img, int slicePeriod);
}
