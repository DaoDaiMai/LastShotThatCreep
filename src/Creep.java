

import java.util.Random;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Creep {
	public int hp;
	private Image healthbar;
	Random r = new Random();
	
	public Creep(int i) throws SlickException {
		hp = i;
		healthbar = new Image("res/healthbar.png");
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
	public void hit() {
		hp-=50;
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
			healthbar.draw(lsthatcreep.GAME_WIDTH/2 + j*50, 700-(lsthatcreep.GAME_HEIGHT/2 + i*2));
		}
	}
	public void Spawn() {
		hp = lsthatcreep.CREEP_HP;
		
	}
}
