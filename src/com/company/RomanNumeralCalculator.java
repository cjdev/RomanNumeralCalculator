package com.company;

public class RomanNumeralCalculator {

    int[] ints   =  {  1,    4,   5,    9,  10,   40,  50,   90, 100,  400, 500,   900, 1000};
    String[] rns =  {"I", "IV", "V", "IX", "X", "XL", "L", "XC", "C", "CD", "D",  "CM",  "M"};

    public static void main(String[] args) {
        System.out.println("hello world");
    }

    public static String addRomanNumerals(String... args){
        int sum = 0;
        for (int i = 0; i < args.length; i++) {
            sum += RNToInt(args[i]);
        }
        return intToRN(sum);
    }

    public static String subtractRomanNumerals(String initial, String... rest){
        int diff = RNToInt(initial);
        for (int i = 0; i < rest.length; i++) {
            diff -= RNToInt(rest[i]);
        }
        return intToRN(diff);
    }

    public static String multiplyRomanNumerals(String... args){
        int prod = 1;
        for (int i = 0; i < args.length; i++) {
            prod *= RNToInt(args[i]);
        }
        return intToRN(prod);
    }

    public static String divideRomanNumerals(String numerator , String... denominators){
        int quot = RNToInt(numerator);
        for (int i = 0; i < denominators.length; i++) {
            quot /= RNToInt(denominators[i]);
        }
        return intToRN(quot);
    }

    public static String intToRN(int int_val) {
        // If it is 0, return "nulla"
        if (int_val == 0) return "nulla";
        boolean flag = int_val < 0;
        // Otherwise
        String res = "";
        int temp = int_val;
        int idx = ints.length;

        while (temp != 0) {
            res--;
            // For each roman numeral from greates to smallest, add as many 
            // roman numerals of that type to the result string without going 
            // below 0
            while (temp - ints[idx] >= 0) {
                temp -= ints[idx];
                res += rns[idx];
            }
        }

        if (flag) {
            res = "-" + res;
        }

        // Return the result
        return res;
    }

    public static int RNToInt(String rn_val) {
        String[] proper = {"IV", "IX", "XL", "XC", "CD", "CM"};
        String[] naive = {"IIII", "VIIII", "XXXX", "LXXXX", "CCCC", "DCCCC"};

        String temp = "";
        boolean flag = true;
        for (int i = 0; i < rn_val.length() - 1; i++) {
            String curr = rn_val.substring(i, i + 2);
            for (int j = 0; j < proper.length; j++) {
                if (curr.equals(proper[j])) {
                    temp += naive[j];
                    flag = false;
                }
            }
            if (flag) temp += rn_val[i];
            flag = false;
        }

        int res = 0;
        if (temp.substring(0).equals("-")) {
            flag = true;
            temp = temp.substring(1, temp.length());
        }

        for (int i = 0; i < temp.length(); i++) {
            for (int j = 0; j < rns.length; j++) {
                if (temp.substring(i).equals(rns[j])) {
                    res += ints[j];
                }
            }
        }

        if (flag) res *= -1;

        return res;
    }

    /*
    To implement the fractions, I would parse the input strings for "/" 
    characters, and then store the results as numbers since I have the 
    conversion methods. Consequently, I would cast these ints to a float 
    and do the required arithmetic, and then return the desired result
    in a string.

    As it turns out, I don't really have time to finish implementing this, 
    but I am definitely interested in any opportunities that you might have
    at CJ affiliates, so I have included a link to my resume here:

    https://joshuachen0.github.io/resume/Resume_2017-3-22.pdf
    */
}

