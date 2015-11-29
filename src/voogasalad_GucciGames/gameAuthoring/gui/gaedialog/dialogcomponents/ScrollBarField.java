package voogasalad_GucciGames.gameAuthoring.gui.gaedialog.dialogcomponents;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.DialogElements;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public class ScrollBarField extends DialogComponent {
	
	private DialogElements dialogElements;
	private String propKey;
	private String itemsKey;
	private ScrollBar scrollBar = new ScrollBar();
	private Text numSpriteText = new Text(Double.toString(scrollBar.getValue()));
	
	public ScrollBarField(DialogElements dialogElements, String propKey, String itemsKey){
		this.dialogElements = dialogElements;
		this.propKey = propKey;
		this.itemsKey = itemsKey;
		makeScrollBar();
		
	}
	
	protected void makeScrollBar(){
		Text title = new Text(dialogElements.getDialogProperties().getProperty(propKey));
		List<String> params = super.parseStringToList(dialogElements.getDialogProperties(), itemsKey);
		scrollBar.setMin(Double.parseDouble(params.get(0)));
		scrollBar.setMax(Double.parseDouble(params.get(1)));
		scrollBar.setUnitIncrement(Integer.parseInt(params.get(2)));
		addListenerToScrollBar();
		this.getChildren().addAll(title, scrollBar, numSpriteText);
		this.setId("hbox-element");
	}
	
	private void addListenerToScrollBar(){
		scrollBar.valueProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable,
					Number oldValue, Number newValue) {
				// TODO Auto-generated method stub
				//TODO: REFACTOR
				if(propKey == "numplayer"){
					dialogElements.getDialogGaeController().setNumberOfPlayers(newValue.intValue());
				}
				numSpriteText.setText(newValue.intValue()+"");
				//dialogElements.getSaveObjProperty().saveObjProperty(propKey, newValue.intValue()+"");				
			}			
		});
	}


	@Override
	public void setSelected(String s) {
		// TODO Auto-generated method stub
		scrollBar.setValue(Double.parseDouble(s));
	}
	
	@Override
	public String getSelected(){
		return null;
		
	}
	
	public double getSelectedDouble(){
		return scrollBar.getValue();
	}

}
