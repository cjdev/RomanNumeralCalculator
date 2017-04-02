package com.company;
import java.util.*;

public class RomanNumeralCalculator {
 
	public static String InttoRoman(int number, boolean lower) {
		// takes a whole integer and turns it into Roman Numerals
		
		int num = number;
		String result = "";
		// if the number is negative, make sure the Roman is too
		if (number < 0) {
			result += "-";
			num -= 2*number;
		}
		// make num I's and replace them until simplified
	    result += String.join("", Collections.nCopies(num, "I"))
	            .replace("IIIII", "V")
	            .replace("IIII", "IV")
	            .replace("VV", "X")
	            .replace("VIV", "IX")
	            .replace("XXXXX", "L")
	            .replace("XXXX", "XL")
	            .replace("LL", "C")
	            .replace("LXL", "XC")
	            .replace("CCCCC", "D")
	            .replace("CCCC", "CD")
	            .replace("DD", "M")
	            .replace("DCD", "CM");
	    // if dealing with fractions, make lowercase
	    if (lower)
	    	return result.toLowerCase();
	    return result;
	}
	
	public static int gcm(int a, int b) {
		// calculates gcm
		 return b == 0 ? a : gcm(b, a % b);
	}
	
	public static String DecimaltoRoman(double number) {
		// takes a decimal number and turns it into Roman Numerals
		// with decimals, "nulla" if 0
			
		if (number == 0)
			return "nulla";

		// rounds the decimal to the nearest hundredth
		double rounded = Math.round(number * 100.0)/100.0;		
		
		// separate the whole integer and the decimal
		String num = Double.toString(rounded);
		String result = num.substring(0, num.indexOf("."));
		result = InttoRoman(Integer.parseInt(result), false);
		
		String decimal = num.substring(num.indexOf(".")+1);
		if (decimal.length() == 1) {
			decimal += "0";
		}		

		// calculate reduced fraction
		int a = Integer.parseInt(decimal);
		if (a == 0) {
			return result;
		}
		int b = 100;
		int gcm = gcm(a, b);
		
		if (!result.equals(""))
			result = result.concat(" ");

		return result.concat(InttoRoman(a/gcm, true) + 
				"/" + InttoRoman(b/gcm, true));
	}
	
	// hashtable used to convert Roman to integer
	static Hashtable<Character, Integer> ht = new Hashtable<Character, Integer>();
	static {
		ht.put('I',1);
	    ht.put('X',10);
	    ht.put('C',100);
	    ht.put('M',1000);
	    ht.put('V',5);
	    ht.put('L',50);
	    ht.put('D',500);
	}
	    
	public static int RomantoInt(String num) {
		// takes a simple Roman Numeral (with no fractions)
		// regardless of lower/upper case and 
		// turns it into a decimal number
		
		if (num.matches("nulla"))
			return 0;
		
		num = num.toUpperCase();
	    int intNum = 0;
	    int prev = 0;
	    for (int i = num.length()-1; i>=0 ; i--)
	    {
	    	//check if the first character is a '-', negative
    		if (Character.toString(num.charAt(i)).matches("-")) {
    			return -intNum;
    		}
            int temp = ht.get(num.charAt(i));
            if (temp < prev)
                intNum -= temp;
            else
                intNum += temp;
            prev = temp;
	    }
	    return intNum;
	}   

	public static double RomanFractoDeci(String num) {
		// takes a whole Roman Numeral including fractions
		// and turns it into a decimal number
		
		// split Roman Numeral input into base number and/or
		// numerator and denominator
		String[] parts = num.split("[ /]");		
		int result = RomantoInt(parts[0]);
		double numer = 0;
		double denom = 1;		
		if (parts.length > 1) {
			numer = RomantoInt(parts[parts.length-2]);
			denom = RomantoInt(parts[parts.length-1]);
			if (parts.length == 2){
				result = 0;
			}
		}
		
		double fraction = numer/denom;		

		if (result < 0)
			return result - fraction;
		return result + fraction;
	}
	
    
    public static String addRomanNumerals(String... args){
    	double result = 0;
    	for (int i = 0; i < args.length; ++i) {
    		double r = RomanFractoDeci(args[i]);
    		result += r; 
    	}
    	return DecimaltoRoman(result);
    }

    public static String subtractRomanNumerals(String initial, String... rest){
    	double result = RomanFractoDeci(initial);
    	for (int i = 0; i < rest.length; ++i) {
    		double r = RomanFractoDeci(rest[i]);
    		result -= r; 
    	}
    	return DecimaltoRoman(result);    
    }

    public static String multiplyRomanNumerals(String... args){
    	double result = 0;
    	if (args.length > 0) {
	    	result = 1;
	    	for (int i = 0; i < args.length; ++i) {
	    		double r = RomanFractoDeci(args[i]);
	    		result *= r;
	    	}
    	}
	    return DecimaltoRoman(result);
    }

    public static String divideRomanNumerals(String numerator , String... denominators){
    	double result = RomanFractoDeci(numerator);
    	for (int i = 0; i < denominators.length; ++i) {
    		double r = RomanFractoDeci(denominators[i]);
    		result /= r;
    	}
    	return DecimaltoRoman(result);    
    }
    
    public static void main(String[] args) {    	
    	System.out.println("hello world");
    }
}

