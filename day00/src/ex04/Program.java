package ex04;

import java.util.*;

public class Program {
    public static final int NUMBER_LETTERS = 10;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input_data = scanner.nextLine();
        char[] letters = input_data.toCharArray();
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
        List<Integer> values = letterCounts.values().stream().sorted(Comparator.reverseOrder()).limit(NUMBER_LETTERS).toList();
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
                steps.add((int)Math.floor(values.get(i)/ step));
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
AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAASSSSSSSSSSSSSSSSSSSSSSSSDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDWEWWKFKKDKKDSKAKLSLDKSKALLLLLLLLLLRTRTETWTWWWWWWWWWWOOOOOOO42

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