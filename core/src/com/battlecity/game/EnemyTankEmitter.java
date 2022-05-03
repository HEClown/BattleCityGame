package com.battlecity.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.battlecity.game.units.EnemyTank;

public class EnemyTankEmitter {

    private EnemyTank[] enemyTanks;

    public static final int MAX_ENEMY_TANKS_COUNT = 16;

    public EnemyTankEmitter(GameScreen gameScreen, TextureAtlas atlas) {
        this.enemyTanks = new EnemyTank[MAX_ENEMY_TANKS_COUNT];
        for (int i = 0; i < enemyTanks.length; i++) {
            this.enemyTanks[i] = new EnemyTank(gameScreen, atlas);
        }

        for (int i = 0; i < 3; i++) {
            enemyTanks[i].activate(100 * (i + 1), 200 * (i + 1));
        }
    }

    public EnemyTank[] getEnemyTanks() {
        return enemyTanks;
    }

    public void activate(float x, float y) {
        for (int i = 0; i < enemyTanks.length; i++) {
            if (!enemyTanks[i].isActive()) {
                enemyTanks[i].activate(x, y);
                break;
            }
        }
    }

    public void render(SpriteBatch batch) {
        for (int i = 0; i < enemyTanks.length; i++) {
            if (enemyTanks[i].isActive()) {
                enemyTanks[i].render(batch);
            }
        }
    }

    public void update(float dt) {
        for (int i = 0; i < enemyTanks.length; i++) {
            if (enemyTanks[i].isActive()) {
                enemyTanks[i].update(dt);
            }
        }
    }

}
