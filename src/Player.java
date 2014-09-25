

public class Player {
	
		public int CurrentHp;
		public int CurrentDmg;
		public int CurrentDelay;
		public int Score;
		public int Coin;
		private int Delay;
		public int delay;
		private int Hp;
		public int count;
		public int Key;
	
	public Player(){
	
	}
	public void setPlayer(Hero hero){
		Hp = hero.hp;
		CurrentHp = Hp;
		CurrentDmg = hero.damage;
		Delay = hero.delay;
		CurrentDelay = Delay;
		delay = Delay;
		Key = 0;
	}
	public void GOLD(int coin){
		Coin += coin;
	}
	public int HP(){
		return Hp;
	}
	public void Hit(int dmg){
		Hp -= dmg;
	}
	
	public void Heal(int heal){
		Hp += heal;
	}
	
	public void UpDMG(int up){
		CurrentDmg += up;
	}
	
	public void UpAS(int up){
		Delay -= up;
	}
	public void setDelay() {
		CurrentDelay = Delay;
	}
}
