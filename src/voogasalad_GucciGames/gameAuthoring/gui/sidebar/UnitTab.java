package voogasalad_GucciGames.gameAuthoring.gui.sidebar;

import java.util.Arrays;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import voogasalad_GucciGames.gameAuthoring.IGuiGaeController;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.NewObjectMaker;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.ISaveCustomObj;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.UnitMakerCustomContent;



public class UnitTab extends AbstractTab {
	private ISaveCustomObj saveCustomObj;
	
	public UnitTab(IGuiGaeController controller, ISaveCustomObj saveCustomObj) {
		super(controller);
		this.saveCustomObj = saveCustomObj;
		setText("Units");
		allImagePaths = Arrays.asList("voogasalad_GucciGames/graphics/fire.png", "voogasalad_GucciGames/graphics/water.png");
		addImages();
		addImageHandler();
		addAddButtonListener();
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
	}

	@Override
	protected void addAddButtonListener() {
		// TODO Auto-generated method stub
		myAddButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override public void handle(ActionEvent e) {
				NewObjectMaker unitMaker = new NewObjectMaker(new UnitMakerCustomContent(), myController);
				unitMaker.showDialog();
			}
		});
	}

}
