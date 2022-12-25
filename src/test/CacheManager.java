package test;

import java.util.HashSet;

public class CacheManager {

    private CacheReplacementPolicy crp;
    private int size;
    private HashSet<String> cache = new HashSet<String>();

    public CacheManager(int size, CacheReplacementPolicy crp) {
        this.crp = crp;
        this.size = size;
    }

    public void add(String word) {
        crp.add(word);
        if (cache.size() < size) {
            cache.add(word);
        } else {
            remove();
            cache.add(word);
        }
    }

    public String remove() {
        String removed = crp.remove();
        cache.remove(removed);
        return removed;

    }

    public boolean query(String word) {
        // if the word is in the cache, return true
        return cache.contains(word);
    }

}
