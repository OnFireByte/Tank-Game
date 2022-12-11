package entity.Particle;

import entity.base.Particle;
import sharedObject.RenderableHolder;

public class BulletShootParticle extends Particle {

    public BulletShootParticle(float x, float y) {
        super(20, 20, x, y, RenderableHolder.loadNewTankExplosion(), 5);

    }
}
