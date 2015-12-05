package structures.run;

import java.util.Map;
/**
 * @author brennamilligan
 */
public interface IParameters {

    public void setParameterMaps(Map<String, String> strings, Map<String, Double> doubles, Map<String, Boolean> booleans);
    public Map<String, Double> getDoubleMap();
    public Map<String, String> getStringMap();
    public Map<String, Boolean> getBooleanMap();
    public void setOriginalParameterMaps();
    public Map<String, Double> getOriginalDoubleMap();
    public Map<String, String> getOriginalStringMap();
    public Map<String, Boolean> getOriginalBooleanMap();
}
