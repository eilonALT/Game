package test;

import java.util.HashMap;

public class DictionaryManager {
    private HashMap<String, Dictionary> dictionaries; // dictionary name -> dictionary
    private static DictionaryManager instance = null; // singleton

    // private constructor to prevent creating new instances
    private DictionaryManager() {
        dictionaries = new HashMap<>();
    }

    // singleton get method to get the only instance of the class
    public static DictionaryManager get() {
        if (instance == null) {
            instance = new DictionaryManager();
        }
        return instance;
    }

    // get the dictionaries map size
    public int getSize() {
        return dictionaries.size();
    }

    // query the dictionaries for the given word
    public boolean query(String... args) {
        if (args.length < 1) {
            return false;
        }
        String searchWord = args[args.length - 1];
        boolean result = false;
        // check if the word is in one of the dictionaries
        for (int i = 0; i < args.length - 1; i++) {
            if (!dictionaries.containsKey(args[i])) {
                // if the dictionary is not in the map, add it
                addDictionary(args[i], new Dictionary(args[i]));
            }
            if (dictionaries.get(args[i]).query(searchWord)) {
                // if the word is in the dictionary, change the result to true
                result = true;
            }
        }
        return result;
    }

    // challenge the dictionaries for the given word
    public boolean challenge(String... args) {
        if (args.length < 1) {
            return false;
        }
        String searchWord = args[args.length - 1];
        boolean result = false;
        // check if the word is in one of the dictionaries
        for (int i = 0; i < args.length - 1; i++) {
            if (!dictionaries.containsKey(args[i])) {
                // if the dictionary is not in the map, add it
                addDictionary(args[i], new Dictionary(args[i]));
            }
            if (dictionaries.get(args[i]).challenge(searchWord)) {
                // if the word is in the dictionary, change the result to true
                result = true;
            }
        }
        return result;
    }

    // private method to add a dictionary to the dictionaries map
    private void addDictionary(String name, Dictionary dictionary) {
        dictionaries.put(name, dictionary);
    }

}
