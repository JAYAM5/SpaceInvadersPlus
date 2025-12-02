package SpaceInvadersPlus.GameObjects.Weapons;

import SpaceInvadersPlus.GameObjects.PlayerStrategies.IMovementStrategy;
import SpaceInvadersPlus.GameObjects.PlayerStrategies.IShootingStrategy;
import SpaceInvadersPlus.GameObjects.Projectiles.Projectile;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class Shoe extends Item {
    Image image;
    Integer xLocation;
    int yLocation;
    IMovementStrategy movementStrategy;
    IShootingStrategy shootingStrategy;
    Boolean isAlive = false;
    Integer RIGHT = 1;
    Integer LEFT = -1;
    Integer direction = RIGHT;
    public long explosionStart;
    Boolean isExploding = false;

    public Shoe(){
        initShoe();

    }
    public Shoe(Integer xLocation, Integer yLocation, IMovementStrategy movementStrategy, IShootingStrategy shootingStrategy) {
        initShoe();
        this.xLocation = xLocation;
        this.yLocation = yLocation;
        this.movementStrategy = movementStrategy;
        this.shootingStrategy = shootingStrategy;
    }

    private void initShoe() {

        ImageIcon playerImg = new ImageIcon("src/images/shoe.png");
        this.image = playerImg.getImage();
        this.image = image.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
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

    public List<Projectile> shoot() {
        return null;
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
    }

    protected void setExplosionStart(){
        explosionStart = System.currentTimeMillis();
    }

    public Boolean explosionFinished(){
        return System.currentTimeMillis() - explosionStart >= 500;
    }

    public void setMovementStrategy(IMovementStrategy movementStrategy){
        this.movementStrategy = movementStrategy;
    }

    public String getType(){
        return "Shoe";
    }
}
