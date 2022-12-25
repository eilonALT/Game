package test;

import java.math.BigInteger;
import java.util.BitSet;
import java.security.MessageDigest;

public class BloomFilter {

    private BitSet bitSet;// bit array
    private int size;// size of the bit array
    private String[] hashFunctions;// hash functions

    public BloomFilter(int size, String... hashFunctions) {
        this.size = size;
        bitSet = new BitSet();
        this.hashFunctions = hashFunctions;
    }

    public void add(String word) {
        // add the word to the bit array
        // use the hash functions to get the index of the bit array
        // set the bit at the index to 1
        for (String hashFunction : hashFunctions) {
            int hash = hash(word, hashFunction);
            bitSet.set(hash);
        }
    }

    private int hash(String word, String hashFunction) {
        // use the hash function to get the index of the bit array
        // return the index
        // if the hash function is not supported, return -1
        try {
            MessageDigest md = MessageDigest.getInstance(hashFunction);
            byte[] messageDigest = md.digest(word.getBytes());
            BigInteger no = new BigInteger(1, messageDigest);
            return Math.abs(no.intValue() % size);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    public boolean contains(String word) {
        // check if the word is in the bit array
        // use the hash functions to get the index of the bit array
        // check if the bit at the index is 1
        // if all the bits are 1, return true
        // otherwise, return false
        for (String hashFunction : hashFunctions) {
            int hash = hash(word, hashFunction);
            if (!bitSet.get(hash)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        // print the bit array in a string format (e.g. 0101010101)
        // return the string
        String result = "";
        for (int i = 0; i < bitSet.length(); i++) {
            result += bitSet.get(i) ? "1" : "0";
        }

        return result;
    }

}
