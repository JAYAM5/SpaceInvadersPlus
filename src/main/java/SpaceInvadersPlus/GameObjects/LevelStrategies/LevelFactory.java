package SpaceInvadersPlus.GameObjects.LevelStrategies;

public class LevelFactory {

    public ILevel makeLevel(Integer levelNumber){
        return switch (levelNumber) {
            case 1 -> new LevelOne();
            case 2 -> new LevelTwo();
            case 3 -> new LevelThree();
            case 4 -> new LevelFour();
            default -> new LevelOne();
        };
    }
}
