package SpaceInvadersPlus.GameObjects.LevelStrategies;

import SpaceInvadersPlus.GameObjects.Characters.Alien;

public interface ILevel {

    Boolean shouldSpawn();
    Alien spawn();
    Integer levelEnemyCount();
}
