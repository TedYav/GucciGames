package voogasalad_GucciGames.gameAuthoring.model.factories.defaultFactory;

import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.mapobjectsettings.xml.ParamObjParser;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.paramObjects.ObjParam;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.paramObjects.ObjParamValue;
import voogasalad_GucciGames.gameAuthoring.gui.gaedialog.paramObjects.OutcomeParamValue;
import voogasalad_GucciGames.gameAuthoring.model.factories.ObjectValues;
import voogasalad_GucciGames.gameEngine.gameConditions.Conditions;
import voogasalad_GucciGames.gameEngine.gameConditions.outcomes.Outcome;


public class OutcomeFactory extends Leaf {

    private static final String PATH_TO_OUTCOME_PROPERTIES = "outcomesPath.properties";
    private Map<String, ObjParam> myConditions = new HashMap<String, ObjParam>();
    private ConditionFactory conditionFactory = new ConditionFactory();
    protected Properties prop;

    public OutcomeFactory () {
       prop =  addProperties();
        createListOfConditions();
    }

    @Override
    protected InputStream getStream () {
        return getClass().getResourceAsStream(PATH_TO_OUTCOME_PROPERTIES);
        }

    private void createListOfConditions () {
        ParamObjParser parser = new ParamObjParser();
        Set<ObjParam> conditions = parser.getConditions();
        for (ObjParam param : conditions) {
            myConditions.put(param.getName(), param);
        }
    }


    @Override
    public Object create (Map<String, ObjParam> params, ObjectValues objValues) throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException,IllegalArgumentException, InvocationTargetException {

        OutcomeParamValue value = objValues.getMyOutcomeParamValue();
        String outcomeName = prop.getProperty(value.getName());
        Outcome outcomeInstance = doReflection(outcomeName);
        outcomeInstance = addConditions(value, outcomeInstance);
        return outcomeInstance;

    }

    private Outcome doReflection (String outcomeName) throws ClassNotFoundException, NoSuchMethodException, InstantiationException,IllegalAccessException, InvocationTargetException {
        @SuppressWarnings("unchecked")
        Class<Outcome> outcome = (Class<Outcome>) Class.forName(outcomeName);
        Constructor<Outcome> outcomeConstructor = outcome.getDeclaredConstructor();
        Outcome outcomeInstance = outcomeConstructor.newInstance();
        return outcomeInstance;
    }

    protected Outcome addConditions (OutcomeParamValue value,Outcome outcomeInstance) throws NoSuchMethodException,ClassNotFoundException,InstantiationException,IllegalAccessException,InvocationTargetException {
        for (ObjParamValue param : value.getConditions()) {
            outcomeInstance.addCondition((Conditions) conditionFactory.create(myConditions, new ObjectValues(param)));
            }
        return outcomeInstance;
    }

}
