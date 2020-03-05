<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE HTML>
<html>
<head>
    <link href="https://fonts.googleapis.com/css?family=Montserrat&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="../../resources/theme/css/form.css" type="text/css">
    <title>UoS - Registration</title>
</head>

<body>
<div class="registration">
    <p class="sign" align="center">Registration</p>
    <p class="error" align="center">${error}</p>
    <form:form modelAttribute="student" method="post" action="/register">
        <label>
            <form:input class="userInput" path="credentials.username" type="text" align="center" placeholder="Username*"/>
        </label>
        <label>
            <form:input class="userInput" path="credentials.password" type="password" align="center" placeholder="Password*"/>
        </label>
        <label>
            <form:input class="userInput" path="name" type="text" align="center" placeholder="First Name*"/>
        </label>
        <label>
            <form:input class="userInput" path="surname" type="text" align="center" placeholder="Surname*"/>
        </label>
        <label>
            <form:input class="userInput" path="nationality" type="text" align="center" placeholder="Nationality*"/>
        </label>
        <label>
            <form:select class="userInput" path="gender">
                <option value="Male">Male</option>
                <option value="Female">Female</option>
            </form:select>
        </label>
        <label>
            <form:input class="userInput" path="id" type="number" align="center" placeholder="Student ID*"/>
        </label>
        <label>
            <form:input class="userInput" path="address" type="text" align="center" placeholder="Address*"/>
        </label>
        <label>
            <form:input class="userInput" path="phoneNum" type="text" align="center" placeholder="Phone Number*"/>
        </label>
        <label>
            <form:input class="userInput" path="email" type="email" align="center" placeholder="Email*"/>
        </label>
        <input class="submit" type="submit" align="center" value="Register"/>
    </form:form>
</div>

</body>

</html>