package zuul.timerunner.pkg_commands;

import zuul.timerunner.pkg_others.GameEngine;
import zuul.timerunner.pkg_others.Player;
import zuul.timerunner.pkg_items.Item;

/**
 * Command to drop player's item in the current Room.
 * 
 * @author ASTIER Naji & ROBIN Yohann
 * @version 01/05/2013
 */
public class DropCommand extends Command
{
    /**
     * Constructor for objects of class model
     */
    public DropCommand()
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
            // if there is no second word, we don't know what to drop...
            GameEngine.getGUI().println("Déposer quoi?");
            return;
        }
        
        String vNomObjet = this.getSecondWord();
        Item vObjet = player.getItemList().getItem( vNomObjet );
        
        // If we can't find the object inside the player's inventory
        if (vObjet == null)
        {
            GameEngine.getGUI().println(vNomObjet + " est introuvable.");
            return;
        }
        
        player.getCurrentRoom().getItemList().addItem(vNomObjet, vObjet);
        player.getItemList().removeItem(vNomObjet);
        GameEngine.getGUI().println("Vous avez déposé " + vNomObjet);
    }
}