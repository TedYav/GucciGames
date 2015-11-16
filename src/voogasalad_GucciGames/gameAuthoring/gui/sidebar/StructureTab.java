package voogasalad_GucciGames.gameAuthoring.gui.sidebar;

import java.util.Arrays;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import voogasalad_GucciGames.gameAuthoring.IGuiGaeController;


public class StructureTab extends AbstractTab {

	public StructureTab(IGuiGaeController controller) {
		super(controller);
		setText("Structures");
		allImagePaths = Arrays.asList("voogasalad_GucciGames/graphics/water.png");
		addImages();
		addImageHandler();
		addDragDropListener();
	}
	
	@Override
	public void update() {

	}
	
	@Override
	protected void addAddButtonListener() {
		// TODO Auto-generated method stub
		myAddButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override public void handle(ActionEvent e) {
				//dialog
			}
		});
	}

	@Override
	protected void addPlayerOwnership() {
		// TODO Auto-generated method stub
		
	}

}
