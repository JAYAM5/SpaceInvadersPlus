package SpaceInvadersPlus.GameObjects.Projectiles;

import javax.swing.*;
import java.awt.*;

public class BaseAlienProjectile extends Projectile {

    Projectile currentProjectile;

    public BaseAlienProjectile(Integer xLocation, Integer yLocation){
        initBaseAlienProjectile();
        movementVelocity = 5;
        setX(xLocation);
        setY(yLocation);
        isPlayerProjectile = false;
    }

    public void move(){
        setY(yLocation += movementVelocity);
    }

    private void initBaseAlienProjectile() {

        ImageIcon projectileImg = new ImageIcon("src/images/base_projectile.png");
        this.image = projectileImg.getImage();
        this.image = image.getScaledInstance(40, 40, Image.SCALE_SMOOTH);

    }

    public Projectile getCurrentProjectile(){
        return currentProjectile;
    }
}
