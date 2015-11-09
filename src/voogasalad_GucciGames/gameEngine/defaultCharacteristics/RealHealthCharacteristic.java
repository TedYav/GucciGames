package voogasalad_GucciGames.gameEngine.defaultCharacteristics;

public class RealHealthCharacteristic extends HealthCharacteristic{

private double myCurrentHealth;
private double myMaxHealth; 


	

	public void changeHealth(double healthDiff){
		myCurrentHealth -= healthDiff;
	}
	
	public void resetHealth(){
		myCurrentHealth = myMaxHealth;
	}
	
	public double getCurrentHealth(){
		return myCurrentHealth;
	}
	
}
