package voogasalad_GucciGames.gameEngine.mapObject;

public abstract class AMapObjectCharacteristic implements Cloneable{
	
	public AMapObjectCharacteristic clone() {
        try {
			return (AMapObjectCharacteristic) super.clone();
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
    }
}
