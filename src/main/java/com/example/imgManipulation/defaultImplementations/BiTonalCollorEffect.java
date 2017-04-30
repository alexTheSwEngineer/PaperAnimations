package com.example.imgManipulation.defaultImplementations;

import com.example.imgManipulation.interfaces.CollorEffects;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by atrposki on 30-Apr-17.
 */
public class BiTonalCollorEffect implements CollorEffects {
    @Override
    public BufferedImage apply(BufferedImage orginalImage) {
        BufferedImage blackAndWhiteImg = new BufferedImage(
                orginalImage.getWidth(null), orginalImage.getHeight(null),
                BufferedImage.TYPE_BYTE_BINARY);

        Graphics2D graphics = blackAndWhiteImg.createGraphics();
        graphics.drawImage(orginalImage, 0, 0, null);
        return blackAndWhiteImg;
    }
}
