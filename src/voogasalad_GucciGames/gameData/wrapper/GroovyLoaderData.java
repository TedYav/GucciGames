package voogasalad_GucciGames.gameData.wrapper;

import java.util.Map;

import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.groovySettings.groovyParams.GActionParams;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.groovySettings.groovyParams.GCharParam;

public class GroovyLoaderData {
    private Map<String, GActionParams> myGroovyActionParams;
    private Map<String, GCharParam> myGroovyMapObjectCharParams;
    public GroovyLoaderData(Map<String, GActionParams> actions, Map<String, GCharParam> characteristics) {
        setGroovyActionParams(actions);
        setGroovyMapObjectCharParams(characteristics);
    }
    public Map<String, GActionParams> getGroovyActionParams () {
        return myGroovyActionParams;
    }
    public void setGroovyActionParams (Map<String, GActionParams> myGroovyActionParams) {
        this.myGroovyActionParams = myGroovyActionParams;
    }
    public Map<String, GCharParam> getGroovyMapObjectCharParams () {
        return myGroovyMapObjectCharParams;
    }
    public void setGroovyMapObjectCharParams (Map<String, GCharParam> myGroovyMapObjectCharParams) {
        this.myGroovyMapObjectCharParams = myGroovyMapObjectCharParams;
    }
}
