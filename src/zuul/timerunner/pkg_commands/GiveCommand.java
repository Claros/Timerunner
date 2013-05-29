package zuul.timerunner.pkg_commands;

import zuul.timerunner.pkg_others.Player;
import zuul.timerunner.pkg_others.GameEngine;
import zuul.timerunner.pkg_others.Character;
import zuul.timerunner.pkg_items.Item;

/**
 * Command to give player's item to non player character.
 * 
 * @author ASTIER Naji & ROBIN Yohann
 * @version 01/05/2013
 */
public class GiveCommand extends Command
{
    /**
     * Constructor for objects of class GiveCommand
     */
    public GiveCommand()
    {
    }
    
    /**
     * Execution of the command
     * @param player An object of the class Player
     */
     @Override
    public void execute(Player player)
    {
        if(!hasSecondWord() || !hasThirdWord())
        {
            // if there is no second word, we don't know what to drop...
            GameEngine.getGUI().println("Donner quoi à qui?");
            return;
        }
        
        String vItemName = this.getSecondWord();
        String vCharacterName = this.getThirdWord ();
        Item vItem = player.getItemList().getItem(vItemName);
        Character vCharacter = player.getCurrentRoom ().getCharacter (vCharacterName);
        
        // If we can't find the object inside the player's inventory
        if (vItem == null)
        {
            GameEngine.getGUI().println("L'objet " + vItemName + " est introuvable.");
            return;
            
        }
        
        if(vCharacter == null)  
        {
            GameEngine.getGUI(). println("Le personnage " + vCharacterName + " n'existe pas. ");
            return; 
        }
        
        if(!vItemName.equals(vCharacter.getObject()))
        {
            GameEngine.getGUI(). println(vCharacterName + ": Je n'en veux pas ! ");
            return; 
        }
        
        player.getCurrentRoom().getCharacter (vCharacterName).getItemList().addItem(vItemName, vItem);
        player.getItemList().removeItem(vItemName);
        GameEngine.getGUI().println(vCharacterName + ": Merci pour ce " + vItemName);
    }

    
}
