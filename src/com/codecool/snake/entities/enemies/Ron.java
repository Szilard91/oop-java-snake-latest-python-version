package com.codecool.snake.entities.enemies;

import com.codecool.snake.Game;
import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.Globals;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.Utils;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.geometry.Point2D;
import javafx.scene.layout.Pane;

import java.util.Random;

// a simple enemy TODO make better ones.
public class Ron extends GameEntity implements Animatable, Interactable {

    private Point2D heading;
    private static final int damage = 20;

    public Ron(Pane pane) {
        super(pane);

        setImage(Globals.Ron);
        pane.getChildren().add(this);
        int speed = 1;
        Random rnd = new Random();

        setX(rnd.nextDouble() * Globals.WINDOW_WIDTH);
        setY(rnd.nextDouble() * Globals.WINDOW_HEIGHT);
      
        double width = rnd.nextDouble() * Globals.WINDOW_WIDTH;
        double height = rnd.nextDouble() * Globals.WINDOW_HEIGHT;
        if(Globals.snakeHead.getHeight() != height || Globals.snakeHead.getWidth() != width){
            setX(width);
            setY(height);
        } else {
            setX(width + 150);
            setY(height + 150);
        }

        double direction = rnd.nextDouble() * 360;
        setRotate(direction);
        heading = Utils.directionToVector(direction, speed);
    }

    @Override
    public void step() {
        if (isOutOfBounds()) {
            destroy();
            new Ron(pane);
        }
        setX(getX() + heading.getX());
        setY(getY() + heading.getY());

        Globals.ronX = getX();
        Globals.ronY = getY();


    }

    @Override
    public void apply(SnakeHead player) {
        player.changeHealth(-damage);
        player.changeScore(-10);
        destroy();
        new Ron(pane);
    }


    @Override
    public String getMessage() {
        return "10 damage";
    }
}
