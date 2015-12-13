// This entire file is part of my masterpiece.
// Sally Al Khamees
package voogasalad_GucciGames.gameAuthoring.model.factories;

import voogasalad_GucciGames.gameAuthoring.model.factories.defaultFactory.TypeName;


/**
 *
 * @author Sally Al and Daniel McKee
 *
 */
public abstract class AFactory {

    public Object getTranslatedValue (String type, String value) {

        if (type.equals(TypeName.INT.getValue()))
            return Integer.parseInt(value);
        if (type.equals(TypeName.DOUBLE.getValue()))
            return Double.parseDouble(value);
        if (type.equals(TypeName.STRING.getValue()))
            return value;
        if (type.equals(TypeName.BOOLEAN.getValue()))
            return Boolean.parseBoolean(value);
        return null;

    }

}
