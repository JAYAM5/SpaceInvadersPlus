package SpaceInvadersPlus.GameObjects.PlayerStrategies;

import SpaceInvadersPlus.GameObjects.Characters.Ship;
import SpaceInvadersPlus.GameObjects.Projectiles.Projectile;

import java.util.List;

public interface IShootingStrategy {
    List<Projectile> shoot(Ship ship);
    Boolean shouldShoot();
}
