package com.company;

import java.math.BigDecimal;
//BigDecimal is used to ensure precision while using Doubles/doubles
public class RomanNumeralCalculator {

    public static void main(String[] args) {
        //not used
    }

    public static String addRomanNumerals(String... args){
        //adds RomanNumerals
        Double value = 0.0;
        int arrlen = args.length;
        for(int i = 0; i < arrlen; i++) {
            value += parser(args[i]);
        }
        return convertToRoman(value);
    }

    public static String subtractRomanNumerals(String initial, String... rest){
        //subtracts RomanNumerals
        Double value = 0.0;
        int arrlen = rest.length;
        for(int i = 0; i < arrlen; i++) {
            value += parser(rest[i]);
        }
        Double init = parser(initial);
        BigDecimal init_temp = BigDecimal.valueOf(init);
        BigDecimal val_temp = BigDecimal.valueOf(value);
        BigDecimal result = init_temp.subtract(val_temp);
        double temp = result.doubleValue();
        return convertToRoman(temp);
    }

    public static String multiplyRomanNumerals(String... args){
        //Multiplies RomanNumerals
        Double value = 1.0;
        int arrlen = args.length;
        for(int i = 0; i < arrlen; i++) {
            value = value * parser(args[i]);
        }
        return convertToRoman(value);
    }

    public static String divideRomanNumerals(String numerator , String... denominators){
        //divides RomanNumerals
        Double result = parser(numerator);
        for(int i = denominators.length - 1; i >= 0; i--)
        {   if(denominators[i] == "nulla")
            //throws exception when dividing by zero;
            throw new IllegalArgumentException("divide by zero error");
            Double divisor = parser(denominators[i]);
            result = result /divisor;
        }
        return convertToRoman(result);
    }

    public static int convertToWhole(String arg) {
        //converts the whole numbers to roman numerals
        int value = 0;
        int counter = 0;
        char last = 'z';
        int pos = arg.length()-1;
        while (pos != -1){
            char current = arg.charAt(pos);
            if(current == 'I' && last == 'V')
                value -= 1;
            else if(current == 'I' && last == 'X')
                value -= 1;
            else if(current == 'X' && last == 'L')
                value -= 10;
            else if(current == 'X' && last == 'C')
                value -= 10;
            else if(current == 'C' && last == 'D')
                value -= 100;
            else if(current == 'C' && last == 'M')
                value -= 100;
            else if (current == 'I')
                value += 1;
            else if(current == 'V')
                value += 5;
            else if(current == 'X')
                value += 10;
            else if(current == 'L')
                value += 50;
            else if(current == 'C')
                value += 100;
            else if(current == 'D')
                value += 500;
            else if(current == 'M')
                value += 1000;
            last = current;
            pos--;
        }
        return value;
    }
    public static String convertToRoman(Double arg)
    {
        //converts number into RomanNumerals
        Double val = arg;
        if(arg == 0.0)
            return "nulla";
        boolean negative = false;
        if(arg < 0) {
            val = -val;
            negative = true;
        }

        //stringbuilder is more memory and time efficient than appending to a string
        StringBuilder sb = new StringBuilder();
        String temp = "";

        //creates the roman numerals
        while(val >= 1000)
        {
            val -= 1000;
            sb.append('M');
        }
        if(val >= 900){
            val -= 900;
            sb.append("CM");
        }
        while(val >= 500)
        {
            val -=500;
            sb.append('D');
        }
        if(val >= 400){
            val -= 400;
            sb.append("CD");
        }
        while(val >= 100)
        {
            val -=100;
            sb.append('C');
        }
        if(val >= 90){
            val -= 90;
            sb.append("XC");
        }
        while(val >= 50)
        {
            val -=50;
            sb.append('L');
        }
        if(val >= 40){
            val -= 40;
            sb.append("XL");
        }
        while(val >= 10)
        {
            val -=10;
            sb.append('X');
        }
        if(val >= 9)
        {
            val -=9;
            sb.append("IX");
        }
        while(val >= 5){
            val -= 5;
            sb.append("V");
        }
        if(val >= 4){
            val -= 4;
            sb.append("IV");
        }
        while(val >= 1)
        {
            val--;
            sb.append('I');
        }
        String temp1 = sb.toString();

        //deals with remaining decimals
        if(val != 0.0)
        {
            if(temp1.length() == 0)
                temp1 = decToFrac(val);
            else
                temp1 += " " + decToFrac(val);
        }
        if(negative)
            return "-"+temp1;
        else
            return temp1;

    }
    public static String decToFrac(Double arg)
    {
        //converts decimals into fractions
        //Times by 100 in order to cut off at hundrendths
        //adds 0.5 in order to round up.
        Double temp = arg * 100 +0.5;
        int temp2 = temp.intValue();

        //gcd is used to reduce fractions
        int temp_gcd;
        temp_gcd = gcd(temp2,100);
        String num = convertToRoman((temp2/temp_gcd)+ 0.0);
        String deno = convertToRoman(100.0/temp_gcd);
        return num.toLowerCase() + "/" + deno.toLowerCase();
    }
    public static int gcd(int num,int deno)
    {
        //finds greatest common denominator
        if(deno == 0)
            return num;
        else
            return gcd(deno,num % deno);
    }

    public static Double parser(String arg)
    {
        //splits strings into whole numbers and fractions
        String[] arr = arg.split(" ");
        int arrlen = arr.length;
        Double value = 0.0;

        //used to keep track of negatives
        boolean negative = false;

        if(arr[0].charAt(0) == '-')
            negative = true;
        for(int i = 0; i < arrlen; i++) {
            if (arr[i].contains("/")) {
                //used to detect the fraction into two different roman numerals for easier use
                value += convertToDecimal(arr[i]);
            }
            else
                value += convertToWhole(arr[i]);
        }
        if (negative) {
            //makes the values negative
            value = -value;
            return value;
        }
        return  value;
    }
    public static Double convertToDecimal(String arg)
    {
        //converts fraction into the decimal portion of a double

        //used to split the fraction into two different roman numerals for easier use
        String[] arr = arg.split("/");

        //used to manipulate the input into being able to be used with existing functions
        String num = arr[0].toUpperCase();
        String deno = arr[1].toUpperCase();

        //creates doubles
        Double numerator = convertToWhole(num) + 0.0;
        Double denominator = convertToWhole(deno) + 0.0;

        return numerator/denominator;
    }

}