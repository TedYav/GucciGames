package voogasalad_GucciGames.gameAuthoring.gui.gaedialog.dialogcomponents;

import java.util.ArrayList;
import java.util.List;

import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.paramObjects.AParams;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.paramObjects.ActionParams;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

public class DialogTableView extends GridPane {
	
	private GridPane controlPane = new GridPane();
	private TextField textField;
	private TableView tableView;
	private ObservableList<TableElement> data;
	
	private ComboBox<String> dropDown = new ComboBox<String>();
	private List<String> availableItems;
	private Button removeBtn = new Button("Remove Selected");
	private String selectedType;

	
	public DialogTableView(List<String> availableItems){
		this.availableItems = availableItems;
		data = FXCollections.observableArrayList(new ArrayList<TableElement>());	
		removeBtn.setOnAction(e -> {
			data.remove(this.tableView.getSelectionModel().getSelectedItem());
			this.tableView.getSelectionModel().clearSelection();
		});	
		this.add(removeBtn, 1, 1);
		setTableView();
		setControlPane();
	}
	
	public DialogTableView(ObservableList<TableElement> data){
		this.data = data;
		setTableView();
	}
	
	public List<String> getData(){
		List<String> items = new ArrayList<String>();
		for(TableElement e: data){
			items.add(e.getName());
		}
		return items;
		
	}
	
	private void setTableView(){
		tableView = new TableView();		
		tableView.setItems(data);
		TableColumn nameCol = new TableColumn("Name");
		nameCol.setCellValueFactory(
				new PropertyValueFactory<TableElement, String>("name"));
		tableView.getColumns().add(nameCol);
		nameCol.setMinWidth(tableView.getWidth());
		this.add(tableView, 0, 0);	
	}
	
	private void setDropDown(){
		dropDown.setItems(FXCollections.observableArrayList(availableItems));
		dropDown.setOnAction(e -> {
			String selected = dropDown.getSelectionModel().getSelectedItem();
			textField.setText(selected);		
		});
		
	}
	
	private void setControlPane(){
		controlPane.setHgap(5);
		controlPane.setVgap(5);
		controlPane.setPadding(new Insets(5,5,5,5));
		textField = new TextField();
		textField.setDisable(true);
		dropDown.setItems(FXCollections.observableArrayList(availableItems));
		dropDown.setOnAction(e -> {
			String selected = dropDown.getSelectionModel().getSelectedItem();
			textField.setText(selected);		
		});
		
		final Button addSelectedBtn = new Button("Add Selected");
		addSelectedBtn.setOnAction(e -> {
			String name = textField.getText();
			if(!contains(name) ){
				TableElement element = new TableElement(name, selectedType);
				data.add(element);	
			}
		});		
		controlPane.add(textField, 0, 0);
		controlPane.add(dropDown, 1, 0);
		controlPane.add(addSelectedBtn, 2, 0);
		this.add(controlPane, 0, 1);
		
	}
	
	private boolean contains(String name){
		for(TableElement e: data){
			if (e.getName().equals(name)){
				tableView.getSelectionModel().select(e);
				return true;
			} 
		}
		return false;
	}
	
	
	
	public void addControlButton(Button btn){
		controlPane.add(btn, 3, 0);
	}
	
	
}
