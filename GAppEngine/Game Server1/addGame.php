<?PHP
if (isset($_GET["NewGameID"]))
{
$newGame = cleanString($_GET["NewGameID"]);

$gameDir = "gs://java-game-apcs.appspot.com/player_status/".$newGame."/";

//does this game not exist yet?
if (!is_dir($gameDir))
{
	if (mkdir($gameDir))
	{
		echo "$newGame has been created!";//only used for manual testing
	}
	else
	{
		echo "Failed to create $newGame";
	}
}
else
{
	echo "$newGame already exists!";
}
}
else
{
	echo "NewGameID not set";
}


function cleanString($str)
{
return preg_replace('/[^a-z0-9-]/', '', $str);
}


?>