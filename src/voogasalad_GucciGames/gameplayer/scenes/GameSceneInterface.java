package voogasalad_GucciGames.gameplayer.scenes;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;

public interface GameSceneInterface {
	
	public <T extends Event> void addEventHandler(EventType<T> eventType, EventHandler<T> eventHandler);
	public <T extends Event> void addEventFilter(EventType<T> eventType, EventHandler<T> eventHandler);
	public void refresh();

}
