package voogasalad.util.cloud.data;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class CloudScore extends CloudObject implements GameScore{

	private String myPlayerName;
	private Double myScore;
	private String myTitle;
	
	public CloudScore(Map<String, String> map){
		this(map.get(CloudVariable.GAMENAME.getValue()), map.get(CloudVariable.PLAYERNAME.getValue()), Double.parseDouble(map.get(CloudVariable.SCORE.getValue())), map.get(CloudVariable.TITLE.getValue()));
		
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
		setParameter(CloudVariable.GAMENAME.getValue(), getGameName());
		setParameter(CloudVariable.PLAYERNAME.getValue(), myPlayerName);
		setParameter(CloudVariable.SCORE.getValue(), myScore.toString());
	}

	@Override
	public Double getScore(){
		return myScore;
	}
	
	@Override
	public String getTitle(){
		return myTitle;
	}
	
	@Override
	public String getPlayerName(){
		return myPlayerName;
	}
	
	@Override
	public String toString(){
		return myPlayerName + " -- " + myScore;
	}

	@Override
	public CloudParameter requestString(){
		return new CloudParameter(CloudVariable.ACTION, CloudVariable.HIGHSCORE);
	}
	
	@Override
	public List<String> getFields(){
		return Arrays.asList(CloudVariable.GAMENAME.getValue(), CloudVariable.PLAYERNAME.getValue(), CloudVariable.SCORE.getValue(), CloudVariable.TITLE.getValue());
	}

	@Override
	public CloudScore cloneFromTemplate(Map<String, String> template) {
		return new CloudScore(template);
	}
}
