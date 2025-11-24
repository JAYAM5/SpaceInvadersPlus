package main.GameObjects.PlayerStrategies;

import main.GameObjects.Characters.Ships;
import main.GameObjects.Projectiles.Projectile;
import main.GameObjects.Projectiles.ProjectileFactory;

import java.util.ArrayList;
import java.util.List;

public class RailgunPlayerShoot implements IShootingStrategy{
    ProjectileFactory projectileFactory = new ProjectileFactory();
    long lastShot;
    long shotCooldown = 100;

    public List<Projectile> shoot(Ships ship){
        List<Projectile> returnList = new ArrayList<>();
        if(shouldShoot()){
            returnList.add(projectileFactory.createBaseProjectile(ship.getXLocation(), ship.getYLocation()));
            lastShot = System.currentTimeMillis();
        }
        return returnList;
    }

    public Boolean shouldShoot(){
        return System.currentTimeMillis() - lastShot >= shotCooldown;
    }
}
