package voogasalad_GucciGames.gameAuthoring.model.factories.TypeParser;

public class DoubleParser implements ITypeParser{

	@Override
	public Object parse(String value) {
		// TODO Auto-generated method stub
		return Double.parseDouble(value);
	}

}
