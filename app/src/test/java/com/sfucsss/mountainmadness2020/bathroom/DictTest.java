package com.sfucsss.mountainmadness2020.bathroom;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class DictTest {
    @Test
    public void check() {
        Dict dict = new Dict();
        assertEquals(dict.isValid("baby"), true);
    }
}
