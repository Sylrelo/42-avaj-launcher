package com.slopez.avaj.exceptions;

public class InvalidMd5Hash extends Exception {
    public InvalidMd5Hash(String expected, String calculated) {
        super(String.format("Invalid hash comparison : %s != %s", expected, calculated));
    }
}
