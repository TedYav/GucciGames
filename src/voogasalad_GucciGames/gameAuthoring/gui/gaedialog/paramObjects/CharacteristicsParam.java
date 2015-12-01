package voogasalad_GucciGames.gameAuthoring.gui.gaedialog.paramObjects;

public class CharacteristicsParam {

	private String name;
	private double param;
	public CharacteristicsParam (String name, double param){
		this.setName(name);
		this.setParam(param);
		
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getParam() {
		return param;
	}
	public void setParam(double param) {
		this.param = param;
	}
}
