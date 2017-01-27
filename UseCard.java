public class UseCard extends Move{
	public UseCard(Card _card)
	{
		card=_card;
	}
	public void execute(Player user,Player enemy){
		card.effect(user,enemy);
	}
}