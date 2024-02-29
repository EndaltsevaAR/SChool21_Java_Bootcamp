package ex02.MAIN.folder2;

/**
Let's implement a utility handling the files. The application shall display information about the files, folder content and size, 
and provide moving/renaming functionality. In essence, the application emulates a command line of Unix-like systems.
The program shall accept as an argument the absolute path to the folder where we start to work, and support the following commands:
mv WHAT WHERE – enables to transfer or rename a file if WHERE contains a file name without a path.
ls – displays the current folder contents (file and subfolder names and sizes in KB)
cd FOLDER_NAME – changes the current directory

Example of the program operation for MAIN directory:

$ java Program
-> /home/joserans/school/java/gitBootcamp/final_common/SChool21_Java_Bootcamp/src/day02/src/ex02/MAIN (your pwd)
-> ls
folder1 60 KB
folder2 90 KB
-> cd folder1
C:/MAIN/folder1
-> ls
image.jpg 10 KB
animation.gif 50 KB
-> mv image.jpg image2.jpg
-> ls
image2.jpg 10 KB
animation.gif 50 KB
-> mv animation.gif ../folder2
-> ls
image2.jpg 10 KB
-> cd ../folder2
C:/MAIN/folder2
-> ls
text.txt 10 KB
Program.java 80 KB
animation.gif 50 KB
-> exit

Note:
You should test the program functionality using your own set of files/folders.
 */

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

import static java.lang.System.exit;

public class Program {
    private static final String STOP_WORD = "exit";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        File file = getFile(scanner);
        printYourLocation(file);
        String command = scanner.nextLine();
        while (!command.equals(STOP_WORD)) {
            file = doOperation(file, command);
            command = scanner.nextLine();
        }
        scanner.close();
    }

    private static File getFile(Scanner scanner) {
        String dirPath;
        File file;
        do {
            dirPath = scanner.nextLine();
            if (dirPath.equals(STOP_WORD)) {
                scanner.close();
                exit(-1);
            }
            file = new File(dirPath);

        } while (!file.isDirectory());
        return file;
    }

    private static File doOperation(File file, String command) {
        String[] parts = command.split(" ");
        switch (parts[0]) {
            case "ls":
                doLS(file);
                break;
            case "mv":
                if (parts.length != 3) {
                    System.err.println("Command is not correct!");
                } else {
                    doMove(file, parts[1], parts[2]);
                }

                break;
            case "cd":
                if (parts.length != 2) {
                    System.err.println("Command is not correct!");
                } else {
                    file = changeDirectory(file, parts[1]);
                }
                printYourLocation(file);
                break;
            default:
                System.err.println("There is no available this command");
        }
        return file;
    }

    private static void doLS(File directoryName) {
        File[] files = directoryName.listFiles();
        if (files != null) {
            for (File file : files) {
                System.out.println(file.getName() + " " + getKb(file.length()) + " KB");
            }
        }
    }

    private static long getKb(long length) {
        return length / 1024;
    }

    private static File changeDirectory(File file, String newDirName) {
        Path currentPath = file.toPath();
        Path newPath = currentPath.resolve(Paths.get(newDirName)).normalize();
        return newPath.toFile();
    }

    private static void doMove(File file, String whatFileName, String whereFileName) {
        Path currentPath = file.toPath();
        Path whatPath = currentPath.resolve(Paths.get(whatFileName)).normalize();
        Path wherePath = currentPath.resolve(Paths.get(whereFileName)).normalize();
        File whatFile = whatPath.toFile();
        File whereFile = wherePath.toFile();
        if (whereFile.isDirectory() && !whatFile.isDirectory()) {
            whereFile = new File(wherePath + "/" + whatFile.getName());
        }
        if (whatFile.isDirectory() && whereFile.isDirectory()) {
            String nesFileName = whatFile.getParent() + "/" + whereFile.getName() + "/" + whatFile.getName();
            if (!whatFile.renameTo(new File(nesFileName))) {
                System.err.println("Something wrong!");
            }
        } else {
            if (!whatFile.renameTo(whereFile)) {
                System.err.println("Something wrong!");
            }
        }
    }

    private static void printYourLocation(File file) {
        System.out.println("You are at " + file.getName());
    }
}
