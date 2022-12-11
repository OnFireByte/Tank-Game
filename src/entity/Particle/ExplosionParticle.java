package entity.Particle;

import entity.base.Particle;
import sharedObject.RenderableHolder;

public class ExplosionParticle extends Particle {

    public ExplosionParticle(float x, float y) {
        super(40, 40, x, y, RenderableHolder.loadNewTankExplosion(), 55);
        RenderableHolder.explosionSound.play();

    }
}
