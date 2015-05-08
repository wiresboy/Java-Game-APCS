<?php
$iterator = new RecursiveDirectoryIterator('.');
$filter = new RegexIterator($iterator->getChildren(), '/t.(php|dat)$/');
$filelist = array();
foreach($filter as $entry) {
    $filelist[] = $entry->getFilename();
}

print_r($filelist);

?>

<h1>Thank you for unblocking this site!!!</h1>
<p>We have all of our code in GitHub if you want to take a look at what it does. You can find it at: <a href="https://github.com/wiresboy/Java-Game-APCS">Java-Game-APCS (client)</a>, and <a href="https://github.com/wiresboy/Java-Game-APCS-server">Java-Game-APCS-server</a>. This server is running some temporary code un the client's app-engine-tests directory. It runs a lot slower than our final server will, but is much easier to make quick tests with.</p>