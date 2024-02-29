package ex00;
/* at vc code add to launch.json at configurations for ex00
"cwd": "${workspaceFolder}/src/ex00",
*/

/**
 * Input/output classes in Java are represented by a broad hierarchy. 
The key classes describing byte input/output behavior are abstract classes InputStream and OutputStream. 
They do not implement specific mechanisms for byte flows processing, rather delegate them to their subclasses, 
such as FileInputStream/FileOutputStream.
To understand the use of this functionality, you should implement an application for analyzing 
signatures of arbitrary files. This signature allows to define file content type and consists of a 
set of "magic numbers." These numbers are usually located in the beginning of the file. For example, 
a signature for PNG file type is represented by first eight bytes of a file that are equal for all PNG images:

89 50 4E 47 0D 0A 1A 0A


You need to implement an application that accepts the signatures.txt as an input (you should describe 
it on your own; the file name is explicitly stated in the program code). It contains a list of file 
types and their respective signatures in the HEX format. Example (specified format of this file must 
be adhered to):

PNG, 89 50 4E 47 0D 0A 1A 0A
GIF, 47 49 46 38 37 61


During execution, your program shall accept full paths to files on hard disk and keep the type 
which file signature corresponds to. The result of program execution should be written to result.txt 
file. If a signature cannot be defined, the execution result is UNDEFINED (no information should be 
written into the file).
Example of program operation:

$java Program
-> C:/Users/Admin/images.png
PROCESSED
-> C:/Users/Admin/Games/WoW.iso
PROCESSED
-> 42

Contents of result.txt file (there is no need to load this file as a result):

PNG
GIF

Notes:

We can accurately determine the content type by analyzing the file signature, since the file extension 
contained in the name (e. g. image.jpg) can be changed by simply renaming the file.

The signatures file shall contain at least 10 different formats for analysis.
 */

import java.io.*;
import java.util.*;

public class Program {
    private static int maxSignatureSize = 0;

    public static void main(String[] args) {
        Map<String, String> fileTypes = getInfoFromSignatures();
        String fileName;
        fileName = getFileName();
        while (!fileName.equals("42")) {
            fileProcessing(fileName, fileTypes);
            fileName = getFileName();
        }
    }

    private static Map<String, String> getInfoFromSignatures() {
        int byteRead;
        boolean isKey = true;
        int currentSize = 0;

        Map<String, String> fileTypes = new HashMap<>();
        try (FileInputStream fileStream = new FileInputStream("signatures.txt")) {
            StringBuilder keyFile = new StringBuilder();
            StringBuilder bytes = new StringBuilder();
            while ((byteRead = fileStream.read()) != -1) {
                if ((char) (byteRead) == ',') {
                    isKey = false;
                } else if ((char) (byteRead) == '\n') {
                    isKey = true;
                    fileTypes.put(bytes.toString().trim(), keyFile.toString().trim());
                    maxSignatureSize = Math.max(currentSize, maxSignatureSize);
                    currentSize = 0;
                    keyFile = new StringBuilder();
                    bytes = new StringBuilder();
                } else if (isKey) {
                    keyFile.append((char) (byteRead));
                } else {
                    bytes.append((char) byteRead);
                    currentSize++;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return fileTypes;
    }

    private static String getFileName() {
        StringBuilder fileName = new StringBuilder();
        int byteRead;
        try {
            while ((byteRead = System.in.read()) != -1) {
                if ((char) byteRead == '\n') {
                    break;
                }
                fileName.append((char) byteRead);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return fileName.toString();
    }

    private static void fileProcessing(String fileName, Map<String, String> fileTypes) {
        boolean isProccessed = false;
        int byteRead;
        int count = 0;

        try (FileInputStream fileInputStream = new FileInputStream(fileName)) {
            StringBuilder builder = new StringBuilder();

            while ((byteRead = fileInputStream.read()) != -1 && count++ < maxSignatureSize) {
                builder.append(String.format("%02X ", byteRead));
            }

            for (Map.Entry<String, String> pair : fileTypes.entrySet()) {
                if (builder.toString().startsWith(pair.getKey())) {
                    System.out.println("PROCESSED");
                    isProccessed = true;
                    try (FileOutputStream fileOutputStream = new FileOutputStream("result.txt", true)) {
                        fileOutputStream.write((pair.getValue() + "\n").getBytes());
                    }
                    break;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        if (!isProccessed) {
            System.out.println("UNDEFINED");
        }
    }
}
