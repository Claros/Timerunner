package zuul.timerunner.pkg_commands;
import zuul.timerunner.pkg_others.Player;
import zuul.timerunner.pkg_others.GameEngine;
import zuul.timerunner.pkg_rooms.RoomRandomizer;

/**
 * Alea command, to control the RoomRandomizer.
 * 
 * @author ASTIER Naji & ROBIN Yohann
 * @version 01/05/2013
 */
public class AleaCommand extends Command
{
    /**
     * Constructor for objects of class AleaCommand
     */
    public AleaCommand()
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
            GameEngine.getGUI().println("tirage aléatoire");
            RoomRandomizer.setSeed("");
        }
        else 
        {
            if(GameEngine.getListRoom().containsKey(getSecondWord()))
            {
                GameEngine.getGUI().println("tirage non aléatoire");
                RoomRandomizer.setSeed(getSecondWord()); 
            }
            else 
            {
                GameEngine.getGUI().println("Cette pièce n'existe pas!");
            }
        }
        
    }
}