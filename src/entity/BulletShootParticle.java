package entity;

import entity.base.Particle;
import sharedObject.RenderableHolder;

public class BulletShootParticle extends Particle {

    public BulletShootParticle(float x, float y) {
        super(20, 20, x - 3f / 4, y - 3f / 4, RenderableHolder.loadNewTankExplosion(), 5);

    }
}
