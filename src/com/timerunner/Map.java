package com.timerunner;

import java.util.ArrayList;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.tiled.TiledMap;

import com.timerunner.entities.Entity;

/**
 * The Class Map.
 */
public class Map extends TiledMap
{
	
	/** The blocked tile. */
	private ArrayList<Rectangle> blockedTile;
    private ArrayList<Entity> characters;
	 
	/**
	 * Instantiates a new map.
	 *
	 * @param ref the map path
	 * @throws SlickException the slick exception
	 */
	public Map(String ref) throws SlickException 
	{
		//envoie à TiledMap l'adresse du fichier .tmx
		super(ref);
		blockedTile = new ArrayList<Rectangle>();
		for (int x = 0; x < width; x++)
		{
		      for (int y = 0; y < height; y++)
		      {
		            for (int l = 0; l < getLayerCount()-1; l++)
		            {
		                int id = getTileId(x, y, l);
		                if (id != 0)
		                { 
		                	String b = getTileProperty(id, "walkable", "");
		                	if (b.equals("false")) 
		                	{
		                		blockedTile.add(new Rectangle(x*32, y*32, 32, 32));
		                	} 
		                }
		            }
		       }
		}
		characters = new ArrayList<Entity>();
	}
	
	/**
	 * Adds an entity to the map 
	 *
	 * @param pEntity the entity
	 */
	public void addCharacter(final Entity pEntity)
	{
		characters.add(pEntity);
	}
	
	/**
	 * Removes an entity from the map.
	 *
	 * @param pEntity the entity
	 */
	public void removeCharacter(final Entity pEntity)
	{
		characters.remove(pEntity);
	}
	
	/**
	 * Gets an entity from the map.
	 *
	 * @param pI the index of the entity
	 * @return the entity
	 */
	public Entity getCharacter(final int pI)
	{
		return characters.get(pI);
	}
	
	/**
	 * @return the characters
	 */
	public ArrayList<Entity> getCharacters() {
		return characters;
	}

	/**
	 * Draw rect.
	 *
	 * @param g the graphics
	 */
	public void drawRect(Graphics g)
	{
		for (Rectangle r : blockedTile)
		{
			g.draw(r);
		}
	}
	
	/**
	 * Checks if is tile blocked.
	 *
	 * @param pShape the shape
	 * @return true, if tile is blocked
	 */
	public boolean isTileBlocked(final Shape pShape) 
	{ 
		for (Rectangle r : blockedTile)
		{
			if (r.intersects(pShape))
			{
				return true;
			}
		}
		return false;
	}
	
	public boolean isCharacterDead(final String pName)
	{
		for (Entity vE : characters)
		{
			if (vE.getName().equals(pName))
			{
				return false;
			}
		}
		return true;
	}
}