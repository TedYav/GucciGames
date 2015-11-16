package voogasalad_GucciGames.gameAuthoring.gui.sidebar;

import java.util.Arrays;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import voogasalad_GucciGames.gameAuthoring.IGuiGaeController;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.StructureMaker;


public class StructureTab extends AbstractTab {

	public StructureTab(IGuiGaeController controller) {
		super(controller);
		setText("Structures");
		allImagePaths = Arrays.asList("voogasalad_GucciGames/graphics/water.png");
		addImages();
		addImageHandler();
//		addDragDropListener();
		addAddButtonListener();
	}
	
	@Override
	public void update() {

	}
	
	@Override
	protected void addAddButtonListener() {
		myAddButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override public void handle(ActionEvent e) {
				StructureMaker structMaker = new StructureMaker();
//				structMaker.showStructureMakerDialog();
			}
		});
	}

}
