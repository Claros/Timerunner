package zuul.timerunner.pkg_commands;

import zuul.timerunner.pkg_others.GameEngine;
import zuul.timerunner.pkg_others.Player;
import zuul.timerunner.pkg_rooms.Room;
/**
 * Command the player use to move arround the level.
 * 
 * @author ASTIER Naji & ROBIN Yohann
 * @version 01/05/2013
 */
public class GoCommand extends Command
{
    /**
     * Constructor for objects of class GoCommand
     */
    public GoCommand()
    {
    }

    /** 
     * Try to go to one direction. If there is an exit, enter the new
     * room, otherwise print an error message. Returns always 'false'.
     */
    @Override
    public void execute(Player player)
    {
        if(!hasSecondWord())
        {
            // if there is no second word, we don't know where to go...
            GameEngine.getGUI().println("Go where?");
            return;
        }

        String vWay = this.getSecondWord();

        // Try to leave current room.
        Room vNextRoom = player.getCurrentRoom().getExit(vWay);

        if (vNextRoom == null)
        {
            GameEngine.getGUI().println("There is no door!");
        }
        else 
        {
            player.pushLastRoom(player.getCurrentRoom());
            player.movePlayer(vNextRoom);
            

//             nombreDappels ++;
//             if (nombreDappels > 15)
//             {
//                 this.aGui.println("vous avez perdu");
//                 endGame();
//             }
        }
    }
}
