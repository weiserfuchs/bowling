package gui;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class Config {

	private Properties	configFile;
	private String		cfgpath;

	public Config(String path) {
		cfgpath = path;
		configFile = new Properties();
		FileReader fileReader;
		
		try {
			fileReader = new FileReader(new File(cfgpath));
			configFile.load(fileReader);
		} catch (SecurityException eta) {
			eta.printStackTrace();
		} catch (IOException eta) {
			eta.printStackTrace();
		} catch (Exception eta) {
			eta.printStackTrace();
		}
	}

	public String getProperty(String key) {
		String value = this.configFile.getProperty(key);
		return value;
	}
}