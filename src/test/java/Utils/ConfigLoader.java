package Utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ConfigLoader {
	
	public static String sendMessage;
	public static String retrieveMessage;
	public static String auth_id;
	public static String auth_token;
	public static String accountInfo;
	
	
	static {
		sendMessage = getPropertyValue("sendMessage", sendMessage);
		retrieveMessage = getPropertyValue("retrieveMessage",retrieveMessage);
		auth_id = getPropertyValue("auth_id", auth_id);
		auth_token = getPropertyValue("auth_token",auth_token);
		accountInfo = getPropertyValue("accountInfo", accountInfo);
	}

	private static String getPropertyValue(String propertyName, String defaultvalue) {
		Properties properties = getAutomationConfiguration();
		return properties.getProperty(propertyName, defaultvalue);
	}

	private static Properties getAutomationConfiguration() {
		try {
			File e = new File("config.properties");
			FileInputStream fileInput = new FileInputStream(e);
			Properties properties = new Properties();
			properties.load(fileInput);
			fileInput.close();
			return properties;
		} catch (FileNotFoundException var3) {
			var3.printStackTrace();
		}catch (IOException var4) {
			var4.printStackTrace();
		}

		return null;
	}

}