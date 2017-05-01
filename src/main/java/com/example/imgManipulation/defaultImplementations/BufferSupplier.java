package com.example.imgManipulation.defaultImplementations;

import com.example.imgManipulation.interfaces.Image;
import com.example.imgManipulation.interfaces.Pixel;
import com.example.imgManipulation.interfaces.Stripe;

import java.awt.image.BufferedImage;
import java.util.List;

import static java.awt.image.BufferedImage.TYPE_INT_ARGB;
import static java.awt.image.BufferedImage.TYPE_INT_RGB;

/**
 * Created by atrposki on 30-Apr-17.
 */
public class BufferSupplier implements com.example.imgManipulation.interfaces.BufferSupplier {
    @Override
    public Image create(List<Stripe> stripes) {
        int width=0;
        int height = 0;
        for (Stripe stripe:stripes) {
            if(stripe.height()>height){
                height = stripe.height();
            }
            width+=stripe.width();
        }
        Image img= new ImageWrapper(new BufferedImage(width,height, TYPE_INT_ARGB));
        for (int i = 0; i < img.getWidth(); i++) {
            for (int j = 0; j < img.getHeight(); j++) {
                img.setPixel(i,j,new Pixel(-16735512));
            }
        }
        return  img;
    }
}
