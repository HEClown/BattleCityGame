package com.battlecity.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.battlecity.game.units.EnemyTank;

// Класс, управляющий апдейтом и отрисовкой танков врагов
public class EnemyTankEmitter {

    private EnemyTank[] enemyTanks;

    public static final int MAX_ENEMY_TANKS_COUNT = 64;

    private float spawnTimer;
    private float spawnCount;

    public EnemyTankEmitter(GameScreen gameScreen, TextureAtlas atlas) {
        // Создаём массив вражеских танков
        this.enemyTanks = new EnemyTank[MAX_ENEMY_TANKS_COUNT];
        for (int i = 0; i < enemyTanks.length; i++) {
            this.enemyTanks[i] = new EnemyTank(gameScreen, atlas);
        }

        this.spawnTimer = 0;
        this.spawnCount = 1;
    }

    public EnemyTank[] getEnemyTanks() {
        return enemyTanks;
    }

    public void render(SpriteBatch batch) {
        for (int i = 0; i < enemyTanks.length; i++) {
            if (enemyTanks[i].isActive()) {
                enemyTanks[i].render(batch);
            }
        }
    }

    public void update(float dt) {
        // Отрисовка вражеских танков
        spawnTimer += dt;
        if (spawnTimer >= 5 && spawnCount <= 3) {
            spawnTimer = 0;
            for (int i = 0; i < spawnCount; i++) {
                activate(100 * (i + 1), 550);
            }
            spawnCount++;
        }

        for (int i = 0; i < enemyTanks.length; i++) {
            if (enemyTanks[i].isActive()) {
                enemyTanks[i].update(dt);
            }
        }
    }

    public void activate(float x, float y) {
        for (int i = 0; i < enemyTanks.length; i++) {
            if (!enemyTanks[i].isActive()) {
                enemyTanks[i].activate(x, y);
                break;
            }
        }
    }

}
