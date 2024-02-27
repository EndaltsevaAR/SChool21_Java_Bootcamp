package ex02;

import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        String stopWord = "42";
        int countPrime = 0;

        Scanner scanner = new Scanner(System.in);
        do {
            String currentString = scanner.nextLine();
            if (currentString.equals(stopWord)) {
                break;
            }
            if (isPrime(currentString)) {
                countPrime++;
            }
        } while (true);
        System.out.println("Count of coffee-request – " + countPrime);
        scanner.close();
    }

    private static boolean isPrime(String currentString) {
        int sum = 0;
        for (int i = 0; i < currentString.length(); i++) {
            sum += (currentString.charAt(i) - '0');
        }
        return ex01.Program.getPrime(sum, false);
    }
}

/*
198131
12901212
11122
42
   Count of coffee-request – 2
 */
