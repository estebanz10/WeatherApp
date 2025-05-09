import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import weather.Period;

import java.util.ArrayList;
import java.util.Objects;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
public class BasePane {
    Button todayTemp, farAndCel, location, customization, character;
    BorderPane basePane, bOther;
    HBox hBaseScene;
    Group a, other;
    StackPane menu, top;
    Rectangle grass, menuBar;
    Ellipse textBox;
    Text shortCast, fc;
    boolean miku = false, gojo = false, luffy = false, bocchi = false, regular;
    public BasePane(ArrayList<Period> forecast){

        //Creates center button
        //Base text on button is current temperature in Fahrenheit
        todayTemp = new Button(forecast.getFirst().temperature + "°F");
        //Makes the button circular
        Circle c = new Circle(500);
        todayTemp.setShape(c);
        //Sets the location of the button
        todayTemp.setTranslateX(325);
        todayTemp.setTranslateY(150);
        //Sets size of the button
        todayTemp.setMinSize(50, 50);


        //Creates the sun button to change between Fahrenheit and Celsius
        farAndCel = new Button("F/C");
        //Makes the button circular
        Circle sun = new Circle(500);
        farAndCel.setShape(sun);
        //Sets the location of the button
        farAndCel.setTranslateX(400);
        farAndCel.setTranslateY(-300);
        //Sets size of the button
        farAndCel.setMinSize(500, 500);
        fc = new Text("F/C");
        fc.setTranslateX(30);
        fc.setTranslateY(50);
        fc.setMouseTransparent(true);
        fc.setStyle("-fx-font-size: 25;");

        //sets up "grass"
        grass = new Rectangle();
        grass.setWidth(700);
        grass.setHeight(350);
        grass.setTranslateX(0);
        grass.setTranslateY(-100);
        grass.setFill(Color.DARKOLIVEGREEN);

        //sets up the images for the buttons
        Image cityIcon = new Image(Objects.requireNonNull(getClass().getResourceAsStream("city.png")));
        ImageView image = new ImageView(cityIcon);

        Image customIcon = new Image(Objects.requireNonNull(getClass().getResourceAsStream("customize.png")));
        ImageView customImage = new ImageView(customIcon);

        Image clouds = new Image(Objects.requireNonNull(getClass().getResourceAsStream("cloud1.png")));
        ImageView cloudImage = new ImageView(clouds);
        ImageView cloudImage1 = new ImageView(clouds);

        Image person = new Image(Objects.requireNonNull(getClass().getResourceAsStream("person1.png")));
        ImageView personImage = new ImageView(person);

        Image person2 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("person2.png")));
        ImageView personImage2 = new ImageView(person2);


        //image adjustments to fit the page
        image.setFitHeight(100);
        image.setFitWidth(100);
        image.setPreserveRatio(true);
        image.setSmooth(true);
        image.setCache(true);

        customImage.setFitHeight(80);
        customImage.setFitWidth(80);
        customImage.setPreserveRatio(true);
        customImage.setSmooth(true);
        customImage.setCache(true);

        cloudImage.setFitHeight(200);
        cloudImage.setFitWidth(200);
        cloudImage.setPreserveRatio(true);
        cloudImage.setSmooth(true);
        cloudImage.setCache(true);
        cloudImage.setTranslateX(100);

        cloudImage1.setFitHeight(200);
        cloudImage1.setFitWidth(200);
        cloudImage1.setPreserveRatio(true);
        cloudImage1.setSmooth(true);
        cloudImage1.setCache(true);

        personImage.setFitHeight(50);
        personImage.setFitWidth(50);
        personImage.setPreserveRatio(true);
        personImage.setSmooth(true);
        personImage.setCache(true);

        personImage2.setFitHeight(50);
        personImage2.setFitWidth(50);
        personImage2.setPreserveRatio(true);
        personImage2.setSmooth(true);
        personImage2.setCache(true);



        //sets up menu bar
        menuBar = new Rectangle();
        menuBar.setWidth(700);
        menuBar.setHeight(100);
        menuBar.setTranslateX(0);
        menuBar.setTranslateY(-250);
        menuBar.setFill(Color.rgb(170, 148, 148));

        //creates location button
        location = new Button();
        location.setGraphic(image);
        location.setBackground(Background.EMPTY);
        location.setTranslateX(-240);
        location.setTranslateY(-250);

        //creates customization button
        customization = new Button();
        customization.setGraphic(customImage);
        customization.setBackground(Background.EMPTY);
        customization.setTranslateX(240);
        customization.setTranslateY(-250);

        //Gathers information from the short forecast
        String weather = forecast.getFirst().shortForecast;

        //creates menu StackPane
        menu = new StackPane();
        menu.getChildren().addAll(menuBar, location, customization);


        //Group creation to put person in
        character = new Button();
        character.setGraphic(personImage);
        character.setBackground(Background.EMPTY);
        character.setTranslateY(-300);
        character.setTranslateX(-280);

        textBox = new Ellipse(60, 40);
        textBox.setOpacity(0.5);
        textBox.setTranslateX(-200);
        textBox.setTranslateY(-350);

        shortCast = new Text(forecast.getFirst().shortForecast + " Today");
        shortCast.setFill(Color.WHITE);
        shortCast.setTranslateX(-200);
        shortCast.setTranslateY(-350);

        //Pane modifications for when it is cloudy or rainy
        if((weather.contains("Mostly") && weather.contains("Cloudy")) || weather.contains("Rain")){
            a = new Group(cloudImage);
            a.setTranslateX(-200);
            a.setTranslateY(50);
            Group b = new Group(cloudImage1);
            b.setTranslateX(-400);
            b.setTranslateY(50);
            Group bScene = new Group(b, a);
            bScene.setTranslateX(-400);
            hBaseScene = new HBox(todayTemp, farAndCel, bScene);
            menu.setTranslateX(-175);
            grass.setTranslateX(-175);
        }
        else if(weather.contains("Cloudy")){
            a = new Group(cloudImage);
            a.setTranslateX(-200);
            a.setTranslateY(50);
            hBaseScene = new HBox(todayTemp, farAndCel, a);
            menu.setTranslateX(-25);
            grass.setTranslateX(-25);
        }
        else{
            hBaseScene = new HBox(todayTemp, farAndCel, fc);
        }

        StackPane charactersOnGrass = new StackPane();
        charactersOnGrass.getChildren().addAll(grass, character, textBox, shortCast);

        //Base pane for sunny
        basePane = new BorderPane();
        basePane.setTop(hBaseScene);
        basePane.setCenter(charactersOnGrass);
        basePane.setBottom(menu);


        //TODO set style in startup depending on forecast


        if(forecast.getFirst().name.equals("This Afternoon")){
            farAndCel.setBackground(new Background(new BackgroundFill(Paint.valueOf("F2D14D"), null, null)));
            todayTemp.setBackground(new Background(new BackgroundFill(Paint.valueOf("81C8E7"), null, null)));
            basePane.setStyle("-fx-background-color: #3451AF");
        }else if(forecast.getFirst().name.equals("Tonight") || forecast.getFirst().name.equals("Overnight")){
            farAndCel.setBackground(new Background(new BackgroundFill(Paint.valueOf("A4B3E2"), null, null)));
            todayTemp.setBackground(new Background(new BackgroundFill(Paint.valueOf("627DD4"), null, null)));
            basePane.setStyle("-fx-background-color: #172C72");
        }
        else{
            //morning / mid-day
            farAndCel.setBackground(new Background(new BackgroundFill(Paint.valueOf("orange"), null, null)));
            todayTemp.setBackground(new Background(new BackgroundFill(Paint.valueOf("81C8E7"), null, null)));
            basePane.setStyle("-fx-background-color: #0EAFFF");
        }


    }
    public BorderPane getPane(){return basePane;}
    public Button getTodayTemp(){return todayTemp;}
    public Button getFarAndCel(){return farAndCel;}
    public Button getLocation(){return location;}
    public Button getCustomization(){return customization;}
    public void updateLocation(ArrayList<Period> forecast){
        todayTemp.setText(forecast.getFirst().temperature + "°F");
    }
    public void updateMiku(){
        Image mikuIcon = new Image(Objects.requireNonNull(getClass().getResourceAsStream("miku4.gif")));
        ImageView mikuImage = new ImageView(mikuIcon);
        mikuImage.setFitHeight(50);
        mikuImage.setFitWidth(50);
        mikuImage.setPreserveRatio(true);
        mikuImage.setSmooth(true);
        mikuImage.setCache(true);
        character.setGraphic(mikuImage);
    }
    public void updateGojo(){
        Image gojoIcon = new Image(Objects.requireNonNull(getClass().getResourceAsStream("gojo3.gif")));
        ImageView gojoImage = new ImageView(gojoIcon);
        gojoImage.setFitHeight(250);
        gojoImage.setFitWidth(250);
        gojoImage.setTranslateX(100);
        gojoImage.setTranslateY(-15);
        gojoImage.setPreserveRatio(true);
        gojoImage.setSmooth(true);
        gojoImage.setCache(true);
        character.setGraphic(gojoImage);
    }
    public void updateLuffy(){
        Image luffyIcon = new Image(Objects.requireNonNull(getClass().getResourceAsStream("luffyIchi1.gif")));
        ImageView luffyImage = new ImageView(luffyIcon);
        luffyImage.setFitHeight(350);
        luffyImage.setFitWidth(350);
        luffyImage.setTranslateX(100);
        luffyImage.setTranslateY(-15);
        luffyImage.setPreserveRatio(true);
        luffyImage.setSmooth(true);
        luffyImage.setCache(true);
        character.setGraphic(luffyImage);
    }
    public void updateBocchi(){
        Image bocchiIcon = new Image(Objects.requireNonNull(getClass().getResourceAsStream("bocchi1.gif")));
        ImageView bocchiImage = new ImageView(bocchiIcon);
        bocchiImage.setFitHeight(50);
        bocchiImage.setFitWidth(50);
        bocchiImage.setTranslateY(5);
        bocchiImage.setPreserveRatio(true);
        bocchiImage.setSmooth(true);
        bocchiImage.setCache(true);
        character.setGraphic(bocchiImage);
    }
}
