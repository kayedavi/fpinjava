package com.github.kayedavi.fpinjava;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LibraryTest {
    @Test
    void testSomeLibraryMethod() {
        var classUnderTest = new Library();
        assertTrue(classUnderTest.someLibraryMethod(), "someLibraryMethod should return 'true'");
    }
}