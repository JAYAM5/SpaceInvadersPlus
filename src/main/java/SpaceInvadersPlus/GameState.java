package SpaceInvadersPlus;

import SpaceInvadersPlus.Events.AudioObserver;
import SpaceInvadersPlus.Events.EventBusSingleton;
import SpaceInvadersPlus.Events.EventType;
import SpaceInvadersPlus.GameObjects.Characters.Alien;
import SpaceInvadersPlus.GameObjects.Characters.CharacterFactory;
import SpaceInvadersPlus.GameObjects.Characters.Player;
import SpaceInvadersPlus.GameObjects.LevelStrategies.ILevel;
import SpaceInvadersPlus.GameObjects.LevelStrategies.LevelFactory;
import SpaceInvadersPlus.GameObjects.Projectiles.Projectile;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class GameState extends JPanel {
    private final int SCORE_Y_LOCATION = 655;
    private final int SCORE_X_LOCATION = 15;
    private Dimension d;
    private List<Alien> aliens;
    private Player player;
    List<Projectile> projectiles;
    private ILevel currentLevel;
    Integer levelCounter = 1;
    Integer levelEnemiesKilled = 0;
    Boolean levelIsStarting;
    private long newLevelStart;
    Boolean levelCompleted = false;
    AudioObserver audioObserver = new AudioObserver();
    Boolean groundCheck = false;
    private int gameScore = 0;

    LevelFactory levelFactory = new LevelFactory();
    CharacterFactory characterFactory = new CharacterFactory();

    GameStateUpdater gameStateUpdater;
    Timer timer;

    public GameState() {

        initBoard();
        EventBusSingleton.getInstance().attach(audioObserver,EventType.All);
        EventBusSingleton.getInstance().postMessage(EventType.LevelStart, "Starting level " + currentLevel);
        player = characterFactory.createPlayer();
        projectiles = new ArrayList<>();
        aliens = new ArrayList<>();
        resetLevel();


    }

    public void update(){
        if (levelIsStarting) {
            if (System.currentTimeMillis() - newLevelStart >= 3000) {
                levelIsStarting = false;
            } else {
                repaint();
                return;
            }
        }
        if (!isGameOver() && !levelIsStarting) {
            player.move();
            if (currentLevel.shouldSpawn()) {
                aliens.add(currentLevel.spawn());
            }

            List<Projectile> projectilesToRemove = new ArrayList<>();

            for (Projectile projectile : projectiles) {
                projectile.move();
                if (projectile.getYLocation() < 0) {
                    projectilesToRemove.add(projectile);
                }
            }
            projectiles.removeAll(projectilesToRemove);

            if (!aliens.isEmpty()) {
                for (Alien alien : aliens) {
                    alien.move();
                    if (alien.shouldShoot()) {
                        projectiles.addAll(alien.shoot());
                    }
                }
            }

            collisions();
            levelCheck();
            nextLevel();

//            // delete this
//            if (levelCounter > 1) {
//                player.setMovementStrategy(new FastPlayerMovement());
//            }

            repaint();
        }
    }

    private void collisions(){
        List<Projectile> projectilesToRemove = new ArrayList<>();

        for (Projectile projectile : projectiles) {
            Integer projectileX = projectile.getXLocation();
            Integer projectileY = projectile.getYLocation();
            for (Alien alien : aliens) {
                if (alien.getIsAlive()) {
                    if (projectileX >= alien.getXLocation() &&
                            projectileX <= alien.getXLocation() + 50 &&
                            projectileY >= alien.getYLocation() &&
                            projectileY <= alien.getYLocation() + 50 &&
                            projectile.getIsPlayerProjectile()) {
                        alien.explode();
                        projectilesToRemove.add(projectile);
                        if(!alien.getIsItem()) {
                            levelEnemiesKilled += 1;
                            gameScore += 10;
                        }
                        else{
                            player.setInventory(alien.dropItem());
                            player.setShootingStrategy(alien.dropItem());
                            player.setMovementStrategy(alien.dropItem());
                        }
                    }
                }
            }
            if (player.getIsAlive()) {
                if (projectileX >= player.getXLocation() &&
                        projectileX <= player.getXLocation() + 50 &&
                        projectileY >= player.getYLocation() &&
                        projectileY <= player.getYLocation() + 50) {
                    player.explode();
                }
            }
        }
        projectiles.removeAll(projectilesToRemove);
        if (groundCheck && !isExplosionOccurring()){
            resetLevel();
        }
        else {
            aliens.removeIf(alien -> !alien.getIsAlive() && alien.explosionFinished());
        }
    }

    private void levelCheck() {
        if (!player.getIsAlive()) {
            if (player.explosionFinished()) {
                resetLevel();
            }
        }
        for (Alien alien : aliens){
            if (alien.getYLocation() >= 625 && alien.getIsAlive() && !alien.getIsItem()) {
                player.setDeathCount(player.getDeaths() + 1);
                groundCheck = true;
                alien.explode();
//                if (alien.explosionFinished() && groundCheck) {
//                    System.out.println("Hit this line");
//                    resetLevel();
//                }
            }
        }
//        if (groundCheck){
//            System.out.println("IsExploding is: " + isExplosionOccurring());
//            resetLevel();
//        }

        if (levelEnemiesKilled == currentLevel.levelEnemyCount()){
            if (!isExplosionOccurring()) {
                levelCompleted = true;
            }
        }

    }

    private void nextLevel() {
        if (levelCompleted == true) {
            levelCounter++;
            resetLevel();
        }
    }

    private void resetLevel() {
        if (levelCompleted == true){
            player.respawnWithItem();
        }
        else{
            player.respawn();
        }
        groundCheck = false;
        levelCompleted = false;
        EventBusSingleton.getInstance().postMessage(EventType.LevelStart, "Starting level " + levelCounter);
        currentLevel = levelFactory.makeLevel(levelCounter);
        aliens.clear();
        projectiles.clear();
        levelEnemiesKilled = 0;

        newLevelStart = System.currentTimeMillis();
        levelIsStarting = true;
    }

    private boolean isExplosionOccurring(){
        Boolean isExplosionOccuring = false;

        for (Alien alien : aliens){
            if (!alien.explosionFinished()) {
                isExplosionOccuring = true;
            }
        }
        if (!player.explosionFinished()){
            isExplosionOccuring = true;
        }
        System.out.println("Explosion is occurring is: " + isExplosionOccuring);
        return isExplosionOccuring;
    }

    private Boolean levelIsStarting() {
        if (!levelIsStarting) return false;

        if (System.currentTimeMillis() - newLevelStart >= 3000) {
            levelIsStarting = false;
            return false;
        }
        return true;
    }


    private void startNewLevel() {
        levelIsStarting = true;
        newLevelStart = System.currentTimeMillis();
    }

    private void levelDisplay(Graphics g) {
        g.setColor(Color.black);
        g.fillRect(0, 0, d.width, d.height);
        g.setColor(Color.green);
        g.setFont(new Font("Arial", Font.BOLD, 50));
        String message = "Level " + levelCounter;

        FontMetrics metrics = g.getFontMetrics();
        int x = (getWidth() - metrics.stringWidth(message)) / 2;
        int y = getHeight() / 2;
        g.drawString(message, x, y);
    }

    private void initBoard() {
        addKeyListener(new TAdapter());
        setFocusable(true);
        d = new Dimension(800, 700);
        setBackground(Color.black);
        setPreferredSize(d);
        gameStateUpdater = new GameStateUpdater();
        gameStateUpdater.setGameState(this);
        timer = new Timer(10, gameStateUpdater);
        timer.start();

    }

    private Boolean isGameOver(){
        if (player.getDeaths() > 2){
            return true;
        }
        return false;
    }

    private void drawPlayer(Graphics g) {
        g.drawImage(player.getImage(), player.getXLocation(), player.getYLocation(), this);
    }

    private void drawProjectile(Graphics g) {
        for (Projectile projectile : projectiles) {
            g.drawImage(projectile.getImage(), projectile.getXLocation(), projectile.getYLocation(), this);
        }
    }

    private void drawAliens(Graphics g){
        for (Alien alien : aliens){
            g.drawImage(alien.getImage(), alien.getXLocation(), alien.getYLocation(), this);
        }
    }

    private void drawHUD(Graphics g) {
        g.setColor(Color.white);
        g.setFont(new Font("Arial", Font.PLAIN, 20));
        g.drawLine(0, 625, 1000, 625);

        String message = "Score: " + gameScore;
        g.drawString(message, SCORE_X_LOCATION, SCORE_Y_LOCATION);

        message = "Lives remaining: " + (3 - player.getDeaths());
        g.drawString(message, SCORE_X_LOCATION + 610, SCORE_Y_LOCATION);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (isGameOver()){
            EventBusSingleton.getInstance().postMessage(EventType.GameLost, "Player has lost the game");
            gameOverScreen(g);
        }
        else if (levelIsStarting){
            levelDisplay(g);
        }
        else{
            doDrawing(g);
        }
    }

    private void doDrawing(Graphics g){
        g.setColor(Color.black);
        g.fillRect(0, 0, d.width, d.height);
        g.setColor(Color.green);

        drawHUD(g);
        drawPlayer(g);
        drawAliens(g);
        drawProjectile(g);
    }



    private void gameOverScreen(Graphics g){
        g.setColor(Color.black);
        g.fillRect(0, 0, d.width, d.height);
        g.setColor(Color.green);

        g.setFont(new Font("Arial", Font.BOLD, 50));
        String message = "GAME OVER";

        FontMetrics metrics = g.getFontMetrics();
        int x = (getWidth() - metrics.stringWidth(message)) / 2;
        int y = getHeight() / 2;
        g.drawString(message, x, y);

        message = "Score: " + gameScore;
        g.setFont(new Font("Arial", Font.BOLD, 27));
        x = (getWidth() - metrics.stringWidth(message)) / 2 + 50;
        g.drawString(message, x, y + 65);
    }



    private class TAdapter extends KeyAdapter {

        @Override
        public void keyReleased(KeyEvent e) {

            player.keyReleased(e);
        }

        @Override
        public void keyPressed(KeyEvent e) {
            Integer key = e.getKeyCode();
            if (key == KeyEvent.VK_SPACE){
                projectiles.addAll(player.shoot());
            }
            player.keyPressed(e);
        }
    }

}
