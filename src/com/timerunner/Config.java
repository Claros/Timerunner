package com.timerunner;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
//import java.io.FileNotFoundException;
//import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public enum Config 
{
	KEY_RIGHT("right", 205), 
	KEY_LEFT("left", 203),
	KEY_UP("up", 200), 
	KEY_DOWN("down", 208),
	KEY_TALK("talk", 45);

	private String property;
	private int value;
	private static Properties prop = new Properties();
	
	Config(final String pProperty, final int pValue)
	{
		this.value = pValue;
		this.property = pProperty;
	}
	
	/**
	 * @return the value
	 */
	public int getValue() {
		return value;
	}

	/**
	 * @param value the value to set
	 */
	public void setValue(int value) {
		this.value = value;
	}
	
	public String getProperty()
	{
		return this.property;
	}
	
	public static void init()
	{
		try 
		{
			prop.load(new FileInputStream("./config.properties"));
			
			for (final Config config : values())
			{
				config.setValue( Integer.parseInt( prop.getProperty(config.getProperty()) ) );
			}
		} catch (FileNotFoundException ex) 
		{
			try 
			{
				// Si on ne trouve pas le fichier, on le créé
				prop.setProperty("right", "205");
				prop.setProperty("left", "203");
				prop.setProperty("up", "200");
				prop.setProperty("down", "208");
				prop.setProperty("talk", "45");
				
				prop.store(new FileOutputStream("./config.properties"), null);
			} catch (IOException e)
			{
				
			}
	    } catch (IOException pe)
	    {
	    	// On ne touche pas à la config
	    }
	}

}
