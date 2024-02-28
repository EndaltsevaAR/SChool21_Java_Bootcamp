package ex02;

/**
 * Today, you are Google.
You need to count queries related to coffee preparation which our search system users make at a certain moment. 
It is clear that the sequence of search queries is infinite. It is impossible to store these queries and count them later.

But there is a solution—process the flow of queries. Why should we waste our resources for all queries if we are only interested in a 
specific feature of this query sequence?  Let's assume that each query is any natural number other than 0 and 1. A query is related to coffee 
preparation only if the sum of digits of the number (query) is a prime number.

So, we need to implement a program that will count the number of elements for a specified set of numbers whose sum of digits is a prime number.
To keep it simple, let's assume that this potentially infinite sequence of queries is still limited, and the last sequence element is number 42.
This task guarantees that input data is absolutely correct.
Example of program operation:

$ java Program
-> 198131
-> 12901212
-> 11122
-> 42
   Count of coffee-request – 2
 */

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
