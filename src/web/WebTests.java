package web;

public class WebTests {

	public static void main(String[] args) {
		String response = "";
		try {
			response = Web.post("http://localhost:11080/player.php", "GameID=testmap&MyID=bob&MyData=eA==.MA==~eQ==.OTY=&TheirID=joe");
			System.out.println(Web.LastResponseCode());
			System.out.println(response);
		} catch (Exception e) {
			System.out.println(Web.LastResponseCode());
			System.out.println(response);
			e.printStackTrace();
		}
		
	}

}
