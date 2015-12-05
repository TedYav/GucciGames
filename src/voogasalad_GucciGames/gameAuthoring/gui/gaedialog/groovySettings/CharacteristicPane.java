package voogasalad_GucciGames.gameAuthoring.gui.gaedialog.groovySettings;

import java.util.ArrayList;
import java.util.List;

import voogasalad.util.reflection.Reflection;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.dialogcomponents.DropDownMenuField;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.groovySettings.groovyParams.GCharParam;
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
import javafx.scene.text.Font;

public class CharacteristicPane extends GridPane{
	
	private final Label lbl = new Label("Create a New Characteristics");
	private Label nameLbl;
	private TableView tableView;
	private ComboBox<String> dropDown;
	private VBox vbox;
	private List<Param> dataList = new ArrayList<Param>();
	private ObservableList<Param> data;
	private GCharParam charParam;

	

	public CharacteristicPane(String name, ISwitchGroovyPane controller){
		super();
		tableView = new TableView();
		nameLbl = new Label(name + " Characteristics");
		nameLbl.setFont(new Font("Arial", 20));
		data = FXCollections.observableArrayList(dataList);
		this.setHgap(5);
		this.setVgap(5);
		setTable();
		setAddElementHBox();
		setControlBtn();
		charParam = new GCharParam(name);
		
	
	}
	
	public void setTable(){
		TableColumn paramNameCol = new TableColumn("Parameter Name");
		paramNameCol.
		setCellValueFactory(new PropertyValueFactory<Param, String>("name"));
		TableColumn paramTypeCol = new TableColumn("Parameter Type");
		paramTypeCol.
		setCellValueFactory(new PropertyValueFactory<Param, String>("type"));
		paramNameCol.setMinWidth(150);
		paramTypeCol.setMinWidth(150);
		tableView.setItems(data);
		tableView.getColumns().addAll(paramNameCol, paramTypeCol);	

	    this.add(lbl, 0, 0);
	    this.add(nameLbl, 0, 1);
	    this.add(tableView, 0, 2);
	    this.setHalignment(lbl, HPos.CENTER);
	    this.setHalignment(nameLbl, HPos.CENTER);
	    this.setHalignment(tableView, HPos.CENTER);
		
	}
	
	private void setControlBtn(){
		final Button deleteBtn = new Button("Delete Selected Row");
		deleteBtn.setOnAction(e -> {
			data.remove(this.tableView.getSelectionModel().getSelectedItem());
			this.tableView.getSelectionModel().clearSelection();
		});
		this.add(deleteBtn,1 , 2);
		
		final Button saveBtn = new Button("Save");
		saveBtn.setOnAction(e -> {
			for(Param p: data){
				charParam.addParam(p.getType(), p.getName());
				//TODO: send to backend
			}
		});
		this.add(saveBtn, 1, 3);
		
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
		this.add(hbox, 0, 3);
		
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
