// This entire file is part of my masterpiece.
// Sally Al Khamees

package voogasalad_GucciGames.gameAuthoring.model.factories.defaultFactory;

import java.io.InputStream;


public class CharacteristicMapFactory extends Leaf {

    @Override
    public InputStream getStream () {
        return FactoryPropertyFilePath.PATH_TO_CHAR_PROPERTIES.getValue();

    }

}
