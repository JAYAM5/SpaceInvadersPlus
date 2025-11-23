import GameObjects.Characters.Alien;
import GameObjects.Characters.CharacterFactory;
import GameObjects.Characters.Player;
import GameObjects.Projectiles.Projectile;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class GameState extends JPanel {
    private Dimension d;
    private List<Alien> aliens;
    private Player player;
    List<Projectile> projectiles;
    CharacterFactory characterFactory = new CharacterFactory();

    private int direction = -1;
    private int deaths = 0;

    GameStateUpdater gameStateUpdater;
    Timer timer;

    public GameState() {

        initBoard();
        player = characterFactory.createPlayer();
        projectiles = new ArrayList<>();
        aliens = new ArrayList<>();
        aliens.add(characterFactory.createAlien());
    }

    public void update(){
        player.move();

        for (Projectile projectile : projectiles){
            projectile.move();
            if (projectile.getXLocation() <= 0) {
                projectiles.remove(projectile);
            }
        }
        for (Alien alien : aliens){
            alien.move();
            if (alien.shouldShoot()){
                projectiles.addAll(alien.shoot());
            }
        }

        collisions();
        repaint();
    }

    private void collisions(){
        for (Projectile projectile : projectiles){
            Integer projectileX = projectile.getXLocation();
            Integer projectileY = projectile.getYLocation();
            for (Alien alien : aliens){
                if (alien.getIsAlive()) {
                    if (projectileX >= alien.getXLocation() &&
                            projectileX <= alien.getXLocation() + 20 &&
                            projectileY >= alien.getYLocation() &&
                            projectileY <= alien.getYLocation() + 20 &&
                            projectile.getIsPlayerProjectile()) {
                        alien.explode();
                    }
                }
            }
            if (projectileX >= player.getXLocation() &&
                    projectileX <= player.getXLocation() + 20 &&
                    projectileY >= player.getYLocation() &&
                    projectileY <= player.getYLocation() + 20) {
                player.explode();
            }

        }
        aliens.removeIf(alien -> !alien.getIsAlive() && alien.explosionFinished());
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

        doDrawing(g);
    }



    private void doDrawing(Graphics g){
        g.setColor(Color.black);
        g.fillRect(0, 0, d.width, d.height);
        g.setColor(Color.green);

        drawPlayer(g);
        drawAliens(g);
        drawProjectile(g);
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
