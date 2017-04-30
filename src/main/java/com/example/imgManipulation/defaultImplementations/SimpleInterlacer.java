package com.example.imgManipulation.defaultImplementations;

import com.example.imgManipulation.interfaces.Pixel;
import com.example.imgManipulation.interfaces.Stripe;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by atrposki on 30-Apr-17.
 */
public class SimpleInterlacer implements com.example.imgManipulation.interfaces.Interlacer {
    @Override
    public List<Stripe> interlace(List<List<Stripe>> stripesPerPictures) {
        List<Stripe> result = new ArrayList<Stripe>();
        int maxStripeCount=0;
        for (List<Stripe> stripes : stripesPerPictures) {
            if (stripes.size() > maxStripeCount) {
                maxStripeCount=stripes.size();
            }
        }

        for(int frameOfset=0;frameOfset<maxStripeCount;frameOfset++){
            for (List<Stripe> stripesPerSingleFrame : stripesPerPictures) {
                if(frameOfset>=stripesPerSingleFrame.size()){
                    Stripe lastNonEmptyStripe = stripesPerSingleFrame.get(stripesPerSingleFrame.size()-1);
                    result.add(empty(lastNonEmptyStripe.width(),lastNonEmptyStripe.height(),lastNonEmptyStripe.getRow(0).get(0)));
                }else {
                    result.add(stripesPerSingleFrame.get(frameOfset));
                }
            }
        }

        return  result;
    }

    private static Stripe empty(int width, int height,Pixel emptyPixel){
        ArrayList<Pixel> emptyRow = new ArrayList<>();
        for (int i=0;i<width;i++){
            emptyRow.add(emptyPixel);
        }

        return  new Stripe() {
            @Override
            public List<Pixel> getRow(int i) {
                return emptyRow;
            }

            @Override
            public int height() {
                return height;
            }

            @Override
            public int width() {
                return width;
            }

            @Override
            public BufferedImage toImg() {
                return null;
            }
        };
    }
}
