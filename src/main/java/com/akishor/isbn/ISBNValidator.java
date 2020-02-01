package com.akishor.isbn;

public class ISBNValidator {

    private int LONG_ISBN_NUMBER_LENGTH=13;
    private int SMALL_ISBN_NUMBER_LENGTH=10;

    public boolean checkISBN(String isbn) {

        if (isbn.length() == LONG_ISBN_NUMBER_LENGTH) {
            return validateLongISBNNumber(isbn);
        }
        else if (isbn.length() == SMALL_ISBN_NUMBER_LENGTH) {
            return validateShortISBNNumber(isbn);
        }

        throw new NumberFormatException("ISBNs must be 10 digits long");

    }

    private boolean validateShortISBNNumber(String isbn) {
        int total = calculateShortISBNNumberTotal(isbn);

        int SHORT_ISBN_TOTAL_DIVIDER = 11;
        return isDivisibleBy(total, SHORT_ISBN_TOTAL_DIVIDER);

    }

    private int calculateShortISBNNumberTotal(String isbn) {
        int total = 0;
        for (int i = 0; i < SMALL_ISBN_NUMBER_LENGTH; i++) {

            char isbnChar = isbn.charAt(i);
            if (!Character.isDigit(isbnChar)) {
                if (isLastCharX(i, isbnChar)) {
                    total += 10;
                } else {
                    throw new NumberFormatException("ISBN cannot be non numeric");
                }

            }else {
                total += Character.getNumericValue(isbn.charAt(i)) * (10 - i);
            }
        }
        return total;
    }

    private boolean isLastCharX(int i, char isbnChar) {
        return i == 9 && isbnChar == 'X';
    }

    private boolean isDivisibleBy(int number, int divider) {
        return number % divider == 0;
    }

    private boolean isEvenNumber(int number) {
        return isDivisibleBy(number,2);
    }

    private boolean validateLongISBNNumber(String isbn) {

        int total = calculateLongISBNTotal(isbn);

        int LONG_ISBN_TOTAL_DIVIDER = 10;
        return isDivisibleBy(total, LONG_ISBN_TOTAL_DIVIDER);
    }

    private int calculateLongISBNTotal(String isbn) {
        int total = 0;
        for(int i=0; i<LONG_ISBN_NUMBER_LENGTH; i++) {

            int isbnDigit = Character.getNumericValue(isbn.charAt(i));
            if (isEvenNumber(i)) {
                total += isbnDigit;
            } else {
                total += isbnDigit * 3;
            }
        }
        return total;
    }
}
