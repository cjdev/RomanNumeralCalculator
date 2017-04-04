import static org.junit.Assert.*;

import org.junit.Test;


public class RomanNumeralCalculatorTest {

	public static String addRomanNumerals(String[] inputArgs){
		
		FracNumber[] fracNumbers = new FracNumber[inputArgs.length];
		
		for(int i = 0; i < inputArgs.length; i++){
			String[] args = inputArgs[i].split(" ");
			int whole = 0;
			int num = 0;
			int den = 0;
			for(int j = 0; j < args.length; j++){
				if(!args[j].contains("/")){
					whole = RomanNumeral.numeralToInt((args[j]));
				}
				else{
					String numStr = args[j].substring(0, args[j].indexOf('/'));
					String denStr = args[j].substring(args[j].indexOf('/') + 1, args[j].length());
					num = RomanNumeral.numeralToInt(numStr);
					den = RomanNumeral.numeralToInt(denStr);
				}
				
			}
			if(num != 0 && den != 0){
				whole *= den;
				num += whole;
				fracNumbers[i] = new FracNumber(num, den);
			}
			else{
				fracNumbers[i] = new FracNumber(whole, 1);
			}
		}
		
		FracNumber a = fracNumbers[0];
		for(int i = 1; i < fracNumbers.length; i++){
			FracNumber b = fracNumbers[i];
			a = FracNumber.addFracNumbers(a, b);
			//System.out.println("Result: " + a.getNum() + " Result: " + a.getDen());
		}
		//System.out.println(a);
		int whole = 0;
		int resultNum = a.getNum();
		int resultDen = a.getDen();
		//System.out.println(resultNum + " " + resultDen);
		String numStr = "";
		String denStr = "";
		String wholeStr = "";
		if((resultNum / resultDen) == 0){
			numStr = RomanNumeral.intToNumeral(resultNum, true);
			denStr = RomanNumeral.intToNumeral(resultDen, true);
		}
		else{
			whole = resultNum / resultDen;
			resultNum = resultNum % resultDen;
			numStr = RomanNumeral.intToNumeral(resultNum, true);
			denStr = RomanNumeral.intToNumeral(resultDen, true);
			wholeStr = RomanNumeral.intToNumeral(whole, false);
		}
		if(whole == 0 && !numStr.equals("nulla")) return numStr + "/" + denStr;
		else if(whole > 0 || whole < 0){
			String resultString = wholeStr;
			if(resultNum > 0){
				resultString += " " + numStr + "/" + denStr;
			}
			return resultString;
		}
		else if(numStr.equals("nulla")) return numStr;
		return numStr;
	}
	
	public String addRomanNumerals(String[] inputArgs1, String[] inputArgs2){
		
		String[] inputArgs = new String[inputArgs1.length + inputArgs2.length];
		for(int i = 0; i < inputArgs1.length; i++){
			inputArgs[i] = inputArgs1[i];
		}
		for(int i = 0; i < inputArgs2.length; i++){
			inputArgs[i + inputArgs.length] = inputArgs2[i]; 
		}
		
		
FracNumber[] fracNumbers = new FracNumber[inputArgs.length];
		
		for(int i = 0; i < inputArgs.length; i++){
			String[] args = inputArgs[i].split(" ");
			int whole = 0;
			int num = 0;
			int den = 0;
			for(int j = 0; j < args.length; j++){
				if(!args[j].contains("/")){
					whole = RomanNumeral.numeralToInt((args[j]));
				}
				else{
					String numStr = args[j].substring(0, args[j].indexOf('/'));
					String denStr = args[j].substring(args[j].indexOf('/') + 1, args[j].length());
					num = RomanNumeral.numeralToInt(numStr);
					den = RomanNumeral.numeralToInt(denStr);
				}
				
			}
			if(num != 0 && den != 0){
				whole *= den;
				num += whole;
				fracNumbers[i] = new FracNumber(num, den);
			}
			else{
				fracNumbers[i] = new FracNumber(whole, 1);
			}
		}
		
		FracNumber a = fracNumbers[0];
		for(int i = 1; i < fracNumbers.length; i++){
			FracNumber b = fracNumbers[i];
			a = FracNumber.addFracNumbers(a, b);
			//System.out.println("Result: " + a.getNum() + " Result: " + a.getDen());
		}
		//System.out.println(a);
		int whole = 0;
		int resultNum = a.getNum();
		int resultDen = a.getDen();
		//System.out.println(resultNum + " " + resultDen);
		String numStr = "";
		String denStr = "";
		String wholeStr = "";
		if((resultNum / resultDen) == 0){
			numStr = RomanNumeral.intToNumeral(resultNum, true);
			denStr = RomanNumeral.intToNumeral(resultDen, true);
		}
		else{
			whole = resultNum / resultDen;
			resultNum = resultNum % resultDen;
			numStr = RomanNumeral.intToNumeral(resultNum, true);
			denStr = RomanNumeral.intToNumeral(resultDen, true);
			wholeStr = RomanNumeral.intToNumeral(whole, false);
		}
		if(whole == 0 && !numStr.equals("nulla")) return numStr + "/" + denStr;
		else if(whole > 0 || whole < 0){
			String resultString = wholeStr;
			if(resultNum > 0){
				resultString += " " + numStr + "/" + denStr;
			}
			return resultString;
		}
		else if(numStr.equals("nulla")) return numStr;
		return numStr;
	}
	
	public String subtractRomanNumerals(String input, String[] inputArgs1){
		
		String[] inputArgs = new String[inputArgs1.length + 1];
		inputArgs[0] = input;
		for(int i = 1; i < inputArgs.length; i++){
			inputArgs[i] = inputArgs1[i - 1];
		}
		
		FracNumber[] fracNumbers = new FracNumber[inputArgs.length];
		
		for(int i = 0; i < inputArgs.length; i++){
			String[] args = inputArgs[i].split(" ");
			int whole = 0;
			int num = 0;
			int den = 0;
			for(int j = 0; j < args.length; j++){
				if(!args[j].contains("/")){
					whole = RomanNumeral.numeralToInt((args[j]));
				}
				else{
					String numStr = args[j].substring(0, args[j].indexOf('/'));
					String denStr = args[j].substring(args[j].indexOf('/') + 1, args[j].length());
					num = RomanNumeral.numeralToInt(numStr);
					den = RomanNumeral.numeralToInt(denStr);
				}
				
			}
			if(num != 0 && den != 0){
				whole *= den;
				num += whole;
				fracNumbers[i] = new FracNumber(num, den);
			}
			else{
				fracNumbers[i] = new FracNumber(whole, 1);
			}
		}
		
		FracNumber a = fracNumbers[0];
		for(int i = 1; i < fracNumbers.length; i++){
			FracNumber b = fracNumbers[i];
			a = FracNumber.subtractFracNumbers(a, b);
			//System.out.println("Result: " + a.getNum() + " Result: " + a.getDen());
		}
		//System.out.println(a);
		int whole = 0;
		int resultNum = a.getNum();
		int resultDen = a.getDen();
		//System.out.println(resultNum + " " + resultDen);
		String numStr = "";
		String denStr = "";
		String wholeStr = "";
		if((resultNum / resultDen) == 0){
			numStr = RomanNumeral.intToNumeral(resultNum, true);
			denStr = RomanNumeral.intToNumeral(resultDen, true);
		}
		else{
			whole = resultNum / resultDen;
			resultNum = resultNum % resultDen;
			numStr = RomanNumeral.intToNumeral(resultNum, true);
			denStr = RomanNumeral.intToNumeral(resultDen, true);
			wholeStr = RomanNumeral.intToNumeral(whole, false);
		}
		if(whole == 0 && !numStr.equals("nulla")) return numStr + "/" + denStr;
		else if(whole > 0 || whole < 0){
			String resultString = wholeStr;
			if(resultNum > 0){
				resultString += " " + numStr + "/" + denStr;
			}
			return resultString;
		}
		else if(numStr.equals("nulla")) return numStr;
		return numStr;
	}
	
	public static String multiplyRomanNumerals(String[] inputArgs){
		
		FracNumber[] fracNumbers = new FracNumber[inputArgs.length];
		
		for(int i = 0; i < inputArgs.length; i++){
			String[] args = inputArgs[i].split(" ");
			int whole = 0;
			int num = 0;
			int den = 0;
			for(int j = 0; j < args.length; j++){
				if(!args[j].contains("/")){
					whole = RomanNumeral.numeralToInt((args[j]));
				}
				else{
					String numStr = args[j].substring(0, args[j].indexOf('/'));
					String denStr = args[j].substring(args[j].indexOf('/') + 1, args[j].length());
					num = RomanNumeral.numeralToInt(numStr);
					den = RomanNumeral.numeralToInt(denStr);
				}
				
			}
			if(num != 0 && den != 0){
				whole *= den;
				if(whole > 0){
					num += whole;
					fracNumbers[i] = new FracNumber(num, den);
				}
				else if(whole < 0){
					num -= whole;
					fracNumbers[i] = new FracNumber(-num, den);
				}
				else{
					fracNumbers[i] = new FracNumber(num, den);
				}
			}
			else{
				fracNumbers[i] = new FracNumber(whole, 1);
			}
		}
		
		FracNumber a = fracNumbers[0];
		for(int i = 1; i < fracNumbers.length; i++){
			FracNumber b = fracNumbers[i];
			a = FracNumber.multiplyFracNumbers(a, b);
			//System.out.println("Result: " + a.getNum() + " Result: " + a.getDen());
		}
		//System.out.println(a);
		int whole = 0;
		int resultNum = a.getNum();
		int resultDen = a.getDen();
		//System.out.println(resultNum + " " + resultDen);
		String numStr = "";
		String denStr = "";
		String wholeStr = "";
		if((resultNum / resultDen) == 0){
			numStr = RomanNumeral.intToNumeral(resultNum, true);
			denStr = RomanNumeral.intToNumeral(resultDen, true);
		}
		else{
			whole = resultNum / resultDen;
			resultNum = Math.abs(resultNum % resultDen);
			numStr = RomanNumeral.intToNumeral(resultNum, true);
			denStr = RomanNumeral.intToNumeral(resultDen, true);
			wholeStr = RomanNumeral.intToNumeral(whole, false);
		}
		if(whole == 0 && !numStr.equals("nulla")) return numStr + "/" + denStr;
		else if(whole > 0 || whole < 0){
			String resultString = wholeStr;
			if(resultNum > 0){
				resultString += " " + numStr + "/" + denStr;
			}
			return resultString;
		}
		else if(numStr.equals("nulla")) return numStr;
		return numStr;
	}
	
public static String divideRomanNumerals(String input, String[] inputArgs1){
		
		String[] inputArgs = new String[inputArgs1.length + 1];
		inputArgs[0] = input;
		for(int i = 1; i < inputArgs.length; i++){
			inputArgs[i] = inputArgs1[i - 1];
		}
		
		
		FracNumber[] fracNumbers = new FracNumber[inputArgs.length];
		
		for(int i = 0; i < inputArgs.length; i++){
			String[] args = inputArgs[i].split(" ");
			int whole = 0;
			int num = 0;
			int den = 0;
			for(int j = 0; j < args.length; j++){
				if(!args[j].contains("/")){
					whole = RomanNumeral.numeralToInt((args[j]));
				}
				else{
					String numStr = args[j].substring(0, args[j].indexOf('/'));
					String denStr = args[j].substring(args[j].indexOf('/') + 1, args[j].length());
					num = RomanNumeral.numeralToInt(numStr);
					den = RomanNumeral.numeralToInt(denStr);
				}
				
			}
			if(num != 0 && den != 0){
				whole *= den;
				if(whole > 0){
					num += whole;
					fracNumbers[i] = new FracNumber(num, den);
				}
				else if(whole < 0){
					num -= whole;
					fracNumbers[i] = new FracNumber(-num, den);
				}
				else{
					fracNumbers[i] = new FracNumber(num, den);
				}
			}
			else if(whole == 0 && den == 0){
				//System.out.println("Div 0 " + den);
				fracNumbers[i] = new FracNumber(whole, den);
			}
			else{
				fracNumbers[i] = new FracNumber(whole, 1);
			}
		}
		
		//System.out.println(fracNumbers.length);
		
		FracNumber a = fracNumbers[0];
		for(int i = 1; i < fracNumbers.length; i++){
			FracNumber b = fracNumbers[i];
			a = FracNumber.divideFracNumbers(a, b);
			//System.out.println("Result: " + a.getNum() + " Result: " + a.getDen());
		}
		//System.out.println(a);
		int whole = 0;
		int resultNum = a.getNum();
		int resultDen = a.getDen();
		//System.out.println(resultNum + " " + resultDen);
		String numStr = "";
		String denStr = "";
		String wholeStr = "";
		if((resultNum / resultDen) == 0){
			numStr = RomanNumeral.intToNumeral(resultNum, true);
			denStr = RomanNumeral.intToNumeral(resultDen, true);
		}
		else{
			whole = resultNum / resultDen;
			resultNum = Math.abs(resultNum % resultDen);
			numStr = RomanNumeral.intToNumeral(resultNum, true);
			denStr = RomanNumeral.intToNumeral(resultDen, true);
			wholeStr = RomanNumeral.intToNumeral(whole, false);
		}
		if(whole == 0 && !numStr.equals("nulla")) return numStr + "/" + denStr;
		else if(whole > 0 || whole < 0){
			String resultString = wholeStr;
			if(resultNum > 0){
				resultString += " " + numStr + "/" + denStr;
			}
			return resultString;
		}
		else if(numStr.equals("nulla")) return numStr;
		return numStr;
	}
	
	@Test
    public void add1() {
        //given
        String[] romanNumerals = new String[]{"I", "II"};

        //when
        String actual = addRomanNumerals(romanNumerals);
        String expected = "III";

        //then
        assertEquals(expected, actual);
    }

    @Test
    public void add2() {
        //given
        String[] romanNumerals = new String[]{"II", "III", "I"};

        //when
        String actual = addRomanNumerals(romanNumerals);
        String expected = "VI";

        //then
        assertEquals(expected, actual);
    }

    @Test
    public void add3() {
        //given
        String[] romanNumerals = new String[]{"XL", "LX"};

        //when
        String actual = addRomanNumerals(romanNumerals);
        String expected = "C";

        //then
        assertEquals(expected, actual);
    }

    @Test
    public void add4() {
        //given
        String[] romanNumerals = new String[]{"III iii/v", "I ii/v", "V"};

        //when
        String actual = addRomanNumerals(romanNumerals);
        String expected = "X";

        //then
        assertEquals(expected, actual);
    }

    @Test
    public void add5() {
        //given
        String[] romanNumerals = new String[]{"-I", "I", "V", "-V"};

        //when
        String actual = addRomanNumerals(romanNumerals);
        String expected = "nulla";

        //then
        assertEquals(expected, actual);
    }

    @Test
    public void subtract1() {
        //given
        String[] romanNumerals = new String[]{"nulla"};

        //when
        String actual = subtractRomanNumerals("III", romanNumerals);
        String expected = "III";

        //then
        assertEquals(expected, actual);

    }

    @Test
    public void subtract2() {
        //given
        String[] romanNumerals = new String[]{"III", "I"};

        //when
        String actual = subtractRomanNumerals("V", romanNumerals);
        String expected = "I";

        //then
        assertEquals(expected, actual);

    }

    @Test
    public void subtract3() {
        //given
        String[] romanNumerals = new String[]{"III i/v"};

        //when
        String actual = subtractRomanNumerals("III ii/v", romanNumerals);
        String expected = "i/v";

        //then
        assertEquals(expected, actual);

    }

    @Test
    public void subtract4() {
        //given
        String[] romanNumerals = new String[]{"V", "III", "I"};

        //when
        String actual = subtractRomanNumerals("III", romanNumerals);
        String expected = "-VI";

        //then
        assertEquals(expected, actual);

    }

    @Test
    public void subtract5() {
        //given
        String[] romanNumerals = new String[]{"-I", "-C", "XVII"};

        //when
        String actual = subtractRomanNumerals("-I", romanNumerals);
        String expected = "LXXXIII";

        //then
        assertEquals(expected, actual);

    }
    
    @Test
    public void multiply1() {
        //given
        String[] romanNumerals = new String[]{"nulla", "I", "-CXXVI"};

        //when
        String actual = multiplyRomanNumerals(romanNumerals);
        String expected = "nulla";

        //then
        assertEquals(expected, actual);

    }

    @Test
    public void multiply2() {
        //given
        String[] romanNumerals = new String[]{"III", "III", "-III i/ii", "XI i/ii"};

        //when
        String actual = multiplyRomanNumerals(romanNumerals);
        String expected = "-CCCLXII i/iv";

        //then
        assertEquals(expected, actual);
    }

    @Test
    public void multiply3() {
        //given
        String[] romanNumerals = new String[]{"vi/vii", "vii/viii"};

        //when
        String actual = multiplyRomanNumerals(romanNumerals);
        String expected = "iii/iv";

        //then
        assertEquals(expected, actual);
    }

    @Test
    public void multiply4() {
        //given
        String[] romanNumerals = new String[]{"I", "I i/x", "I", "I"};

        //when
        String actual = multiplyRomanNumerals(romanNumerals);
        String expected = "I i/x";

        //then
        assertEquals(expected, actual);
    }

    @Test
    public void multiply5() {
        //given
        String[] romanNumerals = new String[]{"MCCCXXXVII", "II"};

        //when
        String actual = multiplyRomanNumerals(romanNumerals);
        String expected = "MMDCLXXIV";

        //then
        assertEquals(expected, actual);
    }

    
    @Test
    public void divide1() {
        //given
        String[] romanNumerals = new String[]{"I"};

        //when
        String actual = divideRomanNumerals("XVI", romanNumerals);
        String expected = "XVI";

        //then
        assertEquals(expected, actual);
    }

    @Test(expected = IllegalArgumentException.class)
    public void divide2() {
        //given
        String[] romanNumerals = new String[]{"nulla"};

        //when
        divideRomanNumerals("XVI", romanNumerals);

        //then EXCEPTION IS THROWN
    }

    @Test
    public void divide3() {
        //given
        String[] romanNumerals = new String[]{"ii/iii"};

        //when
        String actual = divideRomanNumerals("ix/x", romanNumerals);
        String expected = "I vii/xx";

        //then
        assertEquals(expected, actual);
    }

    @Test
    public void divide4() {
        //given
        String[] romanNumerals = new String[]{"VII"};

        //when
        String actual = divideRomanNumerals("XVI", romanNumerals);
        String expected = "II ii/vii";

        //then
        assertEquals(expected, actual);
    }

    @Test
    public void divide5() {
        //given
        String[] romanNumerals = new String[]{"XIII"};

        //when
        String actual = divideRomanNumerals("I", romanNumerals);
        String expected = "i/xiii";

        //then
        assertEquals(expected, actual);
    }
	
}
