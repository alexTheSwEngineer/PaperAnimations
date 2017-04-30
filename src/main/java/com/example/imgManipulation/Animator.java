package com.example.imgManipulation;

import com.example.imgManipulation.interfaces.*;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by atrposki on 30-Apr-17.
 */
public class Animator {

    public static class Animation{
        private BufferedImage animation;
        private BufferedImage grid;

        public Animation(BufferedImage animation, BufferedImage grid) {
            this.animation = animation;
            this.grid = grid;
        }

        public BufferedImage getAnimation() {
            return animation;
        }

        public BufferedImage getGrid() {
            return grid;
        }
    }

    private CollorEffects effects;
    private Interlacer interlacer;
    private Slicer slicer;
    private Resizer inputResizer;
    private Resizer outputResixer;
    private ImgWriter writer;
    private BufferSupplier bufferSupplier;

    public Animator(CollorEffects effects, Interlacer interlacer, Slicer slicer, Resizer inputResizer, Resizer outputResixer, ImgWriter writer, BufferSupplier bufferSupplier) {
        this.effects = effects;
        this.interlacer = interlacer;
        this.slicer = slicer;
        this.inputResizer = inputResizer;
        this.outputResixer = outputResixer;
        this.writer = writer;
        this.bufferSupplier = bufferSupplier;
    }

    public Animation create(List<BufferedImage> frames,Dimension size,int slidSize){
        BufferedImage animation = createAnimation(frames,size,slidSize);
        BufferedImage grid = createOverlay(size,slidSize,frames.size());
        return new Animation(animation,grid);
    }

    private BufferedImage createOverlay(Dimension size,int slidSize,int period){
        List<Pixel> emptyRow = new ArrayList<>();
        for (int i = 0; i < slidSize; i++) {
            emptyRow.add(new Pixel(16777215));
        }

        SparseStripe emptyStripe = new SparseStripe();
        for (int i = 0; i < size.getHeight(); i++) {
            emptyStripe.addOnTop(emptyRow);
        }

        List<Pixel> blackRow = new ArrayList<>();
        for (int i = 0; i < slidSize; i++) {
            emptyRow.add(new Pixel(0));
        }

        SparseStripe darkStripe = new SparseStripe();
        for (int i = 0; i < size.getHeight(); i++) {
            darkStripe.addOnTop(emptyRow);
        }

        ArrayList<Stripe> grid = new ArrayList<>();
        int coveredWidth =0;
        while (coveredWidth<size.getWidth()){
            grid.add(emptyStripe);
            for (int i = 0; i < period - 1; i++) {
                grid.add(darkStripe);
            }
            coveredWidth += period*slidSize;
        }
        BufferedImage img = bufferSupplier.create(grid);
         writer.writeToBuffer(img,grid);
        return img;
    }

    private BufferedImage createAnimation(List<BufferedImage> frames, Dimension resultSize,int slidSize){

        List<BufferedImage> normalizedFrames = frames.stream()
                .map(x -> inputResizer.scaleToFit(x, resultSize))
                .map(effects::apply)
                .collect(Collectors.toList());
        List<List<Stripe>> stripes = createStripes(normalizedFrames,resultSize,slidSize);
        List<Stripe> interlacedStripes = interlacer.interlace(stripes);
        BufferedImage bufferedImage = bufferSupplier.create(interlacedStripes);
        writer.writeToBuffer(bufferedImage, interlacedStripes);

        return outputResixer.scaleToFit(bufferedImage,resultSize);
    }

    private List<List<Stripe>> createStripes(List<BufferedImage> frames, Dimension resultSize,int slidSize){
        int offset = 0;
        int period = frames.size();
        ArrayList<List<Stripe>> result =new ArrayList<>();
        for (BufferedImage frame :
                frames) {
            BufferedImage scaled = inputResizer.scaleToFit(frame, resultSize);
            BufferedImage colorized = effects.apply(scaled);
            if(offset>=period){
                offset = offset%period;
            }
            List<Stripe> stripes = slicer.split(colorized, frames.size(), slidSize, offset);
            offset++;
            result.add(stripes);
        }
        return  result;
    }


}
