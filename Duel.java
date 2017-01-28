
public abstract class Duel{ 
	private Player p1;
	private Player p2;
	
	public Duel(Player player1,Player player2){
		p1=player1;
		p2=player2;
	}
	public Player getPlayer(int p){
		if (p==1)
			return p1;
		else return p2;
	}
	public void getStats(Player player){
		System.out.print("\tCastle: "+player.getCastle()+"\tFence: "+player.getFence()+"\t b:"+player.getBricks()+" w:"+player.getWeapons()+" c:"+player.getCrystals()+
				"\n__________________________________________________________________\nHand------->");
		
	}
	public void showHand(Player player)
	{
			getStats(player);
			player.showHand();
	}
	public abstract void play();
}
