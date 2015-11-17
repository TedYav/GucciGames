package voogasalad_GucciGames.gameAuthoring.gui.gaedialog;

import java.io.File;
import java.util.Properties;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

public class FileBrowserField extends DialogComponent{
	private DialogElements dialogElements;
	private String browseBtnKey, fileChooserKey;
	private HBox content = new HBox();
	private TextField pathTextField = new TextField();
	private Button browseBtn;
	
	public FileBrowserField(DialogElements dialogElements, String browseBtnKey, String fileChooserKey){
		this.dialogElements = dialogElements;
		this.browseBtnKey = browseBtnKey;
		this.fileChooserKey = fileChooserKey;
		makeBrowseElement()	;	
		
	}
	
	protected void makeBrowseElement(){
		browseBtn = new Button(dialogElements.getDialogProperties().getProperty(browseBtnKey));
		addActionHandlerForBrowseBtn();
		pathTextField = new TextField();
		addListenerForPathTextField();

		content.getChildren().addAll(pathTextField, browseBtn);	

	}
	
	private void addListenerForPathTextField(){
		pathTextField.textProperty().addListener((observable, oldValue, newValue)->{
			 dialogElements.getSaveObjProperty().saveObjProperty("imagepath", newValue);
		 });
		
	}
	
	private void addActionHandlerForBrowseBtn(){
		browseBtn.setOnAction(e -> {
			FileChooser fileChooser = new FileChooser();
			fileChooser.setTitle(dialogElements.getDialogProperties().getProperty(fileChooserKey));
//			fileChooser.getExtensionFilters().add(
//			         new ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));	         
			File selectedFile  = fileChooser.showOpenDialog(null);
			pathTextField.setText(selectedFile.getAbsolutePath());
		});
		
	}

	@Override
	HBox getContent() {
		// TODO Auto-generated method stub
		return content;
	}

	@Override
	void setSelected(String s) {
		// TODO Auto-generated method stub
		pathTextField.setText(s);
		
	}

}