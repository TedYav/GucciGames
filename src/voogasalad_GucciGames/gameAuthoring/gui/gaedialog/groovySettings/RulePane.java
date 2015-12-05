package voogasalad_GucciGames.gameAuthoring.gui.gaedialog.groovySettings;

import javafx.geometry.HPos;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;

public class RulePane extends GridPane{
	
	private Label nameLbl;
	private Label ruleLbl = new Label("Rule");
	private TextArea rule = new TextArea();

	
	public RulePane(String name){
		nameLbl = new Label(name + " Action");
		nameLbl.setFont( new Font("Arial", 20));
		this.setHalignment(nameLbl, HPos.RIGHT);
		this.setHgap(5);
		this.setVgap(5);
		this.add(nameLbl, 0, 0);
		init();		
	}
	
	private void init(){
		
	}
	

}
