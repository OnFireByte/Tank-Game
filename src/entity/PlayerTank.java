package entity;

import common.Constant;
import common.Direction;
import entity.base.Tank;
import logic.GameController;
import logic.GameUtil;
import logic.InputUtil;
import sharedObject.RenderableHolder;
import sharedObject.SceneManager;

public class PlayerTank extends Tank {

    private int maxHpLevel;
    private int speedLevel;
    private int shootCoolDownLevel;
    private int sizeLevel;

    public PlayerTank(int x, int y, Direction direction) {
        super(x, y, 2, 3, direction, true);
        setMaxHpLevel(0);
        setSpeedLevel(0);
        setShootCoolDownLevel(0);
        setSizeLevel(0);
    }

    @Override
    protected Direction getNextDirection() {
        return InputUtil.getPlayerKeyDirection();
    }

    @Override
    protected boolean shootInput() {
        return InputUtil.isPlayerShoot();
    }

    @Override
    public void update() {

        for (Upgrader upgrader : GameController.getInstance().getUpgraders()) {
            if (GameUtil.isCollided(upgrader, this)) {
                GameController.getInstance().getUpgraders().remove(upgrader);
                upgrade();
                break;
            }
        }
        super.update();
    }

    private void upgrade() {
        SceneManager.getInstance().openUpgradeModal();

    }

    @Override
    public void hit() {
        // Invincible frame
        if (hitCounter > 0) {
            return;
        }
        super.hit();
    }

    @Override
    public void kill() {
        super.kill();
        GameController.setGameOver(true);
        SceneManager.getInstance().openEndGameModal();
    }

    public int getMaxHpLevel() {
        return maxHpLevel;
    }

    public void setMaxHpLevel(int level) {
        if (level < 0)
            level = 0;
        if (level > Constant.MAX_UPGRADE_LEVEL)
            level = Constant.MAX_UPGRADE_LEVEL;
        this.maxHpLevel = level;

        int newMaxHp = Constant.PLAYER_TANK_MAX_HP_LEVEL.get(level);
        int diff = newMaxHp - maxHp;
        maxHp = newMaxHp;
        hp += diff;
    }

    public void addMaxHpLevel(int level) {
        setMaxHpLevel(maxHpLevel + level);
    }

    public int getSpeedLevel() {
        return speedLevel;
    }

    public void setSpeedLevel(int level) {
        if (level < 0)
            level = 0;
        if (level > Constant.MAX_UPGRADE_LEVEL)
            level = Constant.MAX_UPGRADE_LEVEL;
        this.speedLevel = level;
        speed = Constant.PLAYER_TANK_SPEED_LEVEL.get(level);
    }

    public void addSpeedLevel(int level) {
        setSpeedLevel(speedLevel + level);
    }

    public int getShootCoolDownLevel() {
        return shootCoolDownLevel;
    }

    public void setShootCoolDownLevel(int level) {
        if (level < 0)
            level = 0;
        if (level > Constant.MAX_UPGRADE_LEVEL)
            level = Constant.MAX_UPGRADE_LEVEL;
        this.shootCoolDownLevel = level;
        shootCoolDown = Constant.PLAYER_TANK_SHOOT_COOLDOWN_LEVEL.get(level);
    }

    public void addShootCoolDownLevel(int level) {
        setShootCoolDownLevel(shootCoolDownLevel + level);
    }

    public int getSizeLevel() {
        return sizeLevel;
    }

    public void setSizeLevel(int level) {
        if (level < 0)
            level = 0;
        if (level > Constant.MAX_UPGRADE_LEVEL)
            level = Constant.MAX_UPGRADE_LEVEL;
        this.sizeLevel = level;
        width = Constant.PLAYER_TANK_SIZE_LEVEL.get(level);
        height = Constant.PLAYER_TANK_SIZE_LEVEL.get(level);
    }

    public void addSizeLevel(int level) {
        setSizeLevel(sizeLevel + level);
    }

    public void heal() {
        hp = maxHp;
    }

    @Override
    public void shoot() {
        if (shootCoolDownCounter > 0) {
            return;
        }
        RenderableHolder.ShootSound.play();
        super.shoot();
    }
}
