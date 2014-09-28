

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Hero {

	public int hp;
	public int delay;
	public int damage;
	private String name;
	private Image pic;

	public void set(int i) throws SlickException{
		if(i==0) {
			pic = new Image ("res/Pikachu.png");
			name = "Pikachu";
			hp = 100;
			delay = 50;
			damage = 40;
		} else if(i==1) {
			pic = new Image ("res/Doraemon.png");
			name = "Naruto";
			hp = 120;
			delay = 60;
			damage = 50;
		} else if(i==2) {
			pic = new Image ("res/Goku.png");
			name = "Goku";
			hp = 1200;
			delay = 80;
			damage = 100;
		} else if(i==3) {
			pic = new Image ("res/Pusheen.png");
			name = "Pusheen";
			hp = 10;
			delay = 10;
			damage = 10;
		} else if(i==4) {
			pic = new Image ("res/ULtraman.png");
			name = "Ultraman";
			hp = 140;
			delay = 70;
			damage = 50;
		} else if(i==5) {
			pic = new Image ("res/Simsimi.png");
			name = "Simsimi";
			hp = 200;
			delay = 60;
			damage = 40;
		}
	}

	public void Render(int i, Graphics g) throws SlickException{
		if(i==0) {
			pic.draw(150,200);
			g.drawString("Name "+name, 100, 100);
			g.drawString("Hp "+hp, 100, 120);
			g.drawString("Damage "+damage, 100, 140);
			g.drawString("Delay "+delay, 100, 160);
		} else {
			pic.draw(650,200);
			g.drawString("Name "+name, 500, 100);
			g.drawString("Hp "+hp, 500, 120);
			g.drawString("Damage "+damage, 500, 140);
			g.drawString("Delay "+delay, 500, 160);
		}
		
	}

	public void Draw(int i, int j) {
		pic.draw(i,j);
		
	}
}
