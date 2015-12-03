package voogasalad.util.cloud.data;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.function.Supplier;

public class CloudScore extends CloudObject{

	private String myPlayerName;
	private Double myScore;
	private String myTitle;
	
	public CloudScore(Map<String, String> map){
		this(map.get("gamename"), map.get("playername"), Double.parseDouble(map.get("score")), map.get("title"));
	}
	
	public CloudScore(String gameName){
		this(gameName, "", 0);
	}
	
	public CloudScore(String gameName, String playerName, double score) {
		this(gameName, playerName, score, "");
		
	}
	
	public CloudScore(String gameName, String playerName, double score, String title) {
		super(gameName);
		myPlayerName = playerName;
		myScore = score;
		myTitle = title;
		setParameter("gamename", getGameName());
		setParameter("playername", myPlayerName);
		setParameter("score", myScore.toString());
	}

	public Double getScore(){
		return myScore;
	}
	
	public String getTitle(){
		return myTitle;
	}
	
	public String getPlayerName(){
		return myPlayerName;
	}

	@Override
	public CloudParameter requestString(){
		return new CloudParameter("action", "highscore");
	}
	
	@Override
	public List<String> getFields(){
		return Arrays.asList("gamename", "playername", "score", "title");
	}

	@Override
	public CloudScore cloneFromTemplate(Map<String, String> template) {
		return new CloudScore(template);
	}
}
