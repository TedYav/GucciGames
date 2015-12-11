package voogasalad_GucciGames.gameEngine.mapObject;

import java.lang.reflect.Field;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

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
	
	@Override
	public String toString() {
		Field[] fields = this.getClass().getDeclaredFields();
		ScriptEngineManager manager = new ScriptEngineManager();
		ScriptEngine groovy = manager.getEngineByName("groovy");
		groovy.put("prop", this);
		String result = "";
		for(Field field: fields){
			String var = field.getName();
			try {
				result += var + " = " + groovy.eval("prop.get"+var+"();") + "\n";
			} catch (ScriptException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result.trim();
	}

	/*
	public static void main(String[] args){
		AMapObjectCharacteristic health = new HealthCharacteristic();
		System.out.println(health.toString());
		
		AMapObjectCharacteristic attack = new AttackCharacteristic(5,5,5);
		System.out.println(attack.toString());
	}
	*/
}
