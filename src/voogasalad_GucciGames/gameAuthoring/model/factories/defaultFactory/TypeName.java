// This entire file is part of my masterpiece.
// Sally Al Khamees
package voogasalad_GucciGames.gameAuthoring.model.factories.defaultFactory;

/**
 *
 * @author Sally Al
 *
 */
public enum TypeName {
                   INT("int"),
                   STRING("String"),
                   DOUBLE("double"),
                   BOOLEAN("boolean");
    String myValue;

    TypeName (String value) {
        myValue = value;
    }

  public  String getValue () {
        return myValue;
    }

}
