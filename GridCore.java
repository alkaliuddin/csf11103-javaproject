package SceneAB;

import java.io.FileInputStream;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class GridCore extends Application {   
    private static final int W = 800, H = 600; //Width and Height
    private Button clearBtn;
    
    private int nx = 20, ny = 20;
    private int delta = 10;
    private int turnL, turnR;
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        GridStatic gridAnim = new GridStatic();
        
        Rectangle drawArea = new Rectangle();
        drawArea.setX(10);
        drawArea.setY(10);
        drawArea.setWidth(W*0.99);
        drawArea.setHeight(H*0.65);
        drawArea.setFill(Color.WHITE);
        drawArea.setStrokeWidth(10);
        drawArea.setStroke(Color.BLACK);
        
        
        Image knobImg = new Image("file:src/knob.png");
        ImageView imgView = new ImageView();
        imgView.setImage(knobImg);
        imgView.setFitWidth(170);
        imgView.setPreserveRatio(true);
        imgView.setX(W*0.05);
        imgView.setY(H*0.7);
        
        ImageView imgView2 = new ImageView();
        imgView2.setImage(knobImg);
        imgView2.setFitWidth(170);
        imgView2.setPreserveRatio(true);
        imgView2.setX(W*0.75);
        imgView2.setY(H*0.7);
        
        Path trails = new Path(new MoveTo(nx, ny));
        trails.setStrokeWidth(5);
        trails.setStroke(Color.BLACK);
        
        clearBtn = new Button("Does nothing");
        clearBtn.setLayoutX(10);
        clearBtn.setLayoutY(10);
        
        Group mainGroup = new Group();
        Group sketchBoard = new Group(imgView, imgView2);
        Group drawZone = new Group(drawArea);
        Group sketchLines = new Group(trails);
        
        mainGroup.getChildren().add(sketchBoard);
        mainGroup.getChildren().add(drawZone);
        mainGroup.getChildren().add(sketchLines);
        
        Scene fg = new Scene(mainGroup, W, H);
        
        fg.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.UP) {
                ny -= delta;
                imgView.setRotate(turnR += 8);
            }
            else if (e.getCode() == KeyCode.DOWN) {
                ny += delta;
                imgView.setRotate(turnR -= 8);
            }
            else if (e.getCode() == KeyCode.RIGHT) {
                nx += delta;
                imgView2.setRotate(turnL += 8);
            }
            else if (e.getCode() == KeyCode.LEFT) {
                nx -= delta;
                imgView2.setRotate(turnL -= 8);
            }
            trails.getElements().add(new LineTo(nx, ny));
        });

        primaryStage.setResizable(false);
        primaryStage.setTitle("Sketcher");
        primaryStage.setScene(fg);
        primaryStage.show();

        gridAnim.requestFocus();
    }
    
    public static void main(String[] args) {
        Application.launch(args);
    }
}
