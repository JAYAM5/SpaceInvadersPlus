package SpaceInvadersPlus.GameObjects.Projectiles;

public class ProjectileFactory {
    public BaseProjectile createBaseProjectile(Integer xLocation, Integer yLocation){
        return new BaseProjectile(xLocation + 4, yLocation, Projectile.Angle.NONE);
    }
    public BaseAlienProjectile createBaseAlienProjectile(Integer xLocation, Integer yLocation){
        return new BaseAlienProjectile(xLocation + 4, yLocation);
    }
    public BaseProjectile createLeftProjectile(Integer xLocation, Integer yLocation){
        return new BaseProjectile(xLocation + 4, yLocation, Projectile.Angle.LEFT);
    }
    public BaseProjectile createRightProjectile(Integer xLocation, Integer yLocation){
        return new BaseProjectile(xLocation + 4, yLocation, Projectile.Angle.RIGHT);
    }
}
