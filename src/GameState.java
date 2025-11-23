import GameObjects.Characters.Alien;
import GameObjects.Characters.CharacterFactory;
import GameObjects.Characters.Player;
import GameObjects.LevelStrategies.ILevel;
import GameObjects.LevelStrategies.LevelFactory;
import GameObjects.Projectiles.Projectile;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class GameState extends JPanel {
    private Dimension d;
    private List<Alien> aliens;
    private Player player;
    List<Projectile> projectiles;
    private ILevel currentLevel;
    Integer levelCounter = 0;
    Integer levelEnemiesKilled = 0;
    Boolean levelIsStarting;
    private long newLevelStart;

    LevelFactory levelFactory = new LevelFactory();
    CharacterFactory characterFactory = new CharacterFactory();

    GameStateUpdater gameStateUpdater;
    Timer timer;

    public GameState() {

        initBoard();
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
                if (projectile.getXLocation() <= 0) {
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
            repaint();
        }
    }

    private void collisions(){
        for (Projectile projectile : projectiles) {
            Integer projectileX = projectile.getXLocation();
            Integer projectileY = projectile.getYLocation();
            for (Alien alien : aliens) {
                if (alien.getIsAlive()) {
                    if (projectileX >= alien.getXLocation() &&
                            projectileX <= alien.getXLocation() + 20 &&
                            projectileY >= alien.getYLocation() &&
                            projectileY <= alien.getYLocation() + 20 &&
                            projectile.getIsPlayerProjectile()) {
                        alien.explode();
                        levelEnemiesKilled += 1;
                    }
                }
            }
            if (player.getIsAlive()) {
                if (projectileX >= player.getXLocation() &&
                        projectileX <= player.getXLocation() + 20 &&
                        projectileY >= player.getYLocation() &&
                        projectileY <= player.getYLocation() + 20) {
                    System.out.println("Projectile " + projectile + "is at " + projectileX + ", " + projectileY);
                    System.out.println("Player hit by " + projectile);
                    System.out.println("Player death count: " + player.getDeaths());
                    player.explode();
                }
            }
        }
        levelCheck();
        aliens.removeIf(alien -> !alien.getIsAlive() && alien.explosionFinished());
    }

    private void levelCheck() {
        Boolean groundCheck = false;
        if (!player.getIsAlive()) {
            if (player.explosionFinished()) {
                resetLevel();
            }
        }
        for (Alien alien : aliens){
            if (alien.getYLocation() >= 625 && alien.getIsAlive()){
                player.setDeathCount(player.getDeaths() + 1);
                System.out.println("Alien has hit ground, reset level");
                groundCheck = true;
            }
        }
        if (groundCheck){
            resetLevel();
        }

        if (levelEnemiesKilled == currentLevel.levelEnemyCount()){
            nextLevel();
        }

    }

    private void nextLevel() {
        levelCounter++;
        resetLevel();
    }

    private void resetLevel() {

        System.out.println("Resetting everything");
        currentLevel = levelFactory.makeLevel(levelCounter);
        player.respawn();
        aliens.clear();
        projectiles.clear();
        levelEnemiesKilled = 0;

        newLevelStart = System.currentTimeMillis();
        levelIsStarting = true;
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

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (isGameOver()){
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
