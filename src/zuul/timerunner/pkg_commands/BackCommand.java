package zuul.timerunner.pkg_commands;

import zuul.timerunner.pkg_others.GameEngine;
import zuul.timerunner.pkg_others.Player;

/**
 * Command to go back in the previous rooms.
 * 
 * @author ASTIER Naji & ROBIN Yohann
 * @version 01/05/2013
 */
public class BackCommand extends Command
{
    /**
     * Constructor for objects of class model
     */
    public BackCommand()
    {
    }
    
    /**
     * Execution of the command
     * @param player An object of the class Player
     */
    @Override
    public void execute(Player player)
    {
        if(player.isIteneraryEmpty())
        {
             GameEngine.getGUI().println("Vous êtes retournez au depart.");
             
        }
        else
        {
            player.movePlayer(player.getLastRoom());
        }
    }
}
