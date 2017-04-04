
public class FracNumber {

	private int num;
	private int den;
	
	public FracNumber(int num, int den){
		this.num = num; 
		this.den = den;
	}
	
	public static FracNumber addFracNumbers(FracNumber a, FracNumber b){
		int numA = a.getNum();
		int denA = a.getDen();
		int numB = b.getNum();
		int denB = b.getDen();
		
		//System.out.println("Num A: " + numA + " Num B: " + numB + " Den A: " + denA + " Den B: " + denB);
		
		if(denA != denB){
			int denC = denA;
			numA *= denB;
			denA *= denB;
			numB *= denC;
			denB *= denC;
			//System.out.println(numA + " " + numB);
		}
		int resultNum = numA + numB;
		int resultDen = denA;
		//System.out.println(resultNum + " " + resultDen);
		int greatestDen = 1;
		for(int i = 2; i < resultNum; i++){
			//System.out.println(i);
			if(((resultNum % i) == 0) && ((resultDen % i) == 0)){
				int rem = i;
				//System.out.println("Greatest Den: " + greatestDen);
				if(rem > greatestDen) greatestDen = rem;
			}
		}
		//System.out.println("Greatest Den: " + greatestDen);
		resultNum /= greatestDen;
		resultDen /= greatestDen;
		//For the specific test cases
		return new FracNumber(resultNum, resultDen);
	}
	
	public static FracNumber subtractFracNumbers(FracNumber a, FracNumber b){
		int numA = a.getNum();
		int denA = a.getDen();
		int numB = b.getNum();
		int denB = b.getDen();
		
		//System.out.println("Num A: " + numA + " Num B: " + numB + " Den A: " + denA + " Den B: " + denB);
		
		if(denA != denB){
			int denC = denA;
			numA *= denB;
			denA *= denB;
			numB *= denC;
			denB *= denC;
			//System.out.println(numA + " " + numB);
		}
		int resultNum = numA - numB;
		int resultDen = denA;
		//System.out.println(resultNum + " " + resultDen);
		int greatestDen = 1;
		for(int i = 2; i < resultNum; i++){
			//System.out.println(i);
			if(((resultNum % i) == 0) && ((resultDen % i) == 0)){
				int rem = i;
				//System.out.println("Greatest Den: " + greatestDen);
				if(rem > greatestDen) greatestDen = rem;
			}
		}
		//System.out.println("Greatest Den: " + greatestDen);
		resultNum /= greatestDen;
		resultDen /= greatestDen;
		//For the specific test cases
		return new FracNumber(resultNum, resultDen);
	}
	
	public static FracNumber multiplyFracNumbers(FracNumber a, FracNumber b){
				
		int numA = a.getNum();
		int denA = a.getDen();
		int numB = b.getNum();
		int denB = b.getDen();
		
		if(numA == 0 || numB == 0) return new FracNumber(0, 1);
		
		//System.out.println("NumA: " + numA + " NumB: " + numB + " DenA: " + denA + " DenB: " + denB);
		
		int resultNum = numA * numB;
		int resultDen = denA * denB;
		int greatestDen = 1;
		for(int i = 2; i < resultDen; i++){
			if(resultNum % i == 0 & resultDen % i == 0){
				if(((resultNum % i) == 0) && ((resultDen % i) == 0)){
					int rem = i;
					//System.out.println("Greatest Den: " + greatestDen);
					if(rem > greatestDen) greatestDen = rem;
				}
			}
		}
		resultNum /= greatestDen;
		resultDen /= greatestDen;
		return new FracNumber(resultNum, resultDen);
	}
	
	public static FracNumber divideFracNumbers(FracNumber a, FracNumber b) throws IllegalArgumentException{
		int numA = a.getNum();
		int denA = a.getDen();
		int numB = b.getNum();
		int denB = b.getDen();
		
		//if(numA == 0 || numB == 0) return new FracNumber(0, 1);
		
		//System.out.println("NumA: " + numA + " NumB: " + numB + " DenA: " + denA + " DenB: " + denB);
		
		int resultNum = numA * denB;
		int resultDen = denA * numB;
		
		if(resultNum == 0 || resultDen == 0){
			//System.out.println("About to throw");
			throw new IllegalArgumentException("Dividing by zero");
		}
		
		int greatestDen = 1;
		for(int i = 2; i < resultDen; i++){
			if(resultNum % i == 0 & resultDen % i == 0){
				if(((resultNum % i) == 0) && ((resultDen % i) == 0)){
					int rem = i;
					//System.out.println("Greatest Den: " + greatestDen);
					if(rem > greatestDen) greatestDen = rem;
				}
			}
		}
		resultNum /= greatestDen;
		resultDen /= greatestDen;
		//System.out.println(resultNum + " " + resultDen);
		return new FracNumber(resultNum, resultDen);
	}
	
	public int getNum(){return num;}
	public int getDen(){return den;}
	
	public String toString(){
		return num + "/" + den;
	}
}
