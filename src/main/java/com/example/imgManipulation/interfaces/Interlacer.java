package com.example.imgManipulation.interfaces;

import java.util.List;

/**
 * Created by atrposki on 29-Apr-17.
 */
@FunctionalInterface
public interface Interlacer {
    List<Stripe> interlace(List<List<Stripe>> stripes);
}
