package SpaceInvadersPlus.GameObjects.Characters;

import SpaceInvadersPlus.GameObjects.PlayerStrategies.IMovementStrategy;
import SpaceInvadersPlus.GameObjects.PlayerStrategies.IShootingStrategy;
import SpaceInvadersPlus.GameObjects.Projectiles.Projectile;
import SpaceInvadersPlus.GameObjects.Weapons.Item;

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
    Boolean isItem = false;



    public Alien(Integer xLocation, Integer yLocation, IMovementStrategy movementStrategy, IShootingStrategy shootingStrategy, Boolean defaultDirection){
        this.xLocation = xLocation;
        this.yLocation = yLocation;
        this.movementStrategy = movementStrategy;
        this.shootingStrategy = shootingStrategy;
        if(defaultDirection == true){
            this.direction = RIGHT;
        }
        else{
            this.direction = LEFT;
        }
        initAliens();
    }


    public void move() {
        setX(xLocation += direction * movementStrategy.getXMovementSpeed());
        if (xLocation < -10) {
            setX(-10);
            direction = RIGHT;
            setY(yLocation += movementStrategy.getYMovementSpeed());
        }
        if (xLocation > 748) {
            setX(748);
            direction = LEFT;
            setY(yLocation += movementStrategy.getYMovementSpeed());
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

    public Boolean getIsItem(){
        return isItem;
    }

    public Item dropItem(){
        return null;
    }

}
