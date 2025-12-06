package SpaceInvadersPlus.GameObjects.Items;

import SpaceInvadersPlus.GameObjects.PlayerStrategies.IMovementStrategy;

import java.awt.*;

public abstract class Item {
    Integer xLocation;
    int yLocation;
    Image image;
    IMovementStrategy movementStrategy;
    Integer RIGHT = 1;
    Integer LEFT = -1;
    Integer direction = RIGHT;
    final int leftEdge = -10;
    final int rightEdge = 748;
    final int movementModifier = 2;
    final Integer IMAGE_SIZE = 50;

    public String getType(){
        return null;
    }

    public void setX(Integer x) {
        this.xLocation = x;
    }

    public Image getImage(){
        return image;
    }

    public void move() {
        setX(xLocation += direction * movementStrategy.getXMovementSpeed());
        setY(yLocation += movementStrategy.getYMovementSpeed() / movementModifier);
        if (xLocation < leftEdge) {
            setX(leftEdge);
            direction = RIGHT;
        }
        if (xLocation > rightEdge) {
            setX(rightEdge);
            direction = LEFT;
        }
    }

    public void setMovementStrategy(IMovementStrategy movementStrategy){
        this.movementStrategy = movementStrategy;
    }

    public void setY(Integer y) {
        this.yLocation = y;
    }

    public Integer getXLocation(){
        return xLocation;
    }

    public Integer getYLocation() {
        return yLocation;
    }
}
