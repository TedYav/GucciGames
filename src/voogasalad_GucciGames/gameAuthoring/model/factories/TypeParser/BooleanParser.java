package voogasalad_GucciGames.gameAuthoring.model.factories.TypeParser;

public class BooleanParser implements ITypeParser{

	@Override
	public Object parse(String value) {
		// TODO Auto-generated method stub
		return Boolean.parseBoolean(value);
	}

}
