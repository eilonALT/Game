package test;
import java.util.HashMap;
import java.util.PriorityQueue;

public class LFU implements CacheReplacementPolicy {
    // least frequently used

    // use priority queue
    // use a hash map to store the frequency of each word
    // when adding a word, if the word is already in the hash map, increment the
    // frequency
    // if the word is not in the hash map, add it to the hash map with a frequency of
    // 1
    // when removing a word, remove the word with the lowest frequency
    // if there are multiple words with the same frequency, remove the word that was
    // added first

    private PriorityQueue<String> queue;
    private HashMap<String, Integer> map;

    public LFU() {
        queue = new PriorityQueue<String>();
        map = new HashMap<String, Integer>();
    }

    @Override
    public void add(String word) {
        // if the word is already in the hash map, increment the frequency
        if (map.containsKey(word)) {
            map.put(word, map.get(word) + 1);
        }
        // if the word is not in the hash map, add it to the hash map with a frequency of
        // 1
        else {
            map.put(word, 1);
        }

        // if the word is already in the priority queue, remove it and add it to the
        // priority queue
        if (queue.contains(word)) {
            queue.remove(word);
            queue.add(word);
        }
        // if the word is not in the priority queue, add it to the priority queue
        else {
            queue.add(word);
        }
    }

    @Override
    public String remove() {
        // remove the word with the lowest frequency
        // if there are multiple words with the same frequency, remove the word that was
        // added first
        String lowestFrWord = queue.peek();
        for (String word : queue) {
            if (map.get(word) < map.get(lowestFrWord)) {
                lowestFrWord = word;
            }
        }
        queue.remove(lowestFrWord);
        map.remove(lowestFrWord);
        return lowestFrWord;
    }

}
