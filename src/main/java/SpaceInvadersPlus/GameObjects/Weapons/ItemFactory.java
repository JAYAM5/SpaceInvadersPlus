package SpaceInvadersPlus.GameObjects.Weapons;

import SpaceInvadersPlus.GameObjects.LevelStrategies.LevelFour;
import SpaceInvadersPlus.GameObjects.LevelStrategies.LevelOne;
import SpaceInvadersPlus.GameObjects.LevelStrategies.LevelThree;
import SpaceInvadersPlus.GameObjects.LevelStrategies.LevelTwo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ItemFactory {
    public Railgun createRailgun(){
        return new Railgun();
    }
    public Shoe createShoe(){
        return new Shoe();
    }
    public Trigun createTrigun(){
        return new Trigun();
    }
    public Item createRandomItem(){
        Random rand = new Random();
        enum ItemEnum {
            RAILGUN,
            SHOE,
            TRIGUN
        }

        int pick = rand.nextInt(ItemEnum.values().length);
        ItemEnum randomItem = ItemEnum.values()[pick];

        return switch (randomItem) {
            case RAILGUN -> new Railgun();
            case SHOE -> new Shoe();
            case TRIGUN -> new Trigun();
        };
    }
}
