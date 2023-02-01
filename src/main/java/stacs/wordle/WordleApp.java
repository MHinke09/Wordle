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
    //System.out.println(game.checkSubmission(charInput));
        //Now it has to start a fresh game
        //GAME is an object
            //Check method that sees if the game is finished
        //Dictionary is an object???
        //USER has actions (possible inputs)
        //STATES MAYBE?
        //+-------------------+
       // | S   C   O   N   E |
       // +-------------------+

            //Select a random word from dictionary
            //Start user actions
                //User chooses five letters (maybe l letters meaning the game can be expanded)
                //We check if they are letters in the word and if they are in the correct positioning
                //We also eliminate the unused letters from the users options - CHECK SPEC
                //Repeat for x amount of turns
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
    Boolean won = false;
    public static void main( String[] args ) throws FileNotFoundException
    {
        System.out.println("---------------------------------------");
        System.out.println("       Welcome to " + ANSI_GREEN +"CS5031" + ANSI_RESET + "- Wordle");
        System.out.println("---------------------------------------");
        
        // System.out.println("Guess:");

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

    //Waht if empty, what if null, what if very large
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



// Class methods
    public void setTarget(char[] target){
        this.test = target;
    }


//Output format

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

        //+--+ +-+ +-+ +-+ +--+
       // | S   C   O   N   E |
       // +--+ +-+ +-+ +-+ +--+
    //     +-----+ +-----+ +-----+ +-----+ +-----+
    //     |  d  | |  u  | |  p  | |  r  | |  s  |
    //     +-----+ +-----+ +-----+ +-----+ +-----+
        String output   = "+-------------------+\n";
        String output1  = "| "+colored[0]+"   "+colored[1]+"   "+colored[2]+"   "+colored[3]+"   "+colored[4]+" |\n";
        String output2  = "+-------------------+\n";

        

        return output + output1 + output2;
    }


//Dictionary

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
