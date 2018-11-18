<?php

$conn = new mysqli('eu-cdbr-west-02.cleardb.net', 'b864d9547483d4', '900c8909', 'heroku_6e9e2e43ea89231'); // Create connection
if ($conn->connect_error) {     // Check connection
    die("Connection failed: " . $conn->connect_error);
} 

$date = mysqli_real_escape_string($conn, $_POST['date']);
$IP = mysqli_real_escape_string($conn, $_POST['IP']);
$Platform = mysqli_real_escape_string($conn, $_POST['Platform']);

if (strlen($times) > 200000) {  $times = "";    }

$sql = "INSERT INTO visitors (dateANDtime, IPaddress, platform)
VALUES ('$date', '$IP', '$Platform')";

if ($conn->query($sql) === TRUE) {
    echo "Page saved!";
} else {
    echo "Error: " . $sql . "<br>" . $conn->error;
}
$conn->close();
?>
