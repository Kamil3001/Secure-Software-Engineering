<!DOCTYPE HTML>
<html>
<head>
    <link href="https://fonts.googleapis.com/css?family=Montserrat&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="../../resources/theme/css/form.css" type="text/css">
    <title>Registration</title>
</head>

<body>
<div class="registration">
    <p class="sign" align="center">Registration</p>
    <form  method="post">
        <label>
            <input class="userInput" type="text" align="center" name="username" placeholder="Username*">
        </label>
        <label>
            <input class="userInput" type="password" align="center" name="password" placeholder="Password*">
        </label>
        <label>
            <input class="userInput" type="text" align="center" name="name" placeholder="First Name*">
        </label>
        <label>
            <input class="userInput" type="text" align="center" name="surname" placeholder="Surname*">
        </label>
        <label>
            <input class="userInput" type="text" align="center" name="student_id" placeholder="Student ID*">
        </label>
        <label>
            <input class="userInput" type="text" align="center" name="address" placeholder="Address*">
        </label>
        <label>
            <input class="userInput" type="text" align="center" name="phone_number" placeholder="Phone Number*">
        </label>
        <label>
            <input class="userInput" type="email" align="center" name="email" placeholder="Email*">
        </label>
        <input class="submit" type="submit" align="center" value="Register"/>
        <p class="error" align="center">${error}</p>
    </form>
</div>

</body>

</html>