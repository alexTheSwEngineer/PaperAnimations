package com.example.imgManipulation;

import com.example.imgManipulation.defaultImplementations.*;
import com.example.imgManipulation.defaultImplementations.BufferSupplier;
import com.example.imgManipulation.defaultImplementations.Slicer;
import com.example.imgManipulation.interfaces.*;
import com.example.imgManipulation.interfaces.Image;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * Created by atrposki on 30-Apr-17.
 */
public class Main {
    public static void main(String[] args) throws IOException {

        Animator animator = new AnimatorBuilder()
                                .withBuffer(new BufferSupplier())
                                .withInterlacer(new SimpleInterlacer())
                                .withSlicer(new Slicer())
                                .withWriter(new SliceWriter())
                                .build();

        ArrayList<BufferedImage> imgs = new ArrayList<>();
        for (int i = 1; i <=5 ; i++) {
            String path ="C:\\Users\\atrposki\\Desktop\\animations\\c";
            imgs.add(ImageIO.read(new File(path +  i+".png")));
        }
        Animator.Animation animation = animator.create(imgs.stream().map(x->new ImageWrapper(x)).collect(Collectors.toList()), new Dimension(20,2), 7);
        ImageIO.write(animation.getAnimation().getRaw(),"png",new File("C:\\Users\\atrposki\\Desktop\\animations\\res\\res.png"));
        ImageIO.write(animation.getGrid().getRaw(),"png",new File("C:\\Users\\atrposki\\Desktop\\animations\\res\\back.png"));

    }


}
