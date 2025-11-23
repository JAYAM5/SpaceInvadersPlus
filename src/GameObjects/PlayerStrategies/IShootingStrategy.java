package GameObjects.PlayerStrategies;

import GameObjects.Characters.Ships;
import GameObjects.Projectiles.Projectile;

import java.util.List;

public interface IShootingStrategy {
    public List<Projectile> shoot(Ships ship);
    public Boolean shouldShoot();
}
