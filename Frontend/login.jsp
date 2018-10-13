<!DOCTYPE html>
<%@taglib uri = "http://www.springframework.org/tags/form" prefix = "form"%>
<html>
<head>
    <title>Registration</title>
    <meta name="robots" content="noindex, nofollow">
    <!-- Include CSS File Here -->
    <link rel="stylesheet" href="css/style.css"/>
    <!-- Include JS File Here -->
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
    <script type="text/javascript" src="js/registration.js"></script>
</head>
<body>
<div class="container">
    <div class="main">
        <form class="form" method="post" action="#">
            <h2>Registration</h2>
            <label>Name :</label>
            <input type="text" name="dname" id="name">
            <label>Email :</label>
            <input type="text" name="demail" id="email">
            <label>Password :</label>
            <input type="password" name="password" id="password">
            <label>Confirm Password :</label>
            <input type="password" name="cpassword" id="cpassword">
            <input type="button" name="register" id="register" value="Register">
        </form>
    </div>
</div>
</body>
</html>
