package SpaceInvadersPlus.GameObjects.Weapons;

import SpaceInvadersPlus.GameObjects.Projectiles.Projectile;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class Railgun extends Item {

    public Railgun(){
        initRailgun();
    }

    private void initRailgun() {
        ImageIcon playerImg = new ImageIcon("src/images/railgun.png");
        this.image = playerImg.getImage();
        this.image = image.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
    }

    public List<Projectile> shoot() {
        return null;
    }

    public String getType(){
        return "Railgun";
    }
}
