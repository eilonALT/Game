package test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Dictionary {

    private String[] filesNames;
    private CacheManager exsistsWords;
    private CacheManager nonExsistsWords;
    private BloomFilter bloomFilter;

    public Dictionary(String... filesNames) throws FileNotFoundException {
        this.filesNames = filesNames;
        this.exsistsWords = new CacheManager(400, new LRU());
        this.nonExsistsWords = new CacheManager(100, new LFU());
        this.bloomFilter = new BloomFilter(256, "MD5", "SHA1");
        // add all words from files to bloom filter
        try {
            for (String file : filesNames) {
                Scanner scanner = new Scanner(new File(file));
                while (scanner.hasNext()) {
                    bloomFilter.add(scanner.next());
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    public boolean query(String word) {
        // check if word is in the exsistsWords cache
        if (exsistsWords.query(word)) {
            return true;
        }
        // check if word is in the nonExsistsWords cache
        if (nonExsistsWords.query(word)) {
            return false;
        }
        // check if word is in the files (using bloom filter)
        if (bloomFilter.contains(word)) {
            exsistsWords.add(word);
            return true;
        }
        // word is not in the files
        nonExsistsWords.add(word);
        return false;
    }

    public boolean challenge(String word) {
        // check if word is in the files using the iosearcher
        return IOSearcher.search(word, filesNames);
    }

}
