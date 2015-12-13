// This entire file is part of my masterpiece.
// Sally Al Khamees

package voogasalad_GucciGames.gameAuthoring.model.factories.defaultFactory;

import java.io.InputStream;

/**
 *
 * @author Sally Al
 *
 */
public enum FactoryPropertyFilePath {
        PATH_TO_OUTCOME_PROPERTIES("outcomesPath.properties"),
        PATH_TO_ACTION_PROPERTIES("actionsPath.properties"),
        PATH_TO_CONDITION_PROPERTIES("conditionsPath.properties"),
        PATH_TO_PLAYER_CHARS("playerCharsPath.properties"),
        PATH_TO_RULE_PROPERTIES("rulesPath.properties"),
        PATH_TO_CHAR_PROPERTIES("MapObjectCharacteristicsPath.properties");


    private String myValue;

    FactoryPropertyFilePath (String value) {
        myValue = value;
    }
    protected InputStream getValue(){return getClass().getResourceAsStream(myValue);}
}
