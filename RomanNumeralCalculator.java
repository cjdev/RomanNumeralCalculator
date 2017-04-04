package com.company;

public class RomanNumeralCalculator {

    public static void main(String[] args) {
        System.out.println("hello world");
    }

    public static String addRomanNumerals(String... args){
        return String.join("", args);
    }

    public static String subtractRomanNumerals(String initial, String... rest){
        return initial;
    }

    public static String multiplyRomanNumerals(String... args){
        return "nulla";
    }

    public static String divideRomanNumerals(String numerator , String... denominators){
        return numerator;
    }
}

