package com.holzhausen.bmicounter.logic;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BMIClassification that = (BMIClassification) o;
        return Float.compare(that.bottomBound, bottomBound) == 0 &&
                Float.compare(that.upperBound, upperBound) == 0 &&
                classification.equals(that.classification);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bottomBound, upperBound, classification);
    }
}
