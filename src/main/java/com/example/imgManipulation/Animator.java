package com.example.imgManipulation;

import com.example.imgManipulation.interfaces.*;
import com.example.imgManipulation.interfaces.Image;

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
        private Image animation;
        private Image grid;

        public Animation(Image animation, Image grid) {
            this.animation = animation;
            this.grid = grid;
        }

        public Image getAnimation() {
            return animation;
        }

        public Image getGrid() {
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

    public Animation create(List<Image> frames,Dimension size,int slidSize){
        Image animation = createAnimation(frames,size,slidSize);
        Dimension animationActualSize = new Dimension(animation.getWidth(), animation.getHeight());
        Image grid = createOverlay(animationActualSize,slidSize,frames.size());
        return new Animation(animation,grid);
    }

    private Image createOverlay(Dimension size, int slidSize, int period){

        Stripe whiteStripe = createMonoStripe(slidSize,size.height,new Pixel(Color.white.getRGB()));
        Stripe blackStripe = createMonoStripe(slidSize*(period-1),size.height,new Pixel(Color.black.getRGB()));
        ArrayList<Stripe> grid = new ArrayList<>();

        int coveredWidth = 0;
        while (coveredWidth<size.getWidth()){
            grid.add(whiteStripe);
            grid.add(blackStripe);
            coveredWidth += whiteStripe.width()+blackStripe.width();
        }

         Image img = bufferSupplier.create(grid);
         writer.writeToBuffer(img,grid);
        return img;
    }

    private Stripe createMonoStripe(int width, int height, Pixel pixel){
        List<Pixel> emptyRow = new ArrayList<>();
        for (int i = 0; i < width; i++) {
            emptyRow.add(pixel);
        }

        SparseStripe monotoneStripe = new SparseStripe();
        for (int i = 0; i < height; i++) {
            monotoneStripe.addOnTop(emptyRow);
        }

        return monotoneStripe;

    }

    private Image createAnimation(List<Image> frames, Dimension resultSize, int slidSize){

        List<Image> normalizedFrames;
        normalizedFrames = frames.stream()
                                 .map(x -> inputResizer.scaleToFit(x, resultSize))
                                 .map(effects::apply)
                                 .collect(Collectors.toList());
        List<List<Stripe>> stripes = createStripes(normalizedFrames,resultSize,slidSize);
        List<Stripe> interlacedStripes = interlacer.interlace(stripes);
        Image bufferedImage = bufferSupplier.create(interlacedStripes);
        writer.writeToBuffer(bufferedImage, interlacedStripes);

        return outputResixer.scaleToFit(bufferedImage,resultSize);
    }

    private List<List<Stripe>> createStripes(List<Image> frames, Dimension resultSize,int slidSize){
        int offset = 0;
        int period = frames.size();
        ArrayList<List<Stripe>> result =new ArrayList<>();
        for (Image frame :
                frames) {
            Image scaled = inputResizer.scaleToFit(frame, resultSize);
            Image colorized = effects.apply(scaled);
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
