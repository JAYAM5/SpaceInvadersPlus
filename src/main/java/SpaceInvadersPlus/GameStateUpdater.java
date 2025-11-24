package SpaceInvadersPlus;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameStateUpdater implements ActionListener {

    GameState gameState;

    public void setGameState(GameState gameState){
        this.gameState = gameState;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        gameState.update();
    }

}
