package voogasalad_GucciGames.gameAuthoring.gui.sidebar;

import javafx.event.ActionEvent;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.maindialogs.NewObjMakerDialog;
public class StructureTab extends ATab {
	private static final String TYPE = "structure";

	public StructureTab(SideBar bar) {
		super(bar);
		setText("Structures");
		//init(myController.getImmutableStructureTypes());
	}

	@Override
	protected void addNewTypeDialog(ActionEvent e) {
		NewObjMakerDialog structMaker = new NewObjMakerDialog(myController, TYPE);
		structMaker.showAndWait();
	}

}
