package com.battlecity.game.units;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.battlecity.game.GameScreen;

// Абстрактный класс, описывающий танк
public abstract class Tank {

    protected GameScreen gameScreen;

    protected TextureRegion texture;
    protected TextureRegion[] regions;

    protected TextureRegion textureHPBarBG;
    protected TextureRegion textureHPBar;

    protected float width;
    protected float height;

    protected float angleTank;
    protected float animTimer;
    protected int frameIndex;
    protected float secondsPerFrame = 0.075f;

    protected Vector2 position;
    protected float speed;

    protected float timeAfterFire;
    protected float reloadTime;

    protected float hpMax;
    protected float hp;

    protected Sound fireSound;
    protected Sound hitSound;
    protected Music moveSound;

    protected Rectangle hitBox;

    public Tank(GameScreen gameScreen) {
        this.gameScreen = gameScreen;
        this.fireSound = Gdx.audio.newSound(Gdx.files.internal("sounds/tankFireSound.wav"));
        this.hitSound = Gdx.audio.newSound(Gdx.files.internal("sounds/tankHitSound.wav"));
    }

    public Rectangle getHitBox() {
        return hitBox;
    }

    public abstract void render(SpriteBatch batch);

    public abstract void update(float dt);

    public boolean isTouchBorders() {
        return position.x + height / 2 >= Gdx.graphics.getWidth()
                || position.x - height / 2 <= 0.0f
                || position.y + height / 2 >= Gdx.graphics.getHeight()
                || position.y - height / 2 <= 0.0f;
    }

    public void fire() {
        float angleRadian = (float) Math.toRadians(angleTank + 90.0f);
        float velocityX = gameScreen.getShellEmitter().getShells()[0].getSpeed() * (float) Math.cos(angleRadian);
        float velocityY = gameScreen.getShellEmitter().getShells()[0].getSpeed() * (float) Math.sin(angleRadian);
        gameScreen.getShellEmitter().activate(position.x, position.y, velocityX, velocityY, angleTank);

        fireSound.play();
    }

    public void takeDamage(float damage) {
        hp -= damage;
        if (hp <= 0) {
            destroy();
        }

        hitSound.play();
    }

    public abstract void destroy();

}
