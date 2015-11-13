
package voogasalad_GucciGames.gameEngine.gameRule.Goal;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Sally Al
 * @use case #1
 *
 */
public class Goal {
	protected Integer myId =0;
	protected Map<String, Double> ConditionRequirements = new HashMap<String, Double>();
	private String myStatus;
	public void addRequirement(List<String> names, List<Double> values){
		//this method is called every time a user enters a condition for a goal.
		//this method will populate requirements map with the name of the variables
		// and their target value to achieve a goal.
		// the key of the map will be the condition id, which will increments after each
		//time this method is called

	}

	public void checkSatisfied(){
		//this method will loop over the map of conditions required to achieve a goal
		//if a condition is met, then  myStatus  will be: win,lost, or draw
		// if none of the conditions is met,myStatus= goalNotAchieved.
	}

	public String getMyStatus() {
		return myStatus;
	}


}
