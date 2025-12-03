package SpaceInvadersPlus.GameObjects.PlayerStrategies;

import SpaceInvadersPlus.GameObjects.Characters.Ships;
import SpaceInvadersPlus.GameObjects.Projectiles.Projectile;

import java.util.ArrayList;
import java.util.List;

public class NoShoot implements IShootingStrategy{

    public List<Projectile> shoot(Ships ship){
        return new ArrayList<>();
    }

    public Boolean shouldShoot(){
        return false;
    }
}
