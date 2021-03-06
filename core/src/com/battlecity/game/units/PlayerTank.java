package com.battlecity.game.units;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.battlecity.game.GameScreen;

// Класс, описывающий танк игрока
public class PlayerTank extends Tank {

    private boolean isMove;

    private int lifes;

    public PlayerTank(GameScreen game, TextureAtlas atlas) {
        super(game);
        this.texture = atlas.findRegion("PlayerTankAnim");
        this.regions = new TextureRegion(texture).split(39, 47)[0];
        this.textureHPBarBG = atlas.findRegion("HPBarBG");
        this.textureHPBar = atlas.findRegion("HPBar");
        this.width = regions[0].getRegionWidth();
        this.height = regions[0].getRegionHeight();
        this.angleTank = 0.0f;
        this.animTimer = 0.0f;
        this.isMove = false;
        this.frameIndex = 0;
        this.position = new Vector2(640, 100);
        this.speed = 75;
        this.reloadTime = 1.0f;
        this.timeAfterFire = reloadTime;
        this.lifes = 3;
        this.hpMax = 10;
        this.hp = hpMax;
        this.hitBox = new Rectangle(position.x - width / 2, position.y - height / 2, width, height);
        this.moveSound = Gdx.audio.newMusic(Gdx.files.internal("sounds/tankMoving.mp3"));
    }

    @Override
    public void render(SpriteBatch batch) {
        // Если игрок двигается проигрывается анимация движения
        // если не двигается, то рисуется один и тот же кадр анимации
        if (isMove) {
            frameIndex = (int) (animTimer / secondsPerFrame) % regions.length;
        }
        batch.draw(regions[frameIndex], position.x - width / 2, position.y - height / 2,
                width / 2, height / 2,
                width, height,
                1, 1, angleTank);

        // Если хп меньше максимального, то рисуется полоска хп
        // Если хп максимально, то полоска хп не рисуется
        if (hp < hpMax) {
            batch.draw(textureHPBarBG, position.x - width / 2 + 2, position.y - width / 2 - 15);
            batch.draw(textureHPBar, position.x - width / 2 + 3, position.y - width / 2 - 14,
                    (hp / hpMax) * 33, 4);
        }
    }

    @Override
    public void update(float dt) {
        animTimer += dt;
        timeAfterFire += dt;

        checkMove(dt);

        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE) && timeAfterFire >= reloadTime) {
            fire();
            timeAfterFire = 0;
        }

        if (isTouchBorders()) {
            setPosition();
        }

        if (!isMove) {
            moveSound.stop();
        }
        moveSound.play();

        hitBox.setPosition(position.x - width / 2, position.y - height / 2);
    }

    public void checkMove(float dt) {
        isMove = false;
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            angleTank = 0.0f;
            position.y += speed * dt;
            isMove = true;
        } else if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            angleTank = 180.0f;
            position.y -= speed * dt;
            isMove = true;
        } else if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            angleTank = 90.0f;
            position.x -= speed * dt;
            isMove = true;
        } else if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            angleTank = 270.0f;
            position.x += speed * dt;
            isMove = true;
        }
    }

    public void setPosition() {
        if (position.y + height / 2 >= Gdx.graphics.getHeight()) {
            position.y = Gdx.graphics.getHeight() - height / 2;
        }
        if (position.y - height / 2 <= 0.0f) {
            position.y = 0.0f + height / 2;
        }
        if (position.x - height / 2 <= 0.0f) {
            position.x = 0.0f + height / 2;
        }
        if (position.x + height / 2 >= Gdx.graphics.getWidth()) {
            position.x = Gdx.graphics.getWidth() - height / 2;
        }
    }

    @Override
    public void destroy() {
        lifes--;
    }

}
