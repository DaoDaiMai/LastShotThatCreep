import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Hero {

	public int hp;
	public int delay;
	public int damage;
	private String name;
	public Image pic;

	public void set(int i) throws SlickException{
		hp = 10;
		if(i==0) {
			pic = new Image ("res/Pikachu.png");
			name = "Pikachu";
			delay = 50;
			damage = 40;
		} else if(i==1) {
			pic = new Image ("res/Doraemon.png");
			name = "Doraemon";
			delay = 60;
			damage = 50;
		} else if(i==2) {
			pic = new Image ("res/Goku.png");
			name = "Goku";
			delay = 80;
			damage = 100;
		} else if(i==3) {
			pic = new Image ("res/Pusheen.png");
			name = "Pusheen";
			delay = 10;
			damage = 10;
		} else if(i==4) {
			pic = new Image ("res/ULtraman.png");
			name = "Ultraman";
			delay = 70;
			damage = 50;
		} else if(i==5) {
			pic = new Image ("res/Simsimi.png");
			name = "Simsimi";
			delay = 60;
			damage = 40;
		}
	}

	public void Render(int i, Graphics g) throws SlickException{
		if(i==0) {
			pic.draw(100,100);
			g.drawString("press A to change ", 170, 120);
			g.drawString("Name "+name, 185, 420);
			g.drawString("Hp "+hp, 185, 440);
			g.drawString("Damage "+damage, 185, 460);
			g.drawString("Delay "+delay, 185, 480);
		} else {
			pic.draw(600,100);
			g.drawString("press K to change ", 685, 120);
			g.drawString("Name "+name, 700, 420);
			g.drawString("Hp "+hp, 700, 440);
			g.drawString("Damage "+damage, 700, 460);
			g.drawString("Delay "+delay, 700, 480);
		}
		
	}

	public void Draw(int i, int j) {
		pic.draw(i,j);
		
	}
}
