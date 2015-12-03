package voogasalad_GucciGames.gameAuthoring.gui.gaedialog.groovySettings;

import java.util.ArrayList;
import java.util.List;

import voogasalad.util.reflection.Reflection;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.dialogcomponents.DropDownMenuField;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.paramObjects.CustomCharParams;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.paramObjects.Param;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class CharacteristicPane extends GridPane{
	
	private final Label lbl = new Label("Create a New Characteristics");
	private Label nameLbl;
	private TableView tableView;
	private ComboBox<String> dropDown;
	private VBox vbox;
	private List<Param> dataList = new ArrayList<Param>();
	private ObservableList<Param> data;
	private CustomCharParams charParam;

	

	public CharacteristicPane(String name){
		tableView = new TableView();
		nameLbl = new Label(name + " Characteristics");
		data = FXCollections.observableArrayList(dataList);
		setTable();
		setAddElementHBox();
		//this.setHalignment(vbox, HPos.CENTER);
		this.setValignment(vbox, VPos.CENTER);
		charParam = new CustomCharParams(name);
		
	
	}
	
	public void setTable(){
		TableColumn paramNameCol = new TableColumn("Paramter Name");
		paramNameCol.
		setCellValueFactory(new PropertyValueFactory<Param, String>("name"));
		TableColumn paramTypeCol = new TableColumn("Paramter Type");
		paramTypeCol.
		setCellValueFactory(new PropertyValueFactory<Param, String>("type"));
		paramNameCol.setMinWidth(150);
		paramTypeCol.setMinWidth(150);
		tableView.setItems(data);
		tableView.getColumns().addAll(paramNameCol, paramTypeCol);
		
	
		vbox = new VBox();
	        vbox.setSpacing(5);
	        vbox.setPadding(new Insets(10, 0, 0, 10));
	        vbox.getChildren().addAll(lbl, nameLbl, tableView);
		this.getChildren().add(vbox);
		
	}
	
	public void setAddElementHBox(){
		HBox hbox = new HBox();
		hbox.setSpacing(5);
		hbox.setPadding(new Insets(0, 10, 0, 10));
		final TextField paramNameField = new TextField();
		paramNameField.setPromptText("Parameter Name");
		final Button addBtn = new Button ("Add");
		makeDropDown();
		addBtn.setOnAction(e -> {
			data.add(new Param(dropDown.getSelectionModel().getSelectedItem(), 
					paramNameField.getText()));
			paramNameField.clear();

			
		});
		hbox.getChildren().addAll(paramNameField, dropDown, addBtn);
		vbox.getChildren().add(hbox);
		
	}
	
	private void makeDropDown(){
		dropDown = new ComboBox<String>();
		List<String> types = new ArrayList<String>();
		types.add("int");
		types.add("double");
		types.add("String");
		types.add("boolean");
		ObservableList<String> options = FXCollections.observableArrayList(types);
		dropDown.setItems(options);
		
	}
	
	

}
