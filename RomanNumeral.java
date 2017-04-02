import java.util.HashMap;
import java.util.Map;


public class RomanNumeral {

	private static Map<Character, Integer> map = new HashMap<Character, Integer>();
	private static Map<Integer, Character> numMap = new HashMap<Integer, Character>();
	static{
		map.put('I', 1);
		map.put('V', 5);
		map.put('X', 10);
		map.put('L', 50);
		map.put('C', 100);
		map.put('D', 500);
		map.put('M', 1000);
		map.put('i', 1);
		map.put('v', 5);
		map.put('x', 10);
		map.put('l', 50);
		map.put('c', 100);
		map.put('d', 500);
		map.put('m', 1000);
		numMap.put(1, 'I');
		numMap.put(5, 'V');
		numMap.put(10, 'X');
		numMap.put(50, 'L');
		numMap.put(100, 'C');
		numMap.put(500, 'D');
		numMap.put(1000, 'M');
		
	}
	
	public static boolean isGreater(char a, char b){
		if(map.get((char) a) != null && map.get((char) b) != null){
			int c = map.get((char) a);
			int d = map.get((char) b);
			if(c > d){
				return true;
			}
			return false;
		}
		return false;
	}
	
	public static String intToNumeral(int number, boolean casing){
		boolean negative  = false;
		//System.out.println(number);
		if(number == 0) return "nulla";
		else if(number < 0) negative = true;
		number = Math.abs(number);
		int tempNumber = number;
		StringBuffer strBuff = new StringBuffer();
		int remFactor = 10;
		while(number != 0){
			int rem = tempNumber % remFactor;
			tempNumber -= rem;
			if(rem != 0){
				//System.out.println(tempNumber + " " + remFactor);
				char base = numMap.get(remFactor / 10);
				char base5 = ' ';
				char base10 = ' ';
				if(remFactor < 10000){
					base5 = numMap.get(remFactor / 2);
					base10 = numMap.get(remFactor);
				}
				int remBaseFactor = rem;
				//System.out.println(rem);
				while(remBaseFactor % 10 == 0) remBaseFactor /= 10;
				if(remFactor >= 10000){
					for(int i = 0; i < remBaseFactor; i++){
						strBuff.append(base);
					}
				}
				else if(remBaseFactor > 0 && remBaseFactor < 4){
					for(int i = 0; i < remBaseFactor; i++){
						strBuff.append(base);
					}
				}
				else if(remBaseFactor == 4){
					strBuff.append(base5);
					strBuff.append(base);
				}
				else if(remBaseFactor > 4 && remBaseFactor < 9){
					for(int i = 0; i < remBaseFactor - 5; i++){
						strBuff.append(base);
					}
					strBuff.append(base5);
				}
				else{
					strBuff.append(base10);
					strBuff.append(base);
				}
			}
			remFactor *= 10;
			number /= 10;
			
		}
		String numeral = "";
		if(negative){
			numeral += "-";
			if(casing){
				numeral += strBuff.reverse().toString().toLowerCase(); 
			}
			else{
				numeral += strBuff.reverse().toString();
			}
		}
		else{
			if(casing){
				numeral += strBuff.reverse().toString().toLowerCase(); 
			}
			else{
				numeral += strBuff.reverse().toString();
			}
		}
		return numeral;
	}
	
	public static int numeralToInt(String numeral){
		
		if(numeral.equals("nulla")) return 0;
		boolean negative = false;
		if(numeral.charAt(0) == '-') {
			negative = true;
			numeral = numeral.substring(1);
		}
		
		char[] numerals = numeral.toCharArray();
		
		int sum = 0;
		
		for(int i = numeral.length() - 1; i >= 0; i--){
			if(i > 0){
				if(isGreater(numerals[i], numerals[i - 1])){
					sum += (map.get((char)numerals[i]) - map.get((char)numerals[i - 1]));
					i--;
					continue;
				}
			}
			sum += map.get((char)numerals[i]);
		}
		if(negative) return -sum;
		return sum;
		
	}
	
}
