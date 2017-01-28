
public abstract class Duel{
	
	static Player player1;
	static Player player2;
	static Hand hand;
	static Strategy s;
	static Move move;
	
	public Duel()  { }
	public Duel(Player player, String key)
	{
		hand = new Hand();
		if (key.contains("player1"))
		{
			player1 = new Player();
			player1.changeHand(hand);
			s = new Attack();
			player1.changeStrategy(s);			
		}
		else if (key.contains("player2"))
		{
			player2 = new Player();
			player2.changeHand(hand);
			s = new Attack();
			player2.changeStrategy(s);
		}
	}
	public abstract void play();
}
