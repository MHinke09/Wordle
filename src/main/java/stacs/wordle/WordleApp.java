package stacs.wordle;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.nio.file.Path;
import java.io.File;

public class WordleApp
{
    public static final int MAX_WORD_SIZE = 5;
    public static final int MAX_ATTEMPTS = 6;
    public static final char WINNING_MESSAGE [] = {'W','I','N','N','E','R'};

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m"; 
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    //What operations are we going to be performing the most
    
    //We have to check if the word contains the letter at all (ORANGE SQUARE)
        //Then we have to check if the input[i] matches target[i] (GREEN SQUARE)
    
    //char[] targett = new char[MAX_WORD_SIZE];
    char tempt [] = {'c','r','a','n','e'};
    char test[];
    public static void main( String[] args ) throws FileNotFoundException
    {
        System.out.println("Welcome to CS5031 - Wordle");
        System.out.println("Please enter input: ");

        WordleApp game = new WordleApp();
        ArrayList<String> dic = loadWordlist("../../../resources/wordlist.txt");

        game.test = getWordle(dic).toCharArray();

        for(char w: game.test){
            System.out.print(w);
        }
        System.out.println();

        Scanner kb = new Scanner(System.in);
        for(int i = 0; i < MAX_ATTEMPTS; i++){
            String input = kb.next();
            char[] charInput = input.toCharArray();
            System.out.println(game.checkSubmission(charInput));
            
        }
        kb.close();


    
        //System.out.println(game.checkSubmission(charInput));
        //Now it has to start a fresh game
        //GAME is an object
            //Check method that sees if the game is finished
        //Dictionary is an object???
        //USER has actions (possible inputs)
        //STATES MAYBE?
        //
            //Select a random word from dictionary
            //Start user actions
                //User chooses five letters (maybe l letters meaning the game can be expanded)
                //We check if they are letters in the word and if they are in the correct positioning
                //We also eliminate the unused letters from the users options - CHECK SPEC
                //Repeat for x amount of turns
    
    }

    public char[] checkSubmission(char[] userInput){
        // char test [] = {'c','r','a','n','e'};
        char result[];

        if(isCorrect(userInput)){
            return WINNING_MESSAGE;
        }
        else{
            result = checkLetters(userInput);
            return result;
        }
        
    }


    /**
     * 
     * @param userInput
     * @return
     */
    public char[] checkLetters(char[] userInput){
        char[] result = new char[MAX_WORD_SIZE];

        for(int i = 0; i < MAX_WORD_SIZE; i++){
            if(userInput[i] == this.test[i]){
                result[i] = 'G';
            }
            else{
                for(char letter: this.test){
                    if(userInput[i] == letter){
                        result[i] = 'Y';
                        break;
                    }
                    else{
                        result[i] = 'X';
                    }
                }
            }
        }

        return result;
    }

    /**
     * 
     * @param userInput
     * @return
     */
    public boolean isCorrect(char[] userInput){
        Boolean equal = Arrays.equals(this.test, userInput);
        return equal;
    }

    protected static String getWordle(ArrayList<String> words){
        int index = (int)(Math.random() * words.size());
        return words.get(index);
    }

    /**
     * 
     * @param wordlistPath
     * @return
     * @throws FileNotFoundException
     */
    protected static ArrayList<String> loadWordlist(String wordlistPath) throws FileNotFoundException
    {
        Scanner s = new Scanner(new File(wordlistPath));
        ArrayList<String> words = new ArrayList<String>();
        while (s.hasNext()){
            words.add(s.next());
        }
        s.close();
        return words;
    }
}
