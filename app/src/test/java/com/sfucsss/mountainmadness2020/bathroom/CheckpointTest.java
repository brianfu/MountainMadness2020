package com.sfucsss.mountainmadness2020.bathroom;

import org.junit.Test;

import static org.junit.Assert.*;

public class CheckpointTest {
    @Test
    public void test1() {
        AugmentedPin pin = new AugmentedPin(10, 10, 'i', 10);
        Checkpoint checkpoint = new Checkpoint();
        assertEquals(checkpoint.currentWord(), "");
        assertEquals(checkpoint.totalTime(), 0.0, 0.0);
        assertEquals(checkpoint.score(), 0.0, 0.0);
        assertFalse(checkpoint.contains(pin));
        assertTrue(checkpoint.equal(checkpoint));
        checkpoint.update(pin);
        assertEquals(checkpoint.currentWord(), "i");
        assertEquals(checkpoint.totalTime(), 10.0, 10.0);
        assertEquals(checkpoint.score(), 1.0, 1.0);
        assertTrue(checkpoint.contains(pin));
        assertFalse(checkpoint.equal(new Checkpoint()));
        assertTrue(checkpoint.equal(checkpoint));
        checkpoint.update(new AugmentedPin(50, 50, 'c', 20));
        assertEquals(checkpoint.currentWord(), "ic");
        assertEquals(checkpoint.totalTime(), 20.0, 20.0);
        assertEquals(checkpoint.score(), 2.0, 2.0);
        assertTrue(checkpoint.contains(pin));
        assertTrue(checkpoint.contains(new AugmentedPin(50, 50, 'c', 20)));
        assertFalse(checkpoint.equal(new Checkpoint()));
        assertTrue(checkpoint.equal(checkpoint));
    }
}
