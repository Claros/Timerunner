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

/**
 * The Class GameState.
 */
public class GameState extends GlobalState
{
	public static final int ID = 1;
    
    private int mapHeight, mapWidth;    
    private Player player;
    private Camera camera;
	private Sound shoot;
    private Map map;
    private Graphics renderGraphics;

	/* (non-Javadoc)
	 * @see com.timerunner.states.GlobalState#init(org.newdawn.slick.GameContainer, org.newdawn.slick.state.StateBasedGame)
	 */
	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException 
	{
		super.init(container, game);
		this.shoot = new Sound("res/shoot.wav");

		player = new Player(50, 3100, 32, 48, "pics/vx_chara02_c.png");
		map = new Map("res/map_moyen_age_exterieur.tmx");
		mapWidth = map.getWidth() * map.getTileWidth(); // Map size = Tile Size * number of Tiles
		mapHeight = map.getHeight() * map.getTileHeight();
		camera = new Camera(map, mapWidth, mapHeight);
	}

	/* (non-Javadoc)
	 * @see org.newdawn.slick.state.GameState#render(org.newdawn.slick.GameContainer, org.newdawn.slick.state.StateBasedGame, org.newdawn.slick.Graphics)
	 */
	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException 
	{
		renderGraphics = g;
		camera.translate(g, player);
		for (int i = 0; i < map.getLayerCount()-1; i++)
		{
			map.render(0, 0, i);
		}
		player.render();
		
		map.render(0, 0, map.getLayerCount()-1);
    	
		/*
		 * Si on est en mode DEBUG, on affiche les rectangles pour les collisions,
		 * les FPS ainsi que les coordonnées du perso.
		 */
		if (Game.DEBUG)
		{
			g.draw(player.getBox());
			map.drawRect(g);
			g.drawString("Xp : " + player.getX() + ", Yp : " + player.getY(), 10 - camera.getTransX(), 500 - camera.getTransY());
			g.drawString("Xp : " + Math.round(player.getX()/32) + ", Yp : " + Math.round(player.getY()/32), 10 - camera.getTransX(), 550 -  camera.getTransY());
		}
	}

	/* (non-Javadoc)
	 * @see org.newdawn.slick.state.GameState#update(org.newdawn.slick.GameContainer, org.newdawn.slick.state.StateBasedGame, int)
	 */
	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException 
	{
		player.update(container, mapWidth, mapHeight, delta, map);
	}

	/* (non-Javadoc)
	 * @see com.timerunner.states.GlobalState#getID()
	 */
	@Override
	public int getID() 
	{
		return GameState.ID;
	}

	/**
	 * Gets the shoot sound.
	 *
	 * @return the shoot sound
	 */
	public Sound getShoot() 
	{
		return shoot;
	}
	
	/* (non-Javadoc)
	 * @see com.timerunner.states.GlobalState#getGraphics()
	 */
	@Override
	public Graphics getGraphics()
	{
		return this.renderGraphics;
	}

}
