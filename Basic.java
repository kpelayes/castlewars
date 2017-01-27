import java.util.ArrayList;
class Basic implements Strategy {
	public ArrayList<Card> legalMoves(Player player)
	{
		//check which cards player has enough stocks to be able to use and return array of these cards
		ArrayList<Card> canUse=new ArrayList<Card>();
		for(int i=0;i<8;i++){
			if(player.getCard(i).getResource()=="crystals"){
				if (player.getCrystals()>=player.getCard(i).getAmount())
					canUse.add(player.getCard(i));
			}else if (player.getCard(i).getResource()=="weapons"){
				if (player.getWeapons()>=player.getCard(i).getAmount())
					canUse.add(player.getCard(i));
			}else if (player.getCard(i).getResource()=="bricks"){
				if (player.getBricks()>=player.getCard(i).getAmount())
					canUse.add(player.getCard(i));
			}
		}
		return canUse;
	}
	public int discardCard(Player p)
	{
		//discard card that requires the most stocks
		int highest= 0;
		for (int i=1;i<8;i++)
		{
			if (p.getCard(highest).getAmount()<p.getCard(i).getAmount())
				highest=i;
		}
		return highest;
	}
	public Move getMove(Player p)
	{
		ArrayList<Card> legalMoves=new ArrayList<Card>();
		legalMoves=legalMoves(p);
		int chosen; //index of chosen card
		Move m=new UseCard(p.getCard(0));
		//if theres no other cards to choose from, return this move
		if (legalMoves.isEmpty())
		{
			return m=new DiscardCard(p.getCard(discardCard(p)));
		}
		//first look to increase builders,soldiers, and magic because after each round you acquire as many bricks,weapons,and crystals respectively;
		for (int i=0;i<legalMoves.size();i++)
		{
			if (legalMoves.get(i).getType()=="resource")
				return m=new UseCard(legalMoves.get(i));
		}
		//build your castle to stay alive
		for (int i=0;i<legalMoves.size();i++)
		{
			if (legalMoves.get(i).getType()=="build")
				return m=new UseCard(legalMoves.get(i));
		}
		//attack to prevent other player to win
		for (int i=0;i<legalMoves.size();i++)
		{
			if (legalMoves.get(i).getType()=="attack")
				return m=new UseCard(legalMoves.get(i));
		}
		//gain stocks to be able to use cards
		for (int i=0;i<legalMoves.size();i++)
		{
			if (legalMoves.get(i).getType()=="gain")
				return m=new UseCard(legalMoves.get(i));
		}
		return m;
	}
}