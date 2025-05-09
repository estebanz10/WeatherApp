package weather;

import java.util.ArrayList;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Properties{
    public String units;
    public String forecastGenerator;
    public Date generatedAt;
    public Date updateTime;
    public String validTimes;
    public Elevation elevation;
    public Hazards hazards;
    public ArrayList<Period> periods;
}
