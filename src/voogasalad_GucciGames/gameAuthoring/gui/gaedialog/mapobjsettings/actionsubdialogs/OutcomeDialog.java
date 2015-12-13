package voogasalad_GucciGames.gameAuthoring.gui.gaedialog.mapobjsettings.actionsubdialogs;

import java.util.List;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import voogasalad_GucciGames.gameAuthoring.IDialogGaeController;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.dialogcomponents.DropDownMenuField;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.maindialogs.AGaeDialog;
import voogasalad_GucciGames.gameAuthoring.model.MapObjectType;

public class OutcomeDialog extends AGaeDialog<String> {
	
	private GridPane myGridPane;
	
	private Label title  = new Label("Add an Outcome to Action");
	
	private Button addOutcome = new Button("Add a Outcome");
	
	private DropDownMenuField outcomesMenuField;
	
	private MapObjectType type;
	
	private IDialogGaeController controller;
	
	
	public OutcomeDialog(IDialogGaeController controller, 
			List<String> outcomes, MapObjectType type){
		
		super();
		
		myGridPane = new GridPane();
		
		this.type = type;
		
		this.title.setFont(new Font("Arial", 20));
		
		this.outcomesMenuField = new DropDownMenuField(outcomes);
		
		final ButtonType saveBtn = new ButtonType("Save Outcome & Next", ButtonData.NEXT_FORWARD);
		
		this.getDialogPane().getButtonTypes().add(saveBtn);
		setContent();
		
		this.getDialogPane().setContent(myGridPane);
		
		Node saveBtnNode = getDialogPane().lookupButton(saveBtn);
		
		saveBtnNode.setDisable(outcomesMenuField.getSelected().equals(""));
		
		outcomesMenuField.getDropDown().valueProperty().addListener((ob, oV, nV) -> {
			saveBtnNode.setDisable(nV.trim().isEmpty());
		});

		
		this.setResultConverter(dialogButton -> {
			if(dialogButton == saveBtn){
				if (!outcomesMenuField.getSelected().equals("")){
					System.out.println("selected outcome: " +outcomesMenuField.getSelected() );
					return outcomesMenuField.getSelected();
				}			
			}
			return null;
		});
		
	}
	

	
	private void setContent(){
		myGridPane.getChildren().add(title);
		myGridPane.add(outcomesMenuField, 0, 1);
		
		
	}

	@Override
	protected void setSaveAction() {
		// TODO Auto-generated method stub
		
	}
	
	
	

}
