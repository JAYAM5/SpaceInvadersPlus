package SpaceInvadersPlus.GameObjects.PlayerStrategies;

public class StrategyFactory {
    public BaseAlienMovement createBaseAlienMovement(){return new BaseAlienMovement();}
    public BaseAlienShoot createBaseAlienShoot(){return new BaseAlienShoot();}
    public BasePlayerMovement createBasePlayerMovement(){return new BasePlayerMovement();}
    public BasePlayerShoot createBasePlayerShoot(){return new BasePlayerShoot();}
    public FastPlayerMovement createFastPlayerMovement(){return new FastPlayerMovement();}
    public NoShoot createNoShoot(){return new NoShoot();}
    public PlayerTriShoot createPlayerTriShoot(){return new PlayerTriShoot();}
    public RailgunPlayerShoot createRailgunPlayerShoot(){return new RailgunPlayerShoot();}
    public WideshotPlayerShoot createWideshotPlayerShoot(){return new WideshotPlayerShoot();}

}
