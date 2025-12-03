package SpaceInvadersPlus.GameObjects.Projectiles;

public class ProjectileFactory {
    private final int xAdjustment = 4;

    public BaseProjectile createBaseProjectile(Integer xLocation, Integer yLocation){
        return new BaseProjectile(xLocation + xAdjustment, yLocation, Projectile.Angle.NONE);
    }
    public BaseAlienProjectile createBaseAlienProjectile(Integer xLocation, Integer yLocation){
        return new BaseAlienProjectile(xLocation + xAdjustment, yLocation);
    }
    public BaseProjectile createLeftProjectile(Integer xLocation, Integer yLocation){
        return new BaseProjectile(xLocation + xAdjustment, yLocation, Projectile.Angle.LEFT);
    }
    public BaseProjectile createRightProjectile(Integer xLocation, Integer yLocation){
        return new BaseProjectile(xLocation + xAdjustment, yLocation, Projectile.Angle.RIGHT);
    }
}
