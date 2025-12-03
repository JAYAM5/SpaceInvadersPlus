package SpaceInvadersPlus.GameObjects.Weapons;

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
        setY(yLocation += movementStrategy.getYMovementSpeed() / 2);
        if (xLocation < -10) {
            setX(-10);
            direction = RIGHT;
        }
        if (xLocation > 748) {
            setX(748);
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
