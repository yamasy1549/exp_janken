public class Player{
	private int playerHand = 0;　	//プレーヤの出す手
	private String name;　		//プレーヤの名前	
	private int points = 0;			//プレーヤの点数

	public Player(String name){
		this.name = name;		
	}

	public void setPlayerHand(int hand){
		this.playerHand = hand;		
	}

	public void setPoints(){
		this.points = this.points++;
	}

	public String getName(){
		return name;
    }

    public int getPlayerHand(){
    	return playerHand; 	
    }

    public int getPoints(){
    	return points;
    }
}
   


