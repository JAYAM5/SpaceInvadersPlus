package main.GameObjects.LevelStrategies;

import main.GameObjects.Characters.Alien;

public interface ILevel {

    public Boolean shouldSpawn();
    public Alien spawn();
    public Integer levelEnemyCount();
}
