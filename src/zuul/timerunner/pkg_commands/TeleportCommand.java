package zuul.timerunner.pkg_commands;

import zuul.timerunner.pkg_others.GameEngine;
import zuul.timerunner.pkg_others.Player;
import zuul.timerunner.pkg_items.Beamer;

/**
 * Command to use the Beamer item.
 * Firs time used : the beamer is loaded.
 * Second time used : the beamer is unloaded and the player is teleported.
 * 
 * @author ASTIER Naji & ROBIN Yohann
 * @version 01/05/2013
 */
public class TeleportCommand extends Command
{
    /**
     * Constructor for objects of class model
     */
    public TeleportCommand()
    {
    }
    
    /**
     * Execution of the command
     * @param player An object of the class Player
     */
    @Override
    public void execute(Player player)
    {
         if(player.getItemList().getItem ("Beamer") == null)
         {
            GameEngine.getGUI().println("Vous ne disposez pas encore de cet objet.");
            return;
         }
         
         Beamer vBeamer = (Beamer) player.getItemList ().getItem ("Beamer");
         
         if(vBeamer.getLoaded ())
         {
            player.movePlayer(vBeamer.getSave ());
            //nombreDappels ++;  
            vBeamer.invLoaded();
         }
         else 
         {
            vBeamer.invLoaded ();
            vBeamer.setSave (player.getCurrentRoom());
            GameEngine.getGUI().println("Vous venez de charger le beamer.");
         }
         
         // To refresh the state of the beamer, 
         // we need to remove the old beamer and replace it by the new beamer
         player.getItemList().removeItem("Beamer");
         player.getItemList().addItem("Beamer", vBeamer);
    }
}
