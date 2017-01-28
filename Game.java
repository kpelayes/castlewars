class Game extends Duel{

	public Game(Player player, String key) {
		super(player, key);
		// TODO Auto-generated constructor stub
	}
	public Game() { }

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
	
	public Move turn(Player player,Player enemy){
		Move moveMade=player.makeMove();
		moveMade.execute(player,enemy);
		player.destroyCard(moveMade.getCard());
		String winner=checkForWin(player,enemy);
		if (winner!=null)
		{
			System.out.println(winner+ " won!");
			System.exit(0);//quit the game
		}
		//after each round, stocks are increased for each player accordingly
		increaseStocks(player,enemy);
		
	System.out.println(moveMade.getCard().getName());
		return moveMade;
	}
	public void getStats(Player p1, Player p2){
	    System.out.println("Player 1 \t Player 2");
		System.out.println("Builders: "+p1.getBuilders()+"\tBuilders: "+p2.getBuilders());
		System.out.println("Bricks: "+p1.getBricks()+"\tBricks: "+p2.getBricks());
		System.out.println("Soldiers: "+p1.getSoldiers()+"\tSoldiers: "+p2.getSoldiers());
		System.out.println("Weapons: "+p1.getWeapons()+"\tWeapons: "+p2.getWeapons());
		System.out.println("Magic: "+p1.getMagic()+"\tMagic: "+p2.getMagic());
		System.out.println("Crystals: "+p1.getCrystals()+"\tCrystals: "+p2.getCrystals());
		System.out.println("Castle: "+p1.getCastle()+"\tCastle: "+p2.getCastle());
		System.out.println("Fence: "+p1.getFence()+"\tFence: "+p2.getFence());
		
	}
	public void showHands(Player p1,Player p2)
	{
		if (p1 != null)
		{
			System.out.print("Player 1\tCastle: "+p1.getCastle()+"\tFence: "+p1.getFence()+
					"\n_______________________________________________________\nHand------->");
			p1.showHand();
		}
		else if ( p2 != null)
		{
			System.out.print("Player 2\tCastle: "+p2.getCastle()+"\tFence: "+p2.getFence()+
					"\n_______________________________________________________\nHand------->");
			p2.showHand();
		}		
	}
	public String checkForWin(Player p1,Player p2)
	{
		//Users can win by destroying their enemy's castle or building their castle up to 100
		String winner=null;
		if(p1.getCastle()>99)
		{
			winner="Player 1 ";
		}
		else if (p1.getCastle()<1)
		{
			winner="Player 2 ";
		}
		else if (p2.getCastle()>99)
		{
			winner="Player 2 ";
		}
		else if (p2.getCastle()<1)
		{
			winner="Player 1 ";
		}
		return winner;
		
	}
	//bricks are increased depending on how many builders you have
	//weapons are increased depending on how many soldiers you have
	//crystals are increased depending on how much magic you have
	public void increaseStocks(Player p1, Player p2)
	{
		int amount;
		amount=p1.getBuilders();
		p1.increaseBricks(amount);
		amount=p2.getBuilders();
		p2.increaseBricks(amount);
		amount=p1.getSoldiers();
		p1.increaseWeapons(amount);
		amount=p2.getSoldiers();
		p2.increaseWeapons(amount);
		amount=p1.getMagic();
		p1.increaseCrystals(amount);
		amount=p2.getMagic();
		p2.increaseCrystals(amount);
	}
	
	public static void main(String[] args) {
		
		Duel game = new Game(new Player(), "player1");
		game = new Game(new Player(), "player2");
		game.play();
	}
}