
package voogasalad_GucciGames.gameAuthoring.model.factories.defaultFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import voogasalad_GucciGames.gameAuthoring.model.factories.AFactory;

/**
 *
 * @author Sally Al
 *
 */
public abstract class DefaultFactory extends AFactory{
    protected Properties prop;

    public DefaultFactory() {
        prop =addProperties();
    }

    protected Properties addProperties () {
        prop = new Properties();
        try {
            prop.load(getStream());
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return prop;
    }



    protected abstract InputStream getStream ();


}
