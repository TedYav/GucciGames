package voogasalad_GucciGames.gameAuthoring.gui.gaedialog.mapobjsettings;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

import voogasalad_GucciGames.gameAuthoring.IDialogGaeController;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.dialogcomponents.DialogTableView;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.dialogcomponents.TableElement;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.groovySettings.groovyParams.GCharParam;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.mapobjectsettings.xml.CharacteristicsSAXHandler;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.mapobjectsettings.xml.RulesSAXHandler;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.paramObjects.ObjParam;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.paramObjects.RuleParams;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class RulesPane extends GridPane{
	
	private Text title = new Text("Rules");
	private Button saveBtn = new Button("Save and Next");
	private List<GCharParam> charParams = new ArrayList<GCharParam>();
	
	private List<String> rules  = new ArrayList<String>();
	private DialogTableView tableView;
	private IDialogGaeController controller;

	
	private DialogTableView rulesTableView;
	
	public RulesPane(IDialogGaeController controller){
		this.controller = controller;
		loadRules();		
		
		rulesTableView = new DialogTableView(rules, "Add Rules");
		this.add(rulesTableView, 0, 0);
		this.add(saveBtn, 3, 3);
		
		loadRules();		
		addActionToSaveBtn();
		setLayout();
		
	}
	
	private void setLayout(){
		this.setHgap(5);
		this.setVgap(5);
		this.setPadding(new Insets(5,5,5,5));
	}
	
	private void loadRules(){
		controller.getAllRules().forEach(rule -> {
			rules.add(rule.getName());
		});;
		
		
	}
	

	
	private void addActionToSaveBtn(){
		this.saveBtn.setOnAction(e -> {
			List<String> data = tableView.getData();
			//TODO: add Rules to Action
			
			Dialog d = new Dialog();
			d.showAndWait();
			
			
			
		});
	}

}
