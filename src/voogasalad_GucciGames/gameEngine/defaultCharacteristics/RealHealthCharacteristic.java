package voogasalad_GucciGames.gameEngine.defaultCharacteristics;

public class RealHealthCharacteristic extends HealthCharacteristic{

	private double myCurrentHealth;
	private double myMaxHealth;

	public RealHealthCharacteristic(CharacteristicParams charParams) {
		super(charParams);
		double myMaxHP = (double) charParams.getMyMaxNumberOf();
		defineHealthValue(myMaxHP);
	}
	
	@Override
	public void changeHealth(double healthDiff){
		myCurrentHealth -= healthDiff;
	}

	@Override
	public void resetHealth(){
		myCurrentHealth = myMaxHealth;
	}

	@Override
	public double getCurrentHealth(){
		return myCurrentHealth;
	}


	@Override
	public void defineHealthValue(double healthValue) {
		myMaxHealth=healthValue;
		myCurrentHealth= healthValue;

	}
	
	public boolean isDead(){
		return myCurrentHealth <= 0;
	}

}
