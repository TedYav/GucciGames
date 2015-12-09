package voogasalad_GucciGames.gameAuthoring.gui.sidebar;

import javafx.event.ActionEvent;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.maindialogs.NewObjMakerDialog;

public class UnitTab extends ATab {
	private static final String TYPE = "unit";
	
	public UnitTab(SideBar bar) {
		super(bar, TYPE);
		setText("Units");
		init(myController.getImmutableUnitTypes());
	}

	@Override
	protected void addNewTypeDialog(ActionEvent e) {
		NewObjMakerDialog unitMaker = new NewObjMakerDialog( myController, TYPE);
		unitMaker.showAndWait();
	}

}
