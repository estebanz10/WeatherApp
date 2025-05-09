import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Objects;

public class LocationPane {
    Scene locationScene;
    TextField distinct;
    Button travelRandom, travelDistinct, back;
    StackPane inputStack;
    HBox hInput;
    Rectangle distinctBox;
    Text instructions, errors, random;

    public LocationPane() {
        distinct = new TextField();

        //sets up image for back button
        Image backIcon = new Image(Objects.requireNonNull(getClass().getResourceAsStream("back.png")));
        ImageView backImage = new ImageView(backIcon);

        //image adjustments to fit the page
        backImage.setFitHeight(50);
        backImage.setFitWidth(50);
        backImage.setPreserveRatio(true);
        backImage.setSmooth(true);
        backImage.setCache(true);


        back = new Button();
        back.setGraphic(backImage);
        back.setBackground(Background.EMPTY);
        back.setTranslateX(-310);
        back.setTranslateY(-310);

        travelRandom = new Button("Travel The US");
        travelRandom.setTranslateY(-230);
        travelRandom.setMinWidth(225);
        travelRandom.setMinHeight(50);
        travelRandom.setTranslateX(-10);

        random = new Text("Press Me To Travel To A Random US City");
        random.setTranslateY(-265);
        random.setTranslateX(-5);


        distinctBox = new Rectangle();
        distinctBox.setFill(Color.WHITE);
        distinctBox.setWidth(225);
        distinctBox.setHeight(50);
        distinctBox.setTranslateX(-10);
        distinctBox.setTranslateY(-140);
        distinctBox.setStyle("-fx-background-color: #D9D9D9");
        distinctBox.setOpacity(0.5);


        Image searchIcon = new Image(Objects.requireNonNull(getClass().getResourceAsStream("search.png")));
        ImageView searchImage = new ImageView(searchIcon);
        searchImage.setFitHeight(30);
        searchImage.setFitWidth(30);
        searchImage.setPreserveRatio(true);
        searchImage.setSmooth(true);
        searchImage.setCache(true);

        travelDistinct = new Button();
        travelDistinct.setGraphic(searchImage);
        travelDistinct.setBackground(Background.EMPTY);
        travelDistinct.setTranslateX(-10);
        travelDistinct.setTranslateY(-5);

        instructions = new Text("Input City Within the US in form 'City, State Abbreviation'\n\t\t\t\tIE: Chicago, IL");
        instructions.setTranslateY(-90);

        errors = new Text("");
        errors.setTranslateY(-40);
        errors.setFont(Font.font("Verdana", FontWeight.BOLD, 15));

        hInput = new HBox(travelDistinct, distinct);
        hInput.setTranslateX(250);
        hInput.setTranslateY(200);
        inputStack = new StackPane();
        inputStack.getChildren().addAll(distinctBox, hInput, travelRandom, back, instructions, errors, random);
        inputStack.setStyle("-fx-background-color: AA9494;");
        locationScene = new Scene(inputStack, 700, 700);
    }
    public Scene getScene() {return locationScene;}
    public Button getBack(){return back;}
    public Button getTravelDistinct(){return travelDistinct;}
    public Button getTravelRandom(){return travelRandom;}
    public String getInput(){return distinct.getText();}
    public String[] getLocationCoords(String input){
        String[] coords = new String[3];
        String city;
        String state;
        try{
            state = input.substring(input.indexOf(',') + 2);
            city = input.substring(0, input.indexOf(','));
        }catch(StringIndexOutOfBoundsException ex){
            errors.setText("Invalid input");
            return null;
        }
        Cities cities = new Cities();
        try{
            cities.loadCityInfo();
        }catch(FileNotFoundException ex){
            System.out.println("File not Found");
            return null;
        }
        List<String> stateList = cities.states;
        for(int i = 0; i < 50; i++){
            //checks if state given is in the list
            System.out.println(state + ": " + stateList.get(i));

            if(state.equals(stateList.get(i))){
                System.out.println("Found: " + state);
                String region = cities.getRegion(city, i);
                //checks if city given is in state given
                if(!region.isEmpty()){
                    int[] temp = cities.getCityCoord(city, i);
                    //check if we are given proper coordinates
                    if(temp[0] != -1){
                        coords[0] = region;
                        coords[1] = String.valueOf(temp[0]);
                        coords[2] = String.valueOf(temp[1]);
                        errors.setText("Welcome to " + city + ", " + state);
                        return coords;
                    }
                }

            }
        }
        errors.setText("Wrong input of city and/or state");
        return coords;
    }
    public String[] getRandomCoords(){
        Cities cities = new Cities();
        try{
            cities.loadCityInfo();
        }catch(FileNotFoundException ex){
            System.out.println("File not Found");
            return null;
        }
        String[] coords = cities.getRandom();
        errors.setText("Welcome to " + coords[3] + ", " + coords[4]);
        return coords;
    }
}
