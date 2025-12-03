package SpaceInvadersPlus.GameObjects.PlayerStrategies;

import SpaceInvadersPlus.Events.EventBusSingleton;
import SpaceInvadersPlus.Events.EventType;
import SpaceInvadersPlus.GameObjects.Characters.Ship;
import SpaceInvadersPlus.GameObjects.Projectiles.Projectile;
import SpaceInvadersPlus.GameObjects.Projectiles.ProjectileFactory;

import java.util.ArrayList;
import java.util.List;

public class PlayerTriShoot implements IShootingStrategy {
    ProjectileFactory projectileFactory = new ProjectileFactory();
    long lastShot;
    long shotCooldown = 400;

    public List<Projectile> shoot(Ship ship) {
        List<Projectile> returnList = new ArrayList<>();

        if(shouldShoot()) {
            returnList.add(projectileFactory.createBaseProjectile(ship.getXLocation(), ship.getYLocation()));
            returnList.add(projectileFactory.createBaseProjectile(ship.getXLocation() + 5, ship.getYLocation()));
            returnList.add(projectileFactory.createBaseProjectile(ship.getXLocation() - 5, ship.getYLocation()));
            lastShot = System.currentTimeMillis();
            EventBusSingleton.getInstance().postMessage(EventType.Shoot, "Player is shooting.");
        }
        return returnList;
    }

    @Override
    public Boolean shouldShoot() {
        return System.currentTimeMillis() - lastShot >= shotCooldown;
    }
}
