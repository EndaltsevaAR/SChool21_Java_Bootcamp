package ex05;

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
                String input_line = scanner.nextLine();
                if (input_line.equals(stopWord)) {
                    break;
                }
                switch (i) {
                    case 0:
                        students.add(input_line);
                        break;
                    case 1:
                        lessons.add(input_line);
                        break;
                    default:
                        attendances.add(input_line);
                }
            } while (true);
        }
        scanner.close();
        getTimetable(students, lessons, attendances);
    }

    private static void getTimetable(List<String> students, List<String> lessons, List<String> attendances) {
        LocalDateTime endDate = LocalDateTime.of(2020, 9, 30, 23, 59, 59);

        // поиск всех дат занятий
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

        // поиск пропущенных и присутствующих занятий по количеству студентов
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
            LocalDateTime currentDate = LocalDateTime.of(2020, 9, Integer.parseInt(parts[2]), Integer.parseInt(parts[1]), 0, 0);
            int index = students.indexOf(parts[0]);
            if (parts[3].equals("NOT_HERE")) {
                upsents.get(index).add(currentDate);
            } else {
                presents.get(index).add(currentDate);
            }
        }
        paintGraph(students, lessonDates, upsents, presents);
    }

    private static void paintGraph(List<String> students, List<LocalDateTime> lessonDates, List<List<LocalDateTime>> upsets, List<List<LocalDateTime>> presents) {
        List<StringBuilder> results = new ArrayList<>();
        for (int i = 0; i < students.size() + 1; i++) {
            results.add(new StringBuilder());
        }

        // шапка
        results.get(0).append(" ".repeat(SPACES_NUMBER));
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

        //основная часть
        for (int i = 0; i < students.size(); i++) {
            int nameSize = students.get(0).length();
            results.get(i + 1).append(" ".repeat(SPACES_NUMBER - nameSize)).append(students.get(i));
            for (LocalDateTime lesson : lessonDates) {
                if (upsets.get(i).contains(lesson)) {
                    results.get(i + 1).append(" ".repeat(SPACES_NUMBER - 2)).append("-1|");
                } else if (presents.get(i).contains(lesson)) {
                    results.get(i + 1).append(" ".repeat(SPACES_NUMBER - 1)).append("1|");
                } else {
                    results.get(i + 1).append(" ".repeat(SPACES_NUMBER)).append("|");
                }
            }

        }

        for (StringBuilder builder : results) {
            System.out.println(builder);
        }
    }

    private static LocalDateTime findNearestDate(String dayOfWeek) {
        LocalDateTime startDate = LocalDateTime.of(2020, 9, 1, 0, 0, 0);

        return switch (dayOfWeek) {
            case "MO" -> startDate.with(TemporalAdjusters.next(DayOfWeek.MONDAY));
            case "TU" -> startDate.with(TemporalAdjusters.next(DayOfWeek.TUESDAY));
            case "WE" -> startDate.with(TemporalAdjusters.next(DayOfWeek.WEDNESDAY));
            case "TH" -> startDate.with(TemporalAdjusters.next(DayOfWeek.THURSDAY));
            case "FR" -> startDate.with(TemporalAdjusters.next(DayOfWeek.FRIDAY));
            case "SA" -> startDate.with(TemporalAdjusters.next(DayOfWeek.SATURDAY));
            default -> startDate.with(TemporalAdjusters.next(DayOfWeek.SUNDAY));
        };
    }

    private static String getShortName(String dayOfWeek) {
        return switch (dayOfWeek) {
            case "MONDAY" -> "MO";
            case "TUESDAY" -> "TU";
            case "WEDNESDAY" -> "WE";
            case "THURSDAY" -> "TH";
            case "FRIDAY" -> "FR";
            case "SATURDAY" -> "SA";
            default -> "SU";
        };
    }
}

/*
John
Mike
.
2 MO
4 WE
.
Mike 2 28 NOT_HERE
John 4 9 HERE
Mike 4 9 HERE
.
 */