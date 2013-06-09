package com.timerunner;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Vector2f;

import com.timerunner.entities.Player;

/**
 * The Class Shoot.
 * Manage the shoot from the player.
 */
public class Shoot 
{
	/** The velocity of the shoot. */
	private static float VELOCITY = 0.55f;
	/** The a position. */
	private Vector2f aPos;
	/** The a "box"... */
	private Circle aBox;
	/** The direction. */
	private String direction;
	
	/**
	 * Instantiates a new shoot.
	 *
	 * @param pX the p x
	 * @param pY the p y
	 */
	public Shoot(final float pX, final float pY) 
	{
		this.aPos = new Vector2f(pX, pY);
		this.aBox = new Circle(pX, pY, 5, 5);
	}
	
	/**
	 * Update.
	 *
	 * @param delta the delta
	 * @param player the player
	 * @param mapWidth the map width
	 * @param mapHeight the map height
	 * @param map the map
	 * @return true, if the shoot touched something or someone
	 */
	public boolean update (final float delta, final Player player, final int mapWidth, final int mapHeight, final Map map)
	{
		if (direction == null)
		{
			direction = player.getDirection();
		}
		switch (direction)
		{
			case "bas":
				this.aPos.y += VELOCITY * delta;
			break;
			case "haut":
				this.aPos.y -= VELOCITY * delta;
			break;
			case "droite":
				this.aPos.x += VELOCITY * delta;
			break;
			case "gauche":
				this.aPos.x -= VELOCITY * delta;
			break;
		}
		this.aBox.setY(this.aPos.y);
		this.aBox.setX(this.aPos.x);
		
		if (this.aPos.x > mapWidth || this.aPos.x < 0 || this.aPos.y > mapHeight || this.aPos.y < 0 || map.isTileBlocked(aBox) || player.isEntityHited(aBox))
		{
			return true;
		}
		
		return false;
	}
	
	/**
	 * Render.
	 *
	 * @param g the graphics
	 */
	public void render(final Graphics g)
	{
		g.setColor(Color.red);
		g.fill(this.aBox);
	}
}
