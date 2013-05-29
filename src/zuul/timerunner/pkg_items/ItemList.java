package zuul.timerunner.pkg_items;

import java.util.HashMap;

/**
 * The Class ItemList.
 * Manage a list of object of Item class.
 * 
 * @author  ASTIER Naji & ROBIN Yohann
 */
public class ItemList
{
    /** The item list. */
    private HashMap<String,Item> aItemList;

    /**
     * Instantiates a new item list.
     */
    public ItemList()
    {
        this.aItemList = new HashMap<String,Item>();
    }
    
    /**
     * Adds a new item to the list.
     *
     * @param pName the name
     * @param pDescription the description
     * @param pWeight the weight
     * @param pEdible edible
     */
    public void addItem(final String pName, final String pDescription, final double pWeight, final boolean pEdible) 
    {
        this.aItemList.put(pName, new Item(pWeight, pDescription, pEdible));
    }
    
    /**
     * Adds an item to the list.
     *
     * @param pName the name
     * @param pItem the item
     */
    public void addItem(final String pName, final Item pItem) 
    {
        this.aItemList.put(pName, pItem);
    }
    
    /**
     * Removes an item in the list.
     *
     * @param pName the name
     */
    public void removeItem (final String pName)
    {
        this.aItemList.remove(pName);
    }
    
    /**
     * @return the HashMap size
     */
    public int getSize()
    {
        return this.aItemList.size();
    }
    
    /**
     * Gets the item.
     *
     * @param pName the name
     * @return the item
     */
    public Item getItem(final String pName)
    {
        return this.aItemList.get(pName);
    }
   
    /**
     * Gets the item list in a String.
     *
     * @return All objects inside the list
     */
    public String getItemsString()
    {
        StringBuilder vReturnString = new StringBuilder( "" );
        for ( String vS : this.aItemList.keySet())
        {
        	vReturnString.append( " " + vS + " (" + this.aItemList.get(vS).getWeight() + ")" );
        }
        return vReturnString.toString();
    }
    
    /**
     * Gets the total weight of the list.
     *
     * @return the total weight of the list
     */
    public double getTotalWeight()
    {
        double vTotal = 0;
        for ( String vS : this.aItemList.keySet())
        {
            vTotal += this.aItemList.get(vS).getWeight();
        }
        return vTotal;
    }
}