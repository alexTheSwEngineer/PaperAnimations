package com.example.imgManipulation.defaultImplementations;

import com.example.imgManipulation.interfaces.Stripe;

import java.awt.image.BufferedImage;
import java.util.List;

/**
 * Created by atrposki on 30-Apr-17.
 */
public class BufferSupplier implements com.example.imgManipulation.interfaces.BufferSupplier {
    @Override
    public BufferedImage create(List<Stripe> stripes) {
        int width=0;
        int height = 0;
        for (Stripe stripe:stripes) {
            if(stripe.height()>height){
                height = stripe.height();
            }
            width+=stripe.width();
        }
        return new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
    }
}
