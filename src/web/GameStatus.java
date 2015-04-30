/**
 * Holds status of the game- like players, last message from other player, game name, game status, and will have an array of entities
 * Future uses: Game instance (so multiple people can play the same game)
 * 				Paused
 * 				
 */
package web;
public class GameStatus {
	
	public GameStatus(String gameName_)
	{
		gameName = gameName_;
	}
	
	private String gameName;
	
	public String getGameName() {
		return gameName;
	}
	public void setGameName(String gameName) {
		this.gameName = gameName;
	}


}
