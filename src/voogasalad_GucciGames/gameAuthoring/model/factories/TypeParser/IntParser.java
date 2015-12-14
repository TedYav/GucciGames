package voogasalad_GucciGames.gameAuthoring.model.factories.TypeParser;

public class IntParser implements ITypeParser{

	@Override
	public Object parse(String value) {
		// TODO Auto-generated method stub
		return Integer.parseInt(value);
	}

}
