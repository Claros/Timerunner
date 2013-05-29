package zuul.timerunner.pkg_items;

import zuul.timerunner.pkg_rooms.Room;

/**
 * Beamer class, the item used to teleport
 * Inherits Item.
 *
 * @author ASTIER Naji & ROBIN Yohann
 * @version 08/04/2013
 */
public class Beamer extends Item
{
    /** Beamer state : loaded or not. */
    private boolean aLoaded;
    /** The Room saved. */
    private Room aSave;

    /**
     * Constructor of Beamer class.
     *
     * @param pWeight Weight of the Item
     * @param pDescription Description of the Item
     * @param pEdible Edible or not
     */
    public Beamer(final int pWeight, final String pDescription, final boolean pEdible)
    {
        super (pWeight, pDescription, pEdible);
        this.aLoaded = false;
        this.aSave = null;
    }

    /**
     * getter getLoaded.
     *
     * @return true if the Beamer is loaded
     */
    public boolean getLoaded ()
    {
        return this.aLoaded;
    }
  
    /**
     * Reverse Beamer's state.
     */
    public void invLoaded ()
    {
        this.aLoaded = !this.aLoaded;
        
    }
    
    /**
     * getter getSave.
     *
     * @return Room saved when the beamer is loaded
     */
    public Room getSave ()
    {
        return this.aSave;
    }
    
    /**
     * setter setSave.
     *
     * @param pSave the new Room to save
     */
    public void setSave(final Room pSave)
    {
        this.aSave = pSave;
    }
}