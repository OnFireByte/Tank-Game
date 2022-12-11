package entity.Particle;

import entity.base.Particle;
import sharedObject.RenderableHolder;

public class BulletHitParticle extends Particle {

    public BulletHitParticle(float x, float y) {
        super(10, 10, x, y, RenderableHolder.loadNewTankExplosion(), 40);

    }
}
