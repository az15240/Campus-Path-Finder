package setup;

import java.util.Comparator;

public class BallComparator implements Comparator<Ball> {
    public int compare(Ball b1, Ball b2) {
        if (b1.getVolume() < b2.getVolume()) {
            return -1;
        } else if (b1.getVolume() == b2.getVolume()) {
            return 0;
        } else {
            return 1;
        }
    }
}
