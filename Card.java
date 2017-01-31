import java.util.*;
import java.util.Random;
class Card implements Observer{
	private String name; //card name	
	private String resource; //what resource it needs to be able to use the card
	private int amount;  //how much of that resource it needs
	String type;      //what type of card, build,attack,resource,or gain
	private String power;  //what power or effect it has
	private boolean playable; //wether user has enough resources to use the card
	private List<String> cardNames = Arrays.asList("Knight", "Sorcerer", "School", "Conjure weapons","Wall","Crush crystals","Swat","Defence","Pixies","Platoon","Base","Recruit","Saboteur","Crush weapons","Rider","Tower","Conjure crystals","Archer","Attack","Banshee","Babylon","Fort","Thief","Crush bricks","Fence","Dragon");
	
	//no-param constructor creates a random card
	public Card(){
		//randomly create a card
		Random randomizer = new Random();
		name = cardNames.get(randomizer.nextInt(cardNames.size()));
		setAttributes(name);
	}
	//constructor creates card with the same name 
	public Card(String cardName){
		name=cardName;
		setAttributes(name);
	}
	//sets the attributes of the card according to its name	
	public void setAttributes(String name){
		if (name=="Knight")
		{
			resource= "weapons";
			amount=2;
			power="attack 3";
			type="attack";
		}
		else if (name=="Sorcerer"){
			resource="crystals";
			amount=8;
			power="magic +1";
			type="resource";
		}
		else if(name=="Conjure weapons"){
			resource="crystals";
			amount=4;
			power="weapons +8";
			type="gain";
		}else if(name=="Wall"){
			resource="bricks";
			amount=1;
			power="fence +3";
			type="build";
		}else if(name=="Crush crystals"){
			resource="crystals";
			amount=4;
			power="enemy crystals -8";
			type="attack";
		}else if(name=="Swat"){
			resource="weapons";
			amount=18;
			power="enemy castle -10";
			type="attack";
		}else if(name=="School"){
			resource="bricks";
			amount=8;
			power="builders +1";
			type="resource";
		}else if(name=="Pixies"){
			resource="crystals";
			amount=22;
			power="castle +22";
			type="build";
		}else if(name=="Defence"){
			resource="bricks";
			amount=3;
			power="fence +6";
			type="build";
		}else if(name=="Platoon"){
			resource="weapons";
			amount=4;
			power="attack 6";
			type="attack";
		}else if (name=="Base"){
			resource="bricks";
			amount=1;
			power="castle +2";
			type="build";
		}else if(name=="Recruit"){
			resource="weapons";
			amount=8;
			power="soldiers +1";
			type="resource";
		}else if(name=="Saboteur"){
			resource="weapons";
			amount=12;
			power="enemy stocks -4";
			type="attack";
		}else if(name=="Crush weapons"){
			resource="crystals";
			amount=4;
			power="enemy weapons -8";
			type="attack";
		}else if(name=="Rider"){
			resource="weapons";
			amount=2;
			power="attack 4";
			type="attack";
		}else if(name=="Tower"){
			resource="bricks";
			amount=5;
			power="castle +5";
			type="build";
		}else if(name=="Conjure crystals"){
			resource="crystals";
			amount=4;
			power="crystals +8";
			type="gain";
		}else if(name=="Archer"){
			resource="weapons";
			amount=1;
			power="attack 2";
			type="attack";
		}else if(name=="Attack"){
			resource="weapons";
			amount=10;
			power="attack 12";
			type="attack";
		}else if(name=="Banshee"){
			resource="weapons";
			amount=28;
			power="attack 32";
			type="attack";
		}else if(name=="Fort"){
			resource="bricks";
			amount=18;
			power="castle +20";
			type="build";
		}else if(name=="Babylon"){
			resource="bricks";
			amount=39;
			power="castle +32";
			type="build";
		}else if(name=="Thief"){
			resource="weapons";
			amount=15;
			power="transfer enemy stocks 5";
			type="attack";
		}else if(name=="Crush bricks"){
			resource="crystals";
			amount=4;
			power="enemy bricks -8";
			type="attack";
		}else if(name=="Fence"){
			resource="bricks";
			amount=12;
			power="fence +22";
			type="build";
		}else if(name=="Dragon"){
			resource="crystals";
			amount=21;
			power="attack 25";
			type="attack";
		}
	}
	public void destroyCard(Player user){
		user.destroyCard(this);
	}
	public void effect(Player user, Player enemy)
	{
		//Lose stocks
		if (resource=="crystals")
			user.decreaseCrystals(amount);
		else if (resource=="bricks")
			user.decreaseBricks(amount);
		else if (resource=="weapons")
			user.decreaseWeapons(amount);
			
		//execute its power
		if (power.contains("builders +"))
			user.increaseBuilders();
		if (power.contains("soldiers +"))
			user.increaseSoldiers();
		if (power.contains("magic +"))
			user.increaseMagic();
		if(power.contains("attack")){
			amount=Integer.parseInt(power.replaceAll("[\\D]", ""));
			if (enemy.getFence()<amount)
			{
				int damageRemaining= amount-enemy.getFence();
				enemy.destroyFence(amount);
				enemy.destroyCastle(damageRemaining);
			}else enemy.destroyFence(amount);}
		if (power.contains("castle -")){
			amount=Integer.parseInt(power.replaceAll("[\\D]", ""));
			enemy.destroyCastle(amount);}
		if (power.contains ("castle +")){
			amount=Integer.parseInt(power.replaceAll("[\\D]", ""));
			user.buildCastle(amount);}
		if (power.contains("fence +")){
			amount=Integer.parseInt(power.replaceAll("[\\D]", ""));
			user.buildFence(amount);}
		if (power.contains("crystals -")){
			amount=Integer.parseInt(power.replaceAll("[\\D]", ""));
			enemy.decreaseCrystals(amount);}
		if (power.contains("weapons -")){
			amount=Integer.parseInt(power.replaceAll("[\\D]", ""));
			enemy.decreaseWeapons(amount);}
		if (power.contains("bricks -")){
			amount=Integer.parseInt(power.replaceAll("[\\D]", ""));
			enemy.decreaseBricks(amount);}
		if (power.contains("weapons +")){
			amount=Integer.parseInt(power.replaceAll("[\\D]", ""));
			user.increaseWeapons(amount);}
		if (power.contains("bricks +")){
			amount=Integer.parseInt(power.replaceAll("[\\D]", ""));
			user.increaseBricks(amount);}
		if (power.contains("crystals +")){
			amount=Integer.parseInt(power.replaceAll("[\\D]", ""));
			user.increaseCrystals(amount);}
	}
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
		}else if (resource=="magic"){
			if (playerUpdate.getMagic()>=amount)
				playable=true;
		}
	}
	public boolean isPlayable(){
		return playable;
	}
}