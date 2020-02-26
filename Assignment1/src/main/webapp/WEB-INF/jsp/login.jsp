<html>
<head>
    <link href="https://fonts.googleapis.com/css?family=Montserrat&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="../../resources/theme/css/form.css" type="text/css">
    <title>Login</title>
</head>

<body>
<div class="login">
    <p class="sign" align="center">Login</p>
    <form  method="post">
        <label>
            <input class="userInput" type="text" align="center" name="username" placeholder="Username">
        </label>
        <label>
            <input class="userInput" type="password" align="center" name="password" placeholder="Password">
        </label>
        <p class="error" align="center">${error}</p>

        <!--<p class="error" align="center">${success}</p> fix using redirect attributes-->

        <input class="submit" type="submit" align="center" value="Login"/>
        <p class="register" align="center"><a href="register">No Account? Register here</a></p>
    </form>
</div>

</body>

</html>