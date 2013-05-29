package zuul.timerunner.pkg_commands;

import zuul.timerunner.pkg_others.Player;
import zuul.timerunner.pkg_others.GameEngine;

/**
 * Command to look at the player's surroundings.
 * 
 * @author ASTIER Naji & ROBIN Yohann
 * @version 01/05/2013
 */
public class LookCommand extends Command
{
    /**
     * Constructor for objects of class model
     */
    public LookCommand()
    {
    }
    
    /**
     * Execution of the command
     * @param player An object of the class Player
     */
    @Override
    public void execute(Player player)
    {
        GameEngine.getGUI().println(player.getCurrentRoom().getLongDescription());
        GameEngine.getGUI().println(player.getDescription());
    }
}
