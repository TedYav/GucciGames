package XStreamExample;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import voogasalad_GucciGames.gameEngine.MainGameEngine;

public class XStreamGameEngine {
	
	@SuppressWarnings("resource")
	public static void main(String[] args0){
		XStream serializer = new XStream(new DomDriver());
		String currentTurn = "Current Turn: ";
		String engineLocation = "./examples/XStreamExample/engine.xml";
		
		System.out.println("Creating and saving engine.");
		try {
			MainGameEngine engine = new MainGameEngine(null,null,null);
			
			System.out.println(currentTurn + engine.getCurrentTurn()); // current turn should be 1
			engine.incrementCurrentTurn(); 
			System.out.println(currentTurn + engine.getCurrentTurn()); // current turn should be 2
			
			String engineXML = serializer.toXML(engine); // saved XML File should have current turn as 2
			
			File file = new File(engineLocation);
			BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
			bufferedWriter.write(engineXML);
			bufferedWriter.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Save Complete.");
		
		System.out.println("Loading engine.");
		try {
			File file = new File(engineLocation);
			BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
			StringBuilder engineXMLBuilder = new StringBuilder();
			bufferedReader.lines().forEach(line->engineXMLBuilder.append(line));
			
			 // loaded XML File should have current turn as 2
			MainGameEngine engine = (MainGameEngine) serializer.fromXML(engineXMLBuilder.toString());
			
			System.out.println(currentTurn + engine.getCurrentTurn()); // current turn should be 2
			engine.incrementCurrentTurn(); 
			System.out.println(currentTurn + engine.getCurrentTurn()); // current turn should be 3
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Load complete.");
	}

}
