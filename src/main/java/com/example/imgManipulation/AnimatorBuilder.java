package com.example.imgManipulation;

import java.awt.image.BufferedImage;

/**
 * Created by atrposki on 30-Apr-17.
 */
public class AnimatorBuilder {
    private CollorEffects effects;
    private Interlacer interlacer;
    private Slicer slicer;
    private Resizer inputResizer;
    private Resizer outputResixer;
    private ImgWriter writer;
    private BufferSupplier bufferSupplier;

    public AnimatorBuilder(){}

    private AnimatorBuilder(AnimatorBuilder other) {
        this.effects = other.effects;
        this.interlacer = other.interlacer;
        this.slicer = other.slicer;
        this.inputResizer = other.inputResizer;
        this.outputResixer = other.outputResixer;
        this.writer = other.writer;
        this.bufferSupplier = other.bufferSupplier;
    }

    public Animator build(){
        if(effects==null){
            effects = x->(BufferedImage)x;
        }

        if(inputResizer==null){
            inputResizer=AnimatorBuilder::unit;
        }

        if(outputResixer==null){
            outputResixer=AnimatorBuilder::unit;
        }

        if(slicer == null){
            throw new IllegalStateException("Slicer must be provided");
        }

        if(bufferSupplier == null){
            throw new IllegalStateException("Buffer supplier must be provided");
        }

        if(writer == null){
            throw new IllegalStateException("BufferWriter must be provided");
        }

        if(interlacer == null){
            throw new IllegalStateException("Interlacer must be provided");
        }

        return new Animator(effects,interlacer,slicer,inputResizer,outputResixer,writer,bufferSupplier);
    }

    public AnimatorBuilder withEffect(CollorEffects effect){
        AnimatorBuilder newAnimatorBuilder = new AnimatorBuilder(this);
        newAnimatorBuilder.effects=effect;
        return newAnimatorBuilder;
    }

    public AnimatorBuilder withFrameResizing(Resizer resizer){
        AnimatorBuilder newAnimatorBuilder = new AnimatorBuilder(this);
        newAnimatorBuilder.inputResizer=resizer;
        return newAnimatorBuilder;
    }

    public AnimatorBuilder withResultResizing(Resizer resizer){
        AnimatorBuilder newAnimatorBuilder = new AnimatorBuilder(this);
        newAnimatorBuilder.outputResixer=resizer;
        return newAnimatorBuilder;
    }

    public AnimatorBuilder withWriter(ImgWriter writer){
        AnimatorBuilder newAnimatorBuilder = new AnimatorBuilder(this);
        newAnimatorBuilder.writer=writer;
        return newAnimatorBuilder;
    }

    public AnimatorBuilder withBuffer(BufferSupplier bufferSupplier){
        AnimatorBuilder newAnimatorBuilder = new AnimatorBuilder(this);
        newAnimatorBuilder.bufferSupplier=bufferSupplier;
        return newAnimatorBuilder;
    }

    public AnimatorBuilder withInterlacer(Interlacer interlacer){
        AnimatorBuilder newAnimatorBuilder = new AnimatorBuilder(this);
        newAnimatorBuilder.interlacer=interlacer;
        return newAnimatorBuilder;
    }

    public AnimatorBuilder withSlicer(Slicer slicer){
        AnimatorBuilder newAnimatorBuilder = new AnimatorBuilder(this);
        newAnimatorBuilder.slicer=slicer;
        return newAnimatorBuilder;
    }

    public static <T> T unit(T in){
        return in;
    }

    public static <Tout,Tmodifier> Tout unit(Tout in,Tmodifier tmodifier){
        return in;
    }
}
