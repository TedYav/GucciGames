package voogasalad_GucciGames.gameAuthoring.gui.gaedialog;

import java.util.Properties;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class GroovyTabPane {
	
	private VBox contentVBox = new VBox();
	private TabPane tabPane = new TabPane();
	private Tab selectedTab = new Tab();
	private Properties prop;
	private ISaveGroovy saveGroovy;
	
	public GroovyTabPane(Properties prop, ISaveGroovy saveGroovy){
		this.prop = prop;
		this.saveGroovy = saveGroovy;
		addGroovyTab();
		setSelectedTab(tabPane.getTabs().get(0));
		contentVBox = createTabPaneElements(tabPane, prop);			
		setTabProperties(tabPane);
	}	
	 protected VBox createTabPaneElements(TabPane tabPane, Properties prop){
		 VBox vbox = new VBox();
		 HBox hbox = new HBox();
		 Button saveBtn = new Button(prop.getProperty("savebtn"));
		 Button editBtn = new Button(prop.getProperty("editbtn"));
		 hbox.getChildren().addAll(editBtn, saveBtn);
		 saveBtn.setOnAction(e -> {
			 String groovy = getTextAreaForTab(getSelectedTab()).getText();
			 saveGroovy.saveGroovyTextArea(groovy, getIdForTab(getSelectedTab()));
			 getTextAreaForTab(getSelectedTab()).setDisable(true);			 
		 });
		 editBtn.setOnAction(e -> {
			 getTextAreaForTab(getSelectedTab()).setDisable(false);			
		 });
		 Button addBtn = createAddButton(prop, "addbtn", "groovytitle");
		 Button saveCustomObjBtn = new Button(prop.getProperty("savebtn"));
		 saveCustomObjBtn.setOnAction(e -> {} );
		 vbox.getChildren().addAll(tabPane, hbox, addBtn, saveCustomObjBtn);
		 return vbox;
	 }
	 
	 protected Button createAddButton(Properties prop, String btnKey, String headerKey){
		 Button addBtn = new Button(prop.getProperty(btnKey));
		 addBtn.setOnAction(e -> {
			 addGroovyTab();
			 
		 });
		 return addBtn;		 
	 }
	  
	 protected void addGroovyTab( ){
		 Tab tab = new Tab();
		 VBox content = new VBox();
		 Text title = new Text(prop.getProperty("groovytitle"));
		 TextArea textArea = new TextArea();
		 content.getChildren().addAll(title, textArea);
		 tab.setContent(content);
		 tabPane.getTabs().add(tab); 
	 }
	 
	 private void setTabProperties(TabPane tabPane){
		 updateTabTitle(tabPane);
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
					 if(c.wasRemoved() || c.wasAdded()){					 
						 updateTabTitle(tabPane);
					 }
				 }
			 }
		 });
	 }
	 
	 private void updateTabTitle(TabPane tabPane){
		 for(int i = 0; i < tabPane.getTabs().size(); i++){
			 tabPane.getTabs().get(i).setText("Attribute " + i);
		 }
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
	 
	 private TextArea getTextAreaForTab(Tab t){
		 return (TextArea) (((VBox) t.getContent()).getChildren().get(1));
	 }
	 
	 protected int getIdForTab(Tab t){
		 return Integer.parseInt(t.getText().split("\\s+")[1]);
	 }
	 
	 private void setSelectedTab(Tab t){
		 selectedTab = t;
	 }
	 
	 public Tab getSelectedTab(){
		 return selectedTab;
	 }
	 public VBox getContent(){
		 return contentVBox;
	 }
	
	

}
