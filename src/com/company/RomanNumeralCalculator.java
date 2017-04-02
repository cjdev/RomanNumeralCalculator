package com.company;

import com.company.RomanNumeralConverter.*;

public class RomanNumeralCalculator {
    public static void main(String[] args) {
        /* 
        Used for testing since I wasn't able to run the unit tests w/o Internet 
        access or an IDE
        */
        String[] romanNumerals = new String[]{"I", "II"};
        String actual = addRomanNumerals(romanNumerals);
        System.out.println(String.format("%s = %s", actual, "III"));

        String[] romanNumerals2 = new String[]{"II", "III", "I"};
        String actual2 = addRomanNumerals(romanNumerals2);
        System.out.println(String.format("%s = %s", actual2, "VI"));

        String[] romanNumerals3 = new String[]{"XL", "LX"};
        String actual3 = addRomanNumerals(romanNumerals3);
        System.out.println(String.format("%s = %s", actual3, "C"));

        String[] romanNumerals4 = new String[]{"III iii/v", "I ii/v", "V"};
        String actual4 = addRomanNumerals(romanNumerals4);
        System.out.println(String.format("%s = %s", actual4, "X"));

        String[] romanNumerals5 = new String[]{"-I", "I", "V", "-V"};
        String actual5 = addRomanNumerals(romanNumerals5);
        System.out.println(String.format("%s = %s", actual5, "nulla"));

        String[] romanNumerals6 = new String[]{"nulla"};
        String actual6 = subtractRomanNumerals("III", romanNumerals6);
        System.out.println(String.format("%s = %s", actual6, "III"));

        String[] romanNumerals7 = new String[]{"III", "I"};
        String actual7 = subtractRomanNumerals("V", romanNumerals7);
        System.out.println(String.format("%s = %s", actual7, "I"));

        String[] romanNumerals8 = new String[]{"III i/v"};
        String actual8 = subtractRomanNumerals("III ii/v", romanNumerals8);
        System.out.println(String.format("%s = %s", actual8, "i/v"));

        String[] romanNumerals9 = new String[]{"V", "III", "I"};
        String actual9 = subtractRomanNumerals("III", romanNumerals9);
        System.out.println(String.format("%s = %s", actual9, "-VI"));

        String[] romanNumerals10 = new String[]{"-I", "-C", "XVII"};
        String actual10 = subtractRomanNumerals("-I", romanNumerals10);
        System.out.println(String.format("%s = %s", actual10, "LXXXIII"));

        String[] romanNumerals11 = new String[]{"nulla", "I", "-CXXVI"};
        String actual11 = multiplyRomanNumerals(romanNumerals11);
        System.out.println(String.format("%s = %s", actual11, "nulla"));

        String[] romanNumerals12 = new String[]{"III", "III", "-III i/ii", "XI i/ii"};
        String actual12 = multiplyRomanNumerals(romanNumerals12);
        System.out.println(String.format("%s = %s", actual12, "-CCCLXII i/iv"));

        String[] romanNumerals13 = new String[]{"vi/vii", "vii/viii"};
        String actual13 = multiplyRomanNumerals(romanNumerals13);
        System.out.println(String.format("%s = %s", actual13, "iii/iv"));

        String[] romanNumerals14 = new String[]{"I", "I i/x", "I", "I"};
        String actual14 = multiplyRomanNumerals(romanNumerals14);
        System.out.println(String.format("%s = %s", actual14, "I i/x"));

        String[] romanNumerals15 = new String[]{"MCCCXXXVII", "II"};
        String actual15 = multiplyRomanNumerals(romanNumerals15);
        System.out.println(String.format("%s = %s", actual15, "MMDCLXXIV"));

        String[] romanNumerals16 = new String[]{"I"};
        String actual16 = divideRomanNumerals("XVI", romanNumerals16);
        System.out.println(String.format("%s = %s", actual16, "XVI"));

        String[] romanNumerals17 = new String[]{"nulla"};

        try {
            divideRomanNumerals("XVI", romanNumerals17);
        } catch (IllegalArgumentException e) {
        }

        String[] romanNumerals18 = new String[]{"ii/iii"};
        String actual18 = divideRomanNumerals("ix/x", romanNumerals18);
        System.out.println(String.format("%s = %s", actual18, "I vii/xx"));

        String[] romanNumerals19 = new String[]{"VII"};
        String actual19 = divideRomanNumerals("XVI", romanNumerals19);
        System.out.println(String.format("%s = %s", actual19, "II xxix/c"));

        String[] romanNumerals20 = new String[]{"XIII"};
        String actual20 = divideRomanNumerals("I", romanNumerals20);
        System.out.println(String.format("%s = %s", actual20, "ii/xxv"));
    }

    public static String addRomanNumerals(String... args){
        double total = 0.0;

        for (int i=0; i < args.length; i++) {
            double number = RomanNumeralConverter.romanToDecimal(args[i]);
            
            total += number;
        }

        return RomanNumeralConverter.decimalToRoman(total);
    }

    public static String subtractRomanNumerals(String initial, String... rest){
        double total = RomanNumeralConverter.romanToDecimal(initial);

        for (int i=0; i < rest.length; i++) {
            double number = RomanNumeralConverter.romanToDecimal(rest[i]);
            
            total -= number;
        }

        return RomanNumeralConverter.decimalToRoman(total);
    }

    public static String multiplyRomanNumerals(String... args){
        double total = 1.0;

        for (int i=0; i < args.length; i++) {

            double number = RomanNumeralConverter.romanToDecimal(args[i]);
            
            total *= number;
        }

        return RomanNumeralConverter.decimalToRoman(total);
    }

    public static String divideRomanNumerals(String numerator , String... denominators){
        double total = RomanNumeralConverter.romanToDecimal(numerator);

        for (int i=0; i < denominators.length; i++) {
            double number = RomanNumeralConverter.romanToDecimal(denominators[i]);

            if (number == 0) {
                throw new IllegalArgumentException();
            }
            
            total /= number;
        }

        return RomanNumeralConverter.decimalToRoman(total);        
    }
}
