package SpaceInvadersPlus.GameObjects.PlayerStrategies;

import SpaceInvadersPlus.Events.EventBusSingleton;
import SpaceInvadersPlus.Events.EventType;
import SpaceInvadersPlus.GameObjects.Characters.Ship;
import SpaceInvadersPlus.GameObjects.Projectiles.Projectile;
import SpaceInvadersPlus.GameObjects.Projectiles.ProjectileFactory;

import java.util.ArrayList;
import java.util.List;

public class BasePlayerShoot implements IShootingStrategy{
    ProjectileFactory projectileFactory = new ProjectileFactory();
    long lastShot;
    long shotCooldown = 700;

    public List<Projectile> shoot(Ship ship){
        List<Projectile> returnList = new ArrayList<>();
        if(shouldShoot()){
            returnList.add(projectileFactory.createBaseProjectile(ship.getXLocation(), ship.getYLocation()));
            lastShot = System.currentTimeMillis();
            System.out.println("Projectile X is: " + ship.getXLocation());
            EventBusSingleton.getInstance().postMessage(EventType.Shoot, "Player is shooting.");
        }

        return returnList;
    }

    public Boolean shouldShoot(){
        return System.currentTimeMillis() - lastShot >= shotCooldown;
    }
}
