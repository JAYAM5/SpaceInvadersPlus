package SpaceInvadersPlus;

import java.awt.EventQueue;
import javax.swing.JFrame;


public class SpaceInvadersPlus extends JFrame {

    public SpaceInvadersPlus() {
        initUI();
    }

    private void initUI() {
        add(new GameState());

        setTitle("Space Invaders PLUS");
        setBounds(0, 0, 800, 700);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
    }

    public static void main (String[]args){
        EventQueue.invokeLater(() -> {

            var ex = new SpaceInvadersPlus();
            ex.setVisible(true);
        });
    }
}

