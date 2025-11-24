package main.GameObjects.LevelStrategies;

import main.GameObjects.Characters.Alien;
import main.GameObjects.Characters.CharacterFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class LevelTwo implements ILevel {
    Integer enemyLevelCount = 20;
    CharacterFactory characterFactory = new CharacterFactory();
    Integer spawnRate = 100;
    Random rand = new Random();
    List<Alien> levelEnemies = new ArrayList<>();
    Integer spawnCounter = 0;

    public LevelTwo(){
        for(Integer i = 0; i < enemyLevelCount; i++){
            levelEnemies.add(characterFactory.createAlien());
        }
        Collections.shuffle(levelEnemies);
    }

    @Override
    public Boolean shouldSpawn() {
        if ((rand.nextInt(spawnRate) + 1) < 2){
            if (spawnCounter < enemyLevelCount) {
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