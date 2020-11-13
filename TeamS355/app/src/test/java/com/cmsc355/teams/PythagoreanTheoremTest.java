package com.cmsc355.teams;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PythagoreanTheoremTest {

    @Test
    public void pythagoreanMethod(){
        double x = 3;
        double y = 4;
        double output;
        double expected = 5;

        PythagoreanTheorem pythagoreanTheorem = new PythagoreanTheorem();
        output = pythagoreanTheorem.pythagoreanMethod(x, y);

        assertEquals((int) expected, (int) output);


    }

}
