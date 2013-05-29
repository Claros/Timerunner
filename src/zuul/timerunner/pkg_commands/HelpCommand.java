package zuul.timerunner.pkg_commands;

import zuul.timerunner.pkg_others.GameEngine;
import zuul.timerunner.pkg_others.Player;
/**
 * Command show some help and the list of valid commands.
 * 
 * @author ASTIER Naji & ROBIN Yohann
 * @version 01/05/2013
 */
public class HelpCommand extends Command
{    
    /**
     * Constructor for objects of class HelpCommand
     */
    public HelpCommand()
    {
        
    }
    
    /**
     * Execution of the command
     * @param player An object of the class Player
     */
    @Override
    public void execute(Player player)
    {
        GameEngine.getGUI().println("You are lost. You are alone. You wander");
        GameEngine.getGUI().println("around at Monash Uni, Peninsula Campus." + "\n");
        GameEngine.getGUI().print("Your command words are: ");
        GameEngine.getGUI().print(GameEngine.getParser().showCommands());
    }
    
}
