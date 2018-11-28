package uet.oop.bomberman.entities.tile.item;

import uet.oop.bomberman.Game;
import uet.oop.bomberman.Sound.GameSound;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.character.Bomber;
import uet.oop.bomberman.graphics.Sprite;

public class BombItem extends Item {

	public BombItem(int x, int y, Sprite sprite) {
		super(x, y, sprite);
	}

	@Override
	public boolean collide(Entity e) {
		// TODO: xử lý Bomber ăn Item
                if(e instanceof Bomber){
                    Game.addBombRate(1);
                     GameSound.playSound("src/uet/oop/bomberman/Sound/item.wav");
                     remove();
                }
		return false;
	}
	


}
