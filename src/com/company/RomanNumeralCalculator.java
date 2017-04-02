/**
 * Authors: Zhuojun Chen, Litao Qiao, Zhizhen (Eli) Qin
 */
package com.company;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * The RomanNumeralCalculator class helps calculations with Roman numbers.
 */
public class RomanNumeralCalculator {

    // Tens and hundreds
    private final static double HUNDRED = 100.0;
    private final static int DECIMAL = 10;

    // Levels for characters
    private final static int I_LEVEL = 1;
    private final static int V_LEVEL = 2;
    private final static int X_LEVEL = 2;
    private final static int L_LEVEL = 3;
    private final static int C_LEVEL = 3;
    private final static int D_LEVEL = 4;
    private final static int M_LEVEL = 4;

    // Values for characters
    private final static int I_VALUE = 1;
    private final static int V_VALUE = 5;
    private final static int X_VALUE = 10;
    private final static int L_VALUE = 50;
    private final static int C_VALUE = 100;
    private final static int D_VALUE = 500;
    private final static int M_VALUE = 1000;

    // Level of edge case
    private final static int EDGE_LEVEL = 3;


    public static void main(String[] args) {
        System.out.println("Hello World");
    }

    /**
     * addRomanNumerals takes one argument that the String array of the
     * addend of Roman Number, sums up them, and return the
     * Roman Number of the result.
     *
     * @param inNum the input String array of addend of Roman number.
     * @return the Roman number of the sum.
     */
    public static String addRomanNumerals(String[] inNum){
        /* calculate the sum of the input numbers */
        double result = calcSum(inNum);

        /* return the Roman number of the sum */
        return toRoman(roundToTwoDigit(result));
    }

    /**
     * subtractRomanNumerals takes two arguments (String initial and
     * String[] rest), calculates the result that initial subtracts rest
     * and return the result.
     *
     * @param initial the Roman Number of minuend
     * @param rest the Roman Number of subtrahends
     * @return the Roman number of the result
     */
    public static String subtractRomanNumerals(String initial, String[] rest){

        /* calculate the sum of subtrahend and the result that
        * the minuend subtracts subtrahend */
        double subtrahend = calcSum(rest);
        double minuend = toNumber(initial);
        double result = minuend - subtrahend;

        /* return the Roman number of the sum */
        return toRoman(roundToTwoDigit(result));
    }

    /**
     * multiplyRomanNumerals takes one argument that the String array of the
     * multipliers of Roman Number, multiply them, and return the
     * Roman Number of the result.
     *
     * @param inNum the input String array of multiplier of Roman number.
     * @return the Roman number of the product.
     */
    public static String multiplyRomanNumerals(String[] inNum){
        /* calculate the product of the input numbers */
        double result = calcProduct(inNum);

        /* return the Roman number of the sum */
        return toRoman(roundToTwoDigit(result));
    }

    /**
     * divideRomanNumerals takes two arguments (String numerator and
     * String[] denominators), calculates the result that initial divides rest
     * and return the result.
     *
     * @param numerator the Roman Number of dividend
     * @param denominators the Roman Number of divisor
     * @return the Roman number of the result.
     */
    public static String divideRomanNumerals(String numerator ,
                                             String[] denominators){
        /* calculate the product of denominators */
        double divisor = calcProduct(denominators);

        /* check whether the product of denominators is 0 */
        if (divisor == 0) {
            throw new IllegalArgumentException();
        }

        /* calculate the result that the dividend divided by divisor */
        double dividend = toNumber(numerator);
        double result = dividend / divisor;

        /* return the Roman number of the sum */
        return toRoman(roundToTwoDigit(result));
    }

    /**
     * calcSum calculates the sum of the input string array of addend
     *
     * @param addend the string arrays of Roman Number of addends
     * @return the sum of the addend
     */
    private static double calcSum(String[] addend) {
        /* initialize the sum */
        double sum = 0;

        /* add the addend */
        for (int i = 0; i < addend.length; i++) {
            double number = toNumber(addend[i]);
            sum += number;
        }

        /* return the sum of the addend */
        return sum;
    }

    /**
     * calcProduct calculates the product of the input
     * string array of multiplier
     *
     * @param multiplier the string arrays of Roman Number of multiplier
     * @return the product of the multiplier
     */
    private static double calcProduct(String[] multiplier) {
        /* initialize the product */
        double product = 1;

        /* calculate the product of multiplier */
        for (int i = 0; i < multiplier.length; i++) {
            double number = toNumber(multiplier[i]);
            product *= number;
        }

        /* return the product of the multiplier */
        return product;
    }

    /**
     * roundToTwoDigit rounds up the decimal value to two decimal digit
     *
     * @param result the decimal value to be rounded up
     * @return the rounded decimal value
     */
    private static double roundToTwoDigit(double result) {
        return (double) Math.round(result * HUNDRED) / HUNDRED;
    }

    /**
     * toNumber convert the Roman numeral notation of a number to the numerical
     * notation. The method is going to perform the validation checks, handle
     * negative numbers and fractional numbers.
     *
     * @param rNumber the roman numeral notation of the number
     * @return the actual number converted from Roman numeral notation
     */
    public static double toNumber(String rNumber) {

        // Validation check for null object
        if(rNumber == null) {
            return 0;
        }

        double result = 0;          // Initialize the return result
        boolean negative = false;   // The number by default is not negative
        String integer = null;      // The string representing the integer part
        String fraction = null;     // The string representing the decimal part
        String numer = null;        // The string representing the numerator
        String denum = null;        // The string representing the denominator

        // Determine whether the roman numeral number contains space or slash
        boolean containsSpace = rNumber.contains(" ");
        boolean containsSlash = rNumber.contains("/");

        // Determine whether the roman number has integer or decimal part
        boolean containsDec = containsSlash;
        boolean containsInt = containsSpace ||
                !(containsSpace || containsSlash);


        // Check whether the Roman number is negative
        if(rNumber.charAt(0) == '-') {
            negative = true;
            // Remove the negative sign
            rNumber = rNumber.substring(1);
        }

        // Check whether the Roman number equals zero
        if(rNumber.equals("nulla")) {
            return 0;
        }

        // Construct levels of each Roman numerical character. See method header
        // of stringToInt for more detailed description
        HashMap<Character, Integer> level = new HashMap<>();
        level.put('I', I_LEVEL); level.put('i', I_LEVEL);
        level.put('V', V_LEVEL); level.put('v', V_LEVEL);
        level.put('X', X_LEVEL); level.put('x', X_LEVEL);
        level.put('L', L_LEVEL); level.put('l', L_LEVEL);
        level.put('C', C_LEVEL); level.put('c', C_LEVEL);
        level.put('D', D_LEVEL); level.put('d', D_LEVEL);
        level.put('M', M_LEVEL); level.put('m', M_LEVEL);

        // Split the roman number string by the space
        String[] splittedRNumber = rNumber.split(" ");

        // If the roman number has integer part, construct the integer string
        if(containsInt) {
            integer = splittedRNumber[0];
            // If the roman number has fractional part, the fraction part is the
            // second part of the string slitted by space
            if(containsDec) {
                fraction = splittedRNumber[1];
            }
        }

        // If the roman number doesn't have integer part, the whole
        // representation is the fraction
        else {
            fraction = splittedRNumber[0];
        }

        // If the roman number has decimal part, split it to numerator and
        // denominator
        if(containsDec) {
            String[] splittedFraction = fraction.split("/");
            numer = splittedFraction[0];
            denum = splittedFraction[1];
        }

        // If the roman number contains integer part, go ahead and convert it
        if(containsInt) {
            result += stringToInt(integer, level);
        }

        // If the roman number contains fractional part, convert numerator and
        // denominator and divide them
        if(containsDec) {
            result += (double)stringToInt(numer, level)
                    / (double)stringToInt(denum, level);
        }

        // If the number is negative, convert back to negative number
        if(negative) {
            result = -result;
        }

        return result;
    }

    /**
     * stringToInt convert the Roman numeral notation of a number to the
     * numerical notation. The main idea of this method is using something
     * called "leveled", where 'I' belongs to the first level, 'V' and 'X'
     * belong to the second level, 'L' and 'C' belong to the third level, and
     * 'D' and 'M' belong to the fourth level. For a certain roman notation of a
     * number, we process from the very right and leftward to the first
     * character. For each character, if it is one level less than the previous
     * character (the character on the right, then the corresponding value
     * should be subtracted from the result.
     *
     * @param number the Roman numerical representation of the number
     * @param level the level HashMap
     * @return the number corresponding to the Roman representation
     */
    private static int stringToInt(String number,
                                   HashMap<Character, Integer> level) {
        int result = 0;                 // The converting result
        int length = number.length();   // The length of the string

        // The level of the last character in the string
        int lastLevel = level.get(number.charAt(length - 1));

        // Increment the result with the number value of the last character in
        // the string
        result += charToInt(number.charAt(length - 1));

        // Start from the second character from the right, and process leftward
        for (int i = length - 2; i >= 0; --i) {
            char current = number.charAt(i);      // Current character in String
            int currLevel = level.get(current);   // Level of the curr character
            int currentValue = charToInt(current);// Value of the curr character

            // If the level of the current character is less than that of the
            // last one by 1, subtract!
            if (lastLevel - currLevel == 1) {
                result -= currentValue;
            }
            // Otherwise, add!
            else {
                result += currentValue;
            }

            // Update the level of the last character
            lastLevel = currLevel;
        }

        return result;
    }

    /**
     * Convert a single Roman character to a number
     *
     * @param currentChar the character to be converted
     * @return the numerical value of the Roman character
     */
    private static int charToInt(char currentChar) {
        int currentValue;
        switch(currentChar) {
            case 'I': currentValue = I_VALUE;     break;
            case 'V': currentValue = V_VALUE;     break;
            case 'X': currentValue = X_VALUE;    break;
            case 'L': currentValue = L_VALUE;    break;
            case 'C': currentValue = C_VALUE;   break;
            case 'D': currentValue = D_VALUE;   break;
            case 'M': currentValue = M_VALUE;  break;
            case 'i': currentValue = I_VALUE;     break;
            case 'v': currentValue = V_VALUE;     break;
            case 'x': currentValue = X_VALUE;    break;
            case 'l': currentValue = L_VALUE;    break;
            case 'c': currentValue = C_VALUE;   break;
            case 'd': currentValue = D_VALUE;   break;
            case 'm': currentValue = M_VALUE;  break;
            default:  currentValue = 0;     break;
        }
        return currentValue;
    }


    /**
     * convert the number in the range of [-4999.00 - 4999.99] to the Roman
     * numeral characters by delegating each part of the number as integer to
     * the method integerToRoman. This method is called from each operation
     * method.
     *
     * @param input: the float number to be converted to Roman character
     * @return the Roman String
     */
    public static String toRoman(Double input) {
        String output;              // The output string
        boolean negative = false;

        // handle the case when the result is zero
        if(input == 0) {
            return "nulla";
        }

        // handle the negative case
        if(input < 0) {
            input = -input;
            negative = true;
        }


        // divide the double number to two parts: integer part and decimal part
        int integer = input.intValue();
        int decimal = (int) ((input - integer) * HUNDRED);

        if(decimal != 0) {
            // Simplify the decimal part by dividing the decimal and 100 by
            // their greatest common divisor
            int gcd = gcd(decimal, (int)HUNDRED);
            int numerator = decimal / gcd;
            int denominator = (int)HUNDRED / gcd;

            // Convert each part as integer to Roman character
            output = integerToRoman(integer)
                    + (integer == 0 ? "" : " ")
                    + integerToRoman(numerator).toLowerCase() + "/"
                    + integerToRoman(denominator).toLowerCase();
        }
        else {
            output = integerToRoman(integer);
        }

        // negate the output if the input is negative
        if(negative) {
            output = "-" + output;
        }

        return output;
    }

    /**
     * convert one integer to Roman numeral representation.
     * This is called from toRoman method.
     *
     * @param integer the input integer derived from toRoman method
     * @return the Roman String
     */
    private static String integerToRoman(int integer) {
        String output = "";

        int digit;      // The digit being processed
        int i = 0;      // Iterator

        // Constantly convert the integer to its corresponding Roman characters
        // digit by digit
        while(integer > 0) {
            // get the last digit of the number
            digit = integer % DECIMAL;
            integer = integer / DECIMAL;

            // convert the digit to its corresponding Roman characters
            String base = digitToRoman(digit);
            output = jumpString(base, i) + output;

            i++;
        }


        return output;
    }

    /**
     * convert the digit to its Roman characters.
     * The digit is considered to be less than 10.
     *
     * @param digit the digit to be converted to the Roman characters
     * @return the Roman number of that digit
     */
    private static String digitToRoman(int digit) {
        String output = "";
        switch (digit) {
            case 0: output = "";        break;
            case 1: output = "I";       break;
            case 2: output = "II";      break;
            case 3: output = "III";     break;
            case 4: output = "IV";      break;
            case 5: output = "V";       break;
            case 6: output = "VI";      break;
            case 7: output = "VII";     break;
            case 8: output = "VIII";    break;
            case 9: output = "IX";      break;
        }

        return output;
    }

    /**
     * convert the base Roman representation to its actual representation based
     * on the position of the digit in the number
     *
     * @param original the base Roman representation
     * @param level how many steps the Roman value need to jump to reach the
     *              actual value
     * @return the string representing the roman number
     */
    private static String jumpString(String original, int level) {
        String output = "";

        // Edge case: since there is no chracter representing 5000,
        // IV in level 3 would be MMMM instead
        if(original.equals("IV") && level == EDGE_LEVEL) output = "MMMM";

        // Generate roman number for each numerical digit
        else {
            for (int i = 0; i < original.length(); i++) {
                output = output + jumpChar(original.charAt(i), level);
            }
        }

        return output;
    }

    /**
     * Jump each character to its corresponding level. For example, 4 in the
     * unit digit is IV, in tens digit is XL, and in hundreds digit is CD
     *
     * @param original the digit
     * @param level the level of the digit
     * @return the roman number notation of the digit with its level
     */
    private static char jumpChar(char original, int level) {
        char output;

        Character[] ixcm = {'I', 'X', 'C', 'M'};    // The "Tens" characters
        Character[] vld = {'V', 'L', 'D'};          // The "Fives" characters

        // The corresponding lists
        List<Character> IXCM = new ArrayList<>(Arrays.asList(ixcm));
        List<Character> VLD = new ArrayList<>(Arrays.asList(vld));

        // Get the index of the digit from IXCM list
        int index = IXCM.indexOf(original);
        boolean isIxcm = true;

        // Determine whether the digit is in the IXCM list
        if(index == -1) {
            // If not, find the digit in VLD list
            index = VLD.indexOf(original);
            isIxcm = false;
        }

        // Construct the correct Roman character
        output = isIxcm ? IXCM.get(index + level) : VLD.get(index + level);

        return output;
    }

    /**
     * Find the GCD of two integers recursively
     *
     * @param integer1 the first integer
     * @param integer2 the second integer
     * @return The greatest common divider of the two integers
     */
    private static int gcd(int integer1, int integer2) {
        // If either of the integer is zero, the GCD is the other number
        if(integer1 == 0) {
            return integer2;
        }
        else if(integer2 == 0) {
            return integer1;
        }

        // Find the GCD of the difference of the two integers
        else {
            return integer1 > integer2 ?
                    gcd(integer1 - integer2, integer2) :
                    gcd(integer1, integer2 - integer1);
        }
    }
}