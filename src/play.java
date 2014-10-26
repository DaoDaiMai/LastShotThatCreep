import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;


public class play extends BasicGameState{

	private int CREEP_COUNT = 2;
	private int END;
	private boolean finish;
	private int lvl;
	private StateBasedGame Sbg;
	private Image image;
//	private static SpriteSheet sprite1;
//	private static Animation anim1;
//	private static SpriteSheet sprite2;
//	private static Animation anim2;

	@Override
	public int getID() {
		return 2;
	}
	
	@Override
	public void init(GameContainer arg0, StateBasedGame arg1)
			throws SlickException {
		END = 10;
		lvl = 0;
		finish = true;
		image = new Image("res/bgp.png");
		lsthatcreep.creep = new Creep[CREEP_COUNT];
		for (int i = 0; i < CREEP_COUNT; i++) {
			lsthatcreep.creep[i] = new Creep(lsthatcreep.CREEP_HP);
		}
//		sprite1 = new SpriteSheet("res/sp creep.png",131,150);
//		anim1 = new Animation(sprite1,125);
//		sprite2 = new SpriteSheet("res/sp creep2.png",130,150);
//		anim2 = new Animation(sprite2,150);
	}

	@Override
	public void render(GameContainer arg0, StateBasedGame arg1, Graphics g)
			throws SlickException {
		image.draw(0,0);
		if (END>0) {
			scoreRender(g);
			pointerRender(g);
//			anim1.draw(lsthatcreep.GAME_WIDTH/2,lsthatcreep.GAME_HEIGHT/2);
//			anim2.draw(lsthatcreep.GAME_WIDTH/2-200,lsthatcreep.GAME_HEIGHT/2);
		} else {
			lsthatcreep.hero[lsthatcreep.player[0].count].Draw(50,300);
			lsthatcreep.hero[lsthatcreep.player[1].count].Draw(650,300);
			FinalScoreRender(g);
		}
	}


	
	private void pointerRender(Graphics g) throws SlickException {

		g.drawString("p2",lsthatcreep.GAME_WIDTH/2-40+lsthatcreep.player[1].Key*70,500);
		g.drawString("p1",lsthatcreep.GAME_WIDTH/2-40+lsthatcreep.player[0].Key*70-20,500);
		
	}

	@Override
	public void update(GameContainer arg0, StateBasedGame sbg, int arg2)
			throws SlickException {
		Sbg = sbg;
		if(END>0){
			CreepUpdate();
			Delay();
		} 
		
		System.out.println(""+finish);
		if(END == 0 && finish){
			calculate();
			finish = false;
		}
	}
	private void calculate() {
		int sc = lsthatcreep.player[0].Score - lsthatcreep.player[1].Score;
		if(sc>0){
			lsthatcreep.player[1].CurrentHp -= sc;
		} else {
			lsthatcreep.player[0].CurrentHp += sc;
		}
		if(lsthatcreep.player[0].CurrentHp <= 0 || lsthatcreep.player[1].CurrentHp <= 0){
			Sbg.enterState(3, new FadeOutTransition(), new FadeInTransition());
		}
	}

	@Override
	public void keyPressed(int key, char c) {
		if(END == 0){
			if(key == Input.KEY_SPACE){
				lvl++;
				END = 10 + lvl;
				lsthatcreep.player[0].Score = 0;
				lsthatcreep.player[1].Score = 0;
				for (int i = 0; i < CREEP_COUNT; i++) {
					lsthatcreep.creep[i].Spawn();
				}
				finish = true;
			}
		}
		else {
			swap(key);
			if(lsthatcreep.player[0].CurrentDelay == 0) {
				if(key == Input.KEY_S) {
					lsthatcreep.player[0].CurrentDelay = lsthatcreep.player[0].delay;
					lsthatcreep.creep[lsthatcreep.player[0].Key].hit(lsthatcreep.player[0].CurrentDmg);
					if(lsthatcreep.creep[lsthatcreep.player[0].Key].isDead()){
						lsthatcreep.player[0].Score++;
					}
				}
			}
			if(lsthatcreep.player[1].CurrentDelay == 0) {
				if(key == Input.KEY_L) {
					lsthatcreep.player[1].CurrentDelay = lsthatcreep.player[1].delay;
					lsthatcreep.creep[lsthatcreep.player[1].Key].hit(lsthatcreep.player[1].CurrentDmg);
					if(lsthatcreep.creep[lsthatcreep.player[1].Key].isDead()){
						lsthatcreep.player[1].Score++;
					}
				}
			}
		}
	}

	private void swap(int key) {
		if(key == Input.KEY_A){
			lsthatcreep.player[0].Key--;
			if(lsthatcreep.player[0].Key < 0) {
				lsthatcreep.player[0].Key = CREEP_COUNT-1;
			}
		}
		if(key == Input.KEY_D){
			lsthatcreep.player[0].Key++;
			if(lsthatcreep.player[0].Key > CREEP_COUNT-1) {
				lsthatcreep.player[0].Key = 0;
			}
		}
		if(key == Input.KEY_K){
			lsthatcreep.player[1].Key--;
			if(lsthatcreep.player[1].Key < 0) {
				lsthatcreep.player[1].Key = CREEP_COUNT-1;
			}
		}
		if(key == Input.KEY_SEMICOLON){
			lsthatcreep.player[1].Key++;
			if(lsthatcreep.player[1].Key > CREEP_COUNT-1) {
				lsthatcreep.player[1].Key = 0;
			}
		}
	}

	private void CreepUpdate() {
		for (int i = 0 ; i < CREEP_COUNT ; i++){
			if (!lsthatcreep.creep[i].isDead())	{
				lsthatcreep.creep[i].minus(i+1);
			}	
			if (lsthatcreep.creep[i].isDead()){
				if (END>0){
					END--;
				}
				lsthatcreep.creep[i].Spawn();
			}
		}
//				
		}
	
	
	private void Delay() {
		if (lsthatcreep.player[0].CurrentDelay>0) { 
			lsthatcreep.player[0].CurrentDelay--;
		}
		if (lsthatcreep.player[1].CurrentDelay>0) {
			lsthatcreep.player[1].CurrentDelay--;
		}
		
	}

	private void FinalScoreRender(Graphics g) {
		g.drawString("p1 score " + lsthatcreep.player[0].Score, 140, 220);
		g.drawString("p2 score " + lsthatcreep.player[1].Score, lsthatcreep.GAME_WIDTH/4*3, 220);
		if(lsthatcreep.player[0].Score > lsthatcreep.player[1].Score){
			g.drawString("player1 WIN", lsthatcreep.GAME_WIDTH/2-20, 200);
		} else if (lsthatcreep.player[0].Score < lsthatcreep.player[1].Score){
			g.drawString("player2 WIN", lsthatcreep.GAME_WIDTH/2-20, 200);
		} else {
			g.drawString("Draw", lsthatcreep.GAME_WIDTH/2-20, 200);
		}
		g.drawString("p1 hp :  " + lsthatcreep.player[0].CurrentHp, 140, 200);
		g.drawString("p2 hp :  " + lsthatcreep.player[1].CurrentHp, lsthatcreep.GAME_WIDTH/4*3, 200);
		g.drawString("press SPACE to continue", lsthatcreep.GAME_WIDTH/2-100, lsthatcreep.GAME_HEIGHT-40);
		
	}
	private void scoreRender(Graphics g) {
		g.drawString("p1 score " + lsthatcreep.player[0].Score, lsthatcreep.GAME_WIDTH/2, 0);
		g.drawString("p2 score " + lsthatcreep.player[1].Score, lsthatcreep.GAME_WIDTH/2+120, 0);
		g.drawString("p1 hp :  " + lsthatcreep.player[0].CurrentHp, lsthatcreep.GAME_WIDTH/2, 20);
		g.drawString("p2 hp :  " + lsthatcreep.player[1].CurrentHp, lsthatcreep.GAME_WIDTH/2+120, 20);
		g.drawString("p1 dmg   " + lsthatcreep.player[0].CurrentDmg, lsthatcreep.GAME_WIDTH/2, 40);
		g.drawString("p2 dmg   " + lsthatcreep.player[1].CurrentDmg, lsthatcreep.GAME_WIDTH/2+120, 40);
		g.drawString("p1 delay " + lsthatcreep.player[0].delay, lsthatcreep.GAME_WIDTH/2, 60);
		g.drawString("p2 delay " + lsthatcreep.player[1].delay, lsthatcreep.GAME_WIDTH/2+120, 60);
		g.drawString("p1 delay : " + lsthatcreep.player[0].CurrentDelay, 200, 0);
		g.drawString("p2 delay : " + lsthatcreep.player[1].CurrentDelay, 200, 20);
		//g.drawString("p1 "+lsthatcreep.player[0].Key,lsthatcreep.GAME_WIDTH/2-45l, 450);
		//g.drawString("p2 "+lsthatcreep.player[1].Key, lsthatcreep.GAME_WIDTH/2 + 45, 450);
		for (int i = 0; i < CREEP_COUNT ; i++){
			lsthatcreep.creep[i].render(i);
		}
		g.drawString("Level "+lvl, 30, 60);
		g.drawString("Creep Left "+END, 30, 40);
		lsthatcreep.hero[lsthatcreep.player[0].count].Draw(50,300);
		lsthatcreep.hero[lsthatcreep.player[1].count].Draw(650,300);
		
	}



}
