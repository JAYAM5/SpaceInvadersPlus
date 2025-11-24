package SpaceInvadersPlus.GameObjects.PlayerStrategies;


public class BasePlayerMovement implements IMovementStrategy {
    Integer baseXMovementSpeed = 2;
    Integer baseYMovementSpeed = 0;

    @Override
    public Integer getXMovementSpeed(){
        return baseXMovementSpeed;
    }

    @Override
    public Integer getYMovementSpeed(){
        return baseYMovementSpeed;
    }

    @Override
    public Integer getDifficulty(){
        return 1;
    }


}
