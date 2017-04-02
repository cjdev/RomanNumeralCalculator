package com.company;


/*********************
 * Name: Hamdi Allam
 * School: De Anza College. 2017 Transfer
 **********************/


import java.util.*;


public class RomanNumeralCalculator {


    // Collections to store Decimal <-> Roman connections
    private static Hashtable<String, Integer> table =
            new Hashtable<>();

    private static Hashtable<Integer, String> mapInversed
            = new Hashtable<>();


    // initialize the data set
    public static void initializeTable(){

        // normal set
        table.put("I", 1); table.put("V", 5);
        table.put("X", 10); table.put("L", 50);
        table.put("C", 100); table.put("D", 500);
        table.put("M", 1000);

        // 'other' set
        table.put("IV", 4);
        table.put("IX", 9);
        table.put("XL", 40);
        table.put("XC", 90);
        table.put("CD", 400);
        table.put("CM", 900);


        // inversed table
        for(Map.Entry<String, Integer> entry: table.entrySet()){
            mapInversed.put(entry.getValue(), entry.getKey());
        }

    }

    public static void main(String[] args) {
        /***** My Personal Tests*****/
        initializeTable();

        System.out.println("Conversions ");
        System.out.println("XLIX: " + convertToBase10("XLIX"));
        System.out.println("23: " + convertToRoman(23));

        System.out.println("\n");


        System.out.println("Addition: ");
        System.out.println("XX + IV: " + addRomanNumerals("XX", "IV"));
        System.out.println("XX + -XX: " + addRomanNumerals("XX", "-XX"));
        System.out.println("I v/x + I v/x: " + addRomanNumerals("I", "v/x", "I", "v/x"));

        System.out.println("\n");


        System.out.println("Subtraction: ");
        System.out.println("XX - IV: " + subtractRomanNumerals("XX", "IV"));
        System.out.println("XX - -XX: " + subtractRomanNumerals("XX", "-XX"));

        System.out.println("\n");


        System.out.println("Multiplication: ");
        System.out.println("V * nulla: " + multiplyRomanNumerals("V", "nulla"));
        System.out.println("V * V: " + multiplyRomanNumerals("V", "V"));
        System.out.println("V * -V: " + multiplyRomanNumerals("V", "V"));


        System.out.println("\n");


        System.out.println("Division: ");
        System.out.println("IX/III/III: " + divideRomanNumerals("IX", "III", "III"));
        System.out.println("-IX/III/-III: " + divideRomanNumerals("IX", "III", "III"));
        System.out.println("IX/III/-III: " + divideRomanNumerals("IX", "III", "III"));
        System.out.println("v/x / v/x: " + divideRomanNumerals("v/x", "v/x"));
        System.out.println("v/x / i/iv: " + divideRomanNumerals("v/x", "i/iv"));
    }

    /**************************************************************************
     * Most Computation is done in these two functions for all the methods(add/mul/div/etc)
     * Results are stored in lists and does not do any specific computation.
     *
     * It is up to the specifics methods to reduce the list using the desire algorithm.
     ***************************************************************************/

    // Everything is passed by reference. ALL GOOD :)
    private static void populateList(ArrayList<Double> nums, String... args){
        double frac, temp;
        String[] partials; // used to compute the fractions
        boolean neg = false; // special case with negative numbers
        int marker;
        for(int i = 0; i < args.length; i++){
            args[i] = args[i].trim();

            if(args[i].indexOf(" ") != -1) {
                partials = args[i].split(" ");

                temp = convertToBase10(partials[0]);
                if(temp < 0) {
                    neg = true; // "storing" the negative sign. Restoring later
                    temp = temp * -1;
                }
                // Computation
                marker = partials[1].indexOf('/');
                temp += (convertToBase10(partials[1].substring(0, marker)) / convertToBase10(partials[1].substring(marker + 1)));
                nums.add((neg)? temp * -1 : temp);
                if(neg) neg = false;
            }
            // standalone fraction
            else if((marker = args[i].indexOf('/')) != -1) {
                temp = (convertToBase10(args[i].substring(0, marker)) / convertToBase10(args[i].substring(marker + 1)));
                nums.add(temp);
            }
            else{
                nums.add(convertToBase10(args[i]));
            }
        }
    }
    private static void testInitial(String initial, ArrayList<Double> nums, String... args){
        String[] partials;
        int marker;
        double temp;

        if(initial.indexOf(" ") != -1){
            partials = initial.split(" ");

            temp = convertToBase10(partials[0]);
            marker = partials[1].indexOf('/');
            temp += (convertToBase10(partials[1].substring(0, marker)) / convertToBase10(partials[1].substring(marker + 1)));
            nums.add(temp);
        } else if((marker = initial.indexOf('/')) != -1){
            temp = (convertToBase10(initial.substring(0, marker)) / convertToBase10(initial.substring(marker + 1)));
            nums.add(temp);
        }
        else{
            nums.add(convertToBase10(initial));
        }
    }



    /******************************************************************************/




    public static String addRomanNumerals(String... args){
        // size may need to change through iteration
        // this list will reduce to a value later
        ArrayList<Double> nums = new ArrayList<>();

        populateList(nums, args);

        //reduce the list
        double total = nums.stream().reduce((x,y)-> x + y).get();
        return convertToRoman(total);
    }

    public static String subtractRomanNumerals(String initial, String... args){
        ArrayList<Double> nums = new ArrayList<>();


        testInitial(initial, nums, args);
        populateList(nums, args);

        double total = nums.stream().reduce((x,y) -> x - y).get();
        return convertToRoman(total);
    }

    public static String multiplyRomanNumerals(String... args){
        ArrayList<Double> nums = new ArrayList<>();
        if( Arrays.asList(args).contains("nulla") ) return "nulla";

        populateList(nums, args);

        double total = nums.stream().reduce(1.0, (x,y)-> x*y );
        return convertToRoman(total);
    }

    public static String divideRomanNumerals(String numerator , String... denominators) throws IllegalArgumentException{
        //initial conditions
        if(numerator.equals("nulla")) return "nulla";
        if(Arrays.asList(denominators).contains("nulla")) throw new IllegalArgumentException();

        ArrayList<Double> nums = new ArrayList<>();

        // populate information
        testInitial(numerator, nums, denominators);
        populateList(nums, denominators);

        // 'nums.get(0)*nums.get(0) is a small workaround for intiial condition
        double total = nums.stream().reduce(nums.get(0)*nums.get(0), (x,y) -> x/y);
        return convertToRoman(total);
    }

    public static double convertToBase10(String str){
        if(table.size() == 0) initializeTable();

        if(str.equals("nulla")) return 0;
        str = str.toUpperCase(); // takes care of fractions

        double total = 0; boolean neg = false;

        // negative numbers
        if(str.charAt(0) == '-'){ neg = true; str = str.substring(1); }

        // sum everything up
        for(int i = 0; i < str.length(); i++){
            // upper case for the fractions
            total += table.get( "" + str.charAt(i));
        }


        // check for special cases. Take away the differences
        if(str.contains("IV")) total-=2;
        if(str.contains("IX")) total-=2;
        if(str.contains("XL")) total-=20;
        if(str.contains("XC")) total-=20;
        if(str.contains("CD")) total-=200;
        if(str.contains("CM")) total-=200;

        // take into account negative values
        return  ( neg ) ?  (-1 * total) :  total;
    }

    // Recursive Helper
    private static String _convertToRoman(double num){
        if( num == 0 ) return "nulla";

        // find the next high(lazy search. No performance hit
        int nextHigh = mapInversed.keySet().stream()
                .filter((x) -> x <= num)
                .sorted((x1, x2) -> Double.compare(x2, x1))
                .findFirst().get();
        if( nextHigh == num ) // match is found in the data set
            return mapInversed.get(nextHigh);

        // continue down recursively
        return mapInversed.get(nextHigh) + convertToRoman(num - nextHigh);
    }

    // used to calculate fractions
    private static int gcd(int a , int b){
        return (a == 0) ? b : gcd(b%a, a);
    }


    // uses a recursive helper
    public static String convertToRoman(double num){
        boolean neg = false;

        if(num == 0)
            return "nulla";

        // save state for negative values
        if(num < 0){
            neg = true;
            num = num * -1; //make this positive. Needed for computation
        }

        int preDec = (int) num;

        //Find out if it is a whole number
        if(num % preDec == 0) {
            // print and finish
            return (neg) ? "-" + _convertToRoman(preDec) : _convertToRoman(preDec);
        }
        // find the delta and round to the nearest hundreth
        double delta = num - preDec;
        delta = (double) Math.round(delta * 100)/100;

        // 100 is the round off
        int gcd = gcd((int) (delta*100), 100);
        int[] rf = {((int)(delta*100))/gcd, 100/gcd};


        // --------------------------------------Printing specifics------------------------
        // The value is only a fraction
        if(preDec == 0){
            return (neg) ? "-" + (_convertToRoman(rf[0])).toLowerCase() + "/" + (_convertToRoman(rf[1]).toLowerCase()) :
                    (_convertToRoman(rf[0])).toLowerCase() + "/" + (_convertToRoman(rf[1]).toLowerCase());
        }

        // fraction plus whole number format
        return (neg) ? "-" + _convertToRoman(preDec) + " " + (_convertToRoman(rf[0])).toLowerCase() + "/" + (_convertToRoman(rf[1]).toLowerCase()) :
                _convertToRoman(preDec) + " " + (_convertToRoman(rf[0])).toLowerCase() + "/" + (_convertToRoman(rf[1]).toLowerCase()) ;
    }
}
