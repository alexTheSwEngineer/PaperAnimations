package com.example.imgManipulation;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by atrposki on 30-Apr-17.
 */
@FunctionalInterface
public interface CollorEffects {
    BufferedImage toBiColor(Image image);
}
