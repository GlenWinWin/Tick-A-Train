<?php

$con=mysqli_connect("localhost","root","","train");

$name = $_POST["name"];
$username = $_POST["username"];
$password = $_POST["password"];
$confirm = $_POST["confirm"];

$statement = mysqli_prepare($con, "INSERT INTO user(name,password,username) VALUES (?, ?, ?) ");
mysqli_stmt_blind_param($statement, "sss", $name, $password , $username);
mysqli_stmt_execute($statement);
mysqli_stmt_close($statement);

mysqli_close($con);
?>