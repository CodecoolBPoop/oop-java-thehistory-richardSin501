package main.java.com.codecool.thehistory;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public class TheHistoryLinkedList implements TheHistory {

    /**
     * This implementation should use a String LinkedList so don't change that!
     */
    private List<String> wordsLinkedList = new LinkedList<>();

    @Override
    public void add(String text) {
        wordsLinkedList.addAll(Arrays.asList(text.split("\\s+")));
    }

    @Override
    public void removeWord(String wordToBeRemoved) {
        ListIterator<String> li = wordsLinkedList.listIterator();
        while (li.hasNext()) {
            if (li.next().equals(wordToBeRemoved)) {
                li.remove();
            }
        }
    }

    @Override
    public int size() {
        return wordsLinkedList.size();
    }

    @Override
    public void clear() {
        wordsLinkedList = new LinkedList<>();
    }

    @Override
    public void replaceOneWord(String from, String to) {
        ListIterator<String> li = wordsLinkedList.listIterator();
        while (li.hasNext()) {
            if (li.next().equals(from)) {
                li.set(to);
            }
        }
    }

    @Override
    public void replaceMoreWords(String[] fromWords, String[] toWords) {
        if (fromWords.length > 0 && toWords.length > 0) {
            ListIterator<String> li = wordsLinkedList.listIterator();

            while (li.hasNext()) {
                /*System.out.println(size());
                System.out.println(li.nextIndex());*/
                if (isFromWordsAtIndex(fromWords, li.nextIndex())) {
                    for (String word : fromWords) {
                        if (li.hasNext()) {
                            li.next();
                            li.remove();
                        }
                        else {
                            break;
                        }
                    }
                    for (String word : toWords) {
                        li.add(word);
                    }
                }
                else {
                    li.next();
                }
            }
        }

    }

    private boolean isFromWordsAtIndex(String[] fromWords, int index) {
        ListIterator<String> li = wordsLinkedList.listIterator(index);
        int matchCount = 0;

        for (int i = 0; i < fromWords.length; i++) {
            if (li.hasNext() && li.next().equals(fromWords[i])) {
                matchCount++;
            }
            else {
                break;
            }
        }
        return matchCount == fromWords.length;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (String word : wordsLinkedList) {
            sb.append(word).append(" ");
        }
        if (sb.length() > 0) {
            sb.deleteCharAt(sb.length() - 1); // last space char
        }
        return sb.toString();
    }
}
