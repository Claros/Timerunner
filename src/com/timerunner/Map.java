package com.timerunner;

import java.util.ArrayList;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
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
	
	public void addCharacter(final Entity pEntity)
	{
		characters.add(pEntity);
	}
	
	public void removeCharacter(final Entity pEntity)
	{
		characters.remove(pEntity);
	}
	
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
	 * @param rPlayer the player
	 * @return true, if tile is blocked
	 */
	public boolean isTileBlocked(Rectangle rPlayer) 
	{ 
		for (Rectangle r : blockedTile)
		{
			if (r.intersects(rPlayer))
			{
				return true;
			}
		}
		return false;
	}
}