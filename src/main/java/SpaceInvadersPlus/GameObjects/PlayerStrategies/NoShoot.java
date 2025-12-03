package SpaceInvadersPlus.GameObjects.PlayerStrategies;

import SpaceInvadersPlus.GameObjects.Characters.Ship;
import SpaceInvadersPlus.GameObjects.Projectiles.Projectile;

import java.util.ArrayList;
import java.util.List;

public class NoShoot implements IShootingStrategy{

    public List<Projectile> shoot(Ship ship){
        return new ArrayList<>();
    }

    public Boolean shouldShoot(){
        return false;
    }
}
