import java.util.*;
import java.util.Random;
class Hand {
	private ArrayList<Card> hand=new ArrayList<Card>(); //array of cards
	private List<String> cardNames=Arrays.asList("Fence","Banshee","Wall");
	
	//no-param constructor creates hand of random cards
	public Hand(){
		for(int i=0;i<8;i++)
		{
			Random randomizer=new Random();
			int rand=randomizer.nextInt(cardNames.size());
			Card randomCard;
			switch(rand){
				case 0:
					randomCard=new Fence();
					break;
				case 1:
					randomCard=new Banshee();
					break;
				case 2:
					randomCard=new Wall();
					break;
				default:
					randomCard= new Banshee();
			}
			hand.add(randomCard);
		}
	}
	
	public int size(){
		return hand.size();
	}
	
	//prints out each card in player's hand
	public void displayHand(){
		for (int i=0;i<hand.size();i++)
		{
			System.out.print(getCard(i).getName()+"\t");
		}
		System.out.println();
	}
	//returns the card at the position
	public Card getCard(int index)
	{
		return hand.get(index);
	}
	//removes the card and adds a random card
	public Card removeCard(Card card)
	{
		Random randomizer=new Random();
		int rand=randomizer.nextInt(cardNames.size());
		Card newCard;
		switch(rand){
		case 0:
			newCard=new Fence();
			break;
		case 1:
			newCard=new Banshee();
			break;
		case 2:
			newCard=new Wall();
			break;
		default:
			newCard=new Banshee();
	}
		hand.remove(card);
		hand.add(newCard);
		return newCard;
	}
}