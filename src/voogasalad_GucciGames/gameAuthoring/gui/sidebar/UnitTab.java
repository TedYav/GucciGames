package voogasalad_GucciGames.gameAuthoring.gui.sidebar;

import javafx.event.ActionEvent;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.NewObjectMaker;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.UnitMakerCustomContent;

public class UnitTab extends ATab {
	
	public UnitTab(SideBar bar) {
		super(bar);
		setText("Units");
		init(myController.getImmutableUnitTypes());
	}

	@Override
	protected void addNewTypeDialog(ActionEvent e) {
		NewObjectMaker unitMaker = new NewObjectMaker(new UnitMakerCustomContent(), myController);
		unitMaker.showDialog();
	}

}
