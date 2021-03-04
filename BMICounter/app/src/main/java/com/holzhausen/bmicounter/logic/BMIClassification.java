package com.holzhausen.bmicounter.logic;

class BMIClassification {

    private final float bottomBound;

    private final float upperBound;

    private final String classification;

    public BMIClassification(float bottomBound, float upperBound, String classification) {
        this.bottomBound = bottomBound;
        this.upperBound = upperBound;
        this.classification = classification;
    }

    public float getBottomBound() {
        return bottomBound;
    }

    public float getUpperBound() {
        return upperBound;
    }

    public String getClassification() {
        return classification;
    }
}
