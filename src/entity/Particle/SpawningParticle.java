package entity.Particle;

import entity.base.Particle;
import sharedObject.RenderableHolder;

public class SpawningParticle extends Particle {

    public SpawningParticle(float x, float y) {
        super(50, 50, x - 3, y - 3, RenderableHolder.openingAnimationTank, 40);
    }
}
