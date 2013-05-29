package zuul.timerunner.pkg_rooms;
import java.util.Random;
import java.util.HashMap;

/**
 * RoomRandomizer class.
 * Can return randomly a Room inside a list.
 * 
 * @author  ASTIER Naji & ROBIN Yohann
 * @version 22/04/2013
 */
public class RoomRandomizer 
{
    /** The room array. */
    private Room[] aTabRoom;
    /** The seed to stop the random. */
    private static String aSeed = "";
    /** The room list. */
    private HashMap <String,Room> aListRoom;
    /** The Random object**/
    private Random aRandom;
    
    /**
     * Instantiates a new room randomizer.
     * It convert a HashMap in a array.
     *
     * @param pListeRoom the liste room
     */
    public RoomRandomizer(final HashMap <String,Room> pListRoom)
    {
    	this.aListRoom = pListRoom; 
        this.aRandom = new Random();
        this.aTabRoom = new Room [pListRoom.size() ];
        int vI = 0;
        for (String vS : pListRoom.keySet ())
        {
            this.aTabRoom[vI] = pListRoom.get (vS);
            vI = vI + 1;
        }
    }
    
    /**
     * Next room.
     *
     * @return a random Room inside the room list
     */
    public Room nextRoom()
    {
        if(RoomRandomizer.aSeed.isEmpty())
        {
            return this.aTabRoom[aRandom.nextInt(this.aTabRoom.length)];
        }
        else
        {
            return aListRoom.get(RoomRandomizer.aSeed);
        }
        
    }
    
    /**
     * Sets the seeed.
     *
     * @param pSeed the new seed
     */
    public static void setSeed (final String pSeed)
    {
    	RoomRandomizer.aSeed = pSeed; 
    }
}
