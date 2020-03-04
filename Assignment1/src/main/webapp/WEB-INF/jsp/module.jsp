<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML>
<html>

<head>
    <title>
        MCINO Moodle - ${module.name}
    </title>
    <meta http-equiv="content-type" content="text/html; charset=windows-1252" />
    <link href="https://fonts.googleapis.com/css?family=Montserrat&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="../../resources/theme/css/pages.css" type="text/css">
</head>

<body>
<div id="main">
    <div id="header">
        <div id="logo">
            <div id="logo_text">
                <!-- class="logo_colour", allows you to change the colour of the text -->
                <h1><a href="../home">MCINO<span class="logo_colour">moodle</span></a></h1>
                <h2>Bringing students and staff together.</h2>
            </div>
        </div>
        <div id="menubar">
            <ul id="menu">
                <li ><a href="../home">Home</a></li>
                <% if(session != null && session.getAttribute("username") != null ){%>
                <li ><a href="../statistics">Statistics</a></li>
                <li><a href="../view_modules">Modules</a></li>
                <li ><a href="../my_profile">My Profile</a></li>
                <% if(session.getAttribute("role") != null && session.getAttribute("role").equals("staff")){%>
                <li ><a href="../staff">Staff Portal</a></li>
                <%}%>
                <li><a href="../logout">Logout</a></li>
                <% } %>
            </ul>
        </div>
    </div>
    <div id="content_header"></div>
    <div id="site_content">
        <div class="sidebar">
            <!-- insert your sidebar items here -->
            <h3>Latest News</h3>
            <h4>New Website Launched</h4>
            <h5>January 1st, 2010</h5>
            <p>2010 sees the redesign of our website. Take a look around and let us know what you think.<br /><a href="#">Read more</a></p>
            <p></p>
            <h4>New Website Launched</h4>
            <h5>January 1st, 2010</h5>
            <p>2010 sees the redesign of our website. Take a look around and let us know what you think.<br /><a href="#">Read more</a></p>
            <h3>Useful Links</h3>
            <ul>
                <li><a href="#">link 1</a></li>
                <li><a href="#">link 2</a></li>
                <li><a href="#">link 3</a></li>
                <li><a href="#">link 4</a></li>
            </ul>
            <h3>Search</h3>
            <form method="post" action="#" id="search_form">
                <p>
                    <input class="search" type="text" name="search_field" value="Enter keywords....." />
                    <input name="search" type="image" style="border: 0; margin: 0 0 -9px 5px;" src="../../resources/theme/images/search.png" alt="Search" title="Search" />
                </p>
            </form>
        </div>
        <div id="content">
            <!-- insert the page content here -->
            <h1>${module.name}</h1>
            <table class="student-info">
                <tr>
                    <th>Module ID:</th>
                    <td>${module.id}</td>
                </tr>
                <tr>
                    <th>Coordinator Name</th>
                    <td>TODO</td>
                </tr>
                <tr>
                    <th>Is Ongoing:</th>
                    <td>${!module.terminated}</td>
                    <%-- todo change this to show Active/Terminated instead --%>
                </tr>
                <tr>
                    <th>Capacity:</th>
                    <td>${module.capacity}</td>
                </tr>
                <tr>
                    <th>Number of Enrolled Students:</th>
                    <td>TODO</td>
                </tr>
                <tr>
                    <th>Topics:</th>
                    <td>${module.topics}</td>
                </tr>
            </table>
            <%-- todo implement enrollment button if student logged in and capacity not reached --%>
            <%-- todo add grade distribution display for previous editions of the module --%>
            <button>Enroll</button>
            <h2>Grade Distributions</h2>
        </div>
    </div>
    <div id="content_footer"></div>
    <div id="footer">
        MCINO Moodle @ <a href="https://www.mcinofamily.com">MCINO</a>
    </div>
</div>
</body>
</html>
