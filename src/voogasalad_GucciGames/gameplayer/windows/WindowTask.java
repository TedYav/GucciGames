package voogasalad_GucciGames.gameplayer.windows;

import java.util.TimerTask;

import voogasalad_GucciGames.gameplayer.windows.mainwindow.components.MenuAction;

public class WindowTask extends TimerTask {

	private MenuAction myAction;
	
	public WindowTask(MenuAction action){
		myAction = action;
	}
	
	@Override
	public void run() {
		myAction.activate();
	}

}
