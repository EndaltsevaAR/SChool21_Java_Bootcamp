package ex03;

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
            String day_string = scanner.nextLine();

            if (day_string.equals(stopWord)) {
                break;
            }

            String[] WeekEntry = day_string.split(" ");
            if (Integer.parseInt(WeekEntry[1]) != currentWeek || currentWeek > maxWeeks) {
                isCorrect = false;
                break;
            }

            String weekPoints = scanner.nextLine();
            minimums.add(getMinimum(weekPoints));
            currentWeek++;
        } while (true);

        if (!isCorrect) {
            System.out.println("Illegal Argument");
            exit(-1);
        } else {
            for (int i = 0; i < minimums.size(); i++) {
                StringBuilder builder = new StringBuilder();
                builder.append("Week ").append(i + 1).append(" ").append("=".repeat(minimums.get(i))).append(">");
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
Week 1
4 5 2 4 2
Week 2
7 7 7 7 6
Week 3
4 3 4 9 8
Week 4
9 9 4 6 7
42
 */

/*  18 недель
Week 1
4 5 2 4 2
Week 2
7 7 7 7 6
Week 3
4 3 4 9 8
Week 4
9 9 4 6 7
Week 5
4 5 2 4 2
Week 6
7 7 7 7 6
Week 7
4 3 4 9 8
Week 8
9 9 4 6 7
Week 9
4 5 2 4 2
Week 10
7 7 7 7 6
Week 11
4 3 4 9 8
Week 12
9 9 4 6 7
Week 13
4 5 2 4 2
Week 14
7 7 7 7 6
Week 15
4 3 4 9 8
Week 16
9 9 4 6 7
Week 17
4 3 4 9 8
Week 18
9 9 4 6 7
42
 */

/* на меньше 18 недель - с ошибкой в номерах порядков
Week 1
4 5 2 4 2
Week 2
7 7 7 7 6
Week 3
4 3 4 9 8
Week 4
9 9 4 6 7
Week 1
4 5 2 4 2
Week 2
7 7 7 7 6
Week 3
4 3 4 9 8
Week 4
9 9 4 6 7
Week 1
4 5 2 4 2
Week 2
7 7 7 7 6
Week 3
4 3 4 9 8
Week 4
9 9 4 6 7
Week 1
4 5 2 4 2
Week 2
7 7 7 7 6
Week 3
4 3 4 9 8
Week 4
9 9 4 6 7
42
 */

/*  19 недель
Week 1
4 5 2 4 2
Week 2
7 7 7 7 6
Week 3
4 3 4 9 8
Week 4
9 9 4 6 7
Week 5
4 5 2 4 2
Week 6
7 7 7 7 6
Week 7
4 3 4 9 8
Week 8
9 9 4 6 7
Week 9
4 5 2 4 2
Week 10
7 7 7 7 6
Week 11
4 3 4 9 8
Week 12
9 9 4 6 7
Week 13
4 5 2 4 2
Week 14
7 7 7 7 6
Week 15
4 3 4 9 8
Week 16
9 9 4 6 7
Week 17
4 3 4 9 8
Week 18
9 9 4 6 7
Week 19
9 9 4 6 7
42
 */
