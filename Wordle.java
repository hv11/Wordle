import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;
import java.io.File;
import java.io.IOException;
public class Wordle 
{
    private String[][] guesses;
    private ArrayList<String> wordList;
    private String[][] letterList;
    private String word;
    private String guess;
    private int guessCount;
    private int[] letterCount;
    private int[] letterGuessCount;
    private boolean gameCompleted;
    
    public Wordle()
    {
     guesses = new String[6][5];  
     wordList = new ArrayList<String>();
     letterList = new String[3][10];
     letterCount = new int[26];
     letterGuessCount = new int[26];
     word = "";
     guess = "";
     guessCount = 0;
    }
    
    public void setWordList() throws IOException
    {
        Scanner file = new Scanner(new File("wordList.txt"));
        int count = 0; 
        while(file.hasNextLine())
        {
            String line = file.nextLine();
            wordList.add(count,line);
            count++;
        }
    }
    
    public void setLetterCount()
    {
        for(int i = 0; i < word.length();i++)
        {
            letterCount[word.charAt(i)-97] += 1;
        }
    }
    public void resetLetterGuessCount()
    {
        for(int i = 0; i < letterGuessCount.length;i++)
        {
            letterGuessCount[i] = 0;
        }
    }
    
    public void setLetterList()
    {
        letterList= new String[][]{{"q","w","e","r","t","y","u","i","o","p"},
        {" ","a","s","d","f","g","h","j","k","l"},{" ", " ","z","x","c","v","b","n","m"," "}};
        
    }
    public void setGuessList()
    {
        for(int i = 0; i < guesses.length; i++)
        {
            for(int b = 0; b < guesses[i].length;b++)
            {
                guesses[i][b] = " ";
            }
        }
    }
    
    public void setGuesses(String answerType,int index)
    {
        String letter = guess.substring(index,index+1);
        if(answerType.equals("Correct Spot & Letter"))
        {
            guesses[guessCount][index] = "\033[32m\u001b[4m" + letter.toUpperCase() + "\u001B[0m";
        }
        else if(answerType.equals("Correct Letter") && (letterCount[guess.charAt(index)-97]>= letterGuessCount[guess.charAt(index)-97]))
        {
            guesses[guessCount][index] = "\033[33m\u001b[4m" + letter.toLowerCase() + "\u001B[0m";
        }
        else
        {
            guesses[guessCount][index] ="\u001b[4m" + letter + "\u001B[0m";
        }
    }
    
    public void displayGuesses()
    {
        System.out.println("\t\t  _ _ _ _ _ ");
        for(int i = 0; i < guesses.length;i++)
        {
            System.out.print("\t\t ");
            for(int b = 0; b < guesses[i].length;b++)
            {
                System.out.print("|" + guesses[i][b]);
            }
            System.out.print("|");
            System.out.println();
        }
    }
    
    public String getWord()
    {
        return word;
    }
    
    public boolean getCompletedGame()
    {
        return gameCompleted;
    }
    
    public boolean getWin()
    {
        if(guess.equals(word))
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    
    public void getResult()
    {
        System.out.print("\033[19;7H\tYou got the word in " + guessCount + " guesses.");
    }
    
    public void displayLetterList(String letterChange,String letter)
    {
        for(int i = 0; i < letterList.length;i++)
        {
            for(int b = 0; b < letterList[i].length;b++)
            {
                if(letterChange.equals("Remove")&&letterList[i][b].equals(letter))
                {
                    letterList[i][b] =" ";
                }
                if(letterChange.equals("Capitalize")&&letterList[i][b].contains(letter))
                {
                    letterList[i][b] = "\033[32m" + letter.toUpperCase() + "\u001B[0m";
                }
                if(letterChange.equals("LowerCase")&&letterList[i][b].contains(letter))
                {
                    letterList[i][b] = "\033[33m" + letter.toLowerCase() + "\u001B[0m";
                }
            }
        }
    }
    
    public void printLetterList()
    {
        System.out.print("\033[14;7H\033[K");
        System.out.println("\t      _ _ _ _ _ _ _ _ _ _");
        for(int i = 0; i < letterList.length;i++)
        {
            System.out.print("\t     ");
            for(int b = 0; b < letterList[i].length;b++)
            {
                System.out.print("|\u001b[4m" + letterList[i][b] + "\u001B[0m");
            }
            System.out.print("|");
            System.out.println();
        }
    }
    
    
    public void setUpGame() throws IOException
    {
        setWordList();
        randomizeWordList();
        setGuessList();
        displayGuesses();
        System.out.println();
        setLetterList();
        printLetterList();
        setLetterCount();
    }
    
    public void guessWord(String playerGuess)
    {
        resetLetterGuessCount();
        if(playerGuess.equals(word)||guessCount==5)
        {
            gameCompleted = true;
        }
        guess = playerGuess;
        char letter;
        for(int i = 0; i < playerGuess.length();i++)
        {
            letter = playerGuess.charAt(i);
            if(letter == word.charAt(i))
            {
                setGuesses("Correct Spot & Letter",i);
                displayLetterList("Capitalize",playerGuess.substring(i,i+1));
                letterGuessCount[letter-97]+=1;
            }
        }
        
        for(int i = 0; i < playerGuess.length();i++)
        {
            letter = playerGuess.charAt(i);
            if(guesses[guessCount][i].equals(" "))
            {
            if(word.indexOf(letter)!=-1&&letterGuessCount[letter-97] < letterCount[letter-97])
            {   
                setGuesses("Correct Letter",i);
                displayLetterList("LowerCase",playerGuess.substring(i,i+1));
                letterGuessCount[letter-97]+=1;
            }
            else
            {
                setGuesses("None",i);
                displayLetterList("Remove",playerGuess.substring(i,i+1));
            }
            }
        }
            
        guessCount++;
        System.out.print("\033[6;7H\033[K");
        displayGuesses();
        printLetterList();
  
    }
    
    public void randomizeWordList()
    {
        int num = (int)(Math.random()*wordList.size());
        word = wordList.get(num);
        for(int i = 0; i < wordList.size();i++)
        {
            num = (int)(Math.random()*wordList.size());
            String temp = wordList.get(i);
            
            wordList.set(i,wordList.get(num));
            wordList.set(num,temp);
        }
    }
    
    public void sortWordList() throws IOException
    {
        Scanner sortedWords = new Scanner(new File("wordList.txt"));
        String line = "";
        int count = 0;
        while(sortedWords.hasNextLine())
        {
            line = sortedWords.nextLine();
            wordList.set(count,line);
            count++;
        }
    }
    
    public String toString(){
        String result = "";
        for(int i = 0; i < wordList.size();i++)
        {
            if(i!=wordList.size()-1)
            {
            result += wordList.get(i) + ", ";
            }
            else
            {
                result += wordList.get(i);
            }
            if(i % 10 == 0 && i >10)
            {
                System.out.print("\n");
            }
        }
        return result;
    }
 
}