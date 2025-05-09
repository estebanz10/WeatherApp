import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import weather.Hazards;
import weather.Period;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;

public class DataPane {
    Text dates, futureForecast, todayText, windData , humidity, alerts;
    Button giantInvisibleButton, wind, rain, alert;
    BorderPane blurredPane, transparentPane;
    StackPane dataStack;
    HBox hDataScene;
    VBox vDataScene;
    Rectangle dataLine, alertPage;
    Scene dataScene;
    public DataPane(BasePane base, ArrayList<Period> forecast, int far) {
        //invisible button used to go back to base scene from data scene
        giantInvisibleButton = new Button();
        giantInvisibleButton.setMinSize(700, 700);
        giantInvisibleButton.setBackground(Background.EMPTY);

        //sets up transparent pane where information will be displayed
        transparentPane = new BorderPane();
        transparentPane.setMaxSize(500, 500);
        transparentPane.setStyle("-fx-background-color: #D9D9D9");
        transparentPane.setOpacity(0.5);

        futureForecast = new Text();

        futureForecast.setText("   " + forecast.get(0).temperature + "        " + forecast.get(2).temperature + "        " + forecast.get(4).temperature
                + "        " + forecast.get(6).temperature + "        " + forecast.get(8).temperature);



        futureForecast.setTranslateX(50);
        futureForecast.setTranslateY(50);
        futureForecast.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
        dates = new Text();
        LocalDate today = LocalDate.now();
        String datesSetter;
        String[] dateArr = new String[5];
        dateArr[0] = today.getMonthValue() + "/" + today.getDayOfMonth();
        dateArr[1] = today.plusDays(1).getMonthValue() + "/" + today.plusDays(1).getDayOfMonth();
        dateArr[2] = today.plusDays(2).getMonthValue() + "/" + today.plusDays(2).getDayOfMonth();
        dateArr[3] = today.plusDays(3).getMonthValue() + "/" + today.plusDays(3).getDayOfMonth();
        dateArr[4] = today.plusDays(4).getMonthValue() + "/" + today.plusDays(4).getDayOfMonth();


        datesSetter = "       " + dateArr[0] + "                  " + dateArr[1] + "                  " +
                                  dateArr[2] + "                  " + dateArr[3] + "                  " + dateArr[4];
        dates.setText(datesSetter);

        //line that separates future forecast and data relating to current day
        dataLine = new Rectangle();
        dataLine.setHeight(5);
        dataLine.setWidth(500);
        dataLine.setTranslateY(60);

        //Just the text "Today"

        todayText = new Text(forecast.getFirst().name);
        todayText.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
        if(forecast.getFirst().name.equals("This Afternoon")){
            todayText.setTranslateX(190);
        }else{
            todayText.setTranslateX(220);
        }

        todayText.setTranslateY(75);

        //Text for wind direction and speed
        windData = new Text();
        windData.setText(forecast.getFirst().windDirection + " " + forecast.getFirst().windSpeed);
        windData .setFont(Font.font("Verdana", FontWeight.BOLD, 15));
        windData .setTranslateX(50);
        windData .setTranslateY(150);

        Image windIcon = new Image(Objects.requireNonNull(getClass().getResourceAsStream("wind.png")));
        ImageView windImage = new ImageView(windIcon);

        windImage.setFitHeight(40);
        windImage.setFitWidth(40);
        windImage.setPreserveRatio(true);
        windImage.setSmooth(true);
        windImage.setCache(true);

        wind = new Button();
        wind.setGraphic(windImage);
        wind.setBackground(Background.EMPTY);
        wind.setTranslateX(-150);
        wind.setTranslateY(-50);

        Image rainIcon = new Image(Objects.requireNonNull(getClass().getResourceAsStream("rain.png")));
        ImageView rainImage = new ImageView(rainIcon);

        rainImage.setFitHeight(40);
        rainImage.setFitWidth(40);
        rainImage.setPreserveRatio(true);
        rainImage.setSmooth(true);
        rainImage.setCache(true);

        rain = new Button();
        rain.setGraphic(rainImage);
        rain.setBackground(Background.EMPTY);
        rain.setTranslateX(100);
        rain.setTranslateY(-50);

        Image alertIcon = new Image(Objects.requireNonNull(getClass().getResourceAsStream("alert.png")));
        ImageView alertImage = new ImageView(alertIcon);

        alertImage.setFitHeight(40);
        alertImage.setFitWidth(40);
        alertImage.setPreserveRatio(true);
        alertImage.setSmooth(true);
        alertImage.setCache(true);

        alert = new Button();
        alert.setGraphic(alertImage);
        alert.setBackground(Background.EMPTY);
        alert.setTranslateX(-160);
        alert.setTranslateY(160);

        //text for humidity, I think, number aligns with apple weather for today
        humidity = new Text();
        humidity.setText(forecast.getFirst().probabilityOfPrecipitation.value + "%");
        humidity.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
        humidity.setTranslateX(200);
        humidity.setTranslateY(150);

        //gey box alerts will be displayed in
        alertPage = new Rectangle();
        alertPage.setHeight(100);
        alertPage.setWidth(450);
        alertPage.setFill(Color.GRAY);
        alertPage.setTranslateY(-40);
        alertPage.setTranslateX(25);

        //text for alerts
        alerts = new Text();
        Hazards alertParsed = MyWeatherAPI.getAlert("LOT",77,70);
        try{
            alerts.setText(alertParsed.values.getFirst().value.getFirst().phenomenon);
        }catch (Exception e){
            alerts.setText("All safe for now :)");
        }
        alerts.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
		alerts.setTranslateX(0);
        alerts.setTranslateY(150);

        //adds data to transparent pane
        hDataScene = new HBox(windData , humidity);
        vDataScene = new VBox(futureForecast, dates, dataLine, todayText);
        dates.setTranslateX(50);
        dates.setTranslateY(50);
        transparentPane.setTop(vDataScene);
        transparentPane.setCenter(hDataScene);
        transparentPane.setBottom(alertPage);

        //duplicates the old base pane as to not have to rewrite the code in this file
        BasePane oldBase = new BasePane(forecast);
        blurredPane = oldBase.getPane();
        ColorAdjust adj = new ColorAdjust();
        GaussianBlur blur = new GaussianBlur(55);
        adj.setInput(blur);
        blurredPane.setEffect(adj);
        blurredPane.setTranslateY(125);
        String weather = forecast.getFirst().shortForecast;
        if((weather.contains("Mostly") && weather.contains("Cloudy")) || weather.contains("Rain")){
            blurredPane.setTranslateX(175);
        }
        else if(weather.contains("Cloudy")){
            blurredPane.setTranslateX(20);
        }


        //sets up data scene
        dataStack = new StackPane();
        dataStack.getChildren().addAll(blurredPane, giantInvisibleButton, transparentPane, wind, rain, alert, alerts);
        dataScene = new Scene(dataStack, 700, 700);
        dataScene.setFill(Color.TRANSPARENT);
    }
    public Button getGiantInvisibleButton(){return giantInvisibleButton;}
    public Scene getDataScene(){return dataScene;}
    public void updateFuture(ArrayList<Period> forecast, int far){
        int curr = forecast.get(0).temperature;
        int currPlusOne = forecast.get(1).temperature;
        int currPlusTwo = forecast.get(2).temperature;
        int currPlusThree = forecast.get(3).temperature;
        int currPlusFour = forecast.get(4).temperature;
        String stringCurr;
        String stringPlusOne;
        String stringPlusTwo;
        String stringPlusThree;
        String stringPlusFour;
        if(far == 0){
            if(farToCel(curr) < 10){
                stringCurr = "0" + farToCel(curr);
            }else{
                stringCurr = "" + farToCel(curr);
            }
            if(farToCel(currPlusOne) < 10){
                stringPlusOne = "0" + farToCel(currPlusOne);
            }else{
                stringPlusOne = "" + farToCel(currPlusOne);
            }
            if(farToCel(currPlusTwo) < 10){
                stringPlusTwo = "0" + farToCel(currPlusTwo);
            }else{
                stringPlusTwo = "" + farToCel(currPlusTwo);
            }
            if(farToCel(currPlusThree) < 10){
                stringPlusThree = "0" + farToCel(currPlusThree);
            }else{
                stringPlusThree = "" + farToCel(currPlusThree);
            }
            if(farToCel(currPlusFour) < 10){
                stringPlusFour = "0" + farToCel(currPlusFour);
            }else{
                stringPlusFour = "" + farToCel(currPlusFour);
            }

            futureForecast.setText("   " + stringCurr + "        " + stringPlusOne + "        " + stringPlusTwo
                    + "        " + stringPlusThree + "        " + stringPlusFour);
        }else{
            futureForecast.setText("   " + forecast.get(0).temperature + "        " + forecast.get(1).temperature + "        " + forecast.get(2).temperature
                    + "        " + forecast.get(3).temperature + "        " + forecast.get(4).temperature);
        }

    }
    public long farToCel(int far){return Math.round((far - 32) * (5.0/9));}
    public void updateLocation(ArrayList<Period> forecast, String region, int gridx, int gridy){
        futureForecast.setText("   " + forecast.get(0).temperature + "        " + forecast.get(2).temperature + "        " + forecast.get(4).temperature
                + "        " + forecast.get(6).temperature + "        " + forecast.get(8).temperature);
        todayText.setText(forecast.getFirst().name);
        windData.setText(forecast.getFirst().windDirection + " " + forecast.getFirst().windSpeed);
        humidity.setText(forecast.getFirst().probabilityOfPrecipitation.value + "%");

        Hazards alertParsed = MyWeatherAPI.getAlert(region,gridx,gridy);
        try{
            alerts.setText(alertParsed.values.getFirst().value.getFirst().phenomenon);
        }catch (Exception e){
            alerts.setText("All safe for now :)");
        }

    }
}
