package SpaceInvadersPlus.GameObjects.Characters;

import SpaceInvadersPlus.GameObjects.PlayerStrategies.IMovementStrategy;
import SpaceInvadersPlus.GameObjects.PlayerStrategies.IShootingStrategy;
import SpaceInvadersPlus.GameObjects.Projectiles.Projectile;
import SpaceInvadersPlus.GameObjects.Items.Item;

import javax.swing.*;
import java.awt.*;
import java.util.List;


public class Alien extends Ship {

    IMovementStrategy movementStrategy;
    IShootingStrategy shootingStrategy;
    Integer RIGHT = 1;
    Integer LEFT = -1;
    Integer direction = RIGHT;
    Boolean isItem = false;
    final Integer IMAGE_SIZE = 20;

    public Alien(Integer xLocation, Integer yLocation, IMovementStrategy movementStrategy, IShootingStrategy shootingStrategy, Boolean defaultDirection){
        this.xLocation = xLocation;
        this.yLocation = yLocation;
        this.movementStrategy = movementStrategy;
        this.shootingStrategy = shootingStrategy;
        if(defaultDirection){
            this.direction = RIGHT;
        }
        else{
            this.direction = LEFT;
        }
        initAliens();
    }


    public void move() {
        setX(xLocation += direction * movementStrategy.getXMovementSpeed());
        if (xLocation < leftEdge) {
            setX(leftEdge);
            direction = RIGHT;
            setY(yLocation += movementStrategy.getYMovementSpeed());
        }
        if (xLocation > rightEdge) {
            setX(rightEdge);
            direction = LEFT;
            setY(yLocation += movementStrategy.getYMovementSpeed());
        }
    }

    private void initAliens() {

        ImageIcon playerImg = new ImageIcon("src/images/base_alien.png");
        this.image = playerImg.getImage();
        this.image = image.getScaledInstance(IMAGE_SIZE, IMAGE_SIZE, Image.SCALE_SMOOTH);
    }


    public Boolean shouldShoot(){
       return shootingStrategy.shouldShoot();
    }

    public List<Projectile> shoot() {
        return shootingStrategy.shoot(this);
    }

    public Boolean getIsItem(){
        return isItem;
    }

    public Item dropItem(){
        return null;
    }

}
