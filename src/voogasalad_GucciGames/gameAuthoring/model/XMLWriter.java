package voogasalad_GucciGames.gameAuthoring.model;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import voogasalad_GucciGames.gameEngine.GameLevelEngine;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class XMLWriter {
	
	public void write(GameLevelEngine engine) {
		XStream serializer = new XStream(new DomDriver());
		String engineLocation = "./examples/SavedGames/newengine.xml";
		
		String engineXML = serializer.toXML(engine); // saved XML File should have current turn as 2
		
		File file = new File(engineLocation);
		try {
			BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
			bufferedWriter.write(engineXML);
			bufferedWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}

}
