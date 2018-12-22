package SceneAB;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.util.Duration;

public class GridStatic extends Pane {
    private double radius = 30;
    private double x = radius, y = radius;
    private double dx = 1, dy = 1;
    private Circle circle1, circle2, circle3, circle4;
    private Timeline upDownAnimation, leftRightAnimation;
    
    private void paintGrid() {
        double xStart = 0;
        double yStart = 0;
        double xEnd = getWidth();
        double yEnd = getHeight();
        
        double xCoords1 = getWidth() / 3;
        double xCoords2 = getWidth() / 1.5;
        double yCoords1 = getHeight() / 3;        
        double yCoords2 = getHeight() / 1.5;
        
        Line bdTop = new Line(xStart, yStart , getWidth(), yStart);
        Line bdBtm = new Line(xStart, getHeight(), getWidth(), getHeight());
        Line bdLeft = new Line(xStart, yStart, xStart, getHeight());
        Line bdRight = new Line(getWidth(), yStart, getWidth(), getHeight());
        
        bdTop.setStrokeWidth(10);
        bdBtm.setStrokeWidth(10);
        bdLeft.setStrokeWidth(10);
        bdRight.setStrokeWidth(10);
        
        Line vert1 = new Line(xCoords1, xStart, xCoords1, yEnd);
        Line hor1 = new Line(xStart, yCoords1, xEnd, yCoords1);
        
        Line vert2 = new Line(xCoords2, xStart, xCoords2, yEnd);
        Line hor2 = new Line(xStart, yCoords2, xEnd, yCoords2);
        
        vert1.setStrokeWidth(10);
        vert2.setStrokeWidth(10);
        
        hor1.setStrokeWidth(10);
        hor2.setStrokeWidth(10);

        getChildren().clear();
        getChildren().addAll(bdTop, bdBtm, bdLeft, bdRight, vert1, vert2, hor1, hor2);

        //
        
        circle1 = new Circle(xCoords1, yStart, radius);
        circle2 = new Circle(xCoords2, yStart, radius);
        circle3 = new Circle(xStart, yCoords1, radius);
        circle4 = new Circle(xStart, yCoords2, radius);

        getChildren().addAll(circle1, circle2, circle3, circle4);
        
        upDownAnimation = new Timeline(new KeyFrame(Duration.millis(100), e -> topDown()));
        upDownAnimation.setCycleCount(Timeline.INDEFINITE);
        
        leftRightAnimation = new Timeline(new KeyFrame(Duration.millis(10), e -> leftRight()));
        leftRightAnimation.setCycleCount(Timeline.INDEFINITE);
        leftRightAnimation.setRate(5);
    }
    
    public void play() {
        upDownAnimation.play();
        leftRightAnimation.play();
    }
    
    public void pause() {
        upDownAnimation.pause();
        leftRightAnimation.pause();
    }
    
    public DoubleProperty rateProperty() {
        return upDownAnimation.rateProperty();
    }
    
    protected void topDown() {
        if (y < radius || y > getHeight() - radius) {
            dy *= -1;
            //dy *= Math.random() + getWidth() - radius;
        }

        y += dy;
        
        circle1.setCenterY(y);
        circle2.setCenterY(y);
    }
    
    protected void leftRight() {
        if (x < radius || x > getWidth() - radius) {
            dx *= -1;
            //dx *= Math.random() + getWidth() - radius;
        }
        
        x += dx;

        circle3.setCenterX(x);
        circle4.setCenterX(x);
    }
    
    @Override
    public void setWidth(double width) {
        super.setWidth(width);
        paintGrid();
    }
    
    @Override
    public void setHeight(double height) {
        super.setHeight(height);
        paintGrid();
    }
}
