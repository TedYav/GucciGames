// This entire file is part of my masterpiece.
// Sally Al Khamees
package voogasalad_GucciGames.gameAuthoring.model.factories;

import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.paramObjects.ObjParamValue;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.paramObjects.OutcomeParamValue;


/**
 *
 * @author Sally Al
 *
 */
public class ObjectValues{
    private ObjParamValue myObjectParamValue;
    private OutcomeParamValue myOutcomeParamValue;

    public ObjectValues () {
    }

    public ObjectValues (ObjParamValue objectParamValue) {
        myObjectParamValue = objectParamValue;
    }

    public ObjectValues(OutcomeParamValue outcomeparams){
        myOutcomeParamValue= outcomeparams;
    }

    public ObjParamValue getObjectParamValue () {
        return myObjectParamValue;
    }

    public OutcomeParamValue getMyOutcomeParamValue () {
        return myOutcomeParamValue;
    }

}
