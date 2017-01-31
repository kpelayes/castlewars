import java.util.ArrayList;
class Hand {
	private ArrayList<Card> hand=new ArrayList<Card>(); //array of cards
	
	//no-param constructor creates hand of random cards
	public Hand(){
		for(int i=0;i<8;i++)
		{
			//create a hand of cards--random
			Card card=new Card();
			hand.add(card);
		}
	}
	
	//constructor with array param creates hand with same cards in the array
	public Hand(ArrayList<String> h){
		for(int i=0;i<8;i++)
		{
			Card c=new Card(h.get(i));
			hand.add(c);
		}
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
		Card newCard=new Card();
		hand.remove(card);
		hand.add(newCard);
		return newCard;
	}
}