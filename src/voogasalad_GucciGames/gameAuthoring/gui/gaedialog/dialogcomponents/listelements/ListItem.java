package voogasalad_GucciGames.gameAuthoring.gui.gaedialog.dialogcomponents.listelements;

import javafx.geometry.Insets;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.dialogcomponents.DialogComponent;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.stylesheets.IListView;


public abstract class ListItem extends DialogComponent{
	
	public ListItem(){
		this.setSpacing(20);
		this.setPadding(new Insets(0, 20, 10, 20)); 
	}
	
	public abstract void actionForRemoveBtn(IListView listViewInterface);
	
	public abstract String getName();
	



}
