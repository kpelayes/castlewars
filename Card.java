import java.util.*;
public abstract class Card implements Observer{
	String name; //card name	
	String resource; //what resource it needs to be able to use the card
	int amount;  //how much of that resource it needs
	String type;      //what type of card, build,attack,resource,or gain
	String power;  //what power or effect it has
	private boolean playable; //whether user has enough resources to use the card
	
	public String getName() {return name;}
	public String getResource() {return resource;}
	public int getAmount(){return amount;}
	public String getType(){return type;}
	public String getPower(){return power;}
	public void update(Observable o,Object arg){
		playable=false;
		Player playerUpdate=(Player) o;
		if (resource=="bricks"){
			if (playerUpdate.getBricks()>=amount)
				playable=true;
		}else if (resource=="weapons"){
			if (playerUpdate.getWeapons()>=amount)
				playable=true;
		}else if (resource=="crystals"){
			if (playerUpdate.getCrystals()>=amount)
				playable=true;
		}
	}
	public boolean isPlayable(){
		return playable;
	}
	
	public abstract void effect(Player p1,Player p2);
}