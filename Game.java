class Game {
	
	public Move turn(Player player,Player enemy){
		Move moveMade=new Move(player.makeMove());
		execute(moveMade,player,enemy);
		String winner=checkForWin(player,enemy);
		if (winner!=null)
		{
			System.out.println(winner+ "won!");
			System.exit(0);//quit the game
		}
		//after each round, stocks are increased for each player accordingly
		increaseStocks(player,enemy);
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
	public void execute(Move move,Player user,Player enemy)
	{
		if (move.getMethod()=="discard")
		{
			user.destroyCard(move.getCard());
		}
		else {
			String power=move.getCard().getPower();
			int amount;
			//Lose stocks
			String resource=move.getCard().getResource();
			if (resource=="crystals")
				user.decreaseCrystals(move.getCard().getAmount());
			else if (resource=="bricks")
				user.decreaseBricks(move.getCard().getAmount());
			else if (resource=="weapons")
				user.decreaseWeapons(move.getCard().getAmount());
			//destroy card
			user.destroyCard(move.getCard());
			//execute its power
			if (power.contains("builders +"))
				user.increaseBuilders();
			if (power.contains("soldiers +"))
				user.increaseSoldiers();
			if (power.contains("magic +"))
				user.increaseMagic();
			if(power.contains("attack")){
				amount=Integer.parseInt(power.replaceAll("[\\D]", ""));
				if (enemy.getFence()<amount)
				{
					int damageRemaining= amount-enemy.getFence();
					enemy.destroyFence(amount);
					enemy.destroyCastle(damageRemaining);
				}else enemy.destroyFence(amount);}
			if (power.contains("castle -")){
				amount=Integer.parseInt(power.replaceAll("[\\D]", ""));
				enemy.destroyCastle(amount);}
			if (power.contains ("castle +")){
				amount=Integer.parseInt(power.replaceAll("[\\D]", ""));
				user.buildCastle(amount);}
			if (power.contains("fence +")){
				amount=Integer.parseInt(power.replaceAll("[\\D]", ""));
				user.buildFence(amount);}
			if (power.contains("crystals -")){
				amount=Integer.parseInt(power.replaceAll("[\\D]", ""));
				enemy.decreaseCrystals(amount);}
			if (power.contains("weapons -")){
				amount=Integer.parseInt(power.replaceAll("[\\D]", ""));
				enemy.decreaseWeapons(amount);}
			if (power.contains("bricks -")){
				amount=Integer.parseInt(power.replaceAll("[\\D]", ""));
				enemy.decreaseBricks(amount);}
			if (power.contains("weapons +")){
				amount=Integer.parseInt(power.replaceAll("[\\D]", ""));
				user.increaseWeapons(amount);}
			if (power.contains("bricks +")){
				amount=Integer.parseInt(power.replaceAll("[\\D]", ""));
				user.increaseBricks(amount);}
			if (power.contains("crystals +")){
				amount=Integer.parseInt(power.replaceAll("[\\D]", ""));
				user.increaseCrystals(amount);}
		}
	}
	public void showHands(Player p1,Player p2)
	{
		System.out.print("Player 1\tCastle: "+p1.getCastle()+"\tFence: "+p1.getFence()+
			"\n_______________________________________________________\nHand------->");
		p1.showHand();
		System.out.print("Player 2\tCastle: "+p2.getCastle()+"\tFence: "+p2.getFence()+
		"\n_______________________________________________________\nHand------->");
		p2.showHand();
	}
	public String checkForWin(Player p1,Player p2)
	{
		//Users can win by destroying their enemy's castle or building their castle up to 100
		String winner=null;
		if(p1.getCastle()>99)
			winner="Player 1";
		if (p1.getCastle()<1)
			winner="Player 2 ";
		if (p2.getCastle()>99)
			winner="Player 2 ";
		if (p2.getCastle()<1)
			winner="Player 1 ";
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
}