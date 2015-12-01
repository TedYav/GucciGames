package voogasalad_GucciGames.gameAuthoring.gui.gaedialog.paramObjects;

public class CharacteristicsParam {

	private String name;
	private String displayName;
	private double param;
	private double min;
	private double max;
	
	public CharacteristicsParam(String name){
		this.setName(name);
	}
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
	public double getMin() {
		return min;
	}
	public void setMin(String min) {
		this.min = Double.parseDouble(min);
	}
	public double getMax() {
		return max;
	}
	public void setMax(String max) {
		this.max = Double.parseDouble(max);
	}
	public String getDisplayName() {
		return displayName;
	}
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	
	public void print(){
		System.out.println("display name:" + this.displayName);
	}

}
