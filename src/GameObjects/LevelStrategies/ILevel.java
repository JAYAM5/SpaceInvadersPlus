package GameObjects.LevelStrategies;

import GameObjects.Characters.Alien;

import java.util.*;

public interface ILevel {

    public Boolean shouldSpawn();
    public Alien spawn();
    public Integer levelEnemyCount();
}
