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
	
	private Properties prop;
	private String propKey;
	private String itemsKey;
	private ScrollBar scrollBar = new ScrollBar();
	private Text numSpriteText = new Text(Double.toString(scrollBar.getValue()));
	
	public ScrollBarField(Properties prop, String propKey, String itemsKey){
		this.prop = prop;
		this.propKey = propKey;
		this.itemsKey = itemsKey;
		List<String> params = super.parseStringToList(prop, itemsKey);
		makeScrollBar(prop.getProperty(propKey),
				Double.parseDouble(params.get(0)),
				Double.parseDouble(params.get(1)),
				Double.parseDouble(params.get(2))
				);
		
	}
	
	public ScrollBarField(String name, double min, double max, double increm){
		makeScrollBar(name, min, max, increm);
		
	}
	
	protected void makeScrollBar(String name, double min, double max, double increm){
		Text title = new Text(name);
		scrollBar.setMin(min);
		scrollBar.setMax(max);
		scrollBar.setUnitIncrement(increm);
		addDefaultListener();
		this.add(title, 0, 0);
		this.add(scrollBar, 1, 0);
		this.add(numSpriteText, 2, 0);
	
	}
	
	private void addDefaultListener(){
		scrollBar.valueProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable,
					Number oldValue, Number newValue) {
				numSpriteText.setText(newValue.intValue()+"");
				}			
		});
		
	}
	
	public void addListenerForPlayer(){
		scrollBar.valueProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable,
					Number oldValue, Number newValue) {
				// TODO Auto-generated method stub
				//TODO: REFACTOR
//				if(propKey == "numplayer"){
//					dialogElements.getDialogGaeController().setNumberOfPlayers(newValue.intValue());
//				}
				numSpriteText.setText(newValue.intValue()+"");
				//dialogElements.getSaveObjProperty().saveObjProperty(propKey, newValue.intValue()+"");				
			}			
		});
	}
	
	public void setSelected(Double val){
		scrollBar.setValue(val);
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
