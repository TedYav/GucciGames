
package voogasalad_GucciGames.gameAuthoring.model.factories.defaultFactory;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.paramObjects.ObjParam;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.paramObjects.ObjParamValue;
import voogasalad_GucciGames.gameAuthoring.model.factories.ObjectValues;
import voogasalad_GucciGames.gameAuthoring.model.factories.TypeMap;

/**
 *
 * @author Sally Al
 *
 * any factory that requires mapObjectParams mapObjectValue
 *
 */
public abstract class Leaf extends AFactory{


    public Object create (Map<String, ObjParam> mapObjectParams, ObjectValues objValues)
                                                                                              throws NoSuchMethodException,
                                                                                              SecurityException,
                                                                                              ClassNotFoundException,
                                                                                              InstantiationException,
                                                                                              IllegalAccessException,
                                                                                              IllegalArgumentException,
                                                                                              InvocationTargetException {

        ObjParamValue objParamValue = objValues.getObjectParamValue();

        Map<String, String> typeMap = mapObjectParams.get(objParamValue.getName()).getParamTypeMap();

        Map<Integer, String> orderMap = mapObjectParams.get(objParamValue.getName()).getParamOrderMap();

        Map<String, String> valueMap = objParamValue.getMap();

        Class<?>[] myParameters = new Class<?>[typeMap.size()];
        for (int i = 0; i < myParameters.length; i++) {
            myParameters[i] = TypeMap.getType(typeMap.get(orderMap.get(i)));
        }

        Object[] initargs = new Object[myParameters.length];

        for (int i = 0; i < initargs.length; i++) {

            Object o =
                    getTranslatedValue(typeMap.get(orderMap.get(i)), valueMap.get(orderMap.get(i)));
            if (!o.equals("")) {
                initargs[i] = o;
            }
            else {

                Object[] temp = initargs;
                initargs = new Object[temp.length - 1];
                for (int j = 0; j < initargs.length; j++) {
                    initargs[j] = temp[j];
                }
                myParameters = new Class<?>[0];
            }
        }

        Constructor<?> c  =Class.forName(prop.getProperty(objParamValue.getName())).getConstructor(myParameters);
        Object myObject = c.newInstance(initargs);
        return myObject;

    }

}
