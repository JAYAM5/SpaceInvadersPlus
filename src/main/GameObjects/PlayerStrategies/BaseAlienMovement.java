package main.GameObjects.PlayerStrategies;

public class BaseAlienMovement implements IMovementStrategy {
    Integer baseXMovementSpeed = 1;
    Integer baseYMovementSpeed = 2;
    Integer difficulty = 5;

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
        return difficulty;
    }

}
