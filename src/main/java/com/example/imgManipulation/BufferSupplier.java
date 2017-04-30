package com.example.imgManipulation;

import java.awt.image.BufferedImage;
import java.util.List;

/**
 * Created by atrposki on 30-Apr-17.
 */
@FunctionalInterface
public interface BufferSupplier {
    BufferedImage create(List<Stripe> stripes);
}
