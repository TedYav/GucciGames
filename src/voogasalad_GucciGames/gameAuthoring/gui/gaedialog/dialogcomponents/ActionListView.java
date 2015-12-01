package voogasalad_GucciGames.gameAuthoring.gui.gaedialog.dialogcomponents;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.stylesheets.IListView;

public class ActionListView extends ListView implements IListView  {

	private List<ListItem> myItems;
	private Set<String> myNames;
	
	public ActionListView(){
		myItems = new ArrayList<ListItem>();
		myNames = new HashSet<String>();
		
	}
	
	@Override
	public void addListElement(String name){
		if(!myNames.contains(name)){
			ActionListItem item = new ActionListItem(name, this);
			myItems.add(item);
			myNames.add(name);
			redraw();
		}
		
	}
	@Override
	public void removeListItem(ListItem item){
		myNames.remove(item.getName());
		myItems.remove(item);		
		redraw();
	}
	
	@Override
	protected void redraw(){
		this.getChildren().removeAll(this.getChildren());
		this.getChildren().addAll(myItems);
	}
	

	@Override
	public void removeFromListView(ListItem item) {
		removeListItem(item);
		
	}

	@Override
	public void addToListView(String name) {
		addListElement(name);
		System.out.println(name);
		
		
	}

	@Override
	public List<ListItem> getAllListItems() {
		// TODO Auto-generated method stub
		return myItems;
	}

	@Override
	public Set<String> getAllListItemsName() {
		// TODO Auto-generated method stub
		return myNames;
	}


}
