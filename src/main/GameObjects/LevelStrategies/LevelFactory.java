package main.GameObjects.LevelStrategies;

public class LevelFactory {

    public ILevel makeLevel(Integer levelNumber){
        return switch (levelNumber) {
            case 0 -> new LevelZero();
            case 1 -> new LevelOne();
            case 2 -> new LevelTwo();
            default -> new LevelZero();
        };
    }
}
