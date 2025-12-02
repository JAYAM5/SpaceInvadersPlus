package SpaceInvadersPlus.GameObjects.PlayerStrategies;

import SpaceInvadersPlus.GameObjects.Characters.Ships;
import SpaceInvadersPlus.GameObjects.Projectiles.Projectile;
import SpaceInvadersPlus.GameObjects.Projectiles.ProjectileFactory;

import java.util.ArrayList;
import java.util.List;

public class PlayerTriShoot implements IShootingStrategy {
    ProjectileFactory projectileFactory = new ProjectileFactory();
    long lastShot;
    long shotCooldown = 700;

    public List<Projectile> shoot(Ships ship) {
        //test
        System.out.println("THIS WAS CALLED OOPS");
        List<Projectile> returnList = new ArrayList<>();
        returnList.add(projectileFactory.createBaseProjectile(ship.getXLocation(), ship.getYLocation()));
        returnList.add(projectileFactory.createBaseProjectile(ship.getXLocation() + 5, ship.getYLocation()));
        returnList.add(projectileFactory.createBaseProjectile(ship.getXLocation() - 5, ship.getYLocation()));
        return returnList;
    }

    @Override
    public Boolean shouldShoot() {
        return System.currentTimeMillis() - lastShot >= shotCooldown;
    }
}
