<?PHP
include 'github-api.php';

$api = new Milo\Github\Api;

# Get all available emoticon URLs example
$response = $api->get('/emojis');
$emojis = $api->decode($response);

print_r($emojis);

# Render a Markdown document example
$data = [
    'text' => 'Check out this commit e41d17e4a4a9e5368e018fcac44ff42155bc8738',
    'mode' => 'gfm',
    'context' => 'milo/github-api',
];
$response = $api->post('/markdown', $data);
$html = $api->decode($response);

echo $html;

?>