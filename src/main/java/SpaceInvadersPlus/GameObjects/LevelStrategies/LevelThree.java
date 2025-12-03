package SpaceInvadersPlus.GameObjects.LevelStrategies;

import SpaceInvadersPlus.GameObjects.Characters.Alien;
import SpaceInvadersPlus.GameObjects.Characters.CharacterFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class LevelThree implements ILevel {
    Integer enemyLevelCount = 10;
    Integer levelItemCount = 1;
    CharacterFactory characterFactory = new CharacterFactory();
    Integer spawnRate = 100;
    Random rand = new Random();
    List<Alien> levelEnemies = new ArrayList<>();
    Integer spawnCounter = 0;

    public LevelThree(){
        for(int i = 0; i < enemyLevelCount; i++){
            levelEnemies.add(characterFactory.createAlien());
        }
        levelEnemies.add(characterFactory.createShoeAdapter());
        Collections.shuffle(levelEnemies);
    }

    @Override
    public Boolean shouldSpawn() {
        if ((rand.nextInt(spawnRate) + 1) < 2){
            return spawnCounter - levelItemCount < enemyLevelCount;
        }
        else{
            return false;
        }
    }

    public Alien spawn(){
        return levelEnemies.get(spawnCounter++);
    }

    @Override
    public Integer levelEnemyCount() {
        return enemyLevelCount;
    }
}