// This entire file is part of my masterpiece.
// Sally Al Khamees

package voogasalad_GucciGames.gameAuthoring.model.factories.defaultFactory;

import java.io.InputStream;


/**
 *
 * @author Sally Al and Daniel McKee
 *
 */
public class ConditionFactory extends Leaf {

    @Override
    public InputStream getStream () {
        return FactoryPropertyFilePath.PATH_TO_CHAR_PROPERTIES.getValue();
    }

}
