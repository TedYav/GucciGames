package voogasalad_GucciGames.gameAuthoring.gui.gaedialog.dialogcomponents.listelements;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.dialogcomponents.DialogComponent;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.stylesheets.IListView;


public abstract class ListItem extends DialogComponent{
	
	public abstract void actionForRemoveBtn(IListView listViewInterface);
	
	public abstract String getName();
	



}
