package zuul.timerunner.pkg_others;

import zuul.timerunner.pkg_items.ItemList;
import zuul.timerunner.pkg_rooms.Room;

/**
 * Character are non player entity. They can move and talk.
 * 
 * @author  ASTIER Naji & ROBIN Yohann
 */
public class Character
{
    /** The name. */
    private String aName;
    /** The current room. */
    private Room aCurrentRoom;
    /** The item list. */
    private ItemList aItemList;
    /** The dialog. */
    private String aDialog;
    /** The object. */
    private String aObject;
    
    /**
     * Instantiates a new character.
     *
     * @param pName the name
     * @param pDialog the dialog
     * @param pObject the object
     * @param pCurrentRoom the current room
     */
    public Character(final String pName, final String pDialog, final String pObject, final Room pCurrentRoom)
    {
        aName = pName; 
        aDialog = pDialog;
        aObject = pObject; 
        aCurrentRoom = pCurrentRoom;
        aItemList = new ItemList(); 
    }
    
    /**
     * Gets the name.
     *
     * @return the name
     */
    public String getName()
    {
        return aName;
    }
    
    /**
     * Gets the current room.
     *
     * @return the current room
     */
    public Room getCurrentRoom()
    {
       return aCurrentRoom;
    }

    /**
     * Sets the current room.
     *
     * @param pCurrentRoom the new current room
     */
    public void setCurrentRoom(final Room pCurrentRoom)
    {
        aCurrentRoom = pCurrentRoom;
    }
    
    /**
     * Gets the item list.
     *
     * @return the item list
     */
    public  ItemList getItemList()
    {
        return aItemList;
    }
    
    /**
     * Gets the dialog.
     *
     * @return the dialog
     */
    public String getDialog()
    {
        return aDialog;
    }
    
    /**
     * Gets the object.
     *
     * @return the object
     */
    public String getObject()
    {
        return aObject;
    }
    
    /**
     * Move the Character
     * 
     * @param pNextRoom The Room where the character has to go
     */
    public void moveCharacter(final Room pNextRoom)
    {
        this.aCurrentRoom.removeCharacter(this.aName);
        pNextRoom.addCharacter(this);
        this.aCurrentRoom = pNextRoom;
    }
}
