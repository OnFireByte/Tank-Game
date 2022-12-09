package entity.base;

import common.Direction;
import common.Updatable;

public abstract class MovableEntity extends BaseEntity implements Updatable {
    protected float speed;
    protected Direction direction;

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public MovableEntity(int width, int height, float x, float y, float speed, Direction direction) {
        super(width, height, x, y, 10);
        this.speed = speed;
        this.direction = direction;
    }

    @Override
    public int getZ() {
        return 10;
    }

}
