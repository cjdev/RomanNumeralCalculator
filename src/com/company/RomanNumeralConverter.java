package com.company;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.company.DecimalFractionConverter.*;

public class RomanNumeralConverter {
	private static final int[] NUMBERS = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
	private static final String[] LETTERS = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"}; 

	private static int romanToDecimalRecursive(String roman) {
		for (int i=0; i < LETTERS.length; i++) {
			if (roman.startsWith(LETTERS[i])) {
				return NUMBERS[i] + romanToDecimalRecursive(roman.replaceFirst(LETTERS[i], ""));
			}
		}

		return 0;
	}

	private static double fractionRomanToDecimal(List<String> fractionParts) {
		double wholeNumber = romanToDecimalRecursive(fractionParts.get(0));
		double decimal = romanToDecimalRecursive(fractionParts.get(1)) / romanToDecimal(fractionParts.get(2));

		return wholeNumber + decimal;
	}

	public static double romanToDecimal(String roman) {
		if (roman.equals("nulla")) {
			return 0.0;
		}

		roman = roman.toUpperCase();
		
		boolean isNegative = roman.charAt(0) == '-';

		if (isNegative) {
			roman = roman.substring(1);
		}

		List<String> arr = new ArrayList<>();

		for (String letter: roman.split(" ")) {
			arr.add(letter);
		}

		double decimal;

		String[] fractionParts = roman.split("/");
		
		if (arr.size() > 1) {
			String[] fraction = arr.get(1).split("/");

			arr.set(1, fraction[0]);
			arr.add(fraction[1]);

			decimal = fractionRomanToDecimal(arr);
		} else if (fractionParts.length > 1) {
			decimal = (double) romanToDecimalRecursive(fractionParts[0]) / 
				romanToDecimalRecursive(fractionParts[1]);
		} else {
			decimal = romanToDecimalRecursive(roman);
		}

		if (isNegative) {
			decimal *= -1;
		}

		return decimal;
	}

	private static double roundBy2Places(double number) {
    	return Math.round(number * 100.0) / 100.0;
	}

	private static String numberToRoman(double number) {
		StringBuilder sb = new StringBuilder();

		for (int i=0; i < NUMBERS.length; i++) {
			while (number >= NUMBERS[i]) {
				sb.append(LETTERS[i]);
				number -= NUMBERS[i];
			}
		}

		return sb.toString();
	}

	private static String fractionalToRoman(double number) {
		int wholeNumber = (int) number;
		double fractional = number - wholeNumber;

		fractional = roundBy2Places(fractional);

		DecimalFractionConverter dfc = new DecimalFractionConverter(fractional);
		int numerator = dfc.getNumerator();
		int denominator = dfc.getDenominator();

		if (wholeNumber > 0) {
			return String.format("%s %s/%s", numberToRoman(wholeNumber),
				numberToRoman(numerator).toLowerCase(), numberToRoman(denominator).toLowerCase());			
		} else {
			return String.format("%s/%s", numberToRoman(numerator).toLowerCase(), 
				numberToRoman(denominator).toLowerCase());
		}		
	}

	public static String decimalToRoman(double number) {
		number = roundBy2Places(number);

		boolean isNegative = number < 0;

		String result;

		if (isNegative) {
			number *= -1;
		}

		if (number == 0) {
			return "nulla";
		}
		else if (number == (int) number) {
			result = numberToRoman(number);
		} else {
			result = fractionalToRoman(number);
		}

		if (isNegative) {
			return "-" + result;
		} else {
			return result;
		}
	}

	public static void main(String[] args) {
        /* 
        Used for testing since I wasn't able to run the unit tests w/o Internet 
        access or an IDE
        */		
		System.out.println(String.format("%s = %.1f", romanToDecimal("MCMXC"), 1990.0));
		System.out.println(String.format("%s = %.1f", romanToDecimal("MMVIII"), 2008.0));
		System.out.println(String.format("%s = %.1f", romanToDecimal("MDCLXVI"), 1666.0));
		System.out.println(String.format("%s = %.1f", romanToDecimal("MDLXXXIV"), 1584.0));
		System.out.println(String.format("%s = %.2f", romanToDecimal("II xxvii/l"), 2.54));

		System.out.println(String.format("%s = %s", decimalToRoman(1990), "MCMXC"));
		System.out.println(String.format("%s = %s", decimalToRoman(2008), "MMVIII"));
		System.out.println(String.format("%s = %s", decimalToRoman(1666), "MCDCLXVI"));
		System.out.println(String.format("%s = %s", decimalToRoman(1584), "MDLXXXIV"));
		System.out.println(String.format("%s = %s", decimalToRoman(2.54), "II xxvii/l"));
	}
}