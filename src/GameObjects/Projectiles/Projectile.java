package GameObjects.Projectiles;

import GameObjects.GameObjects;
import java.awt.*;

public abstract class Projectile extends GameObjects {
    Integer xLocation;
    Integer yLocation;
    Integer movementVelocity;
    Image image;
    Boolean isPlayerProjectile;

    public void move(){}
    public void setImage(Image image) {

        this.image = image;
    }

    public Image getImage(){
        return image;
    }

    public void setX(int x) {

        this.xLocation = x;
    }

    public void setY(int y) {

        this.yLocation = y;
    }

    public Integer getXLocation(){
        return xLocation;
    }

    public Integer getYLocation() {
        return yLocation;
    }

    public Boolean getIsPlayerProjectile() {
        return isPlayerProjectile;
    }
}
