package voogasalad_GucciGames.gameAuthoring.model.factories.defaultFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 *
 * @author Sally Al
 *
 */
public abstract class AFactory {

    protected Properties prop;

    public AFactory() {
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



    protected Object getTranslatedValue (String type, String value) {

        if (type.equals("int"))
            return Integer.parseInt(value);
        if (type.equals("double"))
            return Double.parseDouble(value);
        if (type.equals("String"))
            return value;
        if (type.equals("boolean"))
            return Boolean.parseBoolean(value);
        return null;

    }

}
