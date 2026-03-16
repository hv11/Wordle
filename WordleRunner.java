import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
public class WordleRunner
{
    private static Scanner input;
    private static Wordle one;
    private static WordleArt oneArt;
    private static String response;
    
    public static void main(String[] args) throws IOException
    {
        
        one = new Wordle();
        oneArt = new WordleArt();
        oneArt.Wordle();
        input = new Scanner(System.in);
        response = "";
        while(!response.equals("test")&&!response.equals("play"))
        {
            System.out.print("\t\tPlay/Test: ");
            response = input.nextLine().toLowerCase();
            System.out.print("\033[F\033[K");
        }
        if(response.equals("play"))
        {
            playGame();
        }
        if(response.equals("test"))
        {
            testMethods();
        }
        
    }
    
    private static void playGame() throws IOException
    {
        oneArt.Wordle();
        one.setUpGame();
        while(one.getCompletedGame() == false)
        {
            System.out.print("\033[19;7H\t       Enter your guess: ");
            response = input.nextLine().toLowerCase().trim();
            System.out.print("\033[F\033[K");
            while(response.length()!=5)
            {
                System.out.print("\tInvalid Guess. Enter your guess: ");
                response = input.nextLine().toLowerCase().trim();
                System.out.print("\033[F\033[K");
            }
            one.guessWord(response);
        }
        if(one.getWin() == true)
        {
            oneArt.youWon();
            one.getResult();
        }
        else
        {
            oneArt.youLost();
            System.out.println("\033[19;7H\t       The word was " + one.getWord() + ".");
        }
        
    }
    private static void testMethods() throws IOException
    {
        one.setWordList();
        oneArt.testPage();
        System.out.print("\033[14;15HChoice: ");
        response = input.nextLine().toLowerCase();
        while(!response.equals("play"))
        {
            while(!response.equals("randomize")&&!response.equals("sort")&&!response.equals("print")&&!response.equals("play"))
            {
                System.out.print("\033[6;1H\033[J");
                oneArt.testPage();
                System.out.print("\033[14;15HChoice: ");
                response = input.nextLine().toLowerCase();
            }
            if(response.equals("randomize"))
            {
                oneArt.Wordle();
                System.out.print("\033[6;1H\033[J");
                one.randomizeWordList();
                oneArt.testPage2();
                System.out.print("\033[14;15HChoice: ");
                response = input.nextLine().toLowerCase();
            }
            if(response.equals("sort"))
            {
                oneArt.Wordle();
                System.out.print("\033[6;1H\033[J");
                one.sortWordList();
                oneArt.testPage3();
                System.out.print("\033[14;15HChoice: ");
                response = input.nextLine().toLowerCase();
            }
            if(response.equals("print"))
            {
                oneArt.Wordle();
                System.out.print("\033[1;1H\033[J");
                System.out.print(one.toString());
                System.out.println("\n");
                oneArt.testPage();
                System.out.print("\033[77;15HChoice: ");
                response = input.nextLine().toLowerCase();
            }
        }
        System.out.print("\033[6;1H\033[J");
        playGame();
        
    }
}