<?php
/* player.php
 * Attributes:
 *   POST: GameID id of the current game/world being played. *1
 *   POST: MyID my username *1
 *	 POST: TheirID the username of the player to return info from *1
 *   POST: MyData binary blob of data representing my current status. Will be saved in a binary-encoded file
 * Returns:
 *	 TheirData as binary blob with status code 200 unless there was some error.
 * 
 * *1: Field must be alphanumeric with dashes (a-zA-Z0-9-)
 * 
 * 
 * Possible errors:
 *   If GameID doesn't exist, then they are trying to play a non-existant game 
 *   and the server will return response code 412: Precondition Failed.
 * 
 * 	 If TheirID doesn't exist, this returns response code 418: I'm a teapot indicating
 * 	 that the other player either hasn't signed in or hasn't started that game yet.
 * 
 * Please read: http://en.wikipedia.org/wiki/Hyper_Text_Coffee_Pot_Control_Protocol
 * 
 * Files are stored in the player_status/[GameID] directory and named "u[ID].player"
 * 
 */

$GameID  = cleanString( $_GET["GameID"]  );
$MyID    = cleanString( $_GET["MyID"]    );
$MyData  = $_GET["MyData"];
$TheirID = cleanString( $_GET["TheirID"] );

$gameDir = "/player_status/"+$GameID+"/";

$MyFileDir = $gameDir+"u"+$MyID+".player";
$TheirFileDir = $gameDir+"u"+$TheirID+".player";


//make sure that the game exists- if it doesn't, then the map doesn't exist, so 
//set the status code to 412 and exit.
if (is_dir($gameDir))
{
	http_response_code(412);
	exit;
}

if (!file_exists($gameDir+$TheirID))
{
	header("HTTP/1.0 418 I'm A Teapot");//can't use http_response_code since that only allows known responces
	exit;
}

//write my data
$me = fopen($MyFileDir,"w");
fwrite($me, $MyData);
fclose($me);

//read their data
$them = fopen($TheirFileDir,"r");
$dataForThem = fread($them,filesize($TheirFileDir));
fclose($them);

echo $dataForThem;//return the data representing the other player.

function cleanString($str)
{
return preg_replace('/[^a-z0-9-]/', '', $str);
}
?>
