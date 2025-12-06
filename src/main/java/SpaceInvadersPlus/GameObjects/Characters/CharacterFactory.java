package SpaceInvadersPlus.GameObjects.Characters;

import java.util.Random;

import SpaceInvadersPlus.GameObjects.PlayerStrategies.*;
import SpaceInvadersPlus.GameObjects.PlayerStrategies.BaseAlienShoot;
import SpaceInvadersPlus.GameObjects.Items.ItemFactory;

public class CharacterFactory {
    Integer DEFAULT_PLAYER_X = 360;
    Integer DEFAULT_PLAYER_Y = 575;
    Integer DEFAULT_ALIEN_Y = 10;
    Random rand = new Random();
    ItemFactory itemFactory = new ItemFactory();
    private final int rightEdge = 748;
    private final int leftEdge = -10;
    StrategyFactory strategyFactory = new StrategyFactory();

    public Alien createAlien(){
        return new Alien(rand.nextInt(rightEdge - leftEdge + 1) + leftEdge, DEFAULT_ALIEN_Y, strategyFactory.createBaseAlienMovement(), strategyFactory.createBaseAlienShoot(), rand.nextBoolean());
    }

    public Player createPlayer(){
        return new Player(DEFAULT_PLAYER_X, DEFAULT_PLAYER_Y, strategyFactory.createBasePlayerMovement(), strategyFactory.createBasePlayerShoot());
    }

    public Alien createRailgunAdapter(){
        return new AlienItemAdapter(itemFactory.createRailgun(), rand.nextInt(rightEdge - leftEdge + 1) + leftEdge,DEFAULT_ALIEN_Y,
                new BaseAlienMovement(), strategyFactory.createNoShoot(), rand.nextBoolean());
    }

    public Alien createShoeAdapter(){
        return new AlienItemAdapter(itemFactory.createShoe(), rand.nextInt(rightEdge - leftEdge + 1) + leftEdge,DEFAULT_ALIEN_Y,
                new BaseAlienMovement(), strategyFactory.createNoShoot(), rand.nextBoolean());
    }

    public Alien createTrigunAdapter(){
        return new AlienItemAdapter(itemFactory.createTrigun(), rand.nextInt(rightEdge - leftEdge + 1) + leftEdge,DEFAULT_ALIEN_Y,
                new BaseAlienMovement(), strategyFactory.createNoShoot(), rand.nextBoolean());
    }

    public Alien createWideshotAdapter(){
        return new AlienItemAdapter(itemFactory.createWideshot(),  rand.nextInt(rightEdge - leftEdge + 1) + leftEdge,DEFAULT_ALIEN_Y,
                new BaseAlienMovement(), strategyFactory.createNoShoot(), rand.nextBoolean());
    }

    public Alien createRandomItemAdapter(){
        return new AlienItemAdapter(itemFactory.createRandomItem(), rand.nextInt(rightEdge - leftEdge + 1) + leftEdge,DEFAULT_ALIEN_Y,
                new BaseAlienMovement(), strategyFactory.createNoShoot(), rand.nextBoolean());
    }
}
