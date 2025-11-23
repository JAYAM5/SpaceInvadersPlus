package GameObjects.Projectiles;

public class ProjectileFactory {
    public BaseProjectile createBaseProjectile(Integer xLocation, Integer yLocation){
        return new BaseProjectile(xLocation + 4,yLocation);
    }
    public BaseAlienProjectile createBaseAlienProjectile(Integer xLocation, Integer yLocation){
        return new BaseAlienProjectile(xLocation + 4, yLocation);
    }
}
