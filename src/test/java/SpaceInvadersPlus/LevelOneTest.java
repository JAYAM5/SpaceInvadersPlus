package SpaceInvadersPlus;

import SpaceInvadersPlus.GameObjects.LevelStrategies.ILevel;
import SpaceInvadersPlus.GameObjects.LevelStrategies.LevelFactory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LevelOneTest {
    private final LevelFactory levelFactory = new LevelFactory();
    @Test
    public void LevelOneItemTest() {
        ILevel levelOne = levelFactory.makeLevel(1);

        assertEquals(1, levelOne.levelEnemyCount());
    }
}
