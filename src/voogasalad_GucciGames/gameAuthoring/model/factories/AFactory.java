package voogasalad_GucciGames.gameAuthoring.model.factories;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.paramObjects.ObjParam;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.paramObjects.ObjParamValue;
import voogasalad_GucciGames.gameAuthoring.model.factories.TypeParser.BooleanParser;
import voogasalad_GucciGames.gameAuthoring.model.factories.TypeParser.DoubleParser;
import voogasalad_GucciGames.gameAuthoring.model.factories.TypeParser.EmptyParser;
import voogasalad_GucciGames.gameAuthoring.model.factories.TypeParser.ITypeParser;
import voogasalad_GucciGames.gameAuthoring.model.factories.TypeParser.IntParser;
import voogasalad_GucciGames.gameAuthoring.model.factories.TypeParser.TypeMap;
import voogasalad_GucciGames.gameEngine.gamePlayer.chars.PlayerMovesPerTurn;
import voogasalad_GucciGames.gameEngine.gamePlayer.chars.PlayerScore;
import voogasalad_GucciGames.gameEngine.gamePlayer.chars.PlayerWealthChar;

/**
 *
 * @author Sally Al
 *
 */
public abstract class AFactory {
	
	private static final Map<String,ITypeParser> myTranslater = new HashMap<>();
	static{
		myTranslater.put("int", new IntParser());
		myTranslater.put("double", new DoubleParser());
		myTranslater.put("String", new StringParser());
		myTranslater.put("boolean", new BooleanParser());
		myTranslater.put("", new EmptyParser());
	}

	public Object create(Map<String, ObjParam> mapObjectParams, ObjParamValue objParamValue)
			throws NoSuchMethodException, SecurityException, ClassNotFoundException, InstantiationException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		// param name to type
		Map<String, String> typeMap = mapObjectParams.get(objParamValue.getName()).getParamTypeMap();
		// order to param name
		Map<Integer, String> orderMap = mapObjectParams.get(objParamValue.getName()).getParamOrderMap();
		// param name to value
		Map<String, String> valueMap = objParamValue.getMap();

		Class<?>[] myParameters = new Class<?>[typeMap.size()];
		for (int i = 0; i < myParameters.length; i++) {
			myParameters[i] = TypeMap.getType(typeMap.get(orderMap.get(i)));
		}

		Object[] initargs = new Object[myParameters.length];

		for (int i = 0; i < initargs.length; i++) {
			Class<?> constr = myParameters[i];
			initargs[i] = (valueMap.get(orderMap.get(i)));
			Object o = getTranslatedValue(typeMap.get(orderMap.get(i)), valueMap.get(orderMap.get(i)));
			if (!o.equals("")) {
				initargs[i] = o;
			} else {
				// SUPER SKETCHY!!!
				Object[] temp = initargs;
				initargs = new Object[temp.length - 1];
				for (int j = 0; j < initargs.length; j++) {
					initargs[j] = temp[j];
				}
				myParameters = new Class<?>[0];
			}
		}

		Constructor<?> c = makeConstractor(objParamValue, myParameters);
		Object myObject = c.newInstance(initargs);
		if (myObject instanceof PlayerScore) {
			System.out.println(((PlayerScore) myObject).getScore());
		}
		if (myObject instanceof PlayerWealthChar) {
			System.out.println(((PlayerWealthChar) myObject).getWealth());
		}
		if (myObject instanceof PlayerMovesPerTurn) {
			System.out.println(((PlayerMovesPerTurn) myObject).getMyNumberOfMoves());
		}
		return myObject;

	}

	protected abstract Constructor makeConstractor(ObjParamValue objParamValue, Class<?>[] myParameters)
			throws NoSuchMethodException, SecurityException, ClassNotFoundException;
	
	private Object getTranslatedValue(String type, String value) {
		return myTranslater.get(type).parse(value);
	}

}
