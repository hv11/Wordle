public class WordleArt
{
    public void Wordle()
    {
        System.out.println("\033[1;1H\033[J\t__      __          _ _");
        System.out.println("\t\\ \\    / /__ _ _ __| | |___");
        System.out.println("\t \\ \\/\\/ / _ \\ '_/ _' | / -_)");
        System.out.println("\t  \\_/\\_/\\___/_| \\__,_|_\\___|\n");
    }
    
    public void youWon()
    {
        System.out.println("\033[1;1H\033[K      __   __         __      __        _");
        System.out.println("      \\ \\ / /__ _  _  \\ \\    / /__ _ _ | |");
        System.out.println("       \\ v / _ \\ |  |  \\ \\/\\/ / _ \\ ' \\|_|");
        System.out.println("        |_|\\___/\\_,_|   \\_/\\_/\\___/_||_(_)");
    }
    
    public void youLost()
    {
        System.out.println("\033[1;1H\033[K      __   __          _           _   _");
        System.out.println("      \\ \\ / /__ _  _  | |   ___ __| |_| |");
        System.out.println("       \\ v / _ \\ || | | |__/ _ (_-<  _|_|");
        System.out.println("        |_|\\___/\\_,_| |____\\___/__/\\__(_)");
    }
    
    public void testPage()
    {
        System.out.println("\t    +-------------------+");
        System.out.println("\t    |      Options      |");
        System.out.println("\t    +-------------------+");
        System.out.println("\t    |     Randomize     |");
        System.out.println("\t    |        Sort       |");
        System.out.println("\t    |       Print       |");
        System.out.println("\t    |        Play       |");
        System.out.println("\t    +-------------------+");
        System.out.println("\t    |                   |");
        System.out.println("\t    +-------------------+");
    }
    
    
    public void testPage2()
    {
        System.out.println("\t    +-------------------+");
        System.out.println("\t    |Randomize Complete!|");
        System.out.println("\t    +-------------------+");
        System.out.println("\t    |     Randomize     |");
        System.out.println("\t    |        Sort       |");
        System.out.println("\t    |       Print       |");
        System.out.println("\t    |        Play       |");
        System.out.println("\t    +-------------------+");
        System.out.println("\t    |                   |");
        System.out.println("\t    +-------------------+");
        System.out.println("\033[14;15HChoice: ");
        
    }
    
    public void testPage3()
    {
        System.out.println("\033[J\t    +-------------------+");
        System.out.println("\t    |  Sort Complete!   |");
        System.out.println("\t    +-------------------+");
        System.out.println("\t    |     Randomize     |");
        System.out.println("\t    |        Sort       |");
        System.out.println("\t    |       Print       |");
        System.out.println("\t    |        Play       |");
        System.out.println("\t    +-------------------+");
        System.out.println("\t    |                   |");
        System.out.println("\t    +-------------------+");
        System.out.println("\033[14;15HChoice: ");
    }

}