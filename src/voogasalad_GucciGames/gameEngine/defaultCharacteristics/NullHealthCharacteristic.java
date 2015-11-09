package voogasalad_GucciGames.gameEngine.defaultCharacteristics;

public class NullHealthCharacteristic extends HealthCharacteristic{

	@Override
	public void changeHealth(double healthDiff) {
//do nothing
		return;
	}

	@Override
	public void resetHealth() {
//do nothing
		return;
	}

	@Override
	/**
	 * Returns a NaN type
	 */
	public double getCurrentHealth() {
		// TODO Auto-generated method stub
		return Double.NaN;
	}

	
}
