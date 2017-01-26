import java.util.*;
import java.security.acl.*;
import javax.security.auth.callback.*;

class Driver {
	public static void main(String[] args) {
		//players get initialized with basic strategy
		//basic strategy priority order- Acquire,Build, Attack, Gain Stocks
		Game game=new Game();
		Player p1=new Player();
		Player p2=new Player();
		
		ArrayList<String> hand = new ArrayList<String>(
		Arrays.asList("Banshee","Sorcerer","Platoon","Defence","Tower","Dragon","Pixies","Babylon"));
		Hand h=new Hand(hand);
		p1.changeHand(h);
		hand=new ArrayList<String>(
		Arrays.asList("Platoon","Tower","Attack","Knight","Archer","Dragon","Pixies","Babylon"));
		h=new Hand(hand);
		p2.changeHand(h);
		game.showHands(p1,p2);
		//assign attack strategy to player 1
		//Attack Priority order- Acquire, Attack, Build, Gain stocks.
		Strategy s=new Attack();
		p1.changeStrategy(s);
		
		//round 1
		Move move=game.turn(p1,p2);
		//will only be able to use Platoon, Defence, and Tower, should choose Platoon to Attack
		System.out.println("\n\nPlayer 1 should use Platoon: "+move.checkMove("Platoon"));
		System.out.println(move.getMethod()+" "+move.getCard().getName()+"- "+move.getCard().getPower()+"\tCost: "+move.getCard().getAmount()+" "+move.getCard().getResource());
		
		move=game.turn(p2,p1);
		//will only be able to use Platoon, Wall, Tower, Knight
		//Should use Wall
		System.out.println("\n\nPlayer 2 should use Tower: "+move.checkMove("Tower"));
		System.out.println(move.getMethod()+" "+move.getCard().getName()+"- "+move.getCard().getPower()+"\tCost: "+move.getCard().getAmount()+" "+move.getCard().getResource()+"\n\n");
		
		//assign new hands for both players
		hand = new ArrayList<String>(
		Arrays.asList("Banshee","Sorcerer","Babylon","Defence","Tower","Dragon","Pixies","Babylon"));
		h=new Hand(hand);
		p1.changeHand(h);
		hand=new ArrayList<String>(
		Arrays.asList("Platoon","Banshee","Attack","Knight","Archer","Dragon","Pixies","Babylon"));
		h=new Hand(hand);
		p2.changeHand(h);
		game.showHands(p1,p2);
		
		
		//Round 2
		move=game.turn(p1,p2);
		//Will only be able to use Sorcerer, Defence, and Tower. 
		//Should choose Sorcerer to acquire magic
		System.out.println("\n\nPlayer 1 should use Sorcerer: "+move.checkMove("Sorcerer"));
		System.out.println(move.getMethod()+" "+move.getCard().getName()+"- "+move.getCard().getPower()+"\tCost: "+move.getCard().getAmount()+" "+move.getCard().getResource());
		move=game.turn(p2,p1);
		//Will only be able to use Platoon, Attack, Knight,and Archer 
		//Should choose Platoon since there are no build cards available to play
		System.out.println("\n\nPlayer 2 should use Platoon: "+move.checkMove("Platoon"));
		System.out.println(move.getMethod()+" "+move.getCard().getName()+"- "+move.getCard().getPower()+"\tCost: "+move.getCard().getAmount()+" "+move.getCard().getResource()+"\n\n");
		
		//assign new hands for both players
		hand = new ArrayList<String>(
		Arrays.asList("Banshee","Knight","Babylon","Defence","Tower","Dragon","Pixies","Babylon"));
		h=new Hand(hand);
		p1.changeHand(h);
		hand=new ArrayList<String>(
		Arrays.asList("School","Banshee","Attack","Knight","Archer","Dragon","Pixies","Babylon"));
		h=new Hand(hand);
		p2.changeHand(h);
		game.showHands(p1,p2);
		
		//Round 3
		move=game.turn(p1,p2);
		//Will only be able to use Knight, Defence, and Tower. 
		//Should choose Defence to build over attack since his castle and fence add up to less than 40
		System.out.println("\n\nPlayer 1 should use Defence: "+move.checkMove("Defence"));
		System.out.println(move.getMethod()+" "+move.getCard().getName()+"- "+move.getCard().getPower()+"\tCost: "+move.getCard().getAmount()+" "+move.getCard().getResource());
		move=game.turn(p2,p1);
		//Will only be able to use School, Attack, Knight,and Archer 
		//Should choose School to aqcuire more builders
		System.out.println("\n\nPlayer 2 should use School: "+move.checkMove("School"));
		System.out.println(move.getMethod()+" "+move.getCard().getName()+"- "+move.getCard().getPower()+"\tCost: "+move.getCard().getAmount()+" "+move.getCard().getResource()+"\n\n");
		
		//assign new hands for both players
		//same hand for both- all high cards should not be able to use any
		hand = new ArrayList<String>(
		Arrays.asList("Banshee","Babylon","Babylon","Pixies","Pixies","Dragon","Banshee","Dragon"));
		h=new Hand(hand);
		p1.changeHand(h);
		p2.changeHand(h);
		game.showHands(p1,p2);
		
		//Round 4
		move=game.turn(p1,p2);
		//Will not be able to use any card, therefore will choose to discard the card with highest cost
		System.out.println("\n\nPlayer 1 should discard Babylon: "+move.checkMove("Babylon"));
		System.out.println(move.getMethod()+" "+move.getCard().getName()+"- "+move.getCard().getPower()+"\tCost: "+move.getCard().getAmount()+" "+move.getCard().getResource());
	}
	
}