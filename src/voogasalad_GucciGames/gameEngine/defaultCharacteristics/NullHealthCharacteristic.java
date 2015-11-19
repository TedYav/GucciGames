package voogasalad_GucciGames.gameEngine.defaultCharacteristics;

public class NullHealthCharacteristic extends HealthCharacteristic {

	public NullHealthCharacteristic(CharacteristicParams charParams) {
		super(charParams);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void changeHealth(double healthDiff) {
		// do nothing
		return;
	}

	@Override
	public void resetHealth() {
		// do nothing
		return;
	}

	@Override
	/**
	 * Returns a NaN type
	 */
	public double getCurrentHealth() {
		return Double.NaN;
	}

	@Override
	public void defineHealthValue(double healthValue) {
		// do not do anything

	}

}
