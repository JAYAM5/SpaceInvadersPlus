package main.GameObjects.PlayerStrategies;

import main.GameObjects.Characters.Ships;
import main.GameObjects.Projectiles.Projectile;
import main.GameObjects.Projectiles.ProjectileFactory;

import java.util.ArrayList;
import java.util.List;

public class BasePlayerShoot implements IShootingStrategy{
    ProjectileFactory projectileFactory = new ProjectileFactory();

    public List<Projectile> shoot(Ships ship){
        List<Projectile> returnList = new ArrayList<>();
        returnList.add(projectileFactory.createBaseProjectile(ship.getXLocation(), ship.getYLocation()));
        return returnList;
    }

    public Boolean shouldShoot(){
        return null;
    }
}
