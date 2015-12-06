package voogasalad_GucciGames.gameAuthoring.gui.gaedialog.groovySettings;

import java.util.Optional;

import javafx.scene.control.ButtonType;
import javafx.scene.layout.GridPane;

public interface ISwitchGroovyPane {
	
	public void switchGroovyPane(Object p, String title);
	
	public void addBtn( ButtonType t);
	
	public Optional<ButtonType> getRet();
	
	public void setNextPane(GridPane p, String title);


}
