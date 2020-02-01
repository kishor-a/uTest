package com.akishor.isbn;

import org.junit.Test;

import static org.junit.Assert.*;


public class ISBNValidatorTest {

    @Test
    public void checkValidISBN() {
        ISBNValidator validator = new ISBNValidator();
        boolean result = validator.checkISBN("0132350882");
        assertTrue("First ISBN",result);

        result = validator.checkISBN("9350041243");
        assertTrue("Second ISBN",result);
    }

    @Test
    public void checkInvalidISBN(){
        ISBNValidator validator = new ISBNValidator();
        boolean result = validator.checkISBN("0132350881");
        assertFalse(result);
    }

    @Test(expected = NumberFormatException.class)
    public void nineDigitISBNsAreNotAllowed(){
        ISBNValidator validator = new ISBNValidator();
        validator.checkISBN("123456789");

    }

    @Test(expected = NumberFormatException.class)
    public void nonNumericISBNsAreNotAllowed(){
        ISBNValidator validator = new ISBNValidator();
        validator.checkISBN("helloworld");
    }

    @Test
    public void ISBNNumberEndingwithXAreAllowed(){
        ISBNValidator validator = new ISBNValidator();
        boolean result = validator.checkISBN("935286512X");
        assertTrue(result);
    }

    //13 digit

    @Test
    public void check13DigitValidISBN(){
        ISBNValidator validator = new ISBNValidator();
        boolean result = validator.checkISBN("9789350041246");
        assertTrue("First 13 digit isbn",result);

        result = validator.checkISBN("9789352865123");
        assertTrue("Second 13 digit ISBN", result);
    }

    @Test
    public void check13DigitInvalidISBN(){
        ISBNValidator validator = new ISBNValidator();
        boolean result = validator.checkISBN("9789350041244");
        assertFalse(result);
    }

}