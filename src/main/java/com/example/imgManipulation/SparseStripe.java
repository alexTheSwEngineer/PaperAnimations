package com.example.imgManipulation;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by atrposki on 30-Apr-17.
 */
public class SparseStripe implements Stripe {
    private ArrayList<List<Pixel>> rows = new ArrayList<>();
    private int width = 0;

    public void addOnTop(List<Pixel> newRow){
        rows.add(newRow);
        if(newRow.size()>width){
            width=newRow.size();
        }
    }

    @Override
    public List<Pixel> getRow(int i) {
        return rows.get(i);
    }

    @Override
    public int height() {
        return rows.size();
    }

    @Override
    public int width() {
        return width;
    }
}
