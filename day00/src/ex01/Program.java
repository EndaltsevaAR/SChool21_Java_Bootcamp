package ex01;
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
tests
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