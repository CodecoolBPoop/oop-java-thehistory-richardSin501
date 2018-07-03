package main.java.com.codecool.thehistory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.ListIterator;
import javafx.beans.WeakInvalidationListener;

public class TheHistoryArrayList implements TheHistory {

    /**
     * This implementation should use a String ArrayList so don't change that!
     */
    private List<String> wordsArrayList = new ArrayList<>();

    @Override
    public void add(String text) {
        wordsArrayList.addAll(Arrays.asList(text.split("\\s+")));
    }

    @Override
    public void removeWord(String wordToBeRemoved) {
        wordsArrayList.removeAll(Collections.singleton(wordToBeRemoved));
    }

    @Override
    public int size() {
        return wordsArrayList.size();
    }

    @Override
    public void clear() {
        wordsArrayList = new ArrayList<>();
    }

    @Override
    public void replaceOneWord(String from, String to) {
        Collections.replaceAll(wordsArrayList, from, to);
    }

    @Override
    public void replaceMoreWords(String[] fromWords, String[] toWords) {
        List<String> fromWordsList = Arrays.asList(fromWords);
        int indexOfFromWords = indexOfSublist(wordsArrayList, fromWordsList, 0);
        int tLength = toWords.length;
        int fLength = fromWords.length;

        while (indexOfFromWords > -1) {
            if (Arrays.equals(fromWords, toWords)) {
                break;
            }
            if (tLength < fLength) {
                for (int i = 0; i < fLength; i++) {
                    if (i < tLength) {
                        wordsArrayList.set(indexOfFromWords + i, toWords[i]);
                    } else {
                        wordsArrayList.remove(indexOfFromWords + tLength);
                    }
                }
            } else if (tLength > fLength) {
                for (int i = 0; i < tLength; i++) {
                    if (i < fLength) {
                        wordsArrayList.set(indexOfFromWords + i, toWords[i]);
                    } else {
                        wordsArrayList.add(indexOfFromWords + i, toWords[i]);
                    }

                }
            } else {
                for (int i = 0; i < fLength; i++) {
                    wordsArrayList.set(indexOfFromWords + i, toWords[i]);
                }
            }

            indexOfFromWords = indexOfSublist(wordsArrayList, fromWordsList,
                indexOfFromWords + tLength);
        }

    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (String word : wordsArrayList) {
            sb.append(word).append(" ");
        }
        if (sb.length() > 0) {
            sb.deleteCharAt(sb.length() - 1); // last space char
        }
        return sb.toString();
    }

    private int indexOfSublist(List<?> wholeList, List<?> subList, int indexToSearchFrom) {
        int wSize = wholeList.size();
        int sSize = subList.size();

        if (wSize > 0 && sSize > 0) {
            for (int i = indexToSearchFrom; i < wSize; i++) {
                int countOfMatch = 0;

                for (int j = 0; j < sSize; j++) {
                    if (i + j >= wSize || !wholeList.get(i + j).equals(subList.get(j))) {
                        break;
                    }
                    countOfMatch++;
                }
                if (countOfMatch == sSize) {
                    return i;
                }
            }
        }
        return -1;
    }
}
