package test;

import java.util.HashMap;

public class DictionaryManager {
    private HashMap<String, Dictionary> dictionaries;
    private static DictionaryManager instance = null;

    private DictionaryManager() {
        dictionaries = new HashMap<>();
    }

    public static DictionaryManager get() {
        if (instance == null) {
            instance = new DictionaryManager();
        }
        return instance;
    }

    public int getSize() {
        return dictionaries.size();
    }

    public boolean query(String... args) {
        if (args.length < 1) {
            return false;
        }
        String searchWord = args[args.length - 1];
        boolean result = false;
        for (int i = 0; i < args.length - 1; i++) {
            if (!dictionaries.containsKey(args[i])) {
                addDictionary(args[i], new Dictionary(args[i]));
            }
            if (dictionaries.get(args[i]).query(searchWord)) {
                result = true;
            }
        }
        return result;
    }

    public boolean challenge(String... args) {
        if (args.length < 1) {
            return false;
        }
        String searchWord = args[args.length - 1];
        boolean result = false;
        for (int i = 0; i < args.length - 1; i++) {
            if (!dictionaries.containsKey(args[i])) {
                addDictionary(args[i], new Dictionary(args[i]));
            }
            if (dictionaries.get(args[i]).challenge(searchWord)) {
                result = true;
            }
        }
        return result;
    }

    private void addDictionary(String name, Dictionary dictionary) {
        dictionaries.put(name, dictionary);
    }

}
