package com.holzhausen.bmicounter;

import com.holzhausen.bmicounter.logic.BMIClassification;
import com.holzhausen.bmicounter.logic.BMICounter;
import com.holzhausen.bmicounter.logic.MetricBMICounter;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class BmiMetricCounterTest {

    private BMICounter metricCounter;

    @Before
    public void setUp() {
        metricCounter = new MetricBMICounter();
    }

    @Test
    public void testDataValidating(){

        int tooLowHeight = -256;
        int correctHeight = 199;
        int tooHighHeight = 1254;

        int tooLowWeight = -122;
        int correctWeight = 84;
        int tooHighWeight = 1251;

        assertFalse(metricCounter.isHeightValid(tooLowHeight));
        assertTrue(metricCounter.isHeightValid(correctHeight));
        assertFalse(metricCounter.isHeightValid(tooHighHeight));

        assertFalse(metricCounter.isWeightValid(tooLowWeight));
        assertTrue(metricCounter.isWeightValid(correctWeight));
        assertFalse(metricCounter.isWeightValid(tooHighWeight));

    }

    @Test
    public void testBmiCountAndClassification(){
        metricCounter.setWeight(93);
        metricCounter.setHeight(180);
        metricCounter.countBmi();
        assertEquals(28.7, metricCounter.getBmi(), 0.01);
        final BMIClassification expectedClassification = new BMIClassification(25, 30, "overweight",
                "You are overweight");
        assertEquals(expectedClassification, metricCounter.getBmiClassification());
    }

}
