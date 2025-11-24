package SpaceInvadersPlus.GameObjects.LevelStrategies;

import SpaceInvadersPlus.GameObjects.Characters.Alien;

public interface ILevel {

    public Boolean shouldSpawn();
    public Alien spawn();
    public Integer levelEnemyCount();
}
