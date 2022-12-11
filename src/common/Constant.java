package common;

import java.util.Arrays;
import java.util.List;

public class Constant {
        public static final int GAME_HEIGHT = 600;
        public static final int GAME_WIDTH = 1000;
        public static final int APP_HEIGHT = 700;
        public static final int APP_WIDTH = 1000;

        public static final int MAX_UPGRADE_LEVEL = 7;

        public static final List<Float> PLAYER_TANK_SPEED_LEVEL = Arrays.asList(
                        new Float[] { 1.5f, 1.75f, 2f, 2.25f, 2.5f, 2.75f, 3f, 3.25f });

        public static final List<Integer> PLAYER_TANK_MAX_HP_LEVEL = Arrays
                        .asList(new Integer[] { 5, 6, 7, 8, 9, 10, 11, 12 });

        public static final List<Integer> PLAYER_TANK_SHOOT_COOLDOWN_LEVEL = Arrays
                        .asList(new Integer[] { 60, 40, 30, 25, 20, 15, 10 });

        public static final List<Integer> PLAYER_TANK_SIZE_LEVEL = Arrays
                        .asList(new Integer[] { 40, 37, 34, 31, 28, 25, 23 });

}
