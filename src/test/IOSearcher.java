package test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class IOSearcher {

    public static boolean search(String word, String file1, String file2) {
        if (searchInFile(word, file1) || searchInFile(word, file2)) {
            return true;
        }
        return false;

    }

    private static boolean searchInFile(String word, String file) {
        try {
            Scanner scanner = new Scanner(new File(file));
            while (scanner.hasNext()) {
                if (scanner.next().equals(word)) {
                    return true;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }
}
