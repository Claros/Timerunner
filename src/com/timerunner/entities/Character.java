package com.timerunner.entities;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;

import com.timerunner.Map;

/**
 * The Class Character.
 * The Character extends the Entity class. It is managed by the Game Engine and can talk to the player.
 * The Character can't move.
 */
public class Character extends Entity
{
	
	/**
	 * Instantiates a new character.
	 *
	 * @param x the x
	 * @param y the y
	 * @param width the width
	 * @param height the height
	 * @param sprite the sprite
	 * @param spriteX the sprite x
	 * @param spriteY the sprite y
	 * @param pName the name
	 * @throws SlickException the slick exception
	 */
	public Character(float x, float y, int width, int height, String sprite, int spriteX, int spriteY, String pName) throws SlickException 
	{
		super(x, y, width, height, sprite, spriteX, spriteY, pName);
		super.statLife = 30;
		super.statStrength = 10;
		super.statDefense = 10;
	}

	/* (non-Javadoc)
	 * @see com.timerunner.entities.Entity#update(org.newdawn.slick.GameContainer, int, int, int, com.timerunner.Map)
	 */
	@Override
	public void update(GameContainer gc, int mapWidth, int mapHeight, int delta, Map map) 
	{
		
	}
}
