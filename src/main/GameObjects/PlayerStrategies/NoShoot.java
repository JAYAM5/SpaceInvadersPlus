package main.GameObjects.PlayerStrategies;

import main.GameObjects.Characters.Ships;
import main.GameObjects.Projectiles.Projectile;
import main.GameObjects.Projectiles.ProjectileFactory;

import java.util.ArrayList;
import java.util.List;

public class NoShoot implements IShootingStrategy{
    ProjectileFactory projectileFactory = new ProjectileFactory();

    public List<Projectile> shoot(Ships ship){
        List<Projectile> returnList = new ArrayList<>();
        return returnList;
    }

    public Boolean shouldShoot(){
        return false;
    }
}
