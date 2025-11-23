package GameObjects.LevelStrategies;

public class LevelFactory {

    public ILevel makeLevel(Integer levelNumber){
        switch(levelNumber){
            case 1:
                return new LevelOne();
//            case 2:
//                return new LevelTwo();
            default:
                return null;
        }
    }
}
