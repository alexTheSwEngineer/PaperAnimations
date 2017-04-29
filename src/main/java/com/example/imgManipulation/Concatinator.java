package com.example.imgManipulation;

import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * Created by atrposki on 29-Apr-17.
 */
public interface Concatinator {
    Image concat(List<Stripe> stripes);
}
