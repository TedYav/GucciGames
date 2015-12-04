package voogasalad_GucciGames.gameEngine.defaultCharacteristics;

public class RealHealthCharacteristic extends HealthCharacteristic{

	private double myCurrentHealth;
	private double myMaxHealth;

	
	public RealHealthCharacteristic(double maxHP){
		defineHealthValue(maxHP);
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

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Current Health = " + myCurrentHealth;
	}

}
