package voogasalad_GucciGames.gameEngine.defaultCharacteristics;

public class NullHealthCharacteristic extends HealthCharacteristic {

	public NullHealthCharacteristic() {
		CharacteristicParams charParams = new CharacteristicParams();
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

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "";
	}

}
