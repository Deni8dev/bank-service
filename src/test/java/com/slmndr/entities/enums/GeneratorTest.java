package com.slmndr.entities.enums;

import com.slmndr.util.Generator;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class GeneratorTest {

    @Test
    public void accountNumberGenerator() {
        final String s = Generator.accountNumberGenerator();
        final String[] split = s.split( " ");
        Assert.assertTrue(s.contains(" "));
        assertEquals(4, split.length);
    }
}
