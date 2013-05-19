package com.timerunner.states;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.state.StateBasedGame;

import com.timerunner.Camera;
import com.timerunner.Game;
import com.timerunner.Map;
import com.timerunner.Player;

public class GameState extends GlobalState
{
	public static final int ID = 1;
    
    private int mapHeight, mapWidth;    
    private Player player;
    private Camera camera;
	private Sound shoot;
    private Map map;
    private Graphics renderGraphics;

	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException 
	{
		super.init(container, game);
		setShoot(new Sound("res/shoot.wav"));

		player = new Player(50, 3100, 32, 48, "pics/vx_chara02_c.png");
		map = new Map("res/1ère map time runner 2.tmx");
		mapWidth = map.getWidth() * map.getTileWidth(); // Map size = Tile Size * number of Tiles
		mapHeight = map.getHeight() * map.getTileHeight();
		camera = new Camera(map, mapWidth, mapHeight);
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException 
	{
		renderGraphics = g;
		camera.translate(g, player);
		map.render(0, 0);
		player.render();
    	
		if (Game.DEBUG)
		{
			g.draw(player.getBox());
			map.drawRect(g);
			g.drawString("Xp : " + player.getX() + ", Yp : " + player.getY(), 10 - camera.getTransX(), 500 - camera.getTransY());
			g.drawString("Xp : " + Math.round(player.getX()/32) + ", Yp : " + Math.round(player.getY()/32), 10 - camera.getTransX(), 550 -  camera.getTransY());
		}
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException 
	{
		player.update(container, mapWidth, mapHeight, delta, map);
	}

	@Override
	public int getID() 
	{
		return GameState.ID;
	}

	/**
	 * @return the shoot
	 */
	public Sound getShoot() {
		return shoot;
	}

	/**
	 * @param shoot the shoot to set
	 */
	public void setShoot(Sound shoot) {
		this.shoot = shoot;
	}
	
	@Override
	public Graphics getGraphics()
	{
		return this.renderGraphics;
	}

}
