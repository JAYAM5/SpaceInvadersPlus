package SpaceInvadersPlus.GameObjects.Weapons;

import SpaceInvadersPlus.GameObjects.Projectiles.Projectile;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class Trigun extends Item {

    public Trigun(){
        initTrigun();
    }
    private void initTrigun() {
        ImageIcon playerImg = new ImageIcon("src/images/trigun.png");
        this.image = playerImg.getImage();
        this.image = image.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
    }
    public List<Projectile> shoot() {
        return null;
    }

    public String getType(){
        return "Trigun";
    }
}
