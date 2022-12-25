package test;

import java.util.Stack;

public class LRU implements CacheReplacementPolicy {
    // least recently used

    private Stack<String> stack;

    public LRU() {
        stack = new Stack<String>();
    }

    @Override
    public void add(String word) {
        // if the word is already in the stack, remove it and add it to the top
        if (stack.contains(word)) {
            stack.remove(word);
            stack.push(word);
        }
        else {
            stack.push(word);
        }

    }

    @Override
    public String remove() {
        // remove the least recently used word
        return stack.remove(0);
    }

}