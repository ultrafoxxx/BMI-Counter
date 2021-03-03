package com.holzhausen.bmicounter.logic;

public class ImperialBMICounter extends BMICounter{

    private static final int IMPERIAL_MULTIPLY_FACTOR = 703;

    private static final int HEIGHT_UPPER_THRESHOLD = 158;

    private static final int WEIGHT_UPPER_THRESHOLD = 2205;

    @Override
    void countBmi() {
        bmi = ((double)getWeight()) / (getHeight() * getHeight()) * IMPERIAL_MULTIPLY_FACTOR;
    }

    @Override
    boolean isHeightValid(int height) {
        return WEIGHT_AND_HEIGHT_BOTTOM_THRESHOLD < height && height <= HEIGHT_UPPER_THRESHOLD;
    }

    @Override
    boolean isWeightValid(int weight) {
        return WEIGHT_AND_HEIGHT_BOTTOM_THRESHOLD < weight && weight <= WEIGHT_UPPER_THRESHOLD;
    }

}
