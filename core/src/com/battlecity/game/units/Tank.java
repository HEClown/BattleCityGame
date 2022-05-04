package com.battlecity.game.units;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.battlecity.game.GameScreen;

public abstract class Tank {

    GameScreen gameScreen;

    TextureRegion texture;
    TextureRegion[] regions;
    TextureRegion textureHPBarBG;
    TextureRegion textureHPBar;

    float angleTank;
    float animTimer;
    int frameIndex;
    float secondsPerFrame = 0.075f;

    float width;
    float height;

    Vector2 position;
    float speed;

    float timeAfterFire;
    float reloadTime;

    float hpMax;
    float hp;

    public Tank(GameScreen gameScreen) {
        this.gameScreen = gameScreen;
    }

    public abstract void render(SpriteBatch batch);

    public abstract void drawAndAnim(SpriteBatch batch);

    public abstract void update(float dt);

    public void fire() {
        float angleRadian = (float) Math.toRadians(angleTank + 90.0f);
        float velocityX = gameScreen.getShellEmitter().getShells()[0].getSpeed() * (float) Math.cos(angleRadian);
        float velocityY = gameScreen.getShellEmitter().getShells()[0].getSpeed() * (float) Math.sin(angleRadian);
        gameScreen.getShellEmitter().activate(position.x, position.y, velocityX, velocityY, angleTank);
    }

    public boolean isTouchBorders() {
        return position.x + height / 2 >= Gdx.graphics.getWidth() || position.x - height / 2 <= 0.0f || position.y + height / 2 >= Gdx.graphics.getHeight() || position.y - height / 2 <= 0.0f;
    }

}
