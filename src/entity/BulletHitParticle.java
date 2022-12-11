package entity;

import entity.base.Particle;
import sharedObject.RenderableHolder;

public class BulletHitParticle extends Particle {

    public BulletHitParticle(float x, float y) {
        super(10, 10, x - 3f / 4, y - 3f / 4, RenderableHolder.loadNewTankExplosion(), 40);

    }
}
