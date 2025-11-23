package GameObjects.PlayerStrategies;

import GameObjects.Characters.Ships;
import GameObjects.Projectiles.Projectile;
import GameObjects.Projectiles.ProjectileFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BaseAlienShoot implements IShootingStrategy{
    ProjectileFactory projectileFactory = new ProjectileFactory();
    Integer difficulty = 10;
    Random rand = new Random();

    public List<Projectile> shoot(Ships ship){
        List<Projectile> returnList = new ArrayList<>();
        returnList.add(projectileFactory.createBaseAlienProjectile(ship.getXLocation(), ship.getYLocation()));
        return returnList;
    }

    @Override
    public Boolean shouldShoot() {
        return (rand.nextInt(1000) + 1 < difficulty);
    }
}
