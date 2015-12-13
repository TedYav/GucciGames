// This entire file is part of my masterpiece.
// Sally Al Khamees

package voogasalad_GucciGames.gameAuthoring.model.factories.defaultFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import voogasalad_GucciGames.gameAuthoring.model.factories.AFactory;


/**
 *
 * @author Sally Al and Daniel McKee
 *
 */
public abstract class DefaultFactory extends AFactory {
    protected Properties prop;

    public DefaultFactory () {
        prop = addProperties();
    }

    protected Properties addProperties () {
        Properties temp = new Properties();
        try {
                temp.load(getStream());
        } catch (IOException e) {
                e.printStackTrace();
        }
        return temp;
    }

    public abstract InputStream getStream ();

}
