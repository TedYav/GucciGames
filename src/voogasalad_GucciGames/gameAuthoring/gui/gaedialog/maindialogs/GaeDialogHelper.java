package voogasalad_GucciGames.gameAuthoring.gui.gaedialog.maindialogs;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;


public class GaeDialogHelper{

	public Properties loadProperties(String path){
		Properties prop = new Properties();
		InputStream input = getClass().getResourceAsStream(path);
		if(input == null){
			System.out.println("is null");
		}
		try {
			prop.load(input);		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return prop;		
	}
	
	public List<String> parseStringToList(Properties prop, String itemsKey){
		String items = prop.getProperty(itemsKey);	
		List<String> propertiesList = Arrays.asList(items.split("\\s*,\\s*"));	
		return propertiesList;		
	}
	
	public List<String> parseStringToList(String items){
		List<String> propertiesList = Arrays.asList(items.split("\\s*,\\s*"));	
		return propertiesList;		
	}


}
