package com.holzhausen.bmicounter.logic;

import java.util.Arrays;

public abstract class BMICounter {

    final static int WEIGHT_AND_HEIGHT_BOTTOM_THRESHOLD = 0;
    double bmi;
    private int weight;
    private int height;

    private static final BMIClassification[] bmiClassifications = {
            new BMIClassification(0, 16, "severe_thinness"),
            new BMIClassification(16, 17, "moderate_thinness"),
            new BMIClassification(17, 18.5f, "mild_thinness"),
            new BMIClassification(18.5f, 25, "normal"),
            new BMIClassification(25, 30, "overweight"),
            new BMIClassification(30, 35, "obese_first_class"),
            new BMIClassification(35, 40, "obese_second_class"),
            new BMIClassification(40, Float.MAX_VALUE, "obese_third_class"),
    };

    public abstract void countBmi();

    public abstract boolean isHeightValid(int height);

    public abstract boolean isWeightValid(int weight);

    public String getBmiClassification(){
        return Arrays.stream(bmiClassifications)
                .filter(bmiClassification -> bmiClassification.getBottomBound() <= bmi
                && bmi < bmiClassification.getUpperBound())
                .findFirst()
                .map(BMIClassification::getClassification)
                .orElse("black");
    }

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

}
