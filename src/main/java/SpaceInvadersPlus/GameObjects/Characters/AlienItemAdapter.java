package SpaceInvadersPlus.GameObjects.Characters;

import SpaceInvadersPlus.Events.EventBusSingleton;
import SpaceInvadersPlus.Events.EventType;
import SpaceInvadersPlus.GameObjects.PlayerStrategies.IMovementStrategy;
import SpaceInvadersPlus.GameObjects.PlayerStrategies.IShootingStrategy;
import SpaceInvadersPlus.GameObjects.Items.Item;

import javax.swing.*;
import java.awt.*;

public class AlienItemAdapter extends Alien {
    Item item;
    private static final int explosionDuration = 500;
    public AlienItemAdapter(Item item, Integer xLocation, Integer yLocation, IMovementStrategy movementStrategy, IShootingStrategy shootingStrategy, Boolean defaultDirection){
        super(xLocation,yLocation, movementStrategy, shootingStrategy, defaultDirection);
        this.item = item;
        this.image = item.getImage();
        item.setX(xLocation);
        item.setY(yLocation);
        item.setMovementStrategy(this.movementStrategy);
        isItem = true;
        isAlive = true;
    }

    public void move() {
        item.move();
    }

    @Override
    public void explode(){
        ImageIcon playerImg = new ImageIcon("src/images/sonic.png");
        this.image = playerImg.getImage();
        this.image = image.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        setExplosionStart();
        isExploding = true;
        isAlive = false;
        EventBusSingleton.getInstance().postMessage(EventType.ItemExplosion, "Player has received powerup.");
    }

    @Override
    protected void setExplosionStart(){
        explosionStart = System.currentTimeMillis();
    }

    public Boolean explosionFinished(){
        return System.currentTimeMillis() - explosionStart >= explosionDuration;
    }

    @Override
    public Integer getXLocation(){
        return item.getXLocation();
    }

    @Override
    public Integer getYLocation(){
        return item.getYLocation();
    }

    @Override
    public void setX(Integer xValue){
        item.setX(xValue);
    }

    @Override
    public void setY(Integer yValue){
        item.setY(yValue);
    }

    @Override
    public Item dropItem(){
        return item;
    }
}
