package com.battlecity.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

// Класс, управляющий отрисовкой и апдейтом снарядов
public class ShellEmitter {

    private Shell[] shells;

    private TextureRegion texture;

    private static final int MAX_SHELL_COUNT = 50;

    public ShellEmitter(TextureAtlas atlas) {
        this.texture = atlas.findRegion("TankShell");
        this.shells = new Shell[MAX_SHELL_COUNT];
        for (int i = 0; i < shells.length; i++) {
            this.shells[i] = new Shell();
        }
    }

    public Shell[] getShells() {
        return shells;
    }

    public void render(SpriteBatch batch) {
        for (int i = 0; i < shells.length; i++) {
            if (shells[i].isActive()) {
                batch.draw(texture, shells[i].getPosition().x - 2, shells[i].getPosition().y - 5,
                        2, 5, 3, 10,
                        1, 1, shells[i].getAngle());
            }
        }
    }

    public void update(float dt) {
        for (int i = 0; i < shells.length; i++) {
            if (shells[i].isActive()) {
                shells[i].update(dt);
            }
        }
    }

    public void activate(float x, float y, float velocityX, float velocityY, float angleTank) {
        for (int i = 0; i < shells.length; i++) {
            if (!shells[i].isActive()) {
                shells[i].setAngle(angleTank);
                shells[i].activate(x, y, velocityX, velocityY);
                break;
            }
        }
    }

}
