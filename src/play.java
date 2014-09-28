import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;


public class play extends BasicGameState{

	private int CREEP_COUNT = 4;
	private int END;
	private boolean finish;
	private int lvl;

	@Override
	public int getID() {
		return 1;
	}
	
	@Override
	public void init(GameContainer arg0, StateBasedGame arg1)
			throws SlickException {
		END = 10;
		lvl = 0;
		finish = true;
		lsthatcreep.creep = new Creep[CREEP_COUNT];
		for (int i = 0; i < CREEP_COUNT; i++) {
			lsthatcreep.creep[i] = new Creep(lsthatcreep.CREEP_HP);
		}
		
	}

	@Override
	public void render(GameContainer arg0, StateBasedGame arg1, Graphics g)
			throws SlickException {
		if (END>0) {
			scoreRender(g);
			pointerRender(g);
		} else {
			lsthatcreep.hero[lsthatcreep.player[0].count].Draw(100,300);
			lsthatcreep.hero[lsthatcreep.player[1].count].Draw(700,300);
			FinalScoreRender(g);
		}
	}


	
	private void pointerRender(Graphics g) throws SlickException {

		g.drawString("p2",425+lsthatcreep.player[1].Key*70,500);
		g.drawString("p1",425+lsthatcreep.player[0].Key*70-20,500);
		
	}

	@Override
	public void update(GameContainer arg0, StateBasedGame arg1, int arg2)
			throws SlickException {
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
					lsthatcreep.creep[lsthatcreep.player[0].Key].hit(lsthatcreep.player[0].CurrentDmg);
				}
			}
			if(lsthatcreep.player[1].CurrentDelay == 0) {
				if(key == Input.KEY_L) {
					lsthatcreep.creep[lsthatcreep.player[1].Key].hit(lsthatcreep.player[1].CurrentDmg);
				}
			}
		}
	}

	private void swap(int key) {
		if(key == Input.KEY_A){
			lsthatcreep.player[0].Key--;
			if(lsthatcreep.player[0].Key < 0) {
				lsthatcreep.player[0].Key = 3;
			}
		}
		if(key == Input.KEY_D){
			lsthatcreep.player[0].Key++;
			if(lsthatcreep.player[0].Key > 3) {
				lsthatcreep.player[0].Key = 0;
			}
		}
		if(key == Input.KEY_K){
			lsthatcreep.player[1].Key--;
			if(lsthatcreep.player[1].Key < 0) {
				lsthatcreep.player[1].Key = 3;
			}
		}
		if(key == Input.KEY_SEMICOLON){
			lsthatcreep.player[1].Key++;
			if(lsthatcreep.player[1].Key > 3) {
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
		g.drawString("p1 score " + lsthatcreep.player[0].Score, lsthatcreep.GAME_WIDTH/2-100, 400);
		g.drawString("p2 score " + lsthatcreep.player[1].Score, lsthatcreep.GAME_WIDTH/2+100, 400);
		if(lsthatcreep.player[0].Score > lsthatcreep.player[1].Score){
			g.drawString("player1 WIN", lsthatcreep.GAME_WIDTH/2-20, 200);
		} else if (lsthatcreep.player[0].Score < lsthatcreep.player[1].Score){
			g.drawString("player2 WIN", lsthatcreep.GAME_WIDTH/2-20, 200);
		} else {
			g.drawString("Draw", lsthatcreep.GAME_WIDTH/2-20, 200);
		}
		g.drawString("p1 hp :  " + lsthatcreep.player[0].CurrentHp, lsthatcreep.GAME_WIDTH/2, 20);
		g.drawString("p2 hp :  " + lsthatcreep.player[1].CurrentHp, lsthatcreep.GAME_WIDTH/2+120, 20);
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
		g.drawString("p1 "+lsthatcreep.player[0].Key,lsthatcreep.GAME_WIDTH/2-45l, 450);
		g.drawString("p2 "+lsthatcreep.player[1].Key, lsthatcreep.GAME_WIDTH/2 + 45, 450);
		for (int i = 0; i < CREEP_COUNT ; i++){
			lsthatcreep.creep[i].render(i);
		}
		g.drawString("Level "+lvl, 30, 60);
		g.drawString("Creep Left "+END, 30, 40);
		lsthatcreep.hero[lsthatcreep.player[0].count].Draw(100,300);
		lsthatcreep.hero[lsthatcreep.player[1].count].Draw(700,300);
		
	}



}
