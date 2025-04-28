package Games;

import Calculations.Counter;
import Games.Animations.*;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import java.util.List;

/**
 * Class run multiple levels in a row.
 */
public class GameFlow {
    private Counter score;
    private GUI gui;
    private boolean won;

    /**
     * A builder of a GameFlow.
     */
    public GameFlow() {
        this.score = new Counter(0);
        this.gui = new GUI("Our Game", 800, 600);
        this.won = true;
    }

    /**
     * Method run levels inputted in a row.
     *
     * @param levels the levels to run.
     */
    public void runLevels(List<LevelInformation> levels) {
        for (LevelInformation levelInfo : levels) {
            GameLevel level = new GameLevel(levelInfo, gui);
            level.initialize(score);
            while (!level.shouldStop()) {
                level.run();
            }
            if (level.getBallsAmount().getValue() == 0) {
                won = false;
                break;
            }
        }
        AnimationRunner r = new AnimationRunner(gui, 60);
        EndScreen e = new EndScreen(gui.getKeyboardSensor(), score.getValue(), won);
        r.run(new KeyPressStoppableAnimation(gui.getKeyboardSensor(), KeyboardSensor.SPACE_KEY, e));
        gui.close();
    }
}
