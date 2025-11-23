package GameObjects.Characters;

import GameObjects.PlayerStrategies.*;

import java.util.Random;

public class CharacterFactory {
    Integer DEFAULT_PLAYER_X = 360;
    Integer DEFAULT_PLAYER_Y = 625;
    Integer DEFAULT_ALIEN_X = 360;
    Integer DEFAULT_ALIEN_Y = 10;
    Random rand = new Random();

    public Alien createAlien(){
        return new Alien(rand.nextInt(748 - (-10) + 1) + -10,DEFAULT_ALIEN_Y, new BaseAlienMovement(), new BaseAlienShoot(), rand.nextBoolean());
    }

    public Player createPlayer(Integer xValue, Integer yValue, IMovementStrategy movementStrategy, IShootingStrategy shootingStrategy){
        return new Player(xValue,yValue,movementStrategy,shootingStrategy);
    }

    public Player createPlayer(){
        return new Player(DEFAULT_PLAYER_X, DEFAULT_PLAYER_Y, new BasePlayerMovement(), new BasePlayerShoot());
    }
}
