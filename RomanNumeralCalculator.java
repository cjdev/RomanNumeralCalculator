// package com.company;
import java.util.ArrayList;

public class RomanNumeralCalculator {

	//our conversion lists
	public static int[] all_numbers = { 1000,  900,  500,  400,  100, 90, 50,   40,   10,    9,    5,    4,    1 };    
   	public static ArrayList<String> letters = new ArrayList<String>(13);


    public static void main(String[] args) {
        System.out.println("hello world");
        //filling in the arraylist
        String[] strs = new String[]{ "M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I" };
		for (String s : strs)
		    letters.add(s);


    }








    public static String addRomanNumerals(String... args){
    	float result = 0;
    	for (int numbers = 0; numbers< args.length; numbers++){
    		if(args[numbers]=="nulla"){
    			continue;
    		}
    		result += convertRomanToDecimal(args[numbers]);
    	}
    	if(result==0){
    		return "nulla";
    	}
        return convertDToR(result);
    }

    public static String subtractRomanNumerals(String initial, String... rest){

    	float result = convertRomanToDecimal(initial);
    	for (int numbers = 0; numbers< rest.length; numbers++){
    		if(rest[numbers].equals("nulla")){
    			continue;
    		}
    		result -= convertRomanToDecimal(rest[numbers]);
    	}
    	if(result==0){
    		return "nulla";
    	}
        return convertDToR(result);
    }

    public static String multiplyRomanNumerals(String... args){
    	float result = 0;
    	for (int numbers = 0; numbers< args.length; numbers++){
    		if(args[numbers].equals("nulla")){
    			return "nulla";
    		}
    		if(numbers==0){
    			result = convertRomanToDecimal(args[numbers]);
    			continue;
    		}
    		result *= convertRomanToDecimal(args[numbers]);
    	}
    	if(result==0){
    		return "nulla";
    	}
        return convertDToR(result);
    }

    public static String divideRomanNumerals(String numerators , String... denominators){
    	float result = convertRomanToDecimal(numerators);
    	for (int numbers = 0; numbers< denominators.length; numbers++){
    		if(denominators[numbers].equals("nulla")){
    			throw new IllegalArgumentException();
    		}
    		result /= convertRomanToDecimal(denominators[numbers]);
    	}
    	if(result==0){
    		return "nulla";
    	}
        return convertDToR(result);
    }

    public static float convertRomanToDecimal(String romanNumber){
    	romanNumber = romanNumber.toUpperCase();
		float prevNum = -1;
		float fullNumValue = 0;
		boolean isNeg = false;
		boolean isFrac = false;
		float numerators = 0;
		for (int indexLetter = 0; indexLetter < romanNumber.length(); indexLetter++){
			if(romanNumber.charAt(indexLetter)=='-'){
				isNeg = true;
				continue;
			}
			if (prevNum == -1){
				prevNum = all_numbers[letters.indexOf(String.valueOf(romanNumber.charAt(indexLetter)))];
				fullNumValue = prevNum;
				continue;
			} 
			if(romanNumber.charAt(indexLetter)==' '){
				isFrac = true;
				numerators = convertRomanToDecimal(romanNumber.substring(indexLetter+1));
				break;
			}
			if(romanNumber.charAt(indexLetter)=='/'){
				return fullNumValue / convertRomanToDecimal(romanNumber.substring(indexLetter+1));
			}
			if (all_numbers[letters.indexOf(String.valueOf(romanNumber.charAt(indexLetter)))]> prevNum){
				fullNumValue = all_numbers[letters.indexOf(String.valueOf(romanNumber.charAt(indexLetter)))] - fullNumValue;
			}
			else if (all_numbers[letters.indexOf(String.valueOf(romanNumber.charAt(indexLetter)))] == prevNum){
				fullNumValue += prevNum;
			}
			else{
				fullNumValue += all_numbers[letters.indexOf(String.valueOf(romanNumber.charAt(indexLetter)))] ;
			}
			prevNum = all_numbers[letters.indexOf(String.valueOf(romanNumber.charAt(indexLetter)))];
		
		}
		if(isFrac){
			fullNumValue += numerators;
		}
		if(isNeg){
			return -fullNumValue;
		}
		return fullNumValue;
    }

    public static String convertDToR(float dNum){
        String result = "";
        boolean isNeg = false;
 		if(dNum < 0){
 			isNeg = true;
 			dNum = Math.abs(dNum);
 		}
		for (int i = 0; i < all_numbers.length; i++) {
			int numberInPlace = (int)dNum / all_numbers[i];
			if (numberInPlace == 0) continue;
			result += numberInPlace == 4 && i > 0? letters.get(i) + letters.get(i-1):
			new String(new char[numberInPlace]).replace("\0",letters.get(i));
			dNum = dNum % all_numbers[i];
		}
		if(!result.equals("")){
			result += " ";
		}
		if(dNum!=0){

			double newNum = Math.round ((float)dNum * 100.0) / 100.0; 
			int den = (int)Math.pow(10,(int)Math.log10(Integer.MAX_VALUE));
			int num = (int)(newNum*den);

			int[] ndList = reduceFraction(num, den);
			String nresult = "";
			String dresult = "";
			for (int j = 0; j < all_numbers.length; j++) {
				int numberInPlace = (int)ndList[0] / all_numbers[j];
				if (numberInPlace == 0) continue;
				nresult += numberInPlace == 4 && j > 0? letters.get(j) + letters.get(j-1):
				new String(new char[numberInPlace]).replace("\0",letters.get(j));
				ndList[0] = ndList[0] % all_numbers[j];
			}

			for (int j = 0; j < all_numbers.length; j++) {
				int numberInPlace = (int)ndList[1] / all_numbers[j];
				if (numberInPlace == 0) continue;
				dresult += numberInPlace == 4 && j > 0? letters.get(j) + letters.get(j-1):
				new String(new char[numberInPlace]).replace("\0",letters.get(j));
				ndList[1] = ndList[1] % all_numbers[j];
			}
			if(isNeg){
				return "-" + result +nresult.toLowerCase() + "/" + dresult.toLowerCase();
			}
			return result +nresult.toLowerCase() + "/" + dresult.toLowerCase();
		}	

		if(isNeg){
			return "-" + result;
		}
		return result;

    }

    public static int[] reduceFraction(int num, int den){
	    int gcf = GCF(num, den); //greatest common factor
	    int[] rf = {num/gcf, den/gcf};
	    return rf;
	}
	public static int GCF(int a, int b) {
	    if (b == 0) return a;
	    else return (GCF (b, a % b));
	}
}

