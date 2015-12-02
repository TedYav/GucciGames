package voogasalad_GucciGames.gameAuthoring.gui.gaedialog.dialogcomponents.listelements;

import javafx.scene.text.Text;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.stylesheets.IListView;

public class RuleListItem extends ListItem {
	
	private String name;
	
	private Text nameLbl;
	
	public RuleListItem(String name){
		this.name = name;
		nameLbl = new Text(name);
		this.getChildren().add(nameLbl);
	}

	@Override
	public void actionForRemoveBtn(IListView listViewInterface) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}

	@Override
	public void setSelected(String s) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getSelected() {
		// TODO Auto-generated method stub
		return null;
	}

}
