package voogasalad_GucciGames.gameAuthoring.gui.sidebar;

import javafx.event.ActionEvent;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.maindialogs.NewObjectMaker;
public class StructureTab extends ATab {

	public StructureTab(SideBar bar) {
		super(bar);
		setText("Structures");
		//init(myController.getImmutableStructureTypes());
	}

	@Override
	protected void addNewTypeDialog(ActionEvent e) {
		NewObjectMaker structMaker = new NewObjectMaker( myController);
		structMaker.showDialog();
	}

}
