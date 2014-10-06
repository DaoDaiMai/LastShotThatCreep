import java.util.Random;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

public class Creep {
	private static SpriteSheet tt;
	private static Animation anim;
	public int hp;
	private Image healthbar;
	Random r = new Random();
	
	public Creep(int i) throws SlickException {
		hp = i;
		healthbar = new Image("res/healthbar.png");
		tt = new SpriteSheet("res/ghost sprite sheet.png",175,175);
		anim = new Animation(tt,70);
	}
	
	public int HP(){
		return hp;
	}
	
	public void minus(int i) {
		hp -= Random(i);
		if (hp < 0) {
			hp = 0;
		}	
	}
	
	public void hit(int dmg) {
		hp -= dmg;
		if(hp<=0){
			hp=0;
		}
		
	}

	public boolean isDead() {
		if(hp<=0){
			return true;
		} else {
			return false;
		}
	}
	
	public int Random(int i){
		return 1+r.nextInt(i);
	}
	
	public void render(int j) {
		for(int i=0 ; i < hp/2 ; i++){
			healthbar.draw(lsthatcreep.GAME_WIDTH/2 - 100 + j*70, (float) (700-(lsthatcreep.GAME_HEIGHT/2 + i*1.5)));
		}
		anim.draw(lsthatcreep.GAME_WIDTH/2,lsthatcreep.GAME_HEIGHT/2);
	}
	
	public void Spawn() {
		hp = lsthatcreep.CREEP_HP;	
	}
}
