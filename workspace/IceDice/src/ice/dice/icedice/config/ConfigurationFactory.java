package ice.dice.icedice.config;

import ice.dice.icedice.IceDiceConstants;
import ice.dice.icedice.domain.IceDice;
import ice.dice.icedice.ui.swing.IceDiceFrame;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigurationFactory {
	private static ConfigurationFactory singleton;
	private Properties properties;
	private String propFilename = "icedice.conf";
	
	public static ConfigurationFactory getInstance() {
		if (singleton == null) {
			singleton = new ConfigurationFactory();
		}
		return singleton;
	}
	
	/**
	 * Gets the application's properties from a properties file.
	 * 
	 * @return
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	public String getProperty(String name) {
		if (properties == null) {
			properties = new Properties();
			try {
				properties.load(new FileInputStream(propFilename));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return properties.getProperty(name);
	}
	
	public void setProperty(String name, String prop) {
		properties.setProperty(name, prop);
	}
	
	/**
	 * Saves the properties into the same properties file.
	 */
	public void saveProperties() {
		if (properties != null) {
			properties.setProperty(IceDiceConstants.GAME, IceDice.getInstance().getGameName());
			properties.setProperty(IceDiceConstants.XPOS, "" + IceDiceFrame.getInstance().getLocation().x);
			properties.setProperty(IceDiceConstants.YPOS, "" + IceDiceFrame.getInstance().getLocation().y);
			try {
				properties.store(new FileOutputStream(propFilename), "IceDice config file - Do not modify by hand");
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
