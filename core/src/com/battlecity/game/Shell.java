package com.battlecity.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

public class Shell {

    private Vector2 position;
    private Vector2 velocity;
    private float angle;
    private float speed;

    private boolean isActive;

    public Shell() {
        this.position = new Vector2();
        this.velocity = new Vector2();
        this.speed = 300;
    }

    public void setAngle(float angleTank) {
        this.angle = angleTank;
    }

    public float getAngle() {
        return angle;
    }

    public Vector2 getPosition() {
        return position;
    }

    public float getSpeed() {
        return speed;
    }

    public boolean isActive() {
        return isActive;
    }

    public void update(float dt) {
        position.mulAdd(velocity, dt);
        checkCollision();
    }

    public void activate(float x, float y, float velocityX, float velocityY) {
        isActive = true;
        position.set(x, y);
        velocity.set(velocityX, velocityY);
    }

    public void deactivate() {
        isActive = false;
    }

    public void checkCollision() {
        if (position.x <= 0 || position.x >= Gdx.graphics.getWidth() || position.y <= 0 || position.y >= Gdx.graphics.getHeight()) {
            deactivate();
        }
    }

}
