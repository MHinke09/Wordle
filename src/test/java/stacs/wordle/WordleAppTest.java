package stacs.wordle;


import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class WordleAppTest
{
    @Test
    public void shouldLoadWordlist() throws FileNotFoundException
    {
        ArrayList<String> wordlist = WordleApp.loadWordlist("src/test/resources/wordlist-test.txt");

        // test wordlist only contains 3 words, so wordlist should have the size of 3
        assertEquals(3, wordlist.size());
    }

    @Test
    public void shouldCompareTwoCharArrays()
    {
        char input [] = {'c','r','a','n','e'};
        char target [] = {'c','r','a','n','e'};

        WordleApp app = new WordleApp();
        Boolean eq = app.isCorrect(input);
        Boolean ans = true;
        // test wordlist only contains 3 words, so wordlist should have the size of 3
        assertEquals(ans, eq);
    }
}
