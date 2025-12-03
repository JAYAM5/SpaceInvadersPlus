package SpaceInvadersPlus.GameObjects.Characters;

import SpaceInvadersPlus.Events.EventBusSingleton;
import SpaceInvadersPlus.Events.EventType;
import SpaceInvadersPlus.GameObjects.GameObjects;
import SpaceInvadersPlus.GameObjects.Projectiles.Projectile;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public abstract class Ship extends GameObjects {

    Integer xLocation;
    Integer yLocation;
    Image image;
    Boolean isAlive = true;
    Boolean isExploding = false;
    protected long explosionStart;
    final int explosionDuration = 500;
    final int rightEdge = 748;
    final int leftEdge = -10;


    public List<Projectile> shoot() {
        return null;
    }

    public void move(){}
    public void setImage(Image image) {
        this.image = image;
    }

    public Image getImage(){
        return image;
    }

    public void setX(Integer x) {

        this.xLocation = x;
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

    public void explode(){
        ImageIcon playerImg = new ImageIcon("src/images/explosion.png");
        this.image = playerImg.getImage();
        this.image = image.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        setExplosionStart();
        isExploding = true;
        isAlive = false;
        EventBusSingleton.getInstance().postMessage(EventType.Explosion, "Alien has died.");
    }

    public Boolean getIsAlive(){
        return isAlive;
    }

    protected void setExplosionStart(){
        explosionStart = System.currentTimeMillis();
    }

    public Boolean explosionFinished(){
        return System.currentTimeMillis() - explosionStart >= explosionDuration;
    }
}
