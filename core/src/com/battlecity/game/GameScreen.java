/*
 1. + Анимация движения
 2. + Стрельба
 3. + Враги
 4. Препятствия (проходимые и непроходимые)
 5. Механика боя
 6. Жизни (3 ХП у танка игрока, 1 ХП у танков врагов)
 7. Интерфейс (таймер перезарядки, очки, кол-во врагов и т. д.)
 8. Меню
 9. Звуки и музыка
 10. Эмиттеры
 11. Карта
 12. ООП
 13. + Края
 14. + Создать атлас текстур
*/

package com.battlecity.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.battlecity.game.units.PlayerTank;

// Класс, в к-ом создаются главные объекты игрового экрана, обновляются и рисуются
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
