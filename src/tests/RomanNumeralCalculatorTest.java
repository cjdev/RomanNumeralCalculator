package tests;
import org.junit.Test;
import static com.company.RomanNumeralCalculator.*;
import static org.junit.Assert.*;

public class RomanNumeralCalculatorTest {

    @Test
    public void toNumber1() {
        //given
        String romanNumber = "MMMMCMXCIX";

        //when
        double expected = 4999;
        boolean eq = Double.valueOf(expected).equals(toNumber(romanNumber));

        //then
        assertTrue(eq);
    }

    @Test
    public void toNumber2() {
        //given
        String romanNumber = "-MMMMCMXCIX";

        //when
        double expected = -4999;
        boolean eq = Double.valueOf(expected).equals(toNumber(romanNumber));

        //then
        assertTrue(eq);
    }

    @Test
    public void toNumber3() {
        //given
        String romanNumber = "nulla";

        //when
        double expected = 0;
        boolean eq = Double.valueOf(expected).equals(toNumber(romanNumber));

        //then
        assertTrue(eq);

    }

    @Test
    public void toNumber4() {
        //given
        String romanNumber = "i/iv";

        //when
        double expected = 0.25;
        boolean eq = Double.valueOf(expected).equals(toNumber(romanNumber));

        //then
        assertTrue(eq);
    }

    @Test
    public void toNumber5() {
        //given
        String romanNumber = "X i/iv";

        //when
        double expected = 10.25;
        boolean eq = Double.valueOf(expected).equals(toNumber(romanNumber));

        //then
        assertTrue(eq);
    }

    @Test
    public void toNumber6() {
        //given
        String romanNumber = "-X i/iv";

        //when
        double expected = -10.25;
        boolean eq = Double.valueOf(expected).equals(toNumber(romanNumber));

        //then
        assertTrue(eq);
    }


    @Test
    public void toRoman1() {
        //given
        double number = 0.2;

        //when
        String actual = toRoman(number);
        String expected = "i/v";

        //then
        assertEquals(expected, actual);
    }

    @Test
    public void toNumber7() {
        //given
        String romanNumber = "III i/v";

        //when
        double expected = 3.2;
        boolean eq = Double.valueOf(expected).equals(toNumber(romanNumber));

        //then
        assertTrue(eq);
    }

    @Test
    public void toNumber8() {
        //given
        String romanNumber = "III ii/v";

        //when
        double expected = 3.4;
        boolean eq = Double.valueOf(expected).equals(toNumber(romanNumber));

        //then
        assertTrue(eq);
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
        String expected = "II xxix/c";

        //then
        assertEquals(expected, actual);
    }

    @Test
    public void divide5() {
        //given
        String[] romanNumerals = new String[]{"XIII"};

        //when
        String actual = divideRomanNumerals("I", romanNumerals);
        String expected = "ii/xxv";

        //then
        assertEquals(expected, actual);
    }
}
