import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;


public class HowTo extends BasicGameState{

	private StateBasedGame Sbg;
	private Image healthbar;

	@Override
	public int getID() {
		return 1;
	}
	
	@Override
	public void init(GameContainer arg0, StateBasedGame arg1)
			throws SlickException {
		healthbar = new Image("res/healthbar.png");
		
	}

	@Override
	public void render(GameContainer arg0, StateBasedGame arg1, Graphics g)
			throws SlickException {
		g.drawString("this is player1 hero", 100, 300);
		g.drawString("this is player2 hero", 700, 300);
		lsthatcreep.hero[lsthatcreep.player[0].count].pic.draw(100, 300);
		lsthatcreep.hero[lsthatcreep.player[1].count].pic.draw(700, 300);
		g.drawString("creep's No.1-4 health", lsthatcreep.GAME_WIDTH/2-80, 100);
		for(int j=0 ; j < 4 ; j++){
			for(int i=0 ; i < 150 - j*30 ; i++){
				healthbar.draw(lsthatcreep.GAME_WIDTH/2 - 100 + j*70, (float) (700-(lsthatcreep.GAME_HEIGHT/2 + i*1.5)));
			}
		}
		g.drawString("p1", 400, 360);
		g.drawString("p2", 565, 360);
		g.drawString("p1 button", 140, 100);
		g.drawString("press A and D to change target", 55, 120);
		g.drawString("press S to hit", 120, 140);
		g.drawString("p2 button", 730, 100);
		g.drawString("press K and ; to change target", 635, 120);
		g.drawString("press L to hit", 720, 140);
		g.drawString("player1 target", 385, 400);
		g.drawString("player2 target", 550, 400);
		g.drawString("   When You're ready   ", lsthatcreep.GAME_WIDTH/2-100, lsthatcreep.GAME_HEIGHT-60);
		g.drawString("press SPACE to continue", lsthatcreep.GAME_WIDTH/2-100, lsthatcreep.GAME_HEIGHT-40);
		
	}

	@Override
	public void update(GameContainer arg0, StateBasedGame sbg, int arg2)
			throws SlickException {
		Sbg = sbg;
		
	}
	@Override
	public void keyPressed(int key, char c) {
		if(key == Input.KEY_SPACE){
			Sbg.enterState(2, new FadeOutTransition(), new FadeInTransition());
		}
				
	}

	

}
