package main.GameObjects.PlayerStrategies;

import main.GameObjects.Characters.Ships;
import main.GameObjects.Projectiles.Projectile;

import java.util.List;

public interface IShootingStrategy {
    public List<Projectile> shoot(Ships ship);
    public Boolean shouldShoot();
}
