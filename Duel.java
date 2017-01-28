
public class Duel extends Game{
	
	static Player player1;
	static Player player2;
	static Hand hand;
	static Strategy s;
	static Move move;
	
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
	
	public void play()
	{
		int playerTurn = 1;		
		while (true)
		{			
			if (playerTurn % 2 == 1)
			{
				playerTurn = 1;
			}
			else if (playerTurn % 2 == 2)
			{
				playerTurn = 2;
			}
					
			if (playerTurn == 1)
			{
				this.showHands(player1, null);
				this.printResult(player1, player2);
			}
			else if (playerTurn == 2)
			{
				this.showHands(null, player2);
				this.printResult(player2, player1);
			}		
			playerTurn++;
		}
	}
	
	public void printResult(Player p1, Player p2)
	{
		move = this.turn(p1, p2);
		for (int i = 0; i < 5; i++)
		{
			System.out.println("\n");
		}
	}
	
	public static void main(String[] args) {
		
		Duel game = new Duel(new Player(), "player1");
		game = new Duel(new Player(), "player2");
		game.play();
	}
}
