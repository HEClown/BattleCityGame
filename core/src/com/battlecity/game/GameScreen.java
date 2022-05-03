package com.battlecity.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.ScreenUtils;
import com.battlecity.game.units.PlayerTank;

public class GameScreen {

    private final SpriteBatch batch;

    private PlayerTank playerTank;
    private EnemyTankEmitter enemyTankEmitter;

    private ShellEmitter shellEmitter;

    private Map map;

    public GameScreen(SpriteBatch batch) {
        this.batch = batch;
    }

    public void create() {
        TextureAtlas atlas = new TextureAtlas("textures.pack");
        playerTank = new PlayerTank(this, atlas);
        enemyTankEmitter = new EnemyTankEmitter(this, atlas);
        shellEmitter = new ShellEmitter(atlas);
        map = new Map(atlas);
    }

    public ShellEmitter getShellEmitter() {
        return shellEmitter;
    }

    public void render() {
        float dt = Gdx.graphics.getDeltaTime();

        update(dt);

        ScreenUtils.clear(0.3f, 0.3f, 0.3f, 1);

        batch.begin();
        map.render(batch);
        shellEmitter.render(batch);
        enemyTankEmitter.render(batch);
        playerTank.render(batch);
        batch.end();
    }

    public void update(float dt) {
        shellEmitter.update(dt);
        enemyTankEmitter.update(dt);
        playerTank.update(dt);
    }

}
