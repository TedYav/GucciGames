package voogasalad_GucciGames.gameAuthoring.gui.sidebar;

import java.util.Arrays;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import voogasalad_GucciGames.gameAuthoring.IGuiGaeController;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.NewObjectMaker;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.StructMakerCustomContent;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.ISaveCustomObj;

public class StructureTab extends AbstractTab {
	private ISaveCustomObj saveCustomObj;

	public StructureTab(IGuiGaeController controller, ISaveCustomObj saveCustomObj) {
		super(controller);
		this.saveCustomObj = saveCustomObj;
		setText("Structures");
		allImagePaths = Arrays.asList("voogasalad_GucciGames/graphics/water.png");
		addImages();
		addImageHandler();
//		addDragDropListener();
		addAddButtonListener();
	}
	
	@Override
	protected void addAddButtonListener() {
		myAddButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override public void handle(ActionEvent e) {
				NewObjectMaker structMaker = new NewObjectMaker(new StructMakerCustomContent(), myController);
				structMaker.showDialog();
			}
		});
	}

}
