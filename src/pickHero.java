import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;


public class pickHero extends BasicGameState{

	private StateBasedGame Sbg;
	@Override
	public void init(GameContainer arg0, StateBasedGame sbg) throws SlickException {
		lsthatcreep.hero = new Hero[6];
		for (int i = 0; i<6; i++) {
			lsthatcreep.hero[i] = new Hero();
			lsthatcreep.hero[i].set(i);
		}
		lsthatcreep.player = new Player[2];
		lsthatcreep.player[0] = new Player();
		lsthatcreep.player[1] = new Player();
		
	}

	@Override
	public void render(GameContainer arg0, StateBasedGame sbg, Graphics g) throws SlickException {
		lsthatcreep.hero[lsthatcreep.player[0].count].Render(0,g);
		lsthatcreep.hero[lsthatcreep.player[1].count].Render(1,g);
		
	}

	@Override
	public void update(GameContainer container, StateBasedGame sbg, int arg2) throws SlickException {
		Sbg = sbg;
		setPlayerHero();
		
	}

	private void setPlayerHero() {
		lsthatcreep.player[0].setPlayer(lsthatcreep.hero[lsthatcreep.player[0].count]);
		lsthatcreep.player[1].setPlayer(lsthatcreep.hero[lsthatcreep.player[1].count]);
		
	}

	@Override
	public void keyPressed(int key, char c) {
		if(key == Input.KEY_A){
			lsthatcreep.player[0].count++;
			if(lsthatcreep.player[0].count > 5){
				lsthatcreep.player[0].count = 0;
			}
		}
		if(key == Input.KEY_K){
			lsthatcreep.player[1].count++;
			if(lsthatcreep.player[1].count > 5){
				lsthatcreep.player[1].count = 0;
			}
		}
		if(key == Input.KEY_SPACE){
			Sbg.enterState(1, new FadeOutTransition(), new FadeInTransition());
		}
				
	}
	@Override
	public int getID() {
		return 0;
	}

}
