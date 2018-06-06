package main.java.com.codecool.thehistory;

import java.util.Arrays;

public class TheHistoryArray implements TheHistory {

    /**
     * This implementation should use a String array so don't change that!
     */
    private String[] wordsArray = new String[0];

    @Override
    public void add(String text) {
        String[] textArray = text.split("\\s+");
        String[] newWordsArray = new String[this.size() + textArray.length];
        System.arraycopy(this.wordsArray, 0, newWordsArray, 0, this.wordsArray.length);
        System.arraycopy(textArray, 0, newWordsArray, this.size(), textArray.length);
        this.wordsArray = newWordsArray;
    }

    @Override
    public void removeWord(String wordToBeRemoved) {
        int pos = indexOf(this.wordsArray, wordToBeRemoved);
        if (pos > -1) {
            String[] newWordsArray = new String[size() - 1];
            System.arraycopy(this.wordsArray, 0, newWordsArray, 0, pos);
            System.arraycopy(this.wordsArray, pos + 1, newWordsArray, pos,
                newWordsArray.length - pos);
            this.wordsArray = newWordsArray;
        }
    }

    @Override
    public int size() {
        return this.wordsArray.length;
    }

    @Override
    public void clear() {
        this.wordsArray = new String[0];
    }

    @Override
    public void replaceOneWord(String from, String to) {
        for (int i = 0; i < this.size(); i++) {
            if (this.wordsArray[i].equals(from)) {
                this.wordsArray[i] = to;
            }
        }
    }

    @Override
    public void replaceMoreWords(String[] fromWords, String[] toWords) {
        // is fromWords a subset?
        //then what is the index of its first word?
        
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (String word : wordsArray) {
            sb.append(word).append(" ");
        }
        if (sb.length() > 0) {
            sb.deleteCharAt(sb.length() - 1); // last space char
        }
        return sb.toString();
    }

    private int indexOf(String[] wordsArray, String word) {
        for (int i = 0; i < wordsArray.length; i++) {
            if (wordsArray[i].equals(word)) {
                return i;
            }
        }
        return -1;
    }

    private static int indexOfFirstString(String[] wholeArray, String[] subArray) {
        if (wholeArray.length > 0 && subArray.length > 0) {
            for (int i = 0; i < wholeArray.length - subArray.length + 1; i++) {
                int countOfMatch = 0;
                for (int j = 0; j < subArray.length; j++) {
                    if (!wholeArray[i + j].equals(subArray[j])) {
                        break;
                    }
                    countOfMatch++;
                }
                if (countOfMatch == subArray.length) {
                    return i;
                }
            }
        }
        return -1;
    }
}
