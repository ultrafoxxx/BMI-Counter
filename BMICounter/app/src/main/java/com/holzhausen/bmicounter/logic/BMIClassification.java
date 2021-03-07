package com.holzhausen.bmicounter.logic;

public class BMIClassification {

    private final float bottomBound;

    private final float upperBound;

    private final String classification;

    private final String description;

    public BMIClassification(final float bottomBound, final float upperBound,
                             final String classification, final String description) {
        this.bottomBound = bottomBound;
        this.upperBound = upperBound;
        this.classification = classification;
        this.description = description;
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

    public String getDescription() {
        return description;
    }
}
