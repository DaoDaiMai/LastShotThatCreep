import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class lsthatcreep extends StateBasedGame{

	public static final int GAME_WIDTH = 1000;
	public static final int GAME_HEIGHT = 700;
	public static final int CREEP_HP = 300;
	public static Hero[] hero;
	public static Player[] player;
	public static Creep[] creep;
	
	public static void main(String[] args) {
		try {
		      lsthatcreep game = new lsthatcreep("Lastshot that Creep");
		      AppGameContainer appgc = new AppGameContainer(game);
		      appgc.setDisplayMode(GAME_WIDTH, GAME_HEIGHT, false);
		      appgc.start();
		} catch (SlickException e) {
		      e.printStackTrace();
		    }
	}
	
	public lsthatcreep(String name) {
		super(name);
	}

	@Override
	public void initStatesList(GameContainer arg0) throws SlickException {
		this.addState(new pickHero());
		this.addState(new HowTo());
		this.addState(new play());
		this.addState(new EndGame());
		
	}

}
