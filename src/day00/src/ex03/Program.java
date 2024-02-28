package ex03;

/**
 * When developing corporate systems, you often need to collect different kinds of statistics. 
 * And the customer always wants such analytics to be illustrative. Who needs cold, dry figures?
 * 
Educational organizations and online schools often belong to this type of customers. Now, you need to implement functionality to visualize students' progress. 
Customer wants to see a chart showing student's progress changes over several weeks.
Customer evaluates this progress as a minimal grade for five tests within each week. Each test can be graded between 1 and 9.
The maximum number of weeks for the analysis is 18. Once the program has obtained information for each week, 
it displays the graph on the console to show minimum grades for a specific week.
And we keep assuming that 42 is the input data limit.
The exact guaranteed number of tests in a week is 5.
However, the order of weekly data input is not guaranteed, so Week 1's data can be entered after Week 2's data. 
If data input order is wrong, IllegalArgument message shall be displayed, and the program shall be shut down with -1 code.
Note:

There are many options for storing information, and arrays are just one of them. 
Apply another method for storing data about student tests without the use of arrays.
String concatenation often results in unexpected program behavior. If there are many iterations of a concatenation operation 
in a cycle for a single variable, an application may slow down significantly. That is why we should not use string concatenation inside a loop to generate a result.

Example of program operation:

$ java Program
-> Week 1
-> 4 5 2 4 2
-> Week 2
-> 7 7 7 7 6
-> Week 3
-> 4 3 4 9 8
-> Week 4
-> 9 9 4 6 7
-> 42
Week 1 ==>
Week 2 ======>
Week 3 ===>
Week 4 ====>
 */

import java.util.*;

import static java.lang.System.exit;

public class Program {
    public static void main(String[] args) {
        String stopWord = "42";
        int maxWeeks = 18;
        int currentWeek = 1;
        boolean isCorrect = true;

        List<Integer> minimums = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        do {
            String dayString = scanner.nextLine();

            if (dayString.equals(stopWord)) {
                break;
            }

            String[] weekEntry = dayString.split(" ");
            if (Integer.parseInt(weekEntry[1]) != currentWeek || currentWeek > maxWeeks) {
                isCorrect = false;
                break;
            }

            String weekPoints = scanner.nextLine();
            minimums.add(getMinimum(weekPoints));
            currentWeek++;
        } while (true);

        if (!isCorrect) {
            System.err.println("Illegal Argument");
            exit(-1);
        } else {
            for (int i = 0; i < minimums.size(); i++) {
                StringBuilder builder = new StringBuilder();
                builder.append("Week ").append(i + 1).append(" ");
                for (int j = 0; j < minimums.get(i); j++) {
                    builder.append("=");
                }
                builder.append(">");
                System.out.println(builder);
            }
        }
        scanner.close();
    }

    private static Integer getMinimum(String day) {
        String[] points = day.split(" ");
        int min = Integer.parseInt(points[0]);
        for (int i = 1; i < points.length; i++) {
            if (Integer.parseInt(points[i]) < min) {
                min = Integer.parseInt(points[i]);
            }
        }
        return min;
    }
}

/*
 * Week 1
 * 4 5 2 4 2
 * Week 2
 * 7 7 7 7 6
 * Week 3
 * 4 3 4 9 8
 * Week 4
 * 9 9 4 6 7
 * 42
 */

/*
 * 18 недель
 * Week 1
 * 4 5 2 4 2
 * Week 2
 * 7 7 7 7 6
 * Week 3
 * 4 3 4 9 8
 * Week 4
 * 9 9 4 6 7
 * Week 5
 * 4 5 2 4 2
 * Week 6
 * 7 7 7 7 6
 * Week 7
 * 4 3 4 9 8
 * Week 8
 * 9 9 4 6 7
 * Week 9
 * 4 5 2 4 2
 * Week 10
 * 7 7 7 7 6
 * Week 11
 * 4 3 4 9 8
 * Week 12
 * 9 9 4 6 7
 * Week 13
 * 4 5 2 4 2
 * Week 14
 * 7 7 7 7 6
 * Week 15
 * 4 3 4 9 8
 * Week 16
 * 9 9 4 6 7
 * Week 17
 * 4 3 4 9 8
 * Week 18
 * 9 9 4 6 7
 * 42
 */

/*
 * на меньше 18 недель - с ошибкой в номерах порядков
 * Week 1
 * 4 5 2 4 2
 * Week 2
 * 7 7 7 7 6
 * Week 3
 * 4 3 4 9 8
 * Week 4
 * 9 9 4 6 7
 * Week 1
 * 4 5 2 4 2
 * Week 2
 * 7 7 7 7 6
 * Week 3
 * 4 3 4 9 8
 * Week 4
 * 9 9 4 6 7
 * Week 1
 * 4 5 2 4 2
 * Week 2
 * 7 7 7 7 6
 * Week 3
 * 4 3 4 9 8
 * Week 4
 * 9 9 4 6 7
 * Week 1
 * 4 5 2 4 2
 * Week 2
 * 7 7 7 7 6
 * Week 3
 * 4 3 4 9 8
 * Week 4
 * 9 9 4 6 7
 * 42
 */

/*
 * 19 недель
 * Week 1
 * 4 5 2 4 2
 * Week 2
 * 7 7 7 7 6
 * Week 3
 * 4 3 4 9 8
 * Week 4
 * 9 9 4 6 7
 * Week 5
 * 4 5 2 4 2
 * Week 6
 * 7 7 7 7 6
 * Week 7
 * 4 3 4 9 8
 * Week 8
 * 9 9 4 6 7
 * Week 9
 * 4 5 2 4 2
 * Week 10
 * 7 7 7 7 6
 * Week 11
 * 4 3 4 9 8
 * Week 12
 * 9 9 4 6 7
 * Week 13
 * 4 5 2 4 2
 * Week 14
 * 7 7 7 7 6
 * Week 15
 * 4 3 4 9 8
 * Week 16
 * 9 9 4 6 7
 * Week 17
 * 4 3 4 9 8
 * Week 18
 * 9 9 4 6 7
 * Week 19
 * 9 9 4 6 7
 * 42
 */
