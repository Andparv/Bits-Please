<?php

if (isset($_POST['submit'])){

    include_once 'dbh.inc.php';

    $first = mysqli_real_escape_string($conn,$_POST['first']);
    $last = mysqli_real_escape_string($conn,$_POST['first']);
    $email = mysqli_real_escape_string($conn,$_POST['first']);
    $pwd = mysqli_real_escape_string($conn,$_POST['first']);

    //Error handlers
    //Check for empty fields
    if (empty($first)||empty($last)||empty($email)||empty($pwd)){
        header("Location: ../register.php?register=empty");
        exit();
    }
    else{
        if (!preg_match("/^[a-zA-Z]*$/", $first) || !preg_match("/^[a-zA-Z]*$/", $last)){
            header("Location: ..register.php?register=invalid");
            exit();
        } else{
            if (!filter_var($email, FILTER_VALIDATE_EMAIL)){
                header("Location: ..register.php?register=email");
                exit();
            } else{
                $sql = "SELECT * FROM users WHERE email='$email'";
                $result = mysqli_query($conn, $sql);
                $resultCheck = mysql_num_rows($result);
                if ($resultCheck>0){
                    header("Location: ..register.php?register=usertaken");
                    exit();
                }
                else{
                    //Hashing te password
                    $hashedPwd = password_hash($pwd, PASSWORD_DEFAULT);
                    //Insert the used into the database
                    $sql2 = "INSERT INTO users (firstname, lastname, email, password) VALUES 
                    ('$first','$last','$email', '$hashedPwd');";
                    $result2 = mysqli_query($conn, $sql2);
                    header("Location: /Frontend/Homepage.jsp");
                    exit();

                }
            }
        }

    }

}
else{
    header("Location: ../register.php");
        exit();
}