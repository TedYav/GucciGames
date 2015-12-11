package voogasalad_GucciGames.helpers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

import voogasalad.util.fxsprite.Sprite;

public class SpriteDatabase implements ResourceDatabase<Sprite>{

	private ResourceManager myManager;
	private ImageDatabase myImageDatabase;
	private ResourceBundle myConfig = ResourceBundle.getBundle("voogasalad_GucciGames.helpers.config.SpriteDatabase");
	
//	public static void main(String[] args){
//		SpriteDatabase s = new SpriteDatabase(new ResourceManager());
//		System.out.println(s.getDimensions("images/sprites/64x64-2.png"));
//	}
//	
	public SpriteDatabase(ResourceManager manager){
		myManager = manager;
		myImageDatabase = new ImageDatabase();
	}
	
	@Override
	public Sprite request(String URI) {
		//Image sheet = myImageDatabase.request(URI);
		List<Integer> dimensions = getDimensions(URI);
		Sprite sprite = new Sprite(URI, dimensions.get(0), dimensions.get(1));
		return sprite;
	}

	private List<Integer> getDimensions(String URI) {
		LinkedList<String> splitname = split(URI, "/");
		splitname = split(splitname.getLast(), "\\.");
		splitname = split(splitname.getFirst(), "x");
		
		ArrayList<Integer> result = new ArrayList<>();
		result.add(Integer.parseInt(splitname.get(0)));
		
		// in case there's a hyphen
		splitname = split(splitname.getLast(), "-");
		result.add(Integer.parseInt(splitname.get(0)));
		
		return result;
	}

	private LinkedList<String> split(String source, String delim) {
		return new LinkedList<String>(Arrays.asList(source.split(delim)));
	}
	
}
