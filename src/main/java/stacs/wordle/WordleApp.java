package stacs.wordle;

import stacs.trie.Trie;
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
    public static final String WINNING_MESSAGE = "SOLVED";

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
    Trie trie = new Trie();
    Boolean won = false;
    public static void main( String[] args ) throws FileNotFoundException
    {
        System.out.println("---------------------------------------");
        System.out.println("       Welcome to " + ANSI_GREEN +"CS5031" + ANSI_RESET + "- Wordle");
        System.out.println("---------------------------------------");

        String welcome = "                                                         ,--,                       "
                       + "\n                    ,----..                           ,---.'|                     "
                       + "\n           .---.   /   /   \\  ,-.----.       ,---,    |   | :       ,---,.       "
                       + "\n          /. ./|  /   .     : \\    /  \\    .'  .' `\\  :   : |     ,'  .' |     "
                       + "\n      .--'.  ' ; .   /   ;.  \\;   :    \\ ,---.'     \\ |   ' :   ,---.'   |     "
                       + "\n     /__./ \\ : |.   ;   /  ` ;|   | .\\ : |   |  .`\\  |;   ; '   |   |   .'     "
                       + "\n .--'.  '   \\' .;   |  ; \\ ; |.   : |: | :   : |  '  |'   | |__ :   :  |-,      "
                       + "\n/___/ \\ |    ' '|   :  | ; | '|   |  \\ : |   ' '  ;  :|   | :.'|:   |  ;/|      "
                       + "\n;   \\  \\;      :.   |  ' ' ' :|   : .  / '   | ;  .  |'   :    ;|   :   .'      "
                       + "\n \\   ;  `      |'   ;  \\; /  |;   | |  \\ |   | :  |  '|   |  ./ |   |  |-,     "
                       + "\n  .   \\    .\\  ; \\   \\  ',  / |   | ;\\  \\'   : | /  ; ;   : ;   '   :  ;/|  "
                       + "\n   \\   \\   ' \\ |  ;   :    /  :   ' | \\.'|   | '` ,/  |   ,/    |   |    \\   "
                       + "\n    :   '  |--\"    \\   \\ .'   :   : :-'  ;   :  .'    '---'     |   :   .'     "
                       + "\n     \\   \\ ;        `---`     |   |.'    |   ,.'                |   | ,'        "
                       + "\n      '---\"                   `---'      '---'                  `----'           "
                       + "\n                                                                                  "
                       + "\n------------------------------------------------------------------------------";
        System.out.println(welcome);

        WordleApp game = new WordleApp();
        //tool string builder
        String rules = "How To Play\n"
                     + "Guess the Wordle in 6 tries.\n"
                     + "- Each guess must be a valid 5-letter word.\n"
                     + "- The color of the tiles will change to show how close your guess was to the word.\n";
        
        System.out.println(rules);             //Rules 
        //New Game
        //Settings
        // - Hard Mode
        //  - Any revealed hints must be used in subsequent guesses
        // - Color?
        // -Dark 
        // -High Contrast Color Vision
        //Twittfy outcome
        //STATS???


        //Setup
        ArrayList<String> dic = loadWordlist("../../../resources/wordlist.txt");
        game.test = getWord(dic).toCharArray();
        //CHECK IF NULL
        //TESTING
        for(char w: game.test){
            System.out.print(w);
        }
        System.out.println();

        Scanner kb = new Scanner(System.in);

        //Game Logic
        for(int i = 0; i < MAX_ATTEMPTS; i++){
            System.out.println("Guess:");
            String input = kb.next();
            //PERFORM INPUT CHECKS
            System.out.print("\nEnter " + input + " (y/[n])?");
            String confirm = kb.next();


            if(confirm.equals("y")){
                char[] charInput = input.toCharArray();
                String display = game.checkSubmission(charInput);
                if(game.won){
                    System.out.println(display + "\nCorrectly discovered in " +(i + 1)+ " attempts"); 
                    break;
                }
                System.out.println(display); 
            }
            
        }
        kb.close();


        //trie of hash tables or unique ds
    
    }

    //Logic

    /**
    * This function checks the word submitted by the user.
    * @param userInput the word submitted by the user to be checked
    * @return The result of the check, in the form of a string that contains the word and its corresponding letters with colors indicating the correctness of each letter
    */
    public String checkSubmission(char[] userInput){
        // char test [] = {'c','r','a','n','e'};
        String result;
        result = this.beautify(checkLetters(userInput), userInput);

        if(isCorrect(userInput)){
            this.won = true;
            return result;
        }
        else{
            return result;
        }
        
    }

    /**
     * Checks the user's guess against the target word and returns an array of results.
     *
     * @param userInput the user's guess
     * @return an array of results, where 'G' represents a correctly guessed letter and place, 
     *                                    'Y' represents a correctly guessed letter in the wrong place, 
     *                                and 'X' represents a letter that does not exist in the target word
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




    public void setTarget(char[] target){
        this.test = target;
    }

    /**
     * Transforms the encoded result array into a visually appealing string representation.
     *
     * @param encoded the result array from the `checkLetters` function
     * @param userInput the user's guess
     * @return a visually appealing string representation of the encoded result array
     */
    public String beautify(char[] encoded, char[] userInput){
        String[] colored = new String[encoded.length]; 

        for(int i = 0; i < encoded.length; i++){
            if(encoded[i] == 'G'){
                colored[i] = ANSI_GREEN +  userInput[i] + ANSI_RESET;
            }
            else if(encoded[i] == 'Y'){
                colored[i] = ANSI_YELLOW +  userInput[i] + ANSI_RESET;
            }
            else{
                colored[i] = ANSI_RED +  userInput[i] + ANSI_RESET;
            }
        }

        String output   = "+-------------------+\n";
        String output1  = "| "+colored[0]+"   "+colored[1]+"   "+colored[2]+"   "+colored[3]+"   "+colored[4]+" |\n";
        String output2  = "+-------------------+\n";

        return output + output1 + output2;
    }



    /**
     * Gets a random word from a list of words.
     *
     * @param words the list of words from which to select a word
     * @return a randomly selected word from the list, or `null` if the list is empty or `null`
     */
    protected static String getWord(ArrayList<String> words){
        if(words!= null && !words.isEmpty())
        {
            int index = (int)(Math.random() * words.size());
            return words.get(index);
        }
        else{
            System.out.println("Build Failure: It seems the dictionary ArrayList is empty or does not exist.");
            return null; //Is this the best
        }
        
    }

    public boolean inDictionary(ArrayList<String> dictionary, String input){
        return false;
    }

    /**
     * Loads a list of words from a text file and returns the words in an ArrayList.
     *
     * @param wordlistPath the path of the text file containing the words
     * @return an ArrayList of words read from the text file
     * @throws FileNotFoundException if the text file is not found
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

    public void loadWordlistIntoTrie(String wordlistPath) throws FileNotFoundException
    {
        Scanner s = new Scanner(new File(wordlistPath));
        while (s.hasNext()){
            this.trie.insert(s.next());
        }
        s.close();
    }
    //HOW TO SELECT RANDOM WORD FROM TRIE?
    //ONCE GUESS MADE SAVE TO CACHE


}
