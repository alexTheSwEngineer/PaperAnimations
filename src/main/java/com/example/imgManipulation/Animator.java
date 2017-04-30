package com.example.imgManipulation;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by atrposki on 30-Apr-17.
 */
public class Animator {
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

    public BufferedImage createAnimation(List<BufferedImage> frames, Dimension resultSize,int slidSize){

        List<List<Stripe>> stripes = createStripes(frames,resultSize,slidSize);
        List<Stripe> interlacedStripes = interlacer.interlace(stripes);
        BufferedImage bufferedImage = bufferSupplier.create(interlacedStripes);
        writer.writeToBuffer(bufferedImage, interlacedStripes);

        return outputResixer.scaleToFit(bufferedImage,resultSize);
    }

    private List<List<Stripe>> createStripes(List<BufferedImage> frames, Dimension resultSize,int slidSize){
        return  frames.stream()
                .map(x -> inputResizer.scaleToFit(x, resultSize))
                .map(effects::toBiColor)
                .map(x -> slicer.split(x, frames.size(),slidSize))
                .collect(Collectors.toList());
    }


}
