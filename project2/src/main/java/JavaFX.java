import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import weather.Period;
import weather.WeatherAPI;

import java.util.ArrayList;

public class JavaFX extends Application {
	String region = "LOT";
	int gridx = 77;
	int gridy = 70;
	int far = 1;
	BasePane base;
	DataPane data;
	ArrayList<Period> forecast = WeatherAPI.getForecast(region,gridx,gridy);
	public static void main(String[] args) {
		launch(args);
	}

	//feel free to remove the starter code from this method
	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("I'm a professional Weather App!");
//		ArrayList<Period> forecast = WeatherAPI.getForecast(region,gridx,gridy);
		if (forecast == null){
			throw new RuntimeException("Forecast did not load");
		}

		//sets up base pane and scene
		base = new BasePane(forecast);
		data = new DataPane(base, forecast, far);
		LocationPane location = new LocationPane();
		CustomizePane customize = new CustomizePane();


		Scene scene = new Scene(base.getPane(), 700, 700);
		primaryStage.setScene(scene);
		primaryStage.show();


		//goes to data scene
		base.getTodayTemp().setOnAction(e->primaryStage.setScene(data.getDataScene()));

		//when sun pressed, toggle between Fahrenheit and Celsius
		base.getFarAndCel().setOnAction(e->{
			if(far == 1){
				base.getTodayTemp().setText(Math.round((forecast.getFirst().temperature - 32) * (5.0/9)) + "°C");
				far = 0;
				data.updateFuture(forecast, far);
			}else{
				base.getTodayTemp().setText(forecast.getFirst().temperature + "°F");
				far = 1;
				data.updateFuture(forecast, far);
			}
		});

		//when location pressed, go to location scene
		base.getLocation().setOnAction(e-> primaryStage.setScene(location.getScene()));

		//when customize pressed, go to customization scene
		base.getCustomization().setOnAction(e-> primaryStage.setScene(customize.getScene()));

		//leaves data scene
		data.getGiantInvisibleButton().setOnAction(e->primaryStage.setScene(scene));

		//when location back button pressed, go back to base scene
		location.getBack().setOnAction(e->primaryStage.setScene(scene));

		//When location random button pressed, change location to a random location
		location.getTravelRandom().setOnAction(e->{
			String[] coords = location.getRandomCoords();
			try{
				region = coords[0];
				gridx = Integer.parseInt(coords[1]);
				gridy = Integer.parseInt(coords[2]);
				forecast = WeatherAPI.getForecast(region,gridx,gridy);
				if (forecast == null){
					throw new RuntimeException("Forecast did not load");
				}
				base.updateLocation(forecast);
				data.updateLocation(forecast, region, gridx, gridy);
			}catch(Exception ex){
				System.out.println("Something bad happened");
			}
		});

		//When location distinct button pressed, change location to designated location
		location.getTravelDistinct().setOnAction(e->{
			String input = location.getInput();
			String[] coords = location.getLocationCoords(input);
			try{
				region = coords[0];
				gridx = Integer.parseInt(coords[1]);
				gridy = Integer.parseInt(coords[2]);
				forecast = WeatherAPI.getForecast(region,gridx,gridy);
				if (forecast == null){
					throw new RuntimeException("Forecast did not load");
				}
				base.updateLocation(forecast);
				data.updateLocation(forecast, region, gridx, gridy);
			}catch(Exception ex){
				System.out.println("Something bad happened");
			}

		});

		//when customize miku button is pressed, change characters in base pane to miku
		customize.getMikuButton().setOnAction(e->{
			base.updateMiku();
			primaryStage.setScene(scene);
		});

		customize.getGojoButton().setOnAction(e->{
			base.updateGojo();
			primaryStage.setScene(scene);
		});

		customize.getLuffyButton().setOnAction(e->{
			base.updateLuffy();
			primaryStage.setScene(scene);
		});

		customize.getBocchiButton().setOnAction(e->{
			base.updateBocchi();
			primaryStage.setScene(scene);
		});

	}
}