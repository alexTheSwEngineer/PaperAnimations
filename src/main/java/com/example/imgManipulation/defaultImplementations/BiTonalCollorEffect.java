package com.example.imgManipulation.defaultImplementations;

import com.example.imgManipulation.interfaces.*;
import com.example.imgManipulation.interfaces.Image;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by atrposki on 30-Apr-17.
 */
public class BiTonalCollorEffect implements CollorEffects {
    @Override
    public Image apply(Image orginalImage) {
        BufferedImage bitonalEncoded = new BufferedImage(orginalImage.getWidth(),orginalImage.getHeight(),BufferedImage.TYPE_BYTE_BINARY);
        Graphics2D graphics = bitonalEncoded.createGraphics();
        graphics.drawImage(orginalImage.getRaw(),0,0,null);
        BufferedImage bitonalARGBEncoded =new BufferedImage(orginalImage.getWidth(),orginalImage.getHeight(),BufferedImage.TYPE_BYTE_BINARY);
        graphics = bitonalARGBEncoded.createGraphics();
        graphics.drawImage(bitonalEncoded,0,0,null);
        return new ImageWrapper(bitonalARGBEncoded);
    }
}
