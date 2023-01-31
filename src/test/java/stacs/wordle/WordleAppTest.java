package stacs.wordle;
import java.util.Arrays;


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

       // String wordle = WordleApp.getWordle(wordlist);
        //assertEquals("test", wordle);
    }

    @Test
    public void shouldCompareTwoCharArrays()
    {
        char input [] = {'c','r','a','n','e'};
       // char target [] = {'c','r','a','n','e'};
       char target [] = {'c','r','a','n','e'};

        WordleApp app = new WordleApp();
        
        app.setTarget(target);
        Boolean eq = app.isCorrect(input);
        // test wordlist only contains 3 words, so wordlist should have the size of 3
        assertEquals(true, eq);
    }

    @Test
    public void shouldCompareLetters()
    {
        char input [] = {'c','w','a','r','e'};
       // char target [] = {'c','r','a','n','e'};
       char result [] = {'G','X','G','Y','G'};
       char target [] = {'c','r','a','n','e'};

        WordleApp app = new WordleApp();
        app.setTarget(target);
        char[] output = app.checkLetters(input);

        boolean eq = Arrays.equals(result, output);
        // test wordlist only contains 3 words, so wordlist should have the size of 3
        assertEquals(true, eq);
    }
}
