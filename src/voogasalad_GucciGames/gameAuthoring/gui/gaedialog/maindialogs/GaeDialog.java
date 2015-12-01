package voogasalad_GucciGames.gameAuthoring.gui.gaedialog.maindialogs;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;

import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.ISaveGroovy;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

abstract public class GaeDialog{	
	public GaeDialog(){

	}

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

	protected abstract VBox initializeDialog();


	public void showDialog(Stage stage){
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.show();
	}


	protected ISaveGroovy setSaveGroovyFunctions(Map<String, String> groovyBuffer2, ISaveGroovy saveGroovy){		 
		saveGroovy = (str, strName) -> {
			groovyBuffer2.put(strName, str);
			//debug
			groovyBuffer2.forEach((k, v) -> System.out.println(" " + k + " " + v));
			System.out.println("---------");
		};	
		return saveGroovy;

	}



}
