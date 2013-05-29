package zuul.timerunner.pkg_others;

import java.util.Stack;
import zuul.timerunner.pkg_rooms.Room;
import zuul.timerunner.pkg_items.ItemList;

/**
 * Player class.
 * Manage a player, his inventory and Itinerary.
 * 
 * @author  ASTIER Naji & ROBIN Yohann
 * @version 18/03/2013
 */
public class Player
{    
    /** The name of the player. */
    private String aName;    
    /** The current room. */
    private Room aCurrentRoom;    
    /** The max weight he can carry. */
    private int aMaxWeight;    
    /** The itinerary he took. */
    private Stack<Room> aItinerary;    
    /** The inventory. */
    public ItemList aInventory;
    
    /**
     * Constructor for objects of class Player.
     *
     * @param pName the name of the player
     * @param pMaxWeight the max weight he can carry
     */
    public Player(final String pName, final int pMaxWeight)
    {
        this.aName = pName;
        this.aMaxWeight = pMaxWeight;
        this.aInventory = new ItemList();
        this.aCurrentRoom = null;
        this.aItinerary = new Stack<Room>(); 
    }
    
    /**
     * Gets the name.
     *
     * @return the name
     */
    public String getName()
    {
        return this.aName;
    }
    
    /**
     * Sets the current room.
     *
     * @param pCurrentRoom the new current room
     */
    public void setCurrentRoom(final Room pCurrentRoom)
    {
        this.aCurrentRoom = pCurrentRoom; 
    }
    
    /**
     * Push last room.
     *
     * @param pRoom the room
     */
    public void pushLastRoom(final Room pRoom)
    {
        this.aItinerary.push (pRoom);
    }
    
    /**
     * Gets the description.
     *
     * @return the description
     */
    public String getDescription()
    {
        if (this.aInventory.getTotalWeight() > 0)
        {
            return "Inventaire : " + this.aInventory.getItemsString() + "\nPoids : " + this.aInventory.getTotalWeight();
        }
        else
        {
            return "";
        }

    }
    
    /**
     * Gets the current room.
     *
     * @return the current room
     */
    public Room getCurrentRoom ()
    {
        return this.aCurrentRoom;
    }
    
    /**
     * Gets the last room.
     *
     * @return the last room
     */
    public Room getLastRoom()
    {
        return aItinerary.pop();
    }
    
    /**
     * Checks if is itenerary empty.
     *
     * @return true if the itenerary is empty
     */
    public boolean isIteneraryEmpty()
    {
        return aItinerary.empty();
    }
    
    /**
     * Gets the item list.
     *
     * @return the item list
     */
    public ItemList getItemList()
    {
        return this.aInventory;
    }
    
    /**
     * Gets the max weight.
     *
     * @return the max weight
     */
    public int getMaxWeight()
    {
        return this.aMaxWeight;
    }
    
    /**
     * Sets the max weight.
     *
     * @param pWeight the new max weight
     */
    public void setMaxWeight(final int pWeight)
    {
        this.aMaxWeight = pWeight;
    }
    
    /**
     * Move player.
     *
     * @param pRoom the room
     */
    public void movePlayer(final Room pRoom)
    {
        this.setCurrentRoom(pRoom);
        GameEngine.getGUI().println(this.getCurrentRoom().getLongDescription());
        
        if(this.getCurrentRoom().getImageName() != null)
        {
            GameEngine.getGUI().showImage(this.getCurrentRoom().getImageName());
        }
        
    }
}