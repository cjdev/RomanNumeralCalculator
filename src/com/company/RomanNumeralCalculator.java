package com.company;

import com.intellij.icons.AllIcons;

import java.math.BigDecimal;

public class RomanNumeralCalculator {

    public static void main(String[] args) {
        String x = "VII";
        String[] arr = {"II","III","I"};
        String result = addRomanNumerals(arr);
    }

    public static String addRomanNumerals(String... args){
        Double value = 0.0;
        int arrlen = args.length;
        for(int i = 0; i < arrlen; i++) {
            value += parser(args[i]);
        }
        return convertToRoman(value);
    }

    public static String subtractRomanNumerals(String initial, String... rest){
        Double value = 0.0;
        int arrlen = rest.length;
        for(int i = 0; i < arrlen; i++) {
            value += parser(rest[i]);
        }
        Double init = parser(initial);
        BigDecimal init_temp = BigDecimal.valueOf(init);
        BigDecimal  val_temp = BigDecimal.valueOf(value);
        BigDecimal result = init_temp.subtract(val_temp);
        double temp = result.doubleValue();
        return convertToRoman(temp);
    }

    public static String multiplyRomanNumerals(String... args){
        Double value = 1.0;
        int arrlen = args.length;
        for(int i = 0; i < arrlen; i++) {
            value = value * parser(args[i]);
        }
        return convertToRoman(value);
    }

    public static String divideRomanNumerals(String numerator , String... denominators){
        Double result = parser(numerator);
        for(int i = denominators.length - 1; i >= 0; i--)
        {   if(denominators[i] == "nulla")
                throw new IllegalArgumentException("divide by zero error");
            Double divisor = parser(denominators[i]);
            result = result /divisor;
        }
        return convertToRoman(result);
    }

    public static int convertToWhole(String arg) {
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
        Double val = arg;
        if(arg == 0.0)
            return "nulla";
        boolean negative = false;
        if(arg < 0) {
            val = -val;
            negative = true;
        }
        StringBuilder sb = new StringBuilder();
        String temp = "";
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
        Double temp = arg * 100 +0.5;
        int temp2 = temp.intValue();

        int temp_gcd;
        temp_gcd = gcd(temp2,100);
        String num = convertToRoman((temp2/temp_gcd)+ 0.0);
        String deno = convertToRoman(100.0/temp_gcd);
        return num.toLowerCase() + "/" + deno.toLowerCase();
    }
    public static int gcd(int num,int deno)
    {
        if(deno == 0)
            return num;
        else
            return gcd(deno,num % deno);
    }

    public static Double parser(String arg)
    {
        String[] arr = arg.split(" ");
        int arrlen = arr.length;
        Double value = 0.0;
        boolean negative = false;
        if(arr[0].charAt(0) == '-')
            negative = true;
        for(int i = 0; i < arrlen; i++) {
            if (arr[i].contains("/")) {
                value += convertToDecimal(arr[i]);
            }
            else
                value += convertToWhole(arr[i]);
        }
        if (negative) {
            value = -value;
            return value;
        }
        return  value;
    }
    public static Double convertToDecimal(String arg)
    {
        String[] arr = arg.split("/");
        String num = arr[0].toUpperCase();
        String deno = arr[1].toUpperCase();
        Double numerator = convertToWhole(num) + 0.0;
        Double denominator = convertToWhole(deno) + 0.0;
        return numerator/denominator;
    }

}