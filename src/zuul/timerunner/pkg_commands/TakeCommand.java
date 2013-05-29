package zuul.timerunner.pkg_commands;

import zuul.timerunner.pkg_others.Player;
import zuul.timerunner.pkg_others.GameEngine;
import zuul.timerunner.pkg_items.Item;

/**
 * Command to take an item in the current room.
 * Take an item on the currentRoom and stock it
 * In the player's inventory.
 * 
 * @author ASTIER Naji & ROBIN Yohann
 * @version 01/05/2013
 */
public class TakeCommand extends Command
{
    /**
     * Constructor for objects of class model
     */
    public TakeCommand()
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
            // if there is no second word, we don't know what to take...
            GameEngine.getGUI().println("Prendre quoi?");
            return;
        }
        
        String vNomObjet = this.getSecondWord();
        Item vObjet = player.getCurrentRoom().getItemList().getItem( vNomObjet );
        
        // If we can't find the item in the currentRoom
        if (vObjet == null)
        {
            GameEngine.getGUI().println(vNomObjet + " est introuvable.");
            return;
        }
        
        double vFutureWeight = player.getItemList().getTotalWeight() + vObjet.getWeight();
        
        // if the player can't carry more items
        if (vFutureWeight > player.getMaxWeight())
        {
            GameEngine.getGUI().println("Vous ne pouvez pas porter un objet supplémentaire");
            return;
        }
        
        player.getItemList().addItem(vNomObjet, vObjet);
        player.getCurrentRoom().getItemList().removeItem(vNomObjet);
        GameEngine.getGUI().println("Vous avez ramassé " + vNomObjet);
    }
}
