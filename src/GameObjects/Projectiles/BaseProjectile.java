package GameObjects.Projectiles;

import javax.swing.*;
import java.awt.*;

public class BaseProjectile extends Projectile {

    Projectile currentProjectile;

    public BaseProjectile(Integer xLocation, Integer yLocation){
        initBaseProjectile();
        movementVelocity = -5;
        setX(xLocation);
        setY(yLocation);
        isPlayerProjectile = true;
    }

    public void move(){
        setY(yLocation += movementVelocity);
    }

    private void initBaseProjectile() {

        ImageIcon projectileImg = new ImageIcon("src/images/base_projectile.png");
        this.image = projectileImg.getImage();
        this.image = image.getScaledInstance(40, 40, Image.SCALE_SMOOTH);

    }

    public Projectile getCurrentProjectile(){
        return currentProjectile;
    }
}
