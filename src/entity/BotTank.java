package entity;

import java.util.concurrent.ThreadLocalRandom;

import common.Direction;
import logic.GameController;

public class BotTank extends Tank {
    private boolean isAlive;
    private int calculateNextDirectionCoolDown;
    private int calculateNextDirectionCoolDownCounter;
    private boolean isBlocked;

    public BotTank(float x, float y) {
        super(x, y, false);
        isAlive = true;
        isBlocked = false;
        calculateNextDirectionCoolDown = ThreadLocalRandom.current().nextInt(30, 60);
        calculateNextDirectionCoolDownCounter = 0;
    }

    public BotTank(float x, float y, float speed, Direction direction) {
        super(x, y, speed, direction, false);
        isAlive = true;
        isBlocked = false;
        calculateNextDirectionCoolDown = ThreadLocalRandom.current().nextInt(30, 60);
        calculateNextDirectionCoolDownCounter = 0;

    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean isAlive) {
        this.isAlive = isAlive;
    }

    @Override
    protected boolean shootInput() {

        return ThreadLocalRandom.current().nextInt(0, 100) == 0;
    }

    @Override
    protected void unforward() {
        super.unforward();
        isBlocked = true;
    }

    private void calculateNextDirection() {
        if (calculateNextDirectionCoolDownCounter > 0 && !isBlocked) {
            return;
        }
        if (ThreadLocalRandom.current().nextInt(0, 5) == 0) {
            setDirection(Direction.values()[ThreadLocalRandom.current().nextInt(0, 4)]);
        } else {
            Tank player = GameController.getInstance().getPlayer();
            if (player != null) {
                float diffX = player.getX() - getX();
                float diffY = player.getY() - getY();

                if (Math.abs(diffY) < Math.abs(diffX) && diffX > 0) {
                    setDirection(Direction.RIGHT);
                } else if (Math.abs(diffY) < Math.abs(diffX) && diffX < 0) {
                    setDirection(Direction.LEFT);
                } else if (Math.abs(diffY) >= Math.abs(diffX) && diffY > 0) {
                    setDirection(Direction.DOWN);
                } else {
                    setDirection(Direction.UP);
                }
            }
        }
        isBlocked = false;
        calculateNextDirectionCoolDownCounter = calculateNextDirectionCoolDown;
    }

    @Override
    public void update() {
        calculateNextDirection();

        if (calculateNextDirectionCoolDownCounter > 0) {
            calculateNextDirectionCoolDownCounter--;
        }

        super.update();
    }

    @Override
    protected Direction getNextDirection() {
        return direction;
    }

    @Override
    public void hit() {
        GameController.getInstance().getTanks().remove(this);
        isAlive = false;
    }

    @Override
    public boolean isDestroyed() {
        return !isAlive;
    }

}
