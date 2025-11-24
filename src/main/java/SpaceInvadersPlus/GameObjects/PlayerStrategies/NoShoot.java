package SpaceInvadersPlus.GameObjects.PlayerStrategies;

import SpaceInvadersPlus.GameObjects.Characters.Ships;
import SpaceInvadersPlus.GameObjects.Projectiles.Projectile;
import SpaceInvadersPlus.GameObjects.Projectiles.ProjectileFactory;

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
