package SpaceInvadersPlus.AdapterTest;

import SpaceInvadersPlus.GameObjects.Characters.Alien;
import SpaceInvadersPlus.GameObjects.Characters.CharacterFactory;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class AdapterTest {

    CharacterFactory characterFactory = new CharacterFactory();

    @Test
    public void AdapterRailgunTest() {

        Alien railgunAlien = characterFactory.createRailgunAdapter();
        Alien testAlien = characterFactory.createAlien();

        assertEquals (railgunAlien.getIsItem(),!testAlien.getIsItem());

        List<Integer> railgunInitLocation = new ArrayList<>();
        railgunInitLocation.add(railgunAlien.getXLocation());
        railgunInitLocation.add(railgunAlien.getYLocation());

        List<Integer> testAlienInitLocation = new ArrayList<>();
        testAlienInitLocation.add(railgunAlien.getXLocation());
        testAlienInitLocation.add(railgunAlien.getYLocation());

        List<Alien> aliens = new ArrayList<>();

        aliens.add(railgunAlien);
        aliens.add(testAlien);

        for (Alien alien : aliens){
            alien.move();
        }

        List<Integer> railgunNewLocation = new ArrayList<>();
        railgunNewLocation.add(railgunAlien.getXLocation());
        railgunNewLocation.add(railgunAlien.getYLocation());

        List<Integer> testAlienNewLocation = new ArrayList<>();
        testAlienNewLocation.add(testAlien.getXLocation());
        testAlienNewLocation.add(testAlien.getYLocation());

        assertFalse(railgunInitLocation.get(0).equals(railgunNewLocation.get(0)) && railgunInitLocation.get(1).equals(railgunNewLocation.get(1)));
        assertFalse(testAlienInitLocation.get(0).equals(testAlienNewLocation.get(0)) && testAlienInitLocation.get(1).equals(testAlienNewLocation.get(1)));
    }
}