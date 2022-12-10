package entity.base;

import common.Direction;

public abstract class MovableEntity extends BaseEntity {
    protected float speed;
    protected Direction direction;

    public MovableEntity(int width, int height, float x, float y, float speed, Direction direction) {
        super(width, height, x, y, 10);
        this.speed = speed;
        this.direction = direction;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        if (speed < 0) {
            speed = 0;
        }
        this.speed = speed;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    @Override
    public int getZ() {
        return 10;
    }

}
