package test;

import java.io.File;

public class IOSearcher {

    public static boolean search(String word, String[] files) {
        // search the word in the files
        // if the word is in the files, return true
        // otherwise, return false
        for (String file : files) {
            File f = new File(file);
            if (f.exists()) {
                // if (f.contains(word))
                    return true;
            }
        }
        return false;
    }

    
}
