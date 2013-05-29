package zuul.timerunner.pkg_rooms;

import zuul.timerunner.pkg_others.GameEngine;
/**
 * A special Room where the exits randomly teleports the player.
 * 
 * @author  ASTIER Naji & ROBIN Yohann
 * @version 22/04/2013
 */
public class TransporterRoom extends Room 
{
    
    /**
     * Instantiates a new transporter room.
     *
     * @param pDescription the description
     * @param pImage the image
     */
    public TransporterRoom(final String pDescription, final String pImage) 
    {
        super(pDescription, pImage);
    }
    
    /**
     * Return a random room, independent of the direction 
     * parameter.
     * @param direction ignored.
     * @return a random room.
     */
    @Override
    public Room getExit(String direction)
    {
        RoomRandomizer random = new RoomRandomizer (GameEngine.getListRoom());
        return random.nextRoom();
    }
}