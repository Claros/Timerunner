package zuul.timerunner.pkg_items;

/**
 * Item class
 * Manage all the item inside the game.
 * 
 * @author  ASTIER Naji & ROBIN Yohann
 * @version 18/03/2013
 */
public class Item
{
	/** The weight of the item. */
    private double aWeight;
    /** The description of the item. */
    private String aDescription;
    /** The edible state. */
    private boolean aEdible;
   
    /**
     * Constructor for objects of class Items.
     *
     * @param pWeight the weight
     * @param pDescription the description
     * @param pEdible the edible state
     */
    public Item(final double pWeight, final String pDescription, final boolean pEdible)
    {
        this.aWeight = pWeight;
        this.aDescription = pDescription;
        this.aEdible = pEdible;
    }
    
    /**
     * Gets the description.
     *
     * @return the description
     */
    public String getDescription()
    { 
        return this.aDescription; 
    }
    
    /**
     * Gets the weight.
     *
     * @return the weight
     */
    public double getWeight()
    {
        return this.aWeight;
    }
    
    /**
     * Gets the edible state.
     *
     * @return true if the item is edible
     */
    public boolean getEdible()
    {
        return this.aEdible;
    }
}