package SpaceInvadersPlus.GameObjects.PlayerStrategies;

public class FastPlayerMovement implements IMovementStrategy {
    Integer fastXMovementSpeed = 6;
    Integer fastYMovementSpeed = 5;
    Integer difficulty = 5;

    @Override
    public Integer getXMovementSpeed(){
        return fastXMovementSpeed;
    }

    @Override
    public Integer getYMovementSpeed(){
        return fastYMovementSpeed;
    }

    @Override
    public Integer getDifficulty(){
        return difficulty;
    }
}
