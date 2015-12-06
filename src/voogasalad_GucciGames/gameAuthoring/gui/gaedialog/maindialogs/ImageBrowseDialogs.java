package voogasalad_GucciGames.gameAuthoring.gui.gaedialog.maindialogs;

import java.util.HashMap;
import java.util.Map;

import javafx.scene.control.Dialog;
import voogasalad_GucciGames.helpers.GameResourceManagerToGAE;

public class ImageBrowseDialogs{
	private static final String[] myDialogNames = {"units","tiles","structures"};
	
	private Map<String, Dialog<String>> myDialogs = new HashMap<>();

	public ImageBrowseDialogs(GameResourceManagerToGAE resManager) {
		for(String s:myDialogNames){
			myDialogs.put(s, new ImageBrowseDialog(resManager, s));
		}
	}

	public Dialog<String> getDialog(String type){
		return myDialogs.get(type);
	}
}
