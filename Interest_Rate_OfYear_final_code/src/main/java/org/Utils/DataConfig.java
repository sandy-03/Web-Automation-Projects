package org.Utils;


import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;



public class DataConfig {
	private static FileInputStream fis;
	private static Properties prop;
	
	
	
	static String fileName = System.getProperty("user.dir")+"//Test_data//config.properties";
	
	public static void configFile() throws IOException {
		fis = new FileInputStream(fileName);
		prop = new Properties();
        prop.load(fis);	
	}
	
	public static String URL() throws IOException {
		configFile();
		return prop.getProperty("URL");
	}
	
}