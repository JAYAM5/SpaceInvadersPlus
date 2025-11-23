import GameObjects.Characters.Alien;
import GameObjects.Characters.Player;

import java.awt.EventQueue;
import java.util.List;
import javax.swing.JFrame;


public class SpaceInvadersPlus extends JFrame {

    public SpaceInvadersPlus() {
        initUI();
        gameInit();
    }

    private void initUI() {
        add(new GameState());

        setTitle("Space Invaders PLUS");
        //setSize(800, 700);
        setBounds(0, 0, 800, 700);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
    }

    private void gameInit() {

    }


        public static void main (String[]args){
            EventQueue.invokeLater(() -> {

                var ex = new SpaceInvadersPlus();
                ex.setVisible(true);
            });
        }
    }

