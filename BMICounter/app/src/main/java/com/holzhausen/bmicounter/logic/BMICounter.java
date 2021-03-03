package com.holzhausen.bmicounter.logic;

public abstract class BMICounter {

    final static int WEIGHT_AND_HEIGHT_BOTTOM_THRESHOLD = 0;
    double bmi;
    private int weight;
    private int height;

    public boolean setWeight(int weight) {
        if(isWeightValid(weight)) {
            this.weight = weight;
            if(isHeightValid(height)){
                countBmi();
            }
            return true;
        }
        return false;
    }


    public boolean setHeight(int height) {
        if(isHeightValid(height)) {
            this.height = height;
            if(isWeightValid(weight)){
                countBmi();
            }
            return true;
        }
        return false;
    }

    public double getBmi(){
        return bmi;
    }

    public int getWeight() {
        return weight;
    }

    public int getHeight() {
        return height;
    }

    abstract void countBmi();

    abstract boolean isHeightValid(int height);

    abstract boolean isWeightValid(int weight);




}
