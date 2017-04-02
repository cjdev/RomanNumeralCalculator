package com.company;

class DecimalFractionConverter {
	private int numerator, denominator; 

	private int gcm(int num, int denom) {
		return denom == 0 ? num : gcm(denom, num % denom);
	}

	private void reduceFraction(int num, int denom) {
		int gcd = gcm(num, denom);

		this.numerator = num / gcd; 
		this.denominator = denom / gcd;
	}

	public DecimalFractionConverter(double decimal) {
		String str = String.valueOf(decimal);
		int numDigits = str.length() - 1 - str.indexOf('.');
		int denom = 1; 

		for (int i=0; i < numDigits; i++) {
			decimal *= 10;
			denom *= 10;
		}

		int num = (int) Math.round(decimal);

		reduceFraction(num, denom);
	}

	public int getNumerator() {
		return this.numerator;
	}

	public int getDenominator() {
		return this.denominator;
	}

	public String toString() {
		return String.valueOf(this.numerator) + "/" + String.valueOf(this.denominator);
	}
}