package ex01;
/* at vc code add to launch.json at configurations for ex01
"cwd": "${workspaceFolder}/src/ex01",
"args": "inputA.txt inputB.txt",
 
 */

/**
In addition to classes designed to handle byte flows, Java has classes to simplify handling of character flows (char). 
These include abstract classes Reader/Writer, as well as their implementations (FileReader/FileWriter, etc.). 
Of special interest are BufferedReader/BufferedWriter classes which accelerate flow handling via buffering mechanisms.
Now you need to implement an application that will determine the level of similarity between texts. 
The simplest and most obvious method to do this is to analyze the frequency of occurrence of the same words.
Let's assume that we have two following texts:

1. aaa bba bba a ссс
2. bba a a a bb xxx


Let's create a dictionary that contains all words in these texts:

a, aaa, bb, bba, ccc, xxx


Now let's create two vectors with length equal to that of the dictionary. In i-th position of each vector, 
we shall reflect the frequency of occurrence of the i-th word in our dictionary in the former and latter texts:

A = (1, 1, 0, 2, 1, 0)
B = (3, 0, 1, 1, 0, 1)


Thus, each of these vectors characterizes the text in terms of frequency of occurrence of words from our dictionary. 
Let's determine the similarity between vectors using the following formula:

Thus, similarity value for these vectors is:

Numerator A. B = (1 * 3 + 1 * 0 + 0 * 1 + 2 * 1 + 1 * 0 + 0 * 1) = 5
Denominator ||A|| * ||B|| = sqrt(1 * 1 + 1 * 1 + 0 * 0 + 2  * 2 + 1 * 1 + 0 * 0) * sqrt(3 * 3 + 0 * 0 + 1 * 1 + 1 * 1  + 0 * 0 + 1 * 1) = 
= sqrt(7) * sqrt(12) = 2.64 * 3.46 = 9.1
similarity = 5 / 9.1 = 0.54


Your goal is to implement an application that accepts two files as an input (both files are passed as command-line arguments) 
and displays their similarity comparison outcome (cosine measure).
The program shall also create dictionary.txt file containing a dictionary based on these files.
Example of program operation:

$ java Program inputA.txt inputB.txt
Similarity = 0.54


Notes:

Maximum size of these files is 10 MB.
Files may contain non-letter characters.
 */

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

import static java.lang.System.exit;

public class Program {
    public static void main(String[] args) {
        System.out.println("Similarity = " + getSimilarity(args));
    }

    private static double getSimilarity(String[] args) {
        if (args.length != 2) {
            System.err.println("There is no file names!");
            exit(-1);
        }
        Map<String, Integer> wordsA = getWordMap(args[0]);
        Map<String, Integer> wordsB = getWordMap(args[1]);

        Set<String> mergedWords = new TreeSet<>(wordsA.keySet());
        mergedWords.addAll(wordsB.keySet());
        List<String> words = mergedWords.stream().collect(Collectors.toList());

        List<Integer> countsA = getCounts(words, wordsA);
        List<Integer> countsB = getCounts(words, wordsB);

        double numerator = getNumerator(countsA, countsB);
        double denominatorA = getDenominator(countsA);
        double denominatorB = getDenominator(countsB);
        return numerator / (denominatorA * denominatorB);
    }

    private static Map<String, Integer> getWordMap(String fileName) {
        Map<String, Integer> words = new TreeMap<>();
        try (
                FileReader fileReader = new FileReader(fileName);
                BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] parts = line.split(" ");
                for (String part : parts) {
                    if (words.containsKey(part)) {
                        words.put(part, words.get(part) + 1);
                    } else {
                        words.put(part, 1);
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return words;
    }

    private static List<Integer> getCounts(List<String> words, Map<String, Integer> wordsA) {
        List<Integer> counts = new ArrayList<>(Collections.nCopies(words.size(), 0));
        for (Map.Entry<String, Integer> pair : wordsA.entrySet()) {
            if (words.contains(pair.getKey())) {
                int index = words.indexOf(pair.getKey());
                counts.set(index, counts.get(index) + pair.getValue());
            }
        }
        return counts;
    }

    private static double getNumerator(List<Integer> countsA, List<Integer> countsB) {
        double sum = 0;
        for (int i = 0; i < countsA.size(); i++) {
            sum += countsA.get(i) * countsB.get(i);
        }
        return sum;
    }

    private static double getDenominator(List<Integer> countsA) {
        double sum = 0;
        for (Integer count : countsA) {
            sum += Math.pow(count, 2);
        }
        return Math.sqrt(sum);
    }
}
