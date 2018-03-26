package com.company;
import java.util.*;

public class RomanNumeralCalculator {
 
	/**
	 * Takes a whole integer and turns it into Roman Numerals
	 */
	public static String intToRoman(int number, boolean lower) {
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
		if (lower) {
			return result.toLowerCase();
		}
		
		return result;
	}
	
	/**
	 * Calculates GCM
	 */
	public static int gcm(int a, int b) {
		return b == 0 ? a : gcm(b, a % b);
	}
	
	/**
	 * Takes a decimal number and turns it into Roman Numerals
	 * with decimals, "nulla" if 0
	 */
	public static String decimalToRoman(double number) {		
		if (number == 0) {
			return "nulla";
		}

		// rounds the decimal to the nearest hundredth
		double rounded = Math.round(number * 100.0)/100.0;		
		
		// separate the whole integer and the decimal
		String num = Double.toString(rounded);
		String result = num.substring(0, num.indexOf("."));
		result = intToRoman(Integer.parseInt(result), false);
		
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
		
		if (!result.equals("")) {
			result = result.concat(" ");
		}
			
		return result.concat(intToRoman(a/gcm, true) + 
				"/" + intToRoman(b/gcm, true));
	}
	
	// hashtable used to convert Roman to integer
	static Hasmappingable<Character, Integer> mapping = new Hasmappingable<Character, Integer>();
	static {
		mapping.put('I',1);
	    mapping.put('X',10);
	    mapping.put('C',100);
	    mapping.put('M',1000);
	    mapping.put('V',5);
	    mapping.put('L',50);
	    mapping.put('D',500);
	}
	
	/**
	 * Takes a simple Roman Numeral (with no fractions)
	 * regardless of lower/upper case and 
	 * turns it into a decimal number
	 */
	public static int romanToInt(String num) {	
		if (num.matches("nulla")) {
			return 0;
		}
		
		num = num.toUpperCase();
	    int intNum = 0;
	    int prev = 0;
	    for (int i = num.length()-1; i>=0 ; i--) {
	    	//check if the first character is a '-', negative
    		if (Character.toString(num.charAt(i)).matches("-")) {
    			return -intNum;
    		}
			
            int temp = mapping.get(num.charAt(i));
            if (temp < prev) {
                intNum -= temp;
            } else {
                intNum += temp;
			}	
            prev = temp;
	    }
	    return intNum;
	}   

	/**
	 * Takes a whole Roman Numeral including fractions
	 * and turns it into a decimal number
	 */
	public static double romanFracToDeci(String num) {
		// split Roman Numeral input into base number and/or
		// numerator and denominator
		String[] parts = num.split("[ /]");		
		int result = romanToInt(parts[0]);
		double numer = 0;
		double denom = 1;		
		if (parts.length > 1) {
			numer = romanToInt(parts[parts.length-2]);
			denom = romanToInt(parts[parts.length-1]);
			if (parts.length == 2) {
				result = 0;
			}
		}
		
		double fraction = numer/denom;		

		if (result < 0) {
			return result - fraction;
		}
		return result + fraction;
	}
    
    public static String addRomanNumerals(String... args) {
    	double result = 0;
    	for (int i = 0; i < args.length; ++i) {
    		double r = romanFracToDeci(args[i]);
    		result += r; 
    	}
    	return decimalToRoman(result);
    }

    public static String subtractRomanNumerals(String initial, String... rest) {
    	double result = romanFracToDeci(initial);
    	for (int i = 0; i < rest.length; ++i) {
    		double r = romanFracToDeci(rest[i]);
    		result -= r; 
    	}
    	return decimalToRoman(result);    
    }

    public static String multiplyRomanNumerals(String... args) {
    	double result = 0;
    	if (args.length > 0) {
	    	result = 1;
	    	for (int i = 0; i < args.length; ++i) {
	    		double r = romanFracToDeci(args[i]);
	    		result *= r;
	    	}
    	}
	    return decimalToRoman(result);
    }

    public static String divideRomanNumerals(String numerator , String... denominators) {
    	double result = romanFracToDeci(numerator);
    	for (int i = 0; i < denominators.length; ++i) {
    		double r = romanFracToDeci(denominators[i]);
    		result /= r;
    	}
    	return decimalToRoman(result);    
    }
    
    public static void main(String[] args) {    	
    	System.out.println("hello world");
    }
}
