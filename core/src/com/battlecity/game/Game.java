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
*/

package com.battlecity.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Game extends ApplicationAdapter {

	private GameScreen gameScreen;
	
	@Override
	public void create() {
		SpriteBatch batch = new SpriteBatch();
		gameScreen = new GameScreen(batch);
		gameScreen.create();
	}

	@Override
	public void render() {
		gameScreen.render();
	}

}
