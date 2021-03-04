package com.holzhausen.bmicounter.logic;

public abstract class BMICounter {

    final static int WEIGHT_AND_HEIGHT_BOTTOM_THRESHOLD = 0;
    double bmi;
    private int weight;
    private int height;

    public void setWeight(int weight) {
        this.weight = weight;
    }


    public void setHeight(int height) {
        this.height = height;
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

    public abstract void countBmi();

    public abstract boolean isHeightValid(int height);

    public abstract boolean isWeightValid(int weight);




}
