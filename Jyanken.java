public class Jyanken{
	public String winner;
	public int point1 = player1.getPoints();
	public int point2 = player2.getPoints();

	public Jyanken(String name1, String name2){
		Player player1 = new Player(name1);
		Player player2 = new Player(name2);
		playGame(player1, player2);
	 }

	public void playGame(Player player1,Player player2){
		private int hand1 = player1.getPlayerHand();
		private int hand2 = player2.getPlayerHand();
		
		//ゴキブリ=1, 人=2, 殺虫剤=3
		if(hand1 == hand2){
			winner = "あいこ" ;
		}else if(hand1 = 1){  			//プレーヤ1の手が ゴキブリ であるとき
			if(hand2 = 2) {
				winner = player1.getName();			//プレーヤ2が　人　だったらプレーヤ1勝つ
				point1++;
			}else if(hand2 = 3){
				winner = player2.getName(); 		//プレーヤ2が　殺虫剤　だったらプレーヤ2勝つ
				point2++;}

		}else if(hand1 = 2){			//プレーヤ1の手が 人 であるとき
			if(hand2 = 1){
				winner = player2.getName();			//プレーヤ2が　ゴキブリ　だったらプレーヤ1勝つ
				point2++;
			}else if(hand2 = 3){
				winner = player1.getName();			//プレーヤ2が　殺虫剤　だったらプレーヤ1勝つ
				point1++;}

		}else if(hand1 = 3){			//プレーヤ1の手が 殺虫剤 であるとき
			if(hand2 = 1){
				winner = player2.getName();			//プレーヤ2が　ゴキブリ　だったらプレーヤ1勝つ
				point2++;
			}else if(hand2 = 2){
				winner = player1.getName();			//プレーヤ2が　人　だったらプレーヤ1勝つ
				point1++;}
		}
	}
}

	