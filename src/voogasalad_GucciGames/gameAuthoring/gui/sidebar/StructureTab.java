package voogasalad_GucciGames.gameAuthoring.gui.sidebar;

import javafx.event.ActionEvent;
import voogasalad_GucciGames.gameAuthoring.IGuiGaeController;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.NewObjectMaker;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.StructMakerCustomContent;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.ISaveCustomObj;

public class StructureTab extends ATab {
	private ISaveCustomObj saveCustomObj;

	public StructureTab(SideBar bar, ISaveCustomObj saveCustomObj) {
		super(bar);
		this.saveCustomObj = saveCustomObj;
		setText("Structures");
		//init(myController.getImmutableStructureTypes());
	}

	@Override
	protected void addNewTypeDialog(ActionEvent e) {
		NewObjectMaker structMaker = new NewObjectMaker(new StructMakerCustomContent(), myController);
		structMaker.showDialog();
	}

}
