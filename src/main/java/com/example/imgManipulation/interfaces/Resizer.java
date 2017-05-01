package com.example.imgManipulation.interfaces;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by atrposki on 30-Apr-17.
 */
public interface Resizer {
    Image scaleToFit(Image img, Dimension size);
}
