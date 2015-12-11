package voogasalad_GucciGames.gameAuthoring.gui.gaedialog.dialogcomponents;

import java.io.File;
import java.util.Properties;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.DialogElements;

public class FileBrowserField extends DialogComponent{
	private DialogElements dialogElements;
	private String browseBtnKey, fileChooserKey, labelKey;
	private TextField pathTextField = new TextField();
	private Button browseBtn;
	private Properties prop;
	
	public FileBrowserField(Properties prop, String labelKey, String browseBtnKey, String fileChooserKey){
		this.prop = prop;
		this.browseBtnKey = browseBtnKey;
		this.fileChooserKey = fileChooserKey;
		this.labelKey = labelKey;
		makeBrowseElement()	;	
		
	}
	
	protected void makeBrowseElement(){
		Text label = new Text(prop.getProperty(labelKey));
		browseBtn = new Button(prop.getProperty(browseBtnKey));
		addActionHandlerForBrowseBtn();
		pathTextField = new TextField();
		//addListenerForPathTextField();
		this.add(label, 0, 0);
		this.add(pathTextField, 1, 0);
		this.add(browseBtn, 2, 0);

	}
	
//	private void addListenerForPathTextField(){
//		pathTextField.textProperty().addListener((observable, oldValue, newValue)->{
//			
//		 });
//		
//	}
	
	private void addActionHandlerForBrowseBtn(){
		browseBtn.setOnAction(e -> {
			FileChooser fileChooser = new FileChooser();
			fileChooser.setTitle(prop.getProperty(fileChooserKey));
			fileChooser.getExtensionFilters().add(
			         new ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));	         
			File selectedFile  = fileChooser.showOpenDialog(null);
			pathTextField.setText(selectedFile.getAbsolutePath());
		});
		
	}

	@Override
	public void setSelected(String s) {
		// TODO Auto-generated method stub
		pathTextField.setText(s);
		
	}
	
	public String getPath(){
		return pathTextField.getText();
	}

	@Override
	public String getSelected() {
		// TODO Auto-generated method stub
		return null;
	}

}
