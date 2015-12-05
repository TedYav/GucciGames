package voogasalad_GucciGames.usecases;

import voogasalad_GucciGames.gameEngine.gameConditions.oucomes.Outcome;
import javafx.scene.Scene;

public class UseLevel {
	//need condition that is like win but with result being new level
	
	//when user makes a level, everything and nothing changes I guess. Omg this is annoyingly complicated
	
	//should use same game authoring environment, but you'll need a new gameengine because why?
	//can be same tbh, you'll want different list of players and such though. Would it be too much work to add?
	//yes at the moment I think it would. 
	
	//actors are users and the game players
	//preconditions
	//basic flow
	//post conditions
	
	//how the game will react to a new level
	
	public void setCondition(Outcome outcome){
		//what condition will allow new level to come through
		//this should be done through an outcome, so when the game is checking outcomes
			//if a certain outcome is fulfilled, the game switches to a different scene
	}
	
	public void checkTransferable(){
		//rolls through all map objects and if they have transferable characteristic, they're copied into the new level
		//unsure of implementation as of now
	}
	
	
	//how the user will make a new level

	public Scene createNewLevel(){
		Scene levelScene = new Scene(null);
		return levelScene;
		//this creates a new level, which returns a scene that the user can go to.
	}
	
	public void setTranserable(){
		//when user is creating game, if he/she wants something to be transferable, give it the transferable characteristic
		//and everything that is included will have that characteristic.
	}
}
