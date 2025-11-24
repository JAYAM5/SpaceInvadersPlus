package SpaceInvadersPlus.GameObjects.PlayerStrategies;

import SpaceInvadersPlus.GameObjects.Characters.Ships;
import SpaceInvadersPlus.GameObjects.Projectiles.Projectile;

import java.util.List;

public interface IShootingStrategy {
    public List<Projectile> shoot(Ships ship);
    public Boolean shouldShoot();
}
