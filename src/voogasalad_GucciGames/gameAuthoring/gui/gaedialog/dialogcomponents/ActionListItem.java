package voogasalad_GucciGames.gameAuthoring.gui.gaedialog.dialogcomponents;

import javafx.scene.control.Button;
import javafx.scene.text.Text;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.stylesheets.IListView;

public class ActionListItem extends ListItem{
	
	private String name;
	
	private Text nameLbl;
	private Button removeBtn;
	private IListView listViewInterface;
	
	public ActionListItem(String name, IListView listViewInterface){
		this.name = name;
		this.listViewInterface = listViewInterface;
		nameLbl = new Text(name);
		removeBtn = new Button("Remove");
		removeBtn.setOnAction(e-> actionForRemoveBtn(listViewInterface));
		this.getChildren().addAll(nameLbl, removeBtn);
	}

	@Override
	public void actionForRemoveBtn(IListView listViewInterface) {
		listViewInterface.removeFromListView(this);		
	}

	@Override
	public String getName() {
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
