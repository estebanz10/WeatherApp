import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.lang.Math;

public class Cities {

    public List<List<String[]>> cityInfo = new ArrayList<>();

    public List<String> states = Arrays.asList("AL","AK","AZ","AR","CA","CO","CT","DE","FL","GA","HI","ID","IL","IN","IA","KS","KY","LA","ME","MD","MA","MI","MN","MS","MO","MT","NE","NV","NH","NJ","NM","NY","NC","ND","OH","OK","OR","PA","RI","SC","SD","TN","TX","UT","VT","VA","WA","WV","WI","WY");


    public void loadCityInfo() throws FileNotFoundException {
        for(int i = 0 ; i < 50; ++i){
            List<String[]> cities = new ArrayList<>();
            cityInfo.add(cities);
        }
        File myObj = new File("src/main/java/cities.txt");
        Scanner myReader = new Scanner(myObj);
        while (myReader.hasNextLine()) {
            String data = myReader.nextLine();
            String[] tokens = data.split(",");
            int stateNum = Integer.parseInt(tokens[1]);
            String[] infoCity = new String[4];
            infoCity[0] = tokens[0];
            infoCity[1] = tokens[2];
            infoCity[2] = tokens[3];
            infoCity[3] = tokens[4];
            cityInfo.get(stateNum).add(infoCity);
        }
        myReader.close();
    }


    public int[] getCityCoord(String city, int stateNum){
        int[] array = {-1, -1};
        if(!cityInfo.isEmpty() && !cityInfo.get(stateNum).isEmpty()) {
            int size = cityInfo.get(stateNum).size();
            for(int i = 0; i < size; i++) {
                String cityName = cityInfo.get(stateNum).get(i)[0];
                if(cityName.equals(city)){
                    array[0] = Integer.parseInt(cityInfo.get(stateNum).get(i)[2]);
                    array[1] = Integer.parseInt(cityInfo.get(stateNum).get(i)[3]);
                }
            }
        }
        return array;
    }

    public List<Integer> getAllStateNums(String city){
        List<Integer> array = new ArrayList<>();
        if(!cityInfo.isEmpty()) {
            for(int i = 0; i < 50; i++) {
                String cityName = cityInfo.get(i).getFirst()[0];
                if(cityName.equals(city)){
                    array.add(i);
                }
            }
        }
        return array;
    }


    public String getRegion(String city, int stateNum) {
        int size = cityInfo.get(stateNum).size();
        for(int i = 0 ; i < size ; ++i) {
            String cityName = cityInfo.get(stateNum).get(i)[0];
            if (cityName.equals(city)) {
                return cityInfo.get(stateNum).get(i)[1];
            }
        }
        return "";
    }

    public String[] getRandom(){
        String[] coords = new String[5];
        int randomState = (int)(Math.random()*50);
        int size = cityInfo.get(randomState).size();
        int randomCity = (int)(Math.random()*size);
        coords[0] = cityInfo.get(randomState).get(randomCity)[1];
        coords[1] = cityInfo.get(randomState).get(randomCity)[2];
        coords[2] = cityInfo.get(randomState).get(randomCity)[3];
        coords[3] = cityInfo.get(randomState).get(randomCity)[0];
        coords[4] = states.get(randomState);

        return coords;
    }

}
