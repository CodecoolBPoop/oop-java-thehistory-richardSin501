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
        // the first occurrence of the fromWords in the wordsArray
        int indexToSearchFrom = 0;
        int matchIndex = indexOfFirstString(wordsArray, fromWords, 0);

        while (matchIndex > -1) {
            if (fromWords.length != toWords.length) {
                int firstPartLength = matchIndex;
                int secondPartLength = toWords.length;
                int thirdPartLength = wordsArray.length - fromWords.length - firstPartLength;

                String[] newWordsArray = new String[firstPartLength + secondPartLength
                    + thirdPartLength];
                System.arraycopy(wordsArray, 0, newWordsArray, 0, firstPartLength);
                System.arraycopy(toWords, 0, newWordsArray, matchIndex, secondPartLength);
                System.arraycopy(wordsArray, matchIndex + fromWords.length, newWordsArray,
                    firstPartLength + secondPartLength, thirdPartLength);
                wordsArray = newWordsArray;
                // look for new matches from here
                indexToSearchFrom = firstPartLength + secondPartLength;
            } else {
                System.arraycopy(toWords, 0, wordsArray, matchIndex, toWords.length);
                indexToSearchFrom = matchIndex + toWords.length;
            }
            matchIndex = indexOfFirstString(wordsArray, fromWords, indexToSearchFrom);
        }
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

    private static int indexOfFirstString(String[] wholeArray, String[] subArray,
        int indexToSearchFrom) {
        if (wholeArray.length > 0 && subArray.length > 0) {
            // looking for matches from the given index
            for (int i = indexToSearchFrom; i < wholeArray.length - subArray.length + 1; i++) {
                int countOfMatch = 0;
                // checking if it's a matching sequence
                for (int j = 0; j < subArray.length; j++) {
                    if (!wholeArray[i + j].equals(subArray[j])) {
                        break; // if even one doesn't match, it's not worth continuing
                    }
                    countOfMatch++;
                }
                if (countOfMatch == subArray.length) {
                    // returning the first index from the larger array if match is found
                    // this way only the first matching sequence is found
                    return i;
                }
            }
        }
        return -1;
    }
}
