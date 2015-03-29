<?PHP //returns first byte as "0" if generic fail, "1" for a duplicate username, otherwise "2" for success.
$mysql_servername = "localhost";
$dbname = "scoutgea_APCS";
$mysql_username = "scoutgea_1";
$mysql_password = "1";

// Create connection
$conn = new mysqli($mysql_servername, $mysql_username, $mysql_password, $dbname);

// Check connection
if ($conn->connect_error) {
    die("0Connection failed: " . $conn->connect_error);
}


//todo: revert to post commands
$dispName = mysqli_real_escape_string($conn,$_GET['disp']);//get data for new user, making sure to sanitize it for use with the MySQL
$email = mysqli_real_escape_string($conn,$_GET['email']);
$pass_hash = md5($_GET['pass']);


$SQL = "SELECT * FROM Users WHERE email='$email'";//generate SQL query
$result = $conn->query($sql);

if ($result->num_rows == 0) {//check that the email doesn't yet exist
$SQL = "INSERT INTO MyGuests (email, display, password) VALUES ('$email', '$dispName', '$pass_hash')";
echo $SQL;
if (!mysqli_query($con,$SQL)) {
  die('Error: ' . mysqli_error($con));
}
echo "2 success.";//confirm the success
}
else
{
echo "1";// echo 1 to indicate that the email exists
}

$conn->close();
?>