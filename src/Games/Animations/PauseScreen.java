package Games.Animations;

import biuoop.KeyboardSensor;
import biuoop.DrawSurface;
import java.awt.*;

/**
 * A class that operates a pause screen.
 */
public class PauseScreen implements Animation {
    private KeyboardSensor keyboard;
    private boolean stop;

    /**
     * A builder for a pause screen.
     *
     * @param k the KeyboardSensor of our pause screen.
     */
    public PauseScreen(KeyboardSensor k) {
        this.keyboard = k;
        this.stop = false;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        d.setColor(Color.black);
        d.drawText(10, d.getHeight() / 2, "paused -- press space to continue", 32);
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}
