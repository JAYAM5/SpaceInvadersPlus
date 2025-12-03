package SpaceInvadersPlus.GameObjects.Characters;

import java.awt.*;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import java.util.List;

//import main.SpaceInvadersPlus.GameObjects.PlayerStrategies.BasePlayerStrategy;
import SpaceInvadersPlus.Events.EventBusSingleton;
import SpaceInvadersPlus.Events.EventType;
import SpaceInvadersPlus.GameObjects.PlayerStrategies.*;
import SpaceInvadersPlus.GameObjects.Projectiles.Projectile;
import SpaceInvadersPlus.GameObjects.Weapons.Item;

public class Player extends Ships {

    Ships currentShip;
    IMovementStrategy movementStrategy;
    IShootingStrategy shootingStrategy;
    private boolean leftHeld;
    private boolean rightHeld;
    Integer deathCount;
    Item inventory;


    public Player(Integer xLocation, Integer yLocation, IMovementStrategy movementStrategy, IShootingStrategy shootingStrategy) {
        initPlayer();
        this.xLocation = xLocation;
        this.yLocation = yLocation;
        this.movementStrategy = movementStrategy;
        this.shootingStrategy = shootingStrategy;
        this.deathCount = 0;
    }

    public void move() {
        if(leftHeld && !rightHeld) {
            setX(xLocation += -movementStrategy.getXMovementSpeed());
        }
        else if(!leftHeld && rightHeld){
            setX(xLocation += movementStrategy.getXMovementSpeed());
        }
        else{
            setX(getXLocation());
        }
        if (xLocation < -10) {
            setX(-10);
        }
        if (xLocation > 748) {
            setX(748);
        }
    }

    private void initPlayer() {

        ImageIcon playerImg = new ImageIcon("src/images/base_player_ship.png");
        this.image = playerImg.getImage();
        this.image = image.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
    }

    public void setShip(Ships ship) {
        currentShip = ship;
    }

    public Ships getShip() {
        return currentShip;
    }

    public List<Projectile> shoot() {
        return shootingStrategy.shoot(this);
    }


    public void keyPressed(KeyEvent event) {
        int key = event.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
            leftHeld = true;
        }

        if(key == KeyEvent.VK_RIGHT) {
            rightHeld = true;
        }
    }



    public void keyReleased(KeyEvent event) {

        int key = event.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
            leftHeld = false;
        }

        if (key == KeyEvent.VK_RIGHT) {
            rightHeld = false;
        }
    }

    @Override
    public void explode(){
        ImageIcon playerImg = new ImageIcon("src/images/explosion.png");
        this.image = playerImg.getImage();
        this.image = image.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        setExplosionStart();
        isExploding = true;
        isAlive = false;
        deathCount +=1;
        EventBusSingleton.getInstance().postMessage(EventType.Explosion, "Player has been hit.");
        setInventory(null);
    }

    public Integer getDeaths(){
        return deathCount;
    }

    public void setDeathCount(Integer deathCount){
        this.deathCount = deathCount;
    }

    public void respawn(){
        System.out.println("Respawning player");
        ImageIcon playerImg = new ImageIcon("src/images/base_player_ship.png");
        this.image = playerImg.getImage();
        this.image = image.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        setX(360);
        isExploding = false;
        isAlive = true;
        setInventory(null);
        setShootingStrategy(new BasePlayerShoot());
        setMovementStrategy(new BasePlayerMovement());
    }

    public void respawnWithItem(){
        System.out.println("Respawning player");
        ImageIcon playerImg = new ImageIcon("src/images/base_player_ship.png");
        this.image = playerImg.getImage();
        this.image = image.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        setX(360);
        isExploding = false;
        isAlive = true;
    }

    @Override
    public Boolean getIsAlive(){
        return isAlive;
    }

    public void setInventory(Item item){
        inventory = item;
    }

    public void setShootingStrategy(Item item){
        switch(item.getType()){
            case "Railgun":
                setShootingStrategy(new RailgunPlayerShoot());
                break;
            case "Trigun":
                setShootingStrategy(new PlayerTriShoot());
                break;
            case "Wideshot":
                setShootingStrategy(new WideshotPlayerShoot());
                break;
        }
    }

    public void setMovementStrategy(Item item){
        switch(item.getType()){
            case "Shoe":
                setMovementStrategy(new FastPlayerMovement());
                break;
        }
    }

    public void setShootingStrategy(IShootingStrategy strategy){
        this.shootingStrategy = strategy;
    }

    public void setMovementStrategy(IMovementStrategy strategy) {
        this.movementStrategy = strategy;
    }
}

