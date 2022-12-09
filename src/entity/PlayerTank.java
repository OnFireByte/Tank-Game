package entity;

import common.Direction;
import logic.InputUtil;

public class PlayerTank extends Tank {

    public PlayerTank(int x, int y, Direction direction) {
        super(x, y, 2, direction, true);
    }

    @Override
    protected Direction getNextDirection() {
        return InputUtil.getPlayerKeyDirection();
    }

    @Override
    protected boolean shootInput() {
        return InputUtil.isPlayerShoot();
    }

    @Override
    public boolean isDestroyed() {
        // TODO Auto-generated method stub
        return false;
    }
}
