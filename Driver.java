
import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.Test;

public class Driver {
	Player p1=new Player();
	Player p2=new Player();
	
	@Test
	public void useCardtest()
	{	
		//players start off with 5 of each resource and 30 castle and 10 fence.
		Card card=new Wall(); //fence +3
		Move move=new UseCard(card);
		
		//once the move is executed, fence should be 13, and player should have lost the one brick it needed to use the card
		move.execute(p1, p2);
		assertEquals(p1.getFence(),13);
		assertEquals(p1.getBricks(),4);
	}
	@Test
	public void discardCardtest(){
		Card card=new Banshee();
		Move move=new DiscardCard(card);
		
		//once the move is executed, player should not have lost any resources since it didn't use its power
		move.execute(p2, p1);
		assertEquals(p2.getWeapons(),5);
		assertEquals(p2.getBricks(),5);
		assertEquals(p2.getCrystals(),5);
	}
	@AfterClass
	public static void runOnce(){
		System.out.println("\n\n\nBattle\n\n\n");
		Player p1=new Player();
		Player p2=new Player();
		p1.changeStrategy(new Attack());
		Duel game = new Game(p1,p2);
		game.play();
	}

}