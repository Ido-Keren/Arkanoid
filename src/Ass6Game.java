import Calculations.DoubleComparing;
import Games.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Class operates assignment6's game.
 */
public class Ass6Game {
    public static void main(String[] args) {
        List<LevelInformation> l = new ArrayList<>();
        GameFlow gf = new GameFlow();
        boolean selected = false;
        DoubleComparing d = new DoubleComparing();
        for (int i = 0; i < args.length; i++) {
            if (d.isDouble(args[i])){
                double level = d.toDouble(args[i]);
                if (d.isAbsEqual(level, 1)) {
                    l.add(Levels.setLevel1());
                    selected = true;
                }
                else if (d.isAbsEqual(level, 2)) {
                    l.add(Levels.setLevel2());
                    selected = true;
                }
                else if (d.isAbsEqual(level, 3)) {
                    l.add(Levels.setLevel3());
                    selected = true;
                }
            }
        }
        if (selected == false) {
            LevelInformation l1 = Levels.setLevel1();
            LevelInformation l2 = Levels.setLevel2();
            LevelInformation l3 = Levels.setLevel3();
            l.add(l1);
            l.add(l2);
            l.add(l3);
        }
        gf.runLevels(l);
    }
}
