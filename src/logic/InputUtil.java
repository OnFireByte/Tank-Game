package logic;

import java.util.LinkedList;

import common.Direction;
import javafx.scene.input.KeyCode;

public class InputUtil {
    private static boolean isPlayerShoot;
    private static LinkedList<Direction> directions = new LinkedList<>();

    public static Direction getPlayerKeyDirection() {
        if (directions.isEmpty()) {
            return Direction.HOLD;
        }
        return directions.getLast();
    }

    public static boolean isPlayerShoot() {
        return isPlayerShoot;
    }

    private static void push(Direction direction) {
        if (directions.contains(direction)) {
            return;
        }

        directions.add(direction);
    }

    private static void remove(Direction direction) {

        directions.remove(direction);
    }

    public static void setKeyPressed(KeyCode keyCodes) {
        if (keyCodes == null) {
            keyCodes = KeyCode.UNDEFINED;
        }

        switch (keyCodes) {
            case W:
                push(Direction.UP);
                break;
            case S:
                push(Direction.DOWN);
                break;
            case A:
                push(Direction.LEFT);
                break;
            case D:
                push(Direction.RIGHT);
                break;

            case SPACE:
                isPlayerShoot = true;
                break;

            default:
                break;
        }
    }

    public static void setKeyReleased(KeyCode keyCodes) {
        if (keyCodes == null) {
            keyCodes = KeyCode.UNDEFINED;
        }

        switch (keyCodes) {
            case W:
                remove(Direction.UP);
                break;
            case S:
                remove(Direction.DOWN);
                break;
            case A:
                remove(Direction.LEFT);
                break;
            case D:
                remove(Direction.RIGHT);
                break;

            case SPACE:
                isPlayerShoot = false;
                break;

            default:
                break;
        }
    }
}