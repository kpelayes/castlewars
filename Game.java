class Game extends Duel{
	public Game(Player player1,Player player2){
		super(player1,player2);
	}
	//continuous loop to execute each round
	public void play()
	{
		Player p1=getPlayer(1);
		Player p2=getPlayer(2);
		while(true){
			round(p1,p2);
		}
	}
	
	//each player gets a move
	//game checks for a winner after each move is executed
	public Move round(Player p1,Player p2){
		System.out.print("Player 1");
		showHand(p1);
		Move moveMade=p1.makeMove();
		moveMade.execute(p1,p2);
		System.out.print(moveMade.getCard().getName());
		System.out.println(" - "+moveMade.getCard().getPower());
		System.out.println("\n\n\n");
		//p1.destroyCard(moveMade.getCard());
		checkForWin(p1,p2);
		
		System.out.print("Player 2");
		showHand(p2);
		moveMade=p2.makeMove();
		moveMade.execute(p2,p1);
		System.out.print(moveMade.getCard().getName());
		System.out.println(" - "+moveMade.getCard().getPower());
		System.out.println("\n\n\n");
		//p2.destroyCard(moveMade.getCard());
		
		checkForWin(p1,p2);
		
		//after each round, stocks are increased for each player accordingly
		increaseStocks(p1,p2);
		return moveMade;
	}
	public void checkForWin(Player p1,Player p2)
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
		//if there's a winner, display message and terminate the program
		if (winner!=null)
		{
			System.out.println(winner+ " won!");
			System.exit(0);//quit the game
		}
		
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
}