//package GameObjects.LevelStrategies;
//
//import GameObjects.Characters.Alien;
//import GameObjects.Characters.CharacterFactory;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class LevelTwo implements ILevel {
//    Integer enemyCount = 20;
//    CharacterFactory characterFactory = new CharacterFactory();
//    Integer spawnRate = 10;
//
//    @Override
//    public List<Alien> levelEnemies() {
//        List<Alien> levelEnemies = new ArrayList<>();
//        for(Integer i = 0; i < enemyCount; i++){
//            levelEnemies.add(characterFactory.createAlien());
//        }
//        return levelEnemies;
//    }
//
//    public Integer getSpawnRate(){
//        return spawnRate;
//    }
//}
