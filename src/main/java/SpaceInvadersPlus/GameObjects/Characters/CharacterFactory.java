package SpaceInvadersPlus.GameObjects.Characters;

import java.util.Random;

import SpaceInvadersPlus.GameObjects.PlayerStrategies.*;
import SpaceInvadersPlus.GameObjects.PlayerStrategies.BaseAlienShoot;
import SpaceInvadersPlus.GameObjects.PlayerStrategies.IMovementStrategy;
import SpaceInvadersPlus.GameObjects.PlayerStrategies.IShootingStrategy;
import SpaceInvadersPlus.GameObjects.Weapons.ItemFactory;

public class CharacterFactory {
    Integer DEFAULT_PLAYER_X = 360;
    Integer DEFAULT_PLAYER_Y = 575;
    Integer DEFAULT_ALIEN_X = 360;
    Integer DEFAULT_ALIEN_Y = 10;
    Random rand = new Random();
    ItemFactory itemFactory = new ItemFactory();

    public Alien createAlien(){
        return new Alien(rand.nextInt(748 - (-10) + 1) + -10,DEFAULT_ALIEN_Y, new BaseAlienMovement(), new BaseAlienShoot(), rand.nextBoolean());
    }

    public Player createPlayer(Integer xValue, Integer yValue, IMovementStrategy movementStrategy, IShootingStrategy shootingStrategy){
        return new Player(xValue,yValue,movementStrategy,shootingStrategy);
    }

    public Player createPlayer(){
        return new Player(DEFAULT_PLAYER_X, DEFAULT_PLAYER_Y, new BasePlayerMovement(), new BasePlayerShoot());
    }

    public Alien createRailgunAdapter(){
        return new AlienItemAdapter(itemFactory.createRailgun(), rand.nextInt(748 - (-10) + 1) + -10,DEFAULT_ALIEN_Y,
                new BaseAlienMovement(), new NoShoot(), rand.nextBoolean());
    }

    public Alien createShoeAdapter(){
        return new AlienItemAdapter(itemFactory.createShoe(), rand.nextInt(748 - (-10) + 1) + -10,DEFAULT_ALIEN_Y,
                new BaseAlienMovement(), new NoShoot(), rand.nextBoolean());
    }

    public Alien createTrigunAdapter(){
        return new AlienItemAdapter(itemFactory.createTrigun(), rand.nextInt(748 - (-10) + 1) + -10,DEFAULT_ALIEN_Y,
                new BaseAlienMovement(), new NoShoot(), rand.nextBoolean());
    }

    public Alien createRandomItemAdapter(){
        return new AlienItemAdapter(itemFactory.createRandomItem(), rand.nextInt(748 - (-10) + 1) + -10,DEFAULT_ALIEN_Y,
                new BaseAlienMovement(), new NoShoot(), rand.nextBoolean());
    }
}
