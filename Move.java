class Move {
	private String method;
	private Card card;
	
	//constructor creates a move with the same method and card 
	public Move(String m, Card c){
		method=m;   //use its power or discard
		card=c;
	}
	//constructor that creates a duplicate of the move that is passed
	public Move(Move m)
	{
		method=m.getMethod();
		card=m.getCard();
	}
	public String getMethod(){
		return method;
	}
	public Card getCard(){
		return card;
	}
	//for testing purposes
	public boolean checkMove(String name)
	{
		if (name==card.getName())
			return true;
		return false;
	}


}