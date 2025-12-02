package SpaceInvadersPlus.GameObjects.LevelStrategies;

import SpaceInvadersPlus.GameObjects.Characters.Alien;
import SpaceInvadersPlus.GameObjects.Characters.CharacterFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class LevelRandom implements ILevel{
    Integer enemyLevelCount = 0;
    Integer levelItemCount = 0;
    CharacterFactory characterFactory = new CharacterFactory();
    Integer spawnRate = 100;
    Random rand = new Random();
    List<Alien> levelEnemies = new ArrayList<>();
    Integer spawnCounter = 0;
    int MAX_ENEMIES = 30;
    int MIN_ENEMIES = 5;
    int MAX_ITEMS = 4;

    public LevelRandom(){
        enemyLevelCount = rand.nextInt(MAX_ENEMIES - MIN_ENEMIES + 1) + MIN_ENEMIES;
        levelItemCount = rand.nextInt(MAX_ITEMS);
        for(int i = 0; i < enemyLevelCount; i++){
            levelEnemies.add(characterFactory.createAlien());
        }

        for(int i = 0; i < levelItemCount; i++) {
            levelEnemies.add(characterFactory.createRandomItemAdapter());
        }

        //test
        System.out.println("Level enemies: " + enemyLevelCount + " Level items: " + levelItemCount);

        Collections.shuffle(levelEnemies);
    }

    @Override
    public Boolean shouldSpawn() {
        if ((rand.nextInt(spawnRate) + 1) < 2){
            if (spawnCounter - levelItemCount < enemyLevelCount) {
                return true;
            }
            else{
                return false;
            }
        }
        else{
            return false;
        }
    }

    public Integer getSpawnRate(){
        return spawnRate;
    }

    public Alien spawn(){
        return levelEnemies.get(spawnCounter++);
    }

    @Override
    public Integer levelEnemyCount() {
        return enemyLevelCount;
    }
}


