/*
 1. + Анимация движения
 2. + Стрельба
 3. + Враги
 4. Препятствия (проходимые и непроходимые)
 5. Механика боя
 6. + Жизни
 7. Интерфейс (таймер перезарядки, очки, кол-во врагов и т. д.)
 8. Меню
 9. Звуки и музыка
 10. Эмиттеры
 11. Карта
 12. ООП
 13. + Края
 14. + Создать атлас текстур
 15. Документацию
 16. ИИ врагов
 17. Эффекты (взрывы, ..)
*/

package com.battlecity.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.battlecity.game.units.EnemyTank;
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

    public ShellEmitter getShellEmitter() {
        return shellEmitter;
    }

    public void create() {
        TextureAtlas atlas = new TextureAtlas("textures.pack");
        playerTank = new PlayerTank(this, atlas);
        enemyTankEmitter = new EnemyTankEmitter(this, atlas);
        shellEmitter = new ShellEmitter(atlas);
        map = new Map(atlas);
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

        checkShellCollisions();
    }

    // Метод для проверки столкновения снарядов с танками
    public void checkShellCollisions() {
        for (int i = 0; i < shellEmitter.getShells().length; i++) {

            Shell shell = shellEmitter.getShells()[i];
            if (shell.isActive()) {

                for (int j = 0; j < enemyTankEmitter.getEnemyTanks().length; j++) {

                    EnemyTank enemyTank = enemyTankEmitter.getEnemyTanks()[j];
                    if (enemyTank.isActive()) {

                        if (enemyTank.getHitBox().contains(shell.getPosition())) {
                            shell.deactivate();
                            enemyTank.takeDamage(shell.getDamage());
                        }

                    }

                }

            }

        }
    }

}
