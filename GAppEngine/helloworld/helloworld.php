<html>
  <head>
    <link type="text/css" rel="stylesheet" href="/stylesheets/main.css" />
  </head>
  <body>
	Hello World!!
	<br/>
    <?php
	$thisFile = fopen("helloworld.php","r");
	$thisFileContents = fread($thisFile,filesize("helloworld.php"));
	fclose($thisFile);
	
	$newfile = fopen("newHelloWorld.php","w");
	fwrite($newfile,$thisFileContents);
	fclose($newfile);
	
	echo "Success";
	
    ?>
  </body>
</html>