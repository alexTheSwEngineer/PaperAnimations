package com.example.imgManipulation.defaultImplementations;

import com.example.imgManipulation.interfaces.Pixel;
import com.example.imgManipulation.interfaces.SparseStripe;
import com.example.imgManipulation.interfaces.Stripe;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by atrposki on 30-Apr-17.
 */
public class Slicer implements com.example.imgManipulation.interfaces.Slicer {
    @Override
    public List<Stripe> split(BufferedImage x, int period, int slidSize, int offset){
        ArrayList<Stripe> stripes=new ArrayList<Stripe>();
        int pictureWidth  = x.getWidth(null);
        int pictureHeight = x.getHeight(null);
        for (int i = 0; i < pictureWidth; i+=(period*slidSize)) {
            SparseStripe stripe=new SparseStripe();
            for(int j = 0; j<pictureHeight; j++){
                ArrayList<Pixel> pixels = new ArrayList<Pixel>();
                for(int p=0;p<slidSize;p++){
                    int pixelWidthIndex = i+p;
                    if(pixelWidthIndex>pictureWidth){
                        pixels.add(new Pixel(0));
                    }else{
                        pixels.add(new Pixel(x.getRGB(pixelWidthIndex,j)));
                    }
                }
                stripe.addOnTop(pixels);
            }
            stripes.add(stripe);
        }
        return stripes;

    }

}
