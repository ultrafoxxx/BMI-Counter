package com.holzhausen.bmicounter;

import com.holzhausen.bmicounter.logic.BMIClassification;
import com.holzhausen.bmicounter.logic.BMICounter;
import com.holzhausen.bmicounter.logic.ImperialBMICounter;
import com.holzhausen.bmicounter.logic.MetricBMICounter;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class BmiImperialCounterTest {

    private BMICounter imperialCounter;

    @Before
    public void setUp() {
        imperialCounter = new ImperialBMICounter();
    }

    @Test
    public void testDataValidating(){

        int tooLowHeight = -256;
        int correctHeight = 81;
        int tooHighHeight = 199;

        int tooLowWeight = -122;
        int correctWeight = 1251;
        int tooHighWeight = 3678;

        assertFalse(imperialCounter.isHeightValid(tooLowHeight));
        assertTrue(imperialCounter.isHeightValid(correctHeight));
        assertFalse(imperialCounter.isHeightValid(tooHighHeight));

        assertFalse(imperialCounter.isWeightValid(tooLowWeight));
        assertTrue(imperialCounter.isWeightValid(correctWeight));
        assertFalse(imperialCounter.isWeightValid(tooHighWeight));

    }

    @Test
    public void testBmiCountAndClassification(){
        imperialCounter.setWeight(120);
        imperialCounter.setHeight(70);
        imperialCounter.countBmi();
        assertEquals(17.21, imperialCounter.getBmi(), 0.01);
        final BMIClassification expectedClassification = new BMIClassification(17, 18.5f, "mild_thinness",
                "You are mildly thin");
        assertEquals(expectedClassification, imperialCounter.getBmiClassification());
    }

}
