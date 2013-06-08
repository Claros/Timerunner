package com.timerunner.entities;

import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;

import com.timerunner.Config;
import com.timerunner.Map;
import com.timerunner.states.GameState;

/**
 * The Class Player.
 * The Player can move independently from the game engine.
 */
public class Player extends Entity
{ 
	private Entity entityContact;
	
	/**
	 * Instantiates a new player.
	 *
	 * @param x the x
	 * @param y the y
	 * @param width the width
	 * @param height the height
	 * @param sprite the sprite
	 * @throws SlickException the slick exception
	 */
	public Player(float x, float y, int width, int height, String sprite, int spriteX, int spriteY, String pName) throws SlickException 
	{
		super(x, y, width, height, sprite, spriteX, spriteY, pName);
		super.statLife = 30;
		super.statStrength = 10;
		super.statDefense = 10;
	}
 
	/**
	 * Update.
	 *
	 * @param gc the game container
	 * @param mapWidth the map width
	 * @param mapHeight the map height
	 * @param delta the delta
	 * @param map the map
	 */
	@Override
	public void update(GameContainer gc, int mapWidth, int mapHeight, int delta, Map map) 
	{
		Vector2f trans = new Vector2f(0, 0);
		Input input = gc.getInput();
		setRunning(false);
		float speed = 0.15f;
     
    	if(input.isKeyDown(Config.KEY_RIGHT.getValue()))
    	{
    		trans.x= speed * delta;
    		setRunning(true);
			setDirection("droite");
    	}
     
    	if(input.isKeyDown(Config.KEY_LEFT.getValue()))
    	{
    		trans.x= -speed * delta;
    		setRunning(true);
    		setDirection("gauche");
    	}
 
    	if(input.isKeyDown(Config.KEY_UP.getValue()))
    	{//Delta is used to move things on a frame rate independent way
    		trans.y = -speed * delta;
    		setRunning(true);
    		setDirection("haut");
    	}
     
    	if(input.isKeyDown(Config.KEY_DOWN.getValue()))
    	{
    		trans.y = speed * delta;
    		setRunning(true);
    		setDirection("bas");
    	}
    	
    	entityContact = moveEntity(trans, mapWidth, mapHeight, map);
	}
	
	/**
	 * Gets the contact.
	 *
	 * @return the contact
	 */
	public Entity getContact()
	{
		return entityContact;
	}
	
	public Entity isEntityTalkable()
	{
		ArrayList<Entity> entities = new ArrayList<Entity> ( GameState.getCharacters());
		entities.remove(this);
		for (Entity e : entities)
		{
			if (hitbox.intersects(e.getBox()))
			{
				return e;
			}
		}
		return null;
	}
}
