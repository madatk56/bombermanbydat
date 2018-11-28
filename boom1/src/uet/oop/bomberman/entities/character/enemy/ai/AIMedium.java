package uet.oop.bomberman.entities.character.enemy.ai;

import uet.oop.bomberman.entities.character.Bomber;
import uet.oop.bomberman.entities.character.enemy.Enemy;

public class AIMedium extends AI {
	Bomber _bomber;
	Enemy _e;
	
	
	
	public AIMedium(Bomber bomber, Enemy e) {
		_bomber = bomber;
		_e = e;
	}

	@Override
	public int calculateDirection() {
		// TODO: cài đặt thuật toán tìm đường đi

		int col = colDirection ();
		int row = rowDirection ();

		int direction = random.nextInt (2);

		if (direction == 0){
			return col;
		}else if(direction == 1){
			return row;
		}

		return 1;
	}


	protected int colDirection(){

		if (_bomber.getYTile () > _e.getYTile ()){
			return 2; //len
		}else if (_bomber.getYTile () < _e.getYTile ()){
			return 0; //xuong
		}
		return -1;
	}

	protected int rowDirection(){

		if (_bomber.getXTile () > _e.getXTile ()){
			return 1; //phai
		}
		else if (_bomber.getXTile () < _e.getXTile ()){
			return 3; //trai
		}
		return -1;
	}

}
