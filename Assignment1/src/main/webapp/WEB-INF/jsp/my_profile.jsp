<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML>
<html>

<head>
    <title>MCINO Moodle - My Profile</title>
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
                <h1><a href="home">MCINO<span class="logo_colour">moodle</span></a></h1>
                <h2>Bringing students and staff together.</h2>
            </div>
        </div>
        <div id="menubar">
            <ul id="menu">
                <li ><a href="home">Home</a></li>
                <% if(session != null && session.getAttribute("username") != null ){%>
                    <li ><a href="statistics">Statistics</a></li>
                    <li ><a href="view_modules">Modules</a></li>
                    <li class="selected"><a href="my_profile">My Profile</a></li>
                        <% if(session.getAttribute("role") != null && session.getAttribute("role").equals("staff")){%>
                            <li ><a href="staff">Staff Portal</a></li>
                        <%}%>
                    <li><a href="logout">Logout</a></li>
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
            <c:choose>
                <c:when test="${sessionScope.role eq 'student'}">
                    <c:import url="nested/student_profile.jsp"/>
                </c:when>
                <c:when test="${sessionScope.role eq 'staff'}">
                    <c:import url="nested/staff_profile.jsp"/>
                </c:when>
            </c:choose>
        </div>
    </div>
    <div id="content_footer"></div>
    <div id="footer">
        MCINO Moodle @ <a href="https://www.mcinofamily.com">MCINO</a>
    </div>


</div>
</body>
<script>
    var coll = document.getElementsByClassName("collapsible");
    var i;

    for (i = 0; i < coll.length; i++) {
        coll[i].addEventListener("click", function() {
            this.classList.toggle("active");
            var content = this.nextElementSibling;
            if (content.style.display === "block") {
                content.style.display = "none";
            } else {
                content.style.display = "block";
            }
        });
    }
</script>
</html>
