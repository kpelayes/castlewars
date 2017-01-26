import java.util.*;
class Attack implements Strategy{
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
	public Card discardCard(Player p)
	{/**
		//find lowest resource
		int lowest=p.getBricks();
		String lowestResource="bricks";
		if (lowest>p.getCrystals())
		{
			lowest=p.getCrystals();
			lowestResource="crystals";
		}
		if (lowest>p.getWeapons())
		{
			lowest=p.getWeapons();
			lowestResource="weapons";
		}
		*/
		
		Card highest=p.getCard(0);;
		/**boolean set=false;
		//finds card that requires the most of the scarces resource player has
		for(int i=0;i<8;i++)
		{
			if (p.getCard(i).getResource()==lowestResource)
				if (set==false){
					highest=p.getCard((i));
					set=true;
				}
				else{if (p.getCard(i).getAmount()>highest.getAmount())
					highest=p.getCard(i);}
		}*/
		//if player doesn't have any cards that require his scarcest resource he'll discard his highest card
		//if (set==false){
		for (int i=1;i<8;i++)
		{
			if (highest.getAmount()<p.getCard(i).getAmount())
				highest=p.getCard(i);
		}//}
		return highest;
	}
	public Move getMove(Player p)
	{
		String priority="attack";
		if (p.getCastle()+p.getFence()<40)
		{
			priority="build";
		}
		ArrayList<Card> legalMoves=new ArrayList<Card>();
		legalMoves=legalMoves(p);
		Move m=new Move("use",p.getCard(0));
		if (legalMoves.isEmpty()){
			m=new Move("discard",discardCard(p));
			return m;
		}
		//if only one option use it
		if (legalMoves.size()==1)
		{
			m=new Move("use",legalMoves.get(0));
			return m;
		}
		//first look to increase builders,soldiers, and magic because after each round you acquire as many bricks,weapons,and crystals respectively;
		for(int i=0;i<legalMoves.size();i++)
		{
			if (legalMoves.get(i).getType()=="resource")
				return m=new Move("use",legalMoves.get(i));
		}
		//choose based on priority 
		for(int i=0;i<legalMoves.size();i++)
		{
			if (legalMoves.get(i).getType()==priority)
				return m=new Move("use",legalMoves.get(i));
		}
		//do opposite
		if (priority=="build")
			priority="attack";
		if (priority=="attack")
			priority="build";
		//choose based on priority 
		for(int i=0;i<legalMoves.size();i++)
		{
			if (legalMoves.get(i).getType()==priority)
				return m=new Move("use",legalMoves.get(i));
		}
		//gain 
		for(int i=0;i<legalMoves.size();i++)
		{
			if (legalMoves.get(i).getType()=="gain")
				return m=new Move("use",legalMoves.get(i));
		}
		return m;
	}
}