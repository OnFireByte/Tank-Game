package common;

import java.util.Arrays;
import java.util.List;

import javafx.geometry.Point2D;

public class Constant {
    public static final int GAME_HEIGHT = 600;
    public static final int GAME_WIDTH = 1000;
    public static final int APP_HEIGHT = 700;
    public static final int APP_WIDTH = 1000;

    public static final int MAX_UPGRADE_LEVEL = 5;

    public static final List<Float> PLAYER_TANK_SPEED_LEVEL = Arrays.asList(
            new Float[] { 1.5f, 2f, 2.25f, 2.5f, 2.75f, 3f });

    public static final List<Integer> PLAYER_TANK_MAX_HP_LEVEL = Arrays.asList(new Integer[] { 3, 4, 5, 6, 7, 8 });

    public static final List<Integer> PLAYER_TANK_SHOOT_COOLDOWN_LEVEL = Arrays
            .asList(new Integer[] { 40, 35, 30, 25, 20, 15 });

    public static final List<Integer> PLAYER_TANK_SIZE_LEVEL = Arrays
            .asList(new Integer[] { 40, 35, 30, 26, 23, 20 });

    public static Point2D x = new Point2D(1, 2);

}
