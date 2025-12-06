package SpaceInvadersPlus.GameObjects.Projectiles;

public class ProjectileFactory {
    private final int XADJUSTMENT = 4;

    public BaseProjectile createBaseProjectile(Integer xLocation, Integer yLocation){
        return new BaseProjectile(xLocation + XADJUSTMENT, yLocation, Projectile.Angle.NONE);
    }
    public BaseAlienProjectile createBaseAlienProjectile(Integer xLocation, Integer yLocation){
        return new BaseAlienProjectile(xLocation + XADJUSTMENT, yLocation);
    }
    public BaseProjectile createLeftProjectile(Integer xLocation, Integer yLocation){
        return new BaseProjectile(xLocation + XADJUSTMENT, yLocation, Projectile.Angle.LEFT);
    }
    public BaseProjectile createRightProjectile(Integer xLocation, Integer yLocation){
        return new BaseProjectile(xLocation + XADJUSTMENT, yLocation, Projectile.Angle.RIGHT);
    }
}
