package com.example.imgManipulation.defaultImplementations;

import com.example.imgManipulation.interfaces.Image;
import com.example.imgManipulation.interfaces.ImgWriter;
import com.example.imgManipulation.interfaces.Pixel;
import com.example.imgManipulation.interfaces.Stripe;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by atrposki on 30-Apr-17.
 */
public class SliceWriter implements ImgWriter {

    @Override
    public void writeToBuffer(Image img, List<Stripe> stripes) {
        int width=0;
        for (Stripe stripe :   stripes) {
            for (int height = 0; height < stripe.height() && height<img.getHeight(); height++) {
                int pixelWidhtRelativeToStripe=0;
                for (Pixel pixel : stripe.getRow(height)) {
                    int pixelAbsoluteWidth = width+pixelWidhtRelativeToStripe;
                    if(pixelAbsoluteWidth>= img.getWidth()){
                        break;
                    }
                    img.setPixel(pixelAbsoluteWidth,height,pixel);
                    pixelWidhtRelativeToStripe++;
                }
            }
            width+=stripe.width();
        }
    }
}
