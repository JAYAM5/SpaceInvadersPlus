package SpaceInvadersPlus.GameObjects.Weapons;

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
    public Wideshot createWideshot(){
        return new Wideshot();
    }
    public Item createRandomItem(){
        Random rand = new Random();
        enum ItemEnum {
            RAILGUN,
            SHOE,
            TRIGUN,
            WIDESHOT
        }

        int pick = rand.nextInt(ItemEnum.values().length);
        ItemEnum randomItem = ItemEnum.values()[pick];

        return switch (randomItem) {
            case RAILGUN -> new Railgun();
            case SHOE -> new Shoe();
            case TRIGUN -> new Trigun();
            case WIDESHOT -> new Wideshot();
        };
    }
}
