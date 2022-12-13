package entity.base;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import logic.GameController;

public abstract class Particle extends BaseEntity {
    private int lifeTime;

    private int lifeTimeCounter;
    private Image image;

    public Particle(int width, int height, float x, float y, Image image, int lifeTime) {
        super(width, height, x, y);
        this.lifeTime = lifeTime;
        this.lifeTimeCounter = lifeTime;
        this.image = image;
        GameController.getInstance().getParticles().add(this);
    }

    @Override
    public void update() {
        lifeTimeCounter--;
        if (lifeTimeCounter <= 0) {
            visible = false;
            GameController.getInstance().getParticles().remove(this);
            return;
        }
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.drawImage(image, x - width / 2, y - height / 2, width, height);

    }

    public int getLifeTime() {
        return lifeTime;
    }

    public void setLifeTime(int lifeTime) {
        this.lifeTime = lifeTime;
    }
}
