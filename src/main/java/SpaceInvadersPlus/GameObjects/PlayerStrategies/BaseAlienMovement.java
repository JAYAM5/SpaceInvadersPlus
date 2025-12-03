package SpaceInvadersPlus.GameObjects.PlayerStrategies;

public class BaseAlienMovement implements IMovementStrategy {
    Integer baseXMovementSpeed = 1;
    Integer baseYMovementSpeed = 5;

    @Override
    public Integer getXMovementSpeed(){
        return baseXMovementSpeed;
    }

    @Override
    public Integer getYMovementSpeed(){
        return baseYMovementSpeed;
    }

}
