package voogasalad_GucciGames.gameEngine.gameRules;

import java.util.List;
import java.util.Map;

/**
 *
 * @author Sally Al
 *
 */
public class RuleParams {
	private String myName;
	private String myActionName;
	private Map<String, Object> myArgs;
	private List<String> myUnitTypes;

	public RuleParams(String actionName, Map<String, Object> args, List<String> units) {
		myActionName = actionName;
		myName = this.getClass().getSimpleName();
		myArgs = args;
		myUnitTypes = units;
	}

	public String getRuleName() {
		return myName;
	}
	public String getActionName() {
		return myActionName;
	}

	public Map<String, Object> getArgs() {
		return myArgs;
	}

	public List<String> getunitType() {
		return myUnitTypes;
	}

}
