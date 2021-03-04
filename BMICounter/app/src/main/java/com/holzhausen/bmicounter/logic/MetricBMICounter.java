package com.holzhausen.bmicounter.logic;

public class MetricBMICounter extends BMICounter{

    private static final int HEIGHT_UPPER_THRESHOLD = 400;

    private static final int WEIGHT_UPPER_THRESHOLD = 1000;

    @Override
    public void countBmi() {
       bmi = getWeight() / (getHeight() * getHeight() * 0.0001);
    }

    @Override
    public boolean isHeightValid(int height) {
        return WEIGHT_AND_HEIGHT_BOTTOM_THRESHOLD < height && height <= HEIGHT_UPPER_THRESHOLD;
    }

    @Override
    public boolean isWeightValid(int weight) {
        return WEIGHT_AND_HEIGHT_BOTTOM_THRESHOLD < weight && weight <= WEIGHT_UPPER_THRESHOLD;
    }
}
