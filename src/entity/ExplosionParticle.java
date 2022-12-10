package entity;

import entity.base.Particle;
import sharedObject.RenderableHolder;

public class ExplosionParticle extends Particle {

    public ExplosionParticle(float x, float y) {
        super(60, 60, x - 7, y - 7, RenderableHolder.loadNewTankExplosion(), 55);
        RenderableHolder.ExplosionSound.play();

    }
}
