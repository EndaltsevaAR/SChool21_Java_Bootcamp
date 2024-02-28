package ex00;

/**
 * Java is a strictly typed programming language. Fundamental data types
 * (boolean, character, integer, floating point number) are represented in Java
 * by eight primitive types: boolean, char, byte, short, int, long, float,
 * double.
 * Work with integer type.
 * Calculate the sum of digits of a six-digit int number (the number value is
 * set directly in the code by explicitly initializating the number variable).
 * 
 * Example of the program operation for number 479598:
 * $ java Program
 * 42
 */

public class Program {
    public static void main(String[] args) {
        int number = 479598;
        System.out.println("Sum is " + getSum(number));
    }

    private static int getSum(int number) {
        String strNumber = String.valueOf(number);
        return strNumber.charAt(0) + strNumber.charAt(1) + strNumber.charAt(2) + strNumber.charAt(3)
                + strNumber.charAt(4) + strNumber.charAt(5) - '0' * 6;
    }
}