package stacs.wordle;

import org.junit.jupiter.api.Test;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

public class WordleAppTest
{
    @Test
  void testLoadWordlistSuccess() throws FileNotFoundException {
    // Given
    String wordlistPath = "src/test/resources/wordlist-test.txt";
    
    // When
    ArrayList<String> words = WordleApp.loadWordlist(wordlistPath);
    
    // Then
    assertEquals(3, words.size());
    assertEquals("maven", words.get(0));
    assertEquals("debug", words.get(1));
    assertEquals("cache", words.get(2));
  }

  @Test
  void testLoadWordlistFileNotFound() {
    // Given
    String wordlistPath = "src/test/resources/nonexistent.txt";
    
    // When & Then
    Exception exception = assertThrows(FileNotFoundException.class, () -> WordleApp.loadWordlist(wordlistPath));
    assertEquals(wordlistPath + " (No such file or directory)", exception.getMessage());
}

@Test
void testGetWord_validInput() {
    ArrayList<String> words = new ArrayList<>();
    words.add("software");
    words.add("engineering");
    words.add("assignment");

    String word = WordleApp.getWord(words);
    assertNotNull(word, "The returned word should not be null");
}

@Test
void testGetWord_emptyWordsList() {
    ArrayList<String> words = new ArrayList<>();

    String word = WordleApp.getWord(words);
    assertNull(word, "The returned word should be null if the words list is empty");
}

@Test
void testGetWord_nullWordsList() {
    ArrayList<String> words = null;

    String word = WordleApp.getWord(words);
    assertNull(word, "The returned word should be null if the words list is null");
}

@Test
void testCheckLetters() {
    WordleApp wordle = new WordleApp();

    // Test case 1: test with correct letter and position
    char[] userInput = {'c','r','a','n','e'};
    wordle.test = userInput;
    char[] expected = {'G','G','G','G','G'};
    char[] actual = wordle.checkLetters(userInput);
    assertArrayEquals(expected, actual);

    // Test case 2: test with correct letter but wrong position
    userInput = new char[]{'e','n','c','a','r'};
    wordle.test = new char[]{'c','r','a','n','e'};
    expected = new char[]{'Y','Y','Y','Y','Y'};
    actual = wordle.checkLetters(userInput);
    assertArrayEquals(expected, actual);

    // Test case 3: test with incorrect letter
    userInput = new char[]{'a','b','c','d','e'};
    wordle.test = new char[]{'c','r','a','n','e'};
    expected = new char[]{'Y','X','Y','X','G'};
    actual = wordle.checkLetters(userInput);
    assertArrayEquals(expected, actual);
}

@Test
  public void testBeautify_correctGuess() {
    char[] encoded = {'G', 'G', 'G', 'G', 'G'};
    char[] userInput = {'c', 'r', 'a', 'n', 'e'};

    String expectedResult = "+-------------------+\n" +
        "| \u001B[32mc\u001B[0m   \u001B[32mr\u001B[0m   \u001B[32ma\u001B[0m   \u001B[32mn\u001B[0m   \u001B[32me\u001B[0m |\n" +
        "+-------------------+\n";

    assertEquals(expectedResult, WordleApp.beautify(encoded, userInput));
  }

  @Test
  public void testBeautify_incorrectGuess() {
    char[] encoded = {'Y', 'X', 'Y', 'X', 'Y'};
    char[] userInput = {'s', 't', 'a', 'r', 'i'};

    String expectedResult = "+-------------------+\n" +
        "| \u001B[33ms\u001B[0m   \u001B[31mt\u001B[0m   \u001B[33ma\u001B[0m   \u001B[31mr\u001B[0m   \u001B[33mi\u001B[0m |\n" +
        "+-------------------+\n";

    assertEquals(expectedResult, WordleApp.beautify(encoded, userInput));
  }

  @Test
  public void testSetTarget() {
    char[] target = {'c','r','a','n','e'};
    WordleApp wordle = new WordleApp();

      wordle.setTarget(target);
      assertArrayEquals(target, wordle.test);
  }

  @Test
    public void testCheckSubmission() {
        WordleApp wordle = new WordleApp();
        char[] target = {'c','r','a','n','e'};
        wordle.setTarget(target);

        char[] correctGuess = {'c','r','a','n','e'};
        wordle.checkSubmission(correctGuess);
        assertTrue(wordle.won);

        wordle.won = false;
        char[] incorrectGuess = {'c','r','a','n','x'};
        wordle.checkSubmission(incorrectGuess);
        assertFalse(wordle.won);
    }

    
    @Test
    public void isCorrect_userInputMatchesTarget_returnsTrue() {
        WordleApp wordle = new WordleApp();
      char[] target = {'c', 'r', 'a', 'n', 'e'};
      wordle.setTarget(target);
      char[] userInput = {'c', 'r', 'a', 'n', 'e'};
      boolean result = wordle.isCorrect(userInput);
      assertTrue(result);
    }
  
    @Test
    public void isCorrect_userInputDoesNotMatchTarget_returnsFalse() {
        WordleApp wordle = new WordleApp();
      char[] target = {'c', 'r', 'a', 'n', 'e'};
      wordle.setTarget(target);
      char[] userInput = {'c', 'r', 'a', 'n', 'a'};
      boolean result = wordle.isCorrect(userInput);
      assertFalse(result);
    }
   
    @Test
    public void convertingDictionaryIntoTrie(){

    }

    @Test
    public void creatingAnEmptyWordleApp()
    {
        WordleApp app = new WordleApp();
        assertEquals(false, app.won);
        assertEquals(null, app.test);
    }


}
