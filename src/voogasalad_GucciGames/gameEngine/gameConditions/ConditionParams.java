
package voogasalad_GucciGames.gameEngine.gameConditions;

import java.util.List;

/**
 *
 * @author Sally Al
 *
 */
public class ConditionParams {

	private String myName;
	private String myType;
	private List<Object> myArgs;

	public ConditionParams(String name, String type, List<Object> args) {
		myName = name;
		myType = type;
		myArgs = args;
	}

	protected String getName() {
		return myName;
	}

	protected String getType() {
		return myType;
	}

	protected List<Object> getArgs() {
		return myArgs;
	}

}
