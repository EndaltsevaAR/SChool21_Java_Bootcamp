package ex04;

/**
 * Did you know that you can use frequency analysis to decipher poorly encrypted texts?
See https://en.wikipedia.org/wiki/Frequency_analysis
Feel like a hacker and implement a program for counting a character occurrences in a text.
We like visual clarity. This is why the program will display the results in a histogram. 
This chart will show 10 most frequently occurring characters in descending order.

If letters are encountered the same number of times, they should be sorted in a lexicographic order.
Each character may occur in text a great number of times. For that reason, the chart should be scalable. 
The maximum height of the displayed chart is 10, and the minimum is 0.
Input data for the program is a string with a single "\n" character at the end (thus, a single long string can be used as input).
It is assumed that each input character can be contained in a char variable (Unicode BMP; for example, the code of letter "S" is 0053, maximum code value is 65535).

The maximum number of character occurrences is 999.

Note: this problem must be solved without multiple iterations over the source text (sorting and removing repetitions), 
because these methods will significantly slow down the application. Use other information processing methods.
Example of program operation:

$ java Program

-> AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAASSSSSSSSSSSSSSSSSSSSSSSSDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDWEWWKFKKDKKDSKAKLSLDKSKALLLLLLLLLLRTRTETWTWWWWWWWWWWOOOOOOO42

 36
  #  35
  #   #
  #   #  27
  #   #   #
  #   #   #
  #   #   #
  #   #   #  14  12
  #   #   #   #   #   9
  #   #   #   #   #   #   7   4
  #   #   #   #   #   #   #   #   2   2
  D   A   S   W   L   K   O   T   E   R
 */

import java.util.*;
import java.util.stream.Collectors;

public class Program {
    public static final int NUMBER_LETTERS = 10;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String inputData = scanner.nextLine();
        char[] letters = inputData.toCharArray();
        Map<Character, Integer> letterCounts = new HashMap<>();
        for (char letter : letters) {
            if (letterCounts.containsKey(letter)) {
                letterCounts.put(letter, letterCounts.get(letter) + 1);
            } else {
                letterCounts.put(letter, 1);
            }
        }
        scanner.close();
        getStatistics(letterCounts);
    }

    private static void getStatistics(Map<Character, Integer> letterCounts) {
        List<Integer> values = letterCounts.values()
                .stream()
                .sorted(Comparator.reverseOrder())
                .limit(NUMBER_LETTERS)
                .collect(Collectors.toList());
        int max = values.get(0);
        double step = max * 1.0 / 10;
        List<String> letters = new ArrayList<>();
        List<Integer> steps = new ArrayList<>();
        for (int i = 0; i < values.size(); i++) {
            List<String> lettersCurrentCounted = new ArrayList<>();
            for (Map.Entry<Character, Integer> pair : letterCounts.entrySet()) {
                if (pair.getValue().equals(values.get(i))) {
                    lettersCurrentCounted.add(String.valueOf(pair.getKey()));
                }
            }
            lettersCurrentCounted.sort(Comparator.naturalOrder());
            for (int j = 0; j < lettersCurrentCounted.size() && i < NUMBER_LETTERS; j++) {
                letters.add(lettersCurrentCounted.get(j));
                steps.add((int) Math.floor(values.get(i) / step));
                if (lettersCurrentCounted.size() > 1 && j < lettersCurrentCounted.size() - 1) {
                    i++;
                }
            }
        }
        paintGraph(values, letters, steps);
    }

    private static void paintGraph(List<Integer> values, List<String> letters, List<Integer> steps) {
        List<StringBuilder> results = new ArrayList<>();
        for (int i = 0; i < NUMBER_LETTERS + 2; i++) {
            results.add(new StringBuilder());
        }
        for (int i = 0; i < NUMBER_LETTERS; i++) {
            for (int j = NUMBER_LETTERS; j > NUMBER_LETTERS - steps.get(i); j--) {
                results.get(j).append("#   ");
            }
        }

        results.get(0).append(values.get(0));
        for (int i = 1; i < values.size(); i++) {
            results.get(NUMBER_LETTERS - steps.get(i)).append(values.get(i));
            int numberDigits = String.valueOf(values.get(i)).length();
            for (int j = 0; j < 4 - numberDigits; j++) {
                results.get(NUMBER_LETTERS - steps.get(i)).append(" ");
            }
        }

        for (String letter : letters) {
            results.get(NUMBER_LETTERS + 1).append(letter).append("   ");
        }

        for (StringBuilder builder : results) {
            System.out.println(builder);
        }
    }
}

/*
 * AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAASSSSSSSSSSSSSSSSSSSSSSSSDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDWEWWKFKKDKKDSKAKLSLDKSKALLLLLLLLLLRTRTETWTWWWWWWWWWWOOOOOOO42
 * 
 * 36
 * # 35
 * # #
 * # # 27
 * # # #
 * # # #
 * # # #
 * # # # 14 12
 * # # # # # 9
 * # # # # # # 7 4
 * # # # # # # # # 2 2
 * D A S W L K O T E R
 */