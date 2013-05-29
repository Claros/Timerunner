package zuul.timerunner.pkg_commands;

import zuul.timerunner.pkg_others.GameEngine;
import zuul.timerunner.pkg_others.Player;
import zuul.timerunner.pkg_items.Item;

/**
 * Command to eat and edible item
 * 
 * @author ASTIER Naji & ROBIN Yohann
 * @version 01/05/2013
 */
public class EatCommand extends Command
{
    /**
     * Constructor for objects of class model
     */
    public EatCommand()
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
            // if there is no second word, we don't know what to eat...
            GameEngine.getGUI().println("Eat what?");
            return;
        }
        
        String vNomObjet = this.getSecondWord();
        Item vObjet = player.getItemList().getItem(vNomObjet);
        
        // If the item isn't findable
        if (vObjet == null)
        {
            GameEngine.getGUI().println(vNomObjet + " est introuvable.");
            return;
        }
        
        // If the item is not edible
        if (!vObjet.getEdible())
        {
            GameEngine.getGUI().println(vNomObjet + " n'est pas commestible.");
            return;
        }
        
        player.getItemList().removeItem(vNomObjet);
        
        // If the item is the special potion
        if (vNomObjet.equals("potion") )
        {
            GameEngine.getGUI().println("Vous avez bu la potion. Vous pouvez maintenant porter plus d'objets.");
            player.setMaxWeight(player.getMaxWeight() + 10);
            return;
        }
        
        GameEngine.getGUI().println("Vous avez mangé " + vNomObjet);
    }
}
