package com.battlecity.game.units;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.battlecity.game.Direction;
import com.battlecity.game.GameScreen;

public class EnemyTank extends Tank {

    private Direction direction;
    private float directionTimer;
    private float directionPeriod;
    private int indexDirection;

    private boolean isActive;

    public EnemyTank(GameScreen game) {
        super(game);
        this.texture = new Texture("EnemyTank_Anim.png");
        this.regions = new TextureRegion(texture).split(24, 36)[0];
        this.width = regions[0].getRegionWidth();
        this.height = regions[0].getRegionHeight();
        this.animTimer = 0.0f;
        this.frameIndex = 0;
        this.position = new Vector2(640, 200);
        this.speed = 125;
        this.reloadTime = 1.5f;
        this.timeAfterFire = reloadTime;
        this.indexDirection = MathUtils.random(0, Direction.values().length - 1);
        this.angleTank = Direction.values()[indexDirection].getAngleTank();
        this.direction = Direction.values()[indexDirection];
        this.directionTimer = 0.0f;
        this.directionPeriod = 3.0f;
        this.isActive = false;
    }

    public boolean isActive() {
        return isActive;
    }

    @Override
    public void render(SpriteBatch batch) {
        drawAndAnim(batch);
    }

    @Override
    public void drawAndAnim(SpriteBatch batch) {
        frameIndex = (int) (animTimer / secondsPerFrame) % regions.length;
        batch.draw(regions[frameIndex], position.x - width / 2, position.y - height / 2, width / 2, height / 2, width, height, 1, 1, angleTank);
    }

    @Override
    public void update(float dt) {
        animTimer += dt;
        timeAfterFire += dt;
        directionTimer += dt;

        if (isTouchBorders()) {
            setDirectionOnTouchBorders();
        }

        if (directionTimer >= directionPeriod) {
            directionTimer = 0.0f;
            setDirectionOnTimer();
        }

        position.add(speed * direction.getVelocityX() * dt, speed * direction.getVelocityY() * dt);
    }

    public void activate(float x, float y) {
        isActive = true;
        position.set(x, y);
        this.indexDirection = MathUtils.random(0, Direction.values().length - 1);
        this.angleTank = Direction.values()[indexDirection].getAngleTank();
        this.direction = Direction.values()[indexDirection];
    }

    public void setDirectionOnTouchBorders() {
        switch (direction) {
            case UP:
                direction = Direction.DOWN;
                angleTank = 180.0f;
                break;
            case DOWN:
                direction = Direction.UP;
                angleTank = 0.0f;
                break;
            case LEFT:
                direction = Direction.RIGHT;
                angleTank = 270.0f;
                break;
            case RIGHT:
                direction = Direction.LEFT;
                angleTank = 90.0f;
                break;
        }
    }

    public void setDirectionOnTimer() {
        directionPeriod = MathUtils.random(2.0f, 4.0f);
        indexDirection = MathUtils.random(0, Direction.values().length - 1);
        direction = Direction.values()[indexDirection];
        angleTank = Direction.values()[indexDirection].getAngleTank();
    }

}
