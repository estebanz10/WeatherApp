import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import weather.*;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class MyWeatherAPI extends WeatherAPI {
    public static Hazards getAlert(String region, int gridx, int gridy) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.weather.gov/gridpoints/"+region+"/"+String.valueOf(gridx)+","+String.valueOf(gridy)))
                //.method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> response = null;
        try {
            response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        } catch (Exception e) {
            e.printStackTrace();
        }
//        return response.body();
        Root r = getObject(response.body());
        if(r == null){
            System.err.println("Failed to parse JSon");
            return null;
        }
        return r.properties.hazards;
    }
    public static Root getObject(String json){
        ObjectMapper om = new ObjectMapper();
        Root toRet = null;
        try {
            toRet = om.readValue(json, Root.class);
//            Hazards temp = toRet.properties.hazards;

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return toRet;

    }
}
