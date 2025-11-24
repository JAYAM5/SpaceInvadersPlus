package main.GameObjects.Characters;

import main.GameObjects.PlayerStrategies.IMovementStrategy;
import main.GameObjects.PlayerStrategies.IShootingStrategy;
import main.GameObjects.Weapons.Railgun;

public class AlienRailgunAdapter extends Alien {
    Railgun railgun;
    public AlienRailgunAdapter(Railgun railgun, Integer xLocation, Integer yLocation, IMovementStrategy movementStrategy, IShootingStrategy shootingStrategy, Boolean defaultDirection){
        super(xLocation,yLocation, movementStrategy, shootingStrategy, defaultDirection);
        this.railgun = railgun;
        this.image = railgun.getImage();
        railgun.setX(xLocation);
        railgun.setY(yLocation);
        railgun.setMovementStrategy(this.movementStrategy);
        isItem = true;
        isAlive = true;
    }

    public void move() {
        System.out.println("Railgun moving from adapter.");
        railgun.move();
    }

    @Override
    public void explode(){
        railgun.explode();
    }

    public void setIsExploding(Boolean isExploding){
        this.isExploding = isExploding;
    }

    @Override
    protected void setExplosionStart(){
        railgun.explosionStart = System.currentTimeMillis();
    }

    public Boolean explosionFinished(){
        return System.currentTimeMillis() - explosionStart >= 500;
    }

    @Override
    public Integer getXLocation(){
        return railgun.getXLocation();
    }

    @Override
    public Integer getYLocation(){
        return railgun.getYLocation();
    }

    @Override
    public void setX(Integer xValue){
        railgun.setX(xValue);
    }

    @Override
    public void setY(Integer yValue){
        railgun.setY(yValue);
    }
}
