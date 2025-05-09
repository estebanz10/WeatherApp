import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;


import java.util.Objects;

public class CustomizePane {
    Scene customizeScene;
    Button mikuButton, gojoButton, luffyButton, bocchiButton;
    BorderPane inputStack;
    Text pageTitle;

    public CustomizePane() {
        pageTitle = new Text("Customization Settings");
        pageTitle.setFont(Font.font("Verdana", FontWeight.BOLD, 40));
        pageTitle.setTranslateY(30);
        pageTitle.setTranslateX(90);

        //sets up images
        Image backIcon = new Image(Objects.requireNonNull(getClass().getResourceAsStream("back.png")));
        ImageView backImage = new ImageView(backIcon);

        Image mikuIcon = new Image(Objects.requireNonNull(getClass().getResourceAsStream("miku2.gif")));
        ImageView mikuImage = new ImageView(mikuIcon);
        ImageView mikuImage1 = new ImageView(mikuIcon);

        Image gojoIcon = new Image(Objects.requireNonNull(getClass().getResourceAsStream("gojo1.gif")));
        ImageView gojoImage = new ImageView(gojoIcon);
        ImageView gojoImage1 = new ImageView(gojoIcon);

        Image luffyIcon = new Image(Objects.requireNonNull(getClass().getResourceAsStream("luffy2.gif")));
        ImageView luffyImage = new ImageView(luffyIcon);
        ImageView luffyImage1 = new ImageView(luffyIcon);

        Image bocchiIcon = new Image(Objects.requireNonNull(getClass().getResourceAsStream("bocchi3.gif")));
        ImageView bocchiImage = new ImageView(bocchiIcon);
        ImageView bocchiImage1 = new ImageView(bocchiIcon);

        //image adjustments to fit the page
        backImage.setFitHeight(50);
        backImage.setFitWidth(50);
        backImage.setPreserveRatio(true);
        backImage.setSmooth(true);
        backImage.setCache(true);

        //sets up miku image
        mikuImage.setFitHeight(50);
        mikuImage.setFitWidth(50);
        mikuImage.setPreserveRatio(true);
        mikuImage.setSmooth(true);
        mikuImage.setCache(true);

        mikuImage1.setFitHeight(50);
        mikuImage1.setFitWidth(50);
        mikuImage1.setPreserveRatio(true);
        mikuImage1.setSmooth(true);
        mikuImage1.setCache(true);


        //sets up gojo's image
        gojoImage.setFitHeight(50);
        gojoImage.setFitWidth(50);
        gojoImage.setPreserveRatio(true);
        gojoImage.setSmooth(true);
        gojoImage.setCache(true);

        gojoImage1.setFitHeight(50);
        gojoImage1.setFitWidth(50);
        gojoImage1.setPreserveRatio(true);
        gojoImage1.setSmooth(true);
        gojoImage1.setCache(true);

        //sets up luffy's image
        luffyImage.setFitHeight(50);
        luffyImage.setFitWidth(50);
        luffyImage.setPreserveRatio(true);
        luffyImage.setSmooth(true);
        luffyImage.setCache(true);

        luffyImage1.setFitHeight(50);
        luffyImage1.setFitWidth(50);
        luffyImage1.setPreserveRatio(true);
        luffyImage1.setSmooth(true);
        luffyImage1.setCache(true);

        //sets up bocchi's image
        bocchiImage.setFitHeight(50);
        bocchiImage.setFitWidth(50);
        bocchiImage.setPreserveRatio(true);
        bocchiImage.setSmooth(true);
        bocchiImage.setCache(true);

        bocchiImage1.setFitHeight(50);
        bocchiImage1.setFitWidth(50);
        bocchiImage1.setPreserveRatio(true);
        bocchiImage1.setSmooth(true);
        bocchiImage1.setCache(true);

        //image positioning for the buttons
        Group a = new Group(mikuImage);
        Group b = new Group(mikuImage1);
        Group miku = new Group(a, b);

        a.setTranslateX(400);
        miku.setTranslateX(130);
        miku.setTranslateY(10);

        Group c = new Group(gojoImage);
        Group d = new Group(gojoImage1);
        Group gojo = new Group(c, d);

        c.setTranslateX(400);
        gojo.setTranslateX(130);
        gojo.setTranslateY(-25);


        Group e = new Group(luffyImage);
        Group f = new Group(luffyImage1);
        Group luffy = new Group(e, f);

        e.setTranslateX(400);
        luffy.setTranslateX(130);
        luffy.setTranslateY(-60);

        Group g = new Group(bocchiImage);
        Group h = new Group(bocchiImage1);
        Group bocchi = new Group(g, h);

        g.setTranslateX(400);
        bocchi.setTranslateX(130);
        bocchi.setTranslateY(-80);


        //sets up all buttons with images in them
        mikuButton = new Button("TRY MIKU PACK");
        mikuButton.setFont((Font.font("Verdana", FontWeight.BOLD, 30)));
        mikuButton.setTextFill(Color .BLACK);
        mikuButton.setStyle("-fx-background-color: #e12885");
        mikuButton.setTranslateY(80);
        mikuButton.setTranslateX(100);
        mikuButton.setMinWidth(500);
        mikuButton.setMinHeight(100);


        gojoButton = new Button("TRY GOJO PACK");
        gojoButton.setFont((Font.font("Verdana", FontWeight.BOLD, 30)));
        gojoButton.setTextFill(Color .BLACK);
        gojoButton.setStyle("-fx-background-color: #9B8AB4");
        gojoButton.setTranslateY(50);
        gojoButton.setTranslateX(100);
        gojoButton.setMinWidth(500);
        gojoButton.setMinHeight(100);

        luffyButton = new Button("TRY LUFFY PACK");
        luffyButton.setFont((Font.font("Verdana", FontWeight.BOLD, 30)));
        luffyButton.setTextFill(Color .BLACK);
        luffyButton.setStyle("-fx-background-color: #a0976e");
        luffyButton.setTranslateY(20);
        luffyButton.setTranslateX(100);
        luffyButton.setMinWidth(500);
        luffyButton.setMinHeight(100);


        bocchiButton = new Button("TRY BOCCHI PACK");
        bocchiButton.setFont((Font.font("Verdana", FontWeight.BOLD, 30)));
        bocchiButton.setTextFill(Color .BLACK);
        bocchiButton.setStyle("-fx-background-color: #e8a7a1");
        bocchiButton.setTranslateY(-10);
        bocchiButton.setTranslateX(100);
        bocchiButton.setMinWidth(500);
        bocchiButton.setMinHeight(100);









        VBox horiz = new VBox(pageTitle, mikuButton, miku, gojoButton, gojo, luffyButton, luffy, bocchiButton, bocchi);
        inputStack = new BorderPane();
        inputStack.setCenter(horiz);
        inputStack.setStyle("-fx-background-color: AA9494;");
        customizeScene = new Scene(inputStack, 700, 700);
    }
    public Scene getScene() {return customizeScene;}
    public Button getMikuButton(){return mikuButton;}
    public Button getGojoButton(){return gojoButton;}
    public Button getLuffyButton(){return luffyButton;}
    public Button getBocchiButton(){return bocchiButton;}
}