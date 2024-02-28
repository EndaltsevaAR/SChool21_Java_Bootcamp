package ex05;

/**
 * You've just become a great hacker, but your customer comes back to you with another task. 
 * This time, they need to be able to maintain a class timetable in their educational institution. Customer opens a school in September 2020. 
 * So, you need to implement the MVP version of the project for this month only.
 * 
You need to be able to create a list of students and specify time and weekdays for classes. 
Classes can be held on any day of week between 1 pm and 6 pm. Multiple classes can be held on a single day. 
However, total classes per week cannot exceed 10.
Maximum number of students in the timetable is also 10. Maximum length of a student's name is 10 (no spaces).
You should also provide an ability to record student's attendance. 
To do so, time and date of classes shall be specified next to each student's name along with attendance status (HERE, NOT_HERE). 
You do not need to record attendance for all classes in a month.
Therefore, application's life cycle is as follows:

Creating a list of students
Populating a timetable—each class (time, day of week) is entered in a separate row
Attendance recording
Displaying the timetable in tabular form with attendance statuses.

Each application operation stage is divided by "." (period). 
Absolute correctness of data is guaranteed, except for sequential ordering of classes when populating the timetable.
 */

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.time.DayOfWeek;
import java.time.temporal.TemporalAdjusters;

public class Program {
    public static final int SPACES_NUMBER = 10;

    public static void main(String[] args) {
        String stopWord = ".";
        List<String> students = new ArrayList<>();
        List<String> lessons = new ArrayList<>();
        List<String> attendances = new ArrayList<>();

        Scanner scanner = new Scanner(System.in);

        for (int i = 0; i < 3; i++) {
            do {
                String inputLine = scanner.nextLine();
                if (inputLine.equals(stopWord)) {
                    break;
                }
                switch (i) {
                    case 0:
                        students.add(inputLine);
                        break;
                    case 1:
                        lessons.add(inputLine);
                        break;
                    default:
                        attendances.add(inputLine);
                }
            } while (true);
        }
        scanner.close();
        getTimetable(students, lessons, attendances);
    }

    private static void getTimetable(List<String> students, List<String> lessons, List<String> attendances) {
        LocalDateTime endDate = LocalDateTime.of(2020, 9, 30, 23, 59, 59);

        // search all dates
        List<LocalDateTime> lessonDates = new ArrayList<>();
        for (String lesson : lessons) {
            String[] parts = lesson.split(" ");
            LocalDateTime nearestDay = findNearestDate(parts[1]);
            nearestDay = nearestDay.plusHours(Integer.parseInt(parts[0]));
            lessonDates.add(nearestDay);
            while (true) {
                nearestDay = nearestDay.plusWeeks(1);
                if (nearestDay.isAfter(endDate)) {
                    break;
                } else {
                    lessonDates.add(nearestDay);
                }
            }
            lessonDates.sort(Comparator.naturalOrder());
        }

        // search for missed and present classes by number of students
        List<List<LocalDateTime>> upsents = new ArrayList<>();
        List<List<LocalDateTime>> presents = new ArrayList<>();
        for (int i = 0; i < students.size(); i++) {
            List<LocalDateTime> upsent = new ArrayList<>();
            upsents.add(upsent);
            List<LocalDateTime> present = new ArrayList<>();
            presents.add(present);
        }

        for (String attendance : attendances) {
            String[] parts = attendance.split(" ");
            LocalDateTime currentDate = LocalDateTime.of(2020, 9, Integer.parseInt(parts[2]),
                    Integer.parseInt(parts[1]), 0, 0);
            int index = students.indexOf(parts[0]);
            if (parts[3].equals("NOT_HERE")) {
                upsents.get(index).add(currentDate);
            } else {
                presents.get(index).add(currentDate);
            }
        }
        paintGraph(students, lessonDates, upsents, presents);
    }

    private static void paintGraph(List<String> students, List<LocalDateTime> lessonDates,
            List<List<LocalDateTime>> upsets, List<List<LocalDateTime>> presents) {
        List<StringBuilder> results = new ArrayList<>();
        for (int i = 0; i < students.size() + 1; i++) {
            results.add(new StringBuilder());
        }

        // head
        results.get(0).append(repeatNTimes(" ", SPACES_NUMBER));
        for (LocalDateTime lesson : lessonDates) {
            int time = lesson.getHour();
            String day = String.valueOf(lesson.getDayOfMonth());
            String dayOfWeek = getShortName(lesson.getDayOfWeek().name());

            if (day.length() == 1) {
                results.get(0).append(time).append(":00 ").append(dayOfWeek).append("  ").append(day).append("|");
            } else {
                results.get(0).append(time).append(":00 ").append(dayOfWeek).append(" ").append(day).append("|");
            }
        }

        // main part
        for (int i = 0; i < students.size(); i++) {
            int nameSize = students.get(0).length();
            results.get(i + 1).append(repeatNTimes(" ", SPACES_NUMBER - nameSize)).append(students.get(i));
            for (LocalDateTime lesson : lessonDates) {
                if (upsets.get(i).contains(lesson)) {
                    results.get(i + 1).append(repeatNTimes(" ", SPACES_NUMBER - 2)).append("-1|");
                } else if (presents.get(i).contains(lesson)) {
                    results.get(i + 1).append(repeatNTimes(" ", SPACES_NUMBER - 1)).append("1|");
                } else {
                    results.get(i + 1).append(repeatNTimes(" ", SPACES_NUMBER)).append("|");
                }
            }
        }
        for (StringBuilder builder : results) {
            System.out.println(builder);
        }
    }

    private static StringBuilder repeatNTimes(String word, int times) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int index = 0; index < times; index++) {
            stringBuilder.append(word);
        }
        return stringBuilder;
    }

    private static LocalDateTime findNearestDate(String dayOfWeek) {
        LocalDateTime startDate = LocalDateTime.of(2020, 9, 1, 0, 0, 0);

        if ("MO".equals(dayOfWeek)) {
            return startDate.with(TemporalAdjusters.next(DayOfWeek.MONDAY));
        } else if ("TU".equals(dayOfWeek)) {
            return startDate.with(TemporalAdjusters.next(DayOfWeek.TUESDAY));
        } else if ("WE".equals(dayOfWeek)) {
            return startDate.with(TemporalAdjusters.next(DayOfWeek.WEDNESDAY));
        } else if ("TH".equals(dayOfWeek)) {
            return startDate.with(TemporalAdjusters.next(DayOfWeek.THURSDAY));
        } else if ("FR".equals(dayOfWeek)) {
            return startDate.with(TemporalAdjusters.next(DayOfWeek.FRIDAY));
        } else if ("SA".equals(dayOfWeek)) {
            return startDate.with(TemporalAdjusters.next(DayOfWeek.SATURDAY));
        } else {
            return startDate.with(TemporalAdjusters.next(DayOfWeek.SUNDAY));
        }
    }

    private static String getShortName(String dayOfWeek) {
        if ("MONDAY".equals(dayOfWeek)) {
            return "MO";
        } else if ("TUESDAY".equals(dayOfWeek)) {
            return "TU";
        } else if ("WEDNESDAY".equals(dayOfWeek)) {
            return "WE";
        } else if ("THURSDAY".equals(dayOfWeek)) {
            return "TH";
        } else if ("FRIDAY".equals(dayOfWeek)) {
            return "FR";
        } else if ("SATURDAY".equals(dayOfWeek)) {
            return "SA";
        } else {
            return "SU";
        }
    }
}

/*
 * John
 * Mike
 * .
 * 2 MO
 * 4 WE
 * .
 * Mike 2 28 NOT_HERE
 * John 4 9 HERE
 * Mike 4 9 HERE
 * .
 */