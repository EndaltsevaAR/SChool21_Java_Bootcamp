package ex01;

/**
 According to Böhm-Jacopini theorem, any algorithm can be written using three statements: sequence, selection, and iteration.
Using these statements in Java, you need to determine if the input number is a prime. A prime is a number which has no dividers other than the number itself and 1.
The program accepts the number entered from the keyboard as input and displays the result of checking whether that number is a prime.  In addition, the program shall output the number of steps (iterations) required to perform the check. In this task, an iteration is a single comparison operation.
For negative numbers, 0 and 1, display theIllegalArgument message and shut down the program with the -1 code.
Example of program operation:

$ java Program
-> 169
   false 12

$ java Program
-> 113
   true 10

$ java Program
-> 42
   false 1

$ java Program
-> -100 
   Illegal Argument
 */
import java.util.Scanner;

import static java.lang.System.exit;

public class Program {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int number = scanner.nextInt();
        scanner.close();
        if (number <= 1) {
            System.out.println("Illegal Argument");
            exit(-1);
        } else {
            getPrime(number, true);
        }
    }

    public static boolean getPrime(int number, boolean needPrint) {
        int step = 2;
        boolean isPrime = true;
        for (; step <= Math.sqrt(number); step++) {
            if (number % step == 0) {
                isPrime = false;
                break;
            }
        }
        if (needPrint) {
            System.out.println(isPrime + " " + (step - 1)); // because first step starts from 2
        }
        return isPrime;
    }
}

/*
 * tests
 * -> 169
 * false 12
 * 
 * $ java Program
 * -> 113
 * true 10
 * 
 * $ java Program
 * -> 42
 * false 1
 * 
 * $ java Program
 * -> -100
 * Illegal Argument
 */