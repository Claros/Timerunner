package zuul.timerunner.pkg_commands;

import zuul.timerunner.pkg_others.Player;
import zuul.timerunner.pkg_others.GameEngine;
import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;

/**
 * Command to open a test file that contains commands.
 * 
 * @author ASTIER Naji & ROBIN Yohann
 * @version 01/05/2013
 */
public class TestCommand extends Command
{
    /**
     * Constructor for objects of class model
     */
    public TestCommand()
    {
    }
    
    /**
     * Execution of the command
     * @param player An object of the class Player
     */
    @Override
    public void execute(Player player)
    {
        if(!hasSecondWord()) 
        {
            // if there is no second word, we don't know what to test...
            GameEngine.getGUI().println("Tester quoi?");
            return;
        }
        
        Scanner vSr;
        
        try 
        { 
            vSr = new Scanner( new File( "./" + getSecondWord() ) );
            while ( vSr.hasNextLine() )
            {
                String ligne = vSr.nextLine();
                GameEngine.interpretCommand(ligne);
            }
            vSr.close();
        } 
        catch ( FileNotFoundException pObjetException ) 
        {  
            GameEngine.getGUI().println("le nom du fichier est incorrect");
        } 
    }
}
