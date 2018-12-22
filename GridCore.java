package SceneAB;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class GridCore extends Application {   
    private Button cursor;
    
    boolean moveUp, moveDown, moveRight, moveLeft;
    
    private int nx = 720/2, ny = 720/2;
    private int temp = 10;
    
    @Override
    public void start(Stage primaryStage) {
        GridStatic gridAnim = new GridStatic();
        Scene fg, bg;
        
        cursor = new Button("Switch");
        moveCursorNext(10, 10);
        
        Path trails = new Path(new MoveTo(nx, ny));
        trails.setStrokeWidth(5);
        trails.setStroke(Color.BLACK);
        
        Group cursorGrp = new Group(cursor);
        cursorGrp.getChildren().add(trails);
        
        fg = new Scene(cursorGrp, 720, 720, Color.TRANSPARENT);
        bg = new Scene(gridAnim, 720, 720);
        
        
        fg.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.UP) {
                moveUp = true;
                ny -= temp;
            }
            else if (e.getCode() == KeyCode.DOWN) {
                moveDown = true;
                ny += temp;
            }
            else if (e.getCode() == KeyCode.RIGHT) {
                moveRight = true;
                nx += temp;
            }
            else if (e.getCode() == KeyCode.LEFT) {
                moveLeft = true;
                nx -= temp;
            }
            trails.getElements().add(new LineTo(nx, ny));
        });
        
        fg.setOnKeyReleased(e -> {
            if (e.getCode() == KeyCode.UP) {
                moveUp = false;
            }
            else if (e.getCode() == KeyCode.DOWN) {
                moveDown = false;
            }
            else if (e.getCode() == KeyCode.RIGHT) {
                moveRight = false;
            }
            else if (e.getCode() == KeyCode.LEFT) {
                moveLeft = false;
            }
        });
        
        //cursor.setTranslateX(randomizeRange(10, 100));
        //cursor.setTranslateY(randomizeRange(10, 100));
        cursor.setOnAction(e -> primaryStage.setScene(bg));

        gridAnim.setOnMousePressed(e -> gridAnim.pause());
        gridAnim.setOnMouseReleased(e -> gridAnim.play());
        
        primaryStage.setResizable(false);
        primaryStage.setTitle("Gridman");
        primaryStage.setScene(fg);
        primaryStage.show();
        
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                int dx = 0, dy = 0;
                
                if (moveUp) dy -= 1;
                if (moveDown) dy += 1;
                if (moveRight) dx += 1; 
                if (moveLeft) dx -= 1;
                
                moveCursor(dx, dy);
            }
            
        };
        timer.start();
        
        gridAnim.requestFocus();
    }
    
    private void moveCursor(int dx, int dy) {
        if (dx == 0 && dy == 0) return;
        
        final double cx = cursor.getBoundsInLocal().getWidth() / 2;
        final double cy = cursor.getBoundsInLocal().getHeight() / 2;
        
        double x = cx + cursor.getLayoutX() + dx;
        double y = cy + cursor.getLayoutY() + dy;
        
        moveCursorNext(x, y);
    }
    
    private void moveCursorNext(double x, double y) {
        final double cx = cursor.getBoundsInLocal().getWidth() / 2;
        final double cy = cursor.getBoundsInLocal().getHeight() / 2;
        
        double W = 600, H = 600;
        
        if (x - cx >= 0 && x + cx <= W && y - cy >= 0 && y + cy <= H) {
            cursor.relocate(x - cx, y - cy);
        }
    }
    
    int randomizeRange(int min, int max) {
        int range = (max - min) + 1;
        return (int)(Math.random() * range) + min ;
    }
    
    public static void main(String[] args) {
        Application.launch(args);
    }
}
