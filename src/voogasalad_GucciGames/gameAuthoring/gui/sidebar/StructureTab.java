package voogasalad_GucciGames.gameAuthoring.gui.sidebar;

import javafx.event.ActionEvent;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.maindialogs.NewObMakerDialog;
public class StructureTab extends ATab {
	private static final String TYPE = "structure";

	public StructureTab(SideBar bar) {
		super(bar);
		setText("Structures");
		//init(myController.getImmutableStructureTypes());
	}

	@Override
	protected void addNewTypeDialog(ActionEvent e) {
		NewObMakerDialog structMaker = new NewObMakerDialog(myController, TYPE);
		structMaker.showAndWait();
	}

}
