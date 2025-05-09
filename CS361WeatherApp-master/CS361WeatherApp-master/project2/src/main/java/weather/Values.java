package weather;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Values {
    public String validTime;
    public ArrayList<Value> value;
}
