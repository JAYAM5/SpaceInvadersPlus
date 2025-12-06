package SpaceInvadersPlus.GameObjects.Projectiles;

import javax.swing.*;
import java.awt.*;

public class BaseProjectile extends Projectile {

    final Integer MOVEMENT_MODIFIER = 2;

    public BaseProjectile(Integer xLocation, Integer yLocation, Angle angle){
        initBaseProjectile();
        movementVelocity = -5;
        setX(xLocation);
        setY(yLocation);
        isPlayerProjectile = true;
        this.angle = angle;
    }

    public void move(){
        setY(yLocation += movementVelocity);

        switch (angle) {
            case RIGHT:
                setX(xLocation += movementVelocity / MOVEMENT_MODIFIER);
                break;

            case LEFT:
                setX(xLocation -= movementVelocity / MOVEMENT_MODIFIER);
                break;

            case NONE:
                break;
        }

    }

    private void initBaseProjectile() {

        ImageIcon projectileImg = new ImageIcon("src/images/base_projectile.png");
        this.image = projectileImg.getImage();
        this.image = image.getScaledInstance(IMAGE_SIZE, IMAGE_SIZE, Image.SCALE_SMOOTH);

    }

}
