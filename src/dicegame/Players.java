package dicegame;

public class Players {
	public String name; 
	private int score, top, size; 
	Players[] currentPlayer = new Players[4]; 
	
	public Players(){
		this.name = "na";
		this.score = 0;
		this.top = 0;
		this.size = 4; 
	}
	
	public void createPlayer(String theName){ 
		if(this.top <= this.size){
			Players p = new Players();
			this.name = theName;
			currentPlayer[this.top] = p;
			this.top++;
		}
		else
			return; 
	}
	
	public void setName(String theName){
		this.name = theName; 
	}
	
	public String getNames(){
		return this.name;
	}
	
	public int getScore(){
		return this.score;
	}
	
	public void addScore(int add){
		this.score = this.score + add; 
	
	}
	
}
