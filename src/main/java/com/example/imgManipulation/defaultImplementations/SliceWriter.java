package com.example.imgManipulation.defaultImplementations;

import com.example.imgManipulation.interfaces.ImgWriter;
import com.example.imgManipulation.interfaces.Pixel;
import com.example.imgManipulation.interfaces.Stripe;

import java.awt.image.BufferedImage;
import java.util.List;

/**
 * Created by atrposki on 30-Apr-17.
 */
public class SliceWriter implements ImgWriter {
    @Override
    public void writeToBuffer(BufferedImage img, List<Stripe> stripes) {
        int width=0;
        for (Stripe stripe :   stripes) {
            for (int height = 0; height < stripe.height(); height++) {
                int pixelWidhtRelativeToStripe=0;
                for (Pixel pixel :stripe.getRow(height)) {
                    img.setRGB(width+pixelWidhtRelativeToStripe,height,pixel.getRgb());
                    pixelWidhtRelativeToStripe++;
                }
                height++;
            }
            width+=stripe.width();
        }
    }
}
