package Games.Animations;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * A decorator for a stoppable animation via key press.
 */
public class KeyPressStoppableAnimation implements Animation {

    private KeyboardSensor sensor;
    private String key;
    private Animation animation;
    private boolean stop;
    private boolean isPressed;

    /**
     *
     * A builder for a KeyPressStoppableAnimation.
     *
     * @param sensor the keyboard sensor of our KeyPressStoppableAnimation.
     * @param key the stopping key of our KeyPressStoppableAnimation.
     * @param animation the animation of our KeyPressStoppableAnimation.
     */
    public KeyPressStoppableAnimation(KeyboardSensor sensor, String key, Animation animation) {
        this.animation = animation;
        this.key = key;
        this.sensor = sensor;
        this.stop = false;
        this.isPressed = true;
    }

    @Override
    public void doOneFrame(DrawSurface d){
        this.animation.doOneFrame(d);
        if (this.sensor.isPressed(key) && !isPressed) {
            this.stop = true;
        }
        if (!this.sensor.isPressed(key)) {
            isPressed = false;
        }
    }

    @Override
    public boolean shouldStop() {
        return stop;
    }
}
