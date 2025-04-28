package Games.Animations;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import java.awt.*;

public class EndScreen implements Animation {
    private KeyboardSensor keyboard;
    private boolean stop;
    int score;
    boolean won;

    public EndScreen(KeyboardSensor k, int score, boolean won) {
        this.keyboard = k;
        this.stop = false;
        this.score = score;
        this.won = won;
    }

    public void doOneFrame(DrawSurface d) {
        if (won) {
            d.setColor(Color.black);
            d.drawText(10, d.getHeight() / 2, "You Win! Score is : " + score, 32);
        }

        else {
            d.setColor(Color.black);
            d.drawText(10, d.getHeight() / 2, "Game over! Score is : " + score, 32);
        }
    }

    public boolean shouldStop() {
        return this.stop;
    }
}
