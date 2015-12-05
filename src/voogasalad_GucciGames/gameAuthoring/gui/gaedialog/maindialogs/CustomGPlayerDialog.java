package voogasalad_GucciGames.gameAuthoring.gui.gaedialog.maindialogs;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import voogasalad_GucciGames.gameAuthoring.IDialogGaeController;
import voogasalad_GucciGames.gameAuthoring.IGuiGaeController;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.DialogElements;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.dialogcomponents.CheckBoxField;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.paramObjects.GameSettingParams;
import voogasalad_GucciGames.gameplayer.windows.mainwindow.menubar.MenuLoader;

public class CustomGPlayerDialog extends GaeDialog{
	
	private ResourceBundle namesBundle = ResourceBundle.getBundle("voogasalad_GucciGames.gameData.config.GuiComponents");
    private Stage gameSettingDialog = new Stage();
    private VBox myContent = new VBox();
    private VBox parent = new VBox();
    private Properties prop;
    private IDialogGaeController dialogGaeController;
    private IGuiGaeController guiGaeController;
    private DialogElements dialogElements;
    private GameSettingParams gameSettingParams = new GameSettingParams();
    private Button saveButton;

    private List<String> guiList = new ArrayList<String>();
    private List<List<CheckBoxField>> checkBoxFields = new ArrayList<List<CheckBoxField>>();
    private List<List<String>> allCheckedBoxes = new ArrayList<List<String>>(); 
    private int numBars;
    private int maxBars=3;

    public CustomGPlayerDialog(IDialogGaeController dialogGaeController, IGuiGaeController guiGaeController){
    	this.dialogGaeController = dialogGaeController;
        this.guiGaeController = guiGaeController;
        guiList.add("ActionDisplay");
        guiList.add("DisplayChat");
        guiList.add("DisplayMapObjectDetails");
        guiList.add("DisplayMapObjectImage");
        guiList.add("GameStatsDisplay");
        checkBoxFields.add(new ArrayList<CheckBoxField>());
        checkBoxFields.add(new ArrayList<CheckBoxField>());
        checkBoxFields.add(new ArrayList<CheckBoxField>());
        allCheckedBoxes.add(new ArrayList<String>());
        allCheckedBoxes.add(new ArrayList<String>());
        allCheckedBoxes.add(new ArrayList<String>());
        prop = super.loadProperties("/voogasalad_GucciGames/gameAuthoring/gui/gaedialog/maindialogs/dialogproperties/customgplayerdialog.properties");
        dialogElements = new DialogElements(prop, null, dialogGaeController);
        saveButton = new Button("Save");
        setSaveAction();
        myContent.getChildren().addAll(this.initializeDialog(), saveButton);
        Scene gameSettingDialogScene = new Scene(myContent, 500, 500);
        gameSettingDialogScene.getStylesheets().add("voogasalad_GucciGames/gameAuthoring/gui/gaedialog/stylesheets/dialogstylesheet.css");
        gameSettingDialog.setScene(gameSettingDialogScene);		
    }

    protected void setSaveAction(){
        saveButton.setOnAction(e -> {
            for (List<CheckBoxField> cb: checkBoxFields) {
                for (CheckBoxField cbf: cb) {
                    if (cbf.getCheckBox().isSelected()) {
                        if (!allCheckedBoxes.get(Integer.parseInt((String) cbf.getUserData())).contains(cbf.getPropKey())) {
                            allCheckedBoxes.get(Integer.parseInt((String) cbf.getUserData())).add(cbf.getPropKey());
                        }
                    }
                }
            }
            System.out.println(allCheckedBoxes);
//            guiGaeController.setCustomGamePlayerLeftComponents(allCheckedBoxes.get(0));
//            guiGaeController.setCustomGamePlayerBottomComponents(allCheckedBoxes.get(1));
//            guiGaeController.setCustomGamePlayerRightComponents(allCheckedBoxes.get(2));
            guiGaeController.setCustomGamePlayerComponents("Left", allCheckedBoxes.get(0));
            guiGaeController.setCustomGamePlayerComponents("Bottom", allCheckedBoxes.get(1));
            guiGaeController.setCustomGamePlayerComponents("Right", allCheckedBoxes.get(2));
            this.gameSettingDialog.close();
        });
    }

    @Override
    protected VBox initializeDialog() {
        // TODO Auto-generated method stub
        GridPane content = new GridPane();				
        Text titleElement = new Text();
        titleElement.setText(prop.getProperty("title"));

        Text label = new Text("Left");
        GridPane.setConstraints(label,0,0);
        content.getChildren().add(label);
        label = new Text("Bottom");
        GridPane.setConstraints(label,1,0);
        content.getChildren().add(label);
        label = new Text("Right");
        GridPane.setConstraints(label,2,0);
        content.getChildren().add(label);
        for (int j=0;j<5;j++) {
            for (numBars=0;numBars<maxBars;numBars++) {
                CheckBoxField actionDisplay = new CheckBoxField(dialogElements, guiList.get(j));
                actionDisplay.setUserData(""+numBars);
                checkBoxFields.get(numBars).add(actionDisplay);
                GridPane.setConstraints(actionDisplay,numBars,j+1);
                content.getChildren().add(actionDisplay);
            }
        }
        for (numBars=0;numBars<maxBars;numBars++) {
            loadCheckBoxState(numBars,checkBoxFields.get(numBars));
        }
        //setCheckBoxListeners();
        content.getChildren().forEach(hbox->hbox.setId("hbox-element"));		
        titleElement.setId("title");
        content.setId("vbox-element");	
        parent.getChildren().add(content);
        return parent;
    }



    private void loadCheckBoxState (int index, List<CheckBoxField> boxes) {
        switch (index) {
            case 0:
                for (CheckBoxField c: boxes) {
                    if (guiGaeController.getCustomGamePlayerComponents("Left").contains(c.getPropKey())) {
                        c.getCheckBox().setSelected(true);
                    }
                }
                break;
            case 1:
                for (CheckBoxField c: boxes) {
                    if (guiGaeController.getCustomGamePlayerComponents("Bottom").contains(c.getPropKey())) {
                        c.getCheckBox().setSelected(true);
                    }
                }
                break;
            case 2:
                for (CheckBoxField c: boxes) {
                    if (guiGaeController.getCustomGamePlayerComponents("Right").contains(c.getPropKey())) {
                        c.getCheckBox().setSelected(true);
                    }
                }
                break;
            default:
                break;
        }
    }

    public void showCustomGPlayerDialog(){
        super.showDialog(gameSettingDialog);
    }

//    private void setCheckBoxListeners(){
//        for (numBars=0; numBars<maxBars; numBars++){
//            for(int i=0; i<checkBoxFields.get(numBars).size(); i++){
//                CheckBoxField currCheckBoxField = checkBoxFields.get(numBars).get(i);
//                currCheckBoxField.getCheckBox().selectedProperty().addListener((new ChangeListener<Boolean>() {
//                    public void changed(ObservableValue<? extends Boolean> ov,
//                                        Boolean old_val, Boolean new_val) {
//                        if(new_val){
//                            allCheckedBoxes.get(Integer.parseInt((String) currCheckBoxField.getUserData())).add(currCheckBoxField.getPropKey());
//                        }else{
//                            allCheckedBoxes.get(Integer.parseInt((String) currCheckBoxField.getUserData())).remove(currCheckBoxField.getPropKey());
//                        }
//                    }
//                }));
//            }
//        }
//    }

}
