package uet.oop.bomberman.level;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import uet.oop.bomberman.Board;
import uet.oop.bomberman.Game;
import uet.oop.bomberman.entities.LayeredEntity;
import uet.oop.bomberman.entities.character.Bomber;
import uet.oop.bomberman.entities.character.enemy.Balloon;
import uet.oop.bomberman.entities.character.enemy.Oneal;
import uet.oop.bomberman.entities.character.enemy.kondoria;
import uet.oop.bomberman.entities.tile.Grass;
import uet.oop.bomberman.entities.tile.Portal;
import uet.oop.bomberman.entities.tile.Wall;
import uet.oop.bomberman.entities.tile.destroyable.Brick;
import uet.oop.bomberman.entities.tile.item.BombItem;
import uet.oop.bomberman.entities.tile.item.FlameItem;
import uet.oop.bomberman.entities.tile.item.SpeedItem;
import uet.oop.bomberman.exceptions.LoadLevelException;
import uet.oop.bomberman.graphics.Screen;
import uet.oop.bomberman.graphics.Sprite;

public class FileLevelLoader extends LevelLoader {

	/**
	 * Ma trận chứa thông tin bản đồ, mỗi phần tử lưu giá trị kí tự đọc được
	 * từ ma trận bản đồ trong tệp cấu hình
	 */
	private static char[][] _map;
	
	public FileLevelLoader(Board board, int level) throws LoadLevelException {
		super(board, level);
	}
	
	@Override
	public void loadLevel(int level) {
		// TODO: đọc dữ liệu từ tệp cấu hình /levels/Level{level}.txt
		// TODO: cập nhật các giá trị đọc được vào _width, _height, _level, _map
                String path = "res/levels/level"+Integer.toString(level)+".txt";
                File file= new File(path);
            try {
                FileReader reader= new FileReader(file);
                BufferedReader in = new BufferedReader(reader);
                String data = in.readLine();
                StringTokenizer st = new StringTokenizer(data);
                _level=Integer.parseInt(st.nextToken());
                _height=Integer.parseInt(st.nextToken());
                _width=Integer.parseInt(st.nextToken());
                _map=new char[_height][_width];
                 for (int i = 0; i < _height; i++) {
                 data = in.readLine();
                 for (int j = 0; j < _width; j++) {
                    _map[i][j] = data.charAt(j);
                }
                 }
                 in.close();
            } 
             catch (IOException ex) {
                Logger.getLogger(FileLevelLoader.class.getName()).log(Level.SEVERE, null, ex);
            }
            
	}   
            

	@Override
	public void createEntities() {

		for(int y = 0; y < _height ; y++ ) {
			for(int x = 0; x < _width ; x++) {

				int pos = x + y * _width;
				char printSprite = _map[y][x];

				switch (printSprite) {
					case '#':
						_board.addEntity(pos, new Wall(x,y,Sprite.wall));
						break;

					case '*':
						_board.addEntity(pos, new LayeredEntity(x, y, new Grass(x ,y, Sprite.grass), new Brick(x ,y, Sprite.brick)));
						break;

					case 'p':
						_board.addEntity(pos, new Grass(x, y, Sprite.grass));
						_board.addCharacter(new Bomber(Coordinates.tileToPixel(x), Coordinates.tileToPixel(y) + Game.TILES_SIZE, _board));
						Screen.setOffset(0, 0);
						break;

					case 'b':
						_board.addEntity(pos, new LayeredEntity(x, y, new Grass(x, y, Sprite.grass), new BombItem(x, y, Sprite.powerup_bombs), new Brick(x, y, Sprite.brick)));
						break;

					case 's':
						_board.addEntity(pos, new LayeredEntity(x, y, new Grass(x, y, Sprite.grass), new SpeedItem(x, y, Sprite.powerup_speed), new Brick(x, y, Sprite.brick)));
						break;

                    case 'f':
                        _board.addEntity(pos, new LayeredEntity(x, y, new Grass(x, y, Sprite.grass), new FlameItem(x, y, Sprite.powerup_flames), new Brick(x, y, Sprite.brick)));
                        break;

                    case 'x':
                        _board.addEntity(pos, new LayeredEntity(x, y, new Grass(x, y, Sprite.grass), new Portal(x, y,_board, Sprite.portal), new Brick(x, y, Sprite.brick)));
                        break;

					case '1':
						_board.addEntity(pos, new Grass(x, y, Sprite.grass));
						_board.addCharacter(new Balloon(Coordinates.tileToPixel(x), Coordinates.tileToPixel(y) + Game.TILES_SIZE, _board));
						break;

					case '2':
						_board.addEntity(pos, new Grass(x, y, Sprite.grass));
						_board.addCharacter(new Oneal(Coordinates.tileToPixel(x), Coordinates.tileToPixel(y) + Game.TILES_SIZE, _board));
						break;

				/*	case '3':
						_board.addEntity(pos, new Grass(x, y, Sprite.grass));
						_board.addCharacter(new Doll(Coordinates.tileToPixel(x), Coordinates.tileToPixel(y) + Game.TILES_SIZE, _board));
						break;

					case '4':
						_board.addEntity(pos, new Grass(x, y, Sprite.grass));
						_board.addCharacter(new Minvo(Coordinates.tileToPixel(x), Coordinates.tileToPixel(y) + Game.TILES_SIZE, _board));
						break;*/

					case '5':
						_board.addEntity(pos, new Grass(x, y, Sprite.grass));
						_board.addCharacter(new kondoria(Coordinates.tileToPixel(x), Coordinates.tileToPixel(y) + Game.TILES_SIZE, _board));
						break;

					default:
						_board.addEntity(pos, new Grass(x, y, Sprite.grass) );
						break;
				}
			}
		}
	}
}
