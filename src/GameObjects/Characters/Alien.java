package GameObjects.Characters;

import GameObjects.PlayerStrategies.BasePlayerMovement;
import GameObjects.PlayerStrategies.IMovementStrategy;
import GameObjects.PlayerStrategies.IShootingStrategy;
import GameObjects.PlayerStrategies.PlayerTriShoot;
import GameObjects.Projectiles.Projectile;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class Alien extends Ships{
    Ships currentShip;

    IMovementStrategy movementStrategy;
    IShootingStrategy shootingStrategy;
    Integer RIGHT = 1;
    Integer LEFT = -1;
    Integer direction = RIGHT;



    public Alien(Integer xLocation, Integer yLocation, IMovementStrategy movementStrategy, IShootingStrategy shootingStrategy){
        this.xLocation = xLocation;
        this.yLocation = yLocation;
        this.movementStrategy = movementStrategy;
        this.shootingStrategy = shootingStrategy;
        initAliens();
    }


    public void move() {
        setX(xLocation += direction * movementStrategy.getXMovementSpeed());
        setY(yLocation += movementStrategy.getYMovementSpeed() / 2);
        System.out.println("alien position is: " + getYLocation());
        if (xLocation < -10) {
            setX(-10);
            direction = RIGHT;
        }
        if (xLocation > 748) {
            setX(748);
            direction = LEFT;
        }
    }

    private void initAliens() {

        ImageIcon playerImg = new ImageIcon("src/images/base_alien.png");
        this.image = playerImg.getImage();
        this.image = image.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
    }

    public void setShip(Ships ship) {
        currentShip = ship;
    }

    public Ships getShip() {
        return currentShip;
    }

    public Boolean shouldShoot(){
       return shootingStrategy.shouldShoot();
    }

    public List<Projectile> shoot() {
        return shootingStrategy.shoot(this);
    }

    public void setMovementStrategy(IMovementStrategy movementStrategy){
        this.movementStrategy = movementStrategy;
    }

    public void setShootingStrategy(IShootingStrategy shootingStrategy){
        this.shootingStrategy = shootingStrategy;
    }

}
