package voogasalad_GucciGames.gameAuthoring.gui.gaedialog;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Properties;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class GroovyTabPane extends DialogComponent{
	
	private VBox contentVBox = new VBox();
	private TabPane tabPane = new TabPane();
	private Tab selectedTab = new Tab();
	private Button saveBtn;
	private Button editBtn;
	private DialogElements dialogElements;
	private Map<String, GroovyTab> attributeMap = new HashMap<String, GroovyTab>();
	private HBox controlBtnBox = new HBox();

	
	public GroovyTabPane(DialogElements dialogElements){
		this.dialogElements = dialogElements;
		contentVBox = createTabPaneElements(tabPane, this.dialogElements.getDialogProperties(), "vbox-element", "hbox-element");			
		setTabProperties(tabPane);
	}	
	 protected VBox createTabPaneElements(TabPane tabPane, Properties prop, String vboxStyleId, String hboxStyleId){
		 VBox vbox = new VBox();
		 controlBtnBox = new HBox();
//		 saveBtn = new Button(prop.getProperty("savebtn"));
//		 addSaveBtnListener();
//		 editBtn = new Button(prop.getProperty("editbtn"));
//		 addEditBtnListener();
//		 hbox.getChildren().addAll(editBtn, saveBtn);
//		 hbox.setId(hboxStyleId);
		 Button addBtn = createAddButton(prop, "addbtn", "groovytitle");
		 vbox.getChildren().addAll(tabPane, controlBtnBox, addBtn);
		 vbox.setId(vboxStyleId);
		 return vbox;
	 }
	 
	 private void addEditBtnListener(){
		 editBtn.setOnAction(e -> {
			 getTextAreaForTab(getSelectedTab().getText()).setDisable(false);			
		 });		 
	 }
	 
	 private void addSaveBtnListener(){
		 saveBtn.setOnAction(e -> {
			 String groovy = getTextAreaForTab(getSelectedTab().getText()).getText();
			 String name = getSelectedTab().getId();
			 dialogElements.getSaveGroovy().
			 saveGroovyTextArea(groovy, getIdForTab(getSelectedTab()));
			 try {
				 dialogElements.getObjectProperty().addPropertyElement("attributeName", name);
			 } catch (Exception e1) {
				 e1.printStackTrace();
			 }
			 getTextAreaForTab(getSelectedTab().getText()).setDisable(true);			 
		 });
	 }

	 
	 protected Button createAddButton(Properties prop, String btnKey, String headerKey){
		 Button addBtn = new Button(prop.getProperty(btnKey));
		 addBtn.setOnAction(e -> {
			 //addGroovyTab();
			 openAddNewDialog();
			 
		 });
		 return addBtn;		 
	 }
	  
	 protected void addGroovyTab(String tabName ){
		 GroovyTab tab = new GroovyTab(dialogElements, tabName);
		 attributeMap.put(tabName, tab);
		 tabPane.getTabs().add(tab.getTab()); 
	 }
	 
	 private void setTabProperties(TabPane tabPane){
		 //updateTabTitle(tabPane);
		 addTabPaneChangeListener(tabPane);
		 addTabSelectionListener(tabPane);
	 }
	 
	 private void addTabPaneChangeListener(TabPane tabPane){
		 ListView<Tab> tabListView = new ListView<Tab>();
		 tabListView.setItems(tabPane.getTabs());
		 tabListView.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
		 });
		 tabPane.getTabs().addListener(new ListChangeListener<Tab>() {
			 @Override
			 public void onChanged(
					 javafx.collections.ListChangeListener.Change<? extends Tab> c) {
				 // TODO Auto-generated method stub
				 while(c.next()){
					 if(/*c.wasRemoved() || */c.wasAdded() && c.getList().size() == 1){					 
						 //updateTabTitle(tabPane);
						 //TODO: Tab Pane title should be name of attribute
						 addControlBtns();
					 }
				 }
			 }
		 });
	 }
	 
	 private void addControlBtns(){
		 saveBtn = new Button(dialogElements.getDialogProperties().getProperty("savebtn"));
		 addSaveBtnListener();
		 editBtn = new Button(dialogElements.getDialogProperties().getProperty("editbtn"));
		 addEditBtnListener();
		 this.controlBtnBox.getChildren().addAll(editBtn, saveBtn);
		 
	 }
	 
	 private void addTabSelectionListener(TabPane tabPane){
		 tabPane.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Tab>(){
			@Override
			public void changed(ObservableValue<? extends Tab> observable,
					Tab oldValue, Tab newValue) {
				// TODO Auto-generated method stub
				setSelectedTab(newValue);				
			}			 
		 });
	 }
	 
	 private TextArea getTextAreaForTab(String tabName){
		 return this.attributeMap.get(tabName).getTextArea();
	 }
	 
	 private void openAddNewDialog() {
		 TextInputDialog dialog = new TextInputDialog();
		 dialog.setTitle("Create Custom Attribute");
		 dialog.setHeaderText("Enter custom attribute name");
		 dialog.setContentText("new attribute name:");
		 Optional<String> result = dialog.showAndWait();
		 result.ifPresent(name -> this.addGroovyTab(name));
	 }
	 
	 private String getIdForTab(Tab t){
		 return t.getText();
	 }
	 
	 
	 private void setSelectedTab(Tab t){
		 selectedTab = t;
	 }
	 
	 public Tab getSelectedTab(){
		 return selectedTab;
	 }
	 public HBox getContent(){
		 HBox hbox = new HBox();
		 hbox.getChildren().add(contentVBox);
		 return hbox;
	 }

	@Override
	public void setSelected(String s) {
		// TODO Auto-generated method stub
		
	}
	
	public void setValueForTab(Map<String, String> map){
		map.forEach((k, v) -> {
			this.addGroovyTab(k);
			attributeMap.get(k).getTextArea().setText(v);		
		});		
	}
}
